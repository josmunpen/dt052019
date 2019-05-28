
package services;

import java.util.Arrays;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import utilities.TickerGenerator;
import domain.Pet;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class PetServiceTest extends AbstractTest {

	//SUT
	@Autowired
	PetService		petService;

	@Autowired
	Validator		validator;

	@Autowired
	PetTypeService	petTypeService;

	@Autowired
	PetOwnerService	petOwnerService;


	/**
	 * TESTING REQUIREMENT #? and #? (Manage Pet:CREATE)
	 * POSITIVE TEST
	 * COVERED INSTRUCTIONS IN THIS TEST: 100%
	 * COVERED INSTRUCTIONS IN AuditService: 66.8%
	 * COVERED DATA IN THIS TEST: 12%
	 * */

	@Test
	public void createPet() {
		this.authenticate("petOwner1");
		final Pet p = this.petService.create();
		p.setAddress("C/Fábrica Nº171");
		p.setName("Gatito");
		p.setPetType(this.petTypeService.findOne(this.getEntityId("petType2")));
		p.setPetOwner(this.petOwnerService.findByPrincipal());
		p.setIdentifier(TickerGenerator.generateTicker());
		p.setSex("MALE");
		p.setStatus("LOW");

		final Pet p2 = this.petService.create();
		p2.setAddress("C/Fábrica Nº171");
		p2.setName("Gatito");
		p2.setPetType(this.petTypeService.findOne(this.getEntityId("petType1")));
		p2.setPetOwner(this.petOwnerService.findByPrincipal());
		p2.setIdentifier(null);
		p2.setSex("MALE");
		p2.setStatus("LOW");

		final Pet p3 = this.petService.create();
		p3.setAddress("C/Fábrica Nº171");
		p3.setName("Gatito");
		p3.setPetType(this.petTypeService.findOne(this.getEntityId("petType1")));
		p3.setPetOwner(this.petOwnerService.findByPrincipal());
		p3.setAge(1);
		p3.setCareRequirements("Cuidados");
		p3.setDietRequirements("Dieta");
		p3.setFamilyRequirements("Requisitos de familia");
		p3.setManagementCost(1);
		p3.setNature("Tranquila");
		p3.setPedigree("No");
		p3.setPetsRequirements("Requisitos de mascotas");
		p3.setPhotos(Arrays.asList("http://www.photo1.com"));
		p3.setSex("MALE");
		p3.setStatus("LOW");

		final Pet p4 = this.petService.create();
		p4.setAddress("C/Fábrica Nº171");
		p4.setPetType(this.petTypeService.findOne(this.getEntityId("petType1")));
		p4.setPetOwner(this.petOwnerService.findByPrincipal());
		p4.setName(null);
		p4.setSex("MALE");
		p4.setStatus("LOW");

		this.unauthenticate();

		final Object testingData[][] = {

			/**
			 * TESTING REQUIREMENT #3.1 and #3.2
			 * POSITIVE TEST
			 * COVERED INSTRUCTIONS: 100%
			 * COVERED DATA: 10%
			 * */
			{
				"petowner1", p, null
			},
			/**
			 * TESTING REQUIREMENT #3.1 and #3.2
			 * POSITIVE TEST
			 * COVERED INSTRUCTIONS: 100%
			 * COVERED DATA: 10%
			 * */
			{

				"petowner1", p3, null
			},

			/**
			 * TESTING REQUIREMENT #3.1 and #3.2
			 * NEGATIVE TEST (YOU CAN NOT CREATE A PET WITH NO IDENTIFIER)
			 * (Expected ConstraintViolationException)
			 * COVERED INSTRUCTIONS: 100%
			 * COVERED DATA: 10%
			 * */
			{

				"petowner1", p2, ConstraintViolationException.class
			},

			/**
			 * TESTING REQUIREMENT #3.1 and #3.2
			 * NEGATIVE TEST (YOU CAN NOT CREATE A PET WITH NO NAME)
			 * (Expected ConstraintViolationException)
			 * COVERED INSTRUCTIONS: 100%
			 * COVERED DATA: 10%
			 * */
			{

				"petowner1", p4, ConstraintViolationException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.template2((String) testingData[i][0], (Pet) testingData[i][1], (Class<?>) testingData[i][2]);
	}
	/**
	 * TESTING REQUIREMENT #3.2 (Manage Audit:EDIT)
	 * POSITIVE TEST
	 * COVERED INSTRUCTIONS IN THIS TEST: 100%
	 * COVERED INSTRUCTIONS IN AuditService: 66.8%
	 * COVERED DATA IN THIS TEST: 12%
	 * */

	@Test
	public void editPet() {
		this.authenticate("petowner1");

		final Pet p5 = this.petService.findOne(this.getEntityId("pet1"));
		p5.setCareRequirements("Cambio");

		final Pet p7 = this.petService.findOne(this.getEntityId("pet1"));
		p7.setDietRequirements("Cambio");

		this.unauthenticate();

		final Object testingData[][] = {

			/**
			 * TESTING REQUIREMENT #3.2
			 * POSITIVE TEST
			 * COVERED INSTRUCTIONS: 100%
			 * COVERED DATA: 10%
			 * */
			{
				"petowner1", p5, null
			},
			/**
			 * TESTING REQUIREMENT #3.2
			 * POSITIVE TEST
			 * COVERED INSTRUCTIONS: 100%
			 * COVERED DATA: 10%
			 * */
			{
				"petowner1", p7, null
			}

		};

		for (int i = 0; i < testingData.length; i++)
			this.template2((String) testingData[i][0], (Pet) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	@Test
	public void editPet2() {
		this.authenticate("petowner1");

		final Pet p6 = this.petService.findOne(this.getEntityId("pet2"));
		p6.setSex("Indefinido");

		final Pet p8 = this.petService.findOne(this.getEntityId("pet2"));
		p8.setStatus("Indefinido");

		this.unauthenticate();

		final Object testingData[][] = {
			/**
			 * TESTING REQUIREMENT #15
			 * NEGATIVE TEST: SEX CAN BE JUST MALE OR FEMALE
			 * (Expected ConstraintViolationException)
			 * COVERED INSTRUCTIONS: 100%
			 * COVERED DATA: 10%
			 * */
			{
				"petowner1", p6, ConstraintViolationException.class
			},

			/**
			 * TESTING REQUIREMENT #15
			 * NEGATIVE TEST: STATUS CAN BE JUST LOW OR MEDIUM OR HIGH
			 * (Expected ConstraintViolationException)
			 * COVERED INSTRUCTIONS: 100%
			 * COVERED DATA: 10%
			 * */
			{
				"petowner1", p8, ConstraintViolationException.class
			}

		};

		for (int i = 0; i < testingData.length; i++)
			this.template2((String) testingData[i][0], (Pet) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	/**
	 * TESTING REQUIREMENT #3.2 (Manage Pet: DELETE)
	 * POSITIVE TEST
	 * COVERED INSTRUCTIONS IN THIS TEST: 100%
	 * COVERED INSTRUCTIONS IN AuditService: 66.8%
	 * COVERED DATA IN THIS TEST: 12%
	 * */

	@Test
	public void deletePet() {
		this.authenticate("petowner1");
		final Pet p9 = this.petService.findOne(this.getEntityId("pet1"));

		final Pet p10 = this.petService.findOne(this.getEntityId("pet2"));

		this.unauthenticate();

		final Object testingData[][] = {

			/**
			 * TESTING REQUIREMENT #3.2
			 * POSITIVE TEST
			 * COVERED INSTRUCTIONS: 100%
			 * COVERED DATA: 10%
			 * */
			{
				"petowner1", p10, null
			},
			/**
			 * TESTING REQUIREMENT #15
			 * NEGATIVE TEST: YOU CANNOT DELETE A PET BEING A VETERINARIAN
			 * (Expected NullPointerException)
			 * COVERED INSTRUCTIONS: 100%
			 * COVERED DATA: 10%
			 * */
			{
				"veterinarian1", p9, NullPointerException.class
			}

		};

		for (int i = 0; i < testingData.length; i++)
			this.templateD((String) testingData[i][0], (Pet) testingData[i][1], (Class<?>) testingData[i][2]);
	}
	protected void template2(final String username, final Pet p, final Class<?> expected) {

		Class<?> caught;

		caught = null;

		try {
			this.authenticate(username);
			this.petService.save(p);
			this.petService.flush();

			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
	}
	protected void templateD(final String username, final Pet p, final Class<?> expected) {

		Class<?> caught;

		caught = null;

		try {
			this.authenticate(username);
			this.petService.delete(p);
			this.petService.flush();

			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
	}
}
