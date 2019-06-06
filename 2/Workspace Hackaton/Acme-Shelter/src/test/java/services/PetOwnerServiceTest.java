
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import security.Authority;
import security.UserAccount;
import utilities.AbstractTest;
import domain.PetOwner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class PetOwnerServiceTest extends AbstractTest {

	//SUT
	@Autowired
	private PetOwnerService	petOwnerService;


	/**
	 * TESTING REQUIREMENT #15.1 (Register as a petOwner)
	 * POSITIVE TEST
	 * COVERED INSTRUCTIONS IN THIS TEST: 100%
	 * COVERED INSTRUCTIONS IN PetOwnerService: 29.3%
	 * COVERED DATA IN THIS TEST: 12%
	 * */

	@Test
	public void createPetOwner() {
		final UserAccount ua = new UserAccount();
		ua.setPassword("petOwner5");
		ua.setUsername("petOwner5");
		final Collection<Authority> coll = new ArrayList<Authority>();
		final Authority a = new Authority();
		a.setAuthority(Authority.PETOWNER);
		coll.add(a);
		ua.setAuthorities(coll);
		final PetOwner nc = this.petOwnerService.create();
		nc.setAddress("Sample address");
		nc.setEmail("newPetOwner@gmail.com");
		nc.setName("Sample");
		nc.setPhoneNumber("+34 1231456789");
		nc.setPhoto("http://www.sample.com");
		nc.setSurname("Sample surname");
		nc.setUserAccount(ua);
		nc.setCvv(123);
		nc.setExpirationMonth(12);
		nc.setExpirationYear(2019);
		nc.setHolderName("sampleHolderName");
		nc.setMakeName("VISA");
		nc.setNumber("4929094533598606");
		nc.setDescription("Sample description");

		final UserAccount ua2 = new UserAccount();
		ua2.setPassword("petOwner6");
		ua2.setUsername("petOwner6");
		final Collection<Authority> coll2 = new ArrayList<Authority>();
		final Authority a2 = new Authority();
		a.setAuthority(Authority.ADOPTER);
		coll2.add(a2);
		ua2.setAuthorities(coll2);
		final PetOwner nc2 = this.petOwnerService.create();
		nc2.setAddress("Sample address");
		nc2.setEmail("sad");
		nc2.setName(null);
		nc2.setPhoneNumber("+34 1231456789");
		nc2.setPhoto("sample");
		nc2.setSurname("");
		nc2.setUserAccount(ua2);
		nc2.setCvv(123);
		nc2.setExpirationMonth(23);
		nc2.setExpirationYear(1);
		nc2.setHolderName("sampleHolderName");
		nc2.setMakeName("AA");
		nc2.setNumber("123");
		nc2.setDescription("Sample description");

		final Object testingData[][] = {

			/**
			 * TESTING REQUIREMENT #15.1
			 * POSITIVE TEST
			 * COVERED INSTRUCTIONS: 100%
			 * COVERED DATA: 10%
			 * */
			{
				nc, null
			},

			/**
			 * TESTING REQUIREMENT #15.1
			 * NEGATIVE TEST: YOU CANNOT REGISTER WITH SOME NULL VALUES
			 * (Expected ConstraintViolationException)
			 * COVERED INSTRUCTIONS: 100%
			 * COVERED DATA: 10%
			 * */
			{
				nc2, javax.validation.ValidationException.class
			},
		};

		for (int i = 0; i < testingData.length; i++)
			this.template2((PetOwner) testingData[i][0], (Class<?>) testingData[i][1]);
	}
	protected void template2(final PetOwner lr, final Class<?> expected) {

		Class<?> caught;

		caught = null;

		//this.startTransaction();

		try {
			this.petOwnerService.save(lr);
			this.petOwnerService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);

		//this.commitTransaction();
	}

	/**
	 * THIS TEST IS FOR TESTING THE REQUIREMENT #10.2 (EDIT PERSONAL DATA)
	 * COVERED INSTRUCTIONS IN THIS TEST: 100%
	 * COVERED INSTRUCTIONS IN PetOwnerService: 29.3%
	 * COVERED DATA IN THIS TEST: 70%
	 * */

	@Test
	public void editPetOwner() {

		final List<PetOwner> companies = (List<PetOwner>) this.petOwnerService.findAll();

		final PetOwner compa1 = companies.get(0);

		compa1.setAddress("Sample address");
		compa1.setEmail("newPetOwner@gmail.com");
		compa1.setName("Sample");
		compa1.setPhoneNumber("+34 123145689");
		compa1.setPhoto("http://www.sample.com");
		compa1.setSurname("Sample surname");

		final PetOwner com2 = companies.get(1);

		com2.setAddress(null);
		com2.setEmail(null);
		com2.setName(null);
		com2.setPhoneNumber("+34 1231456789");
		com2.setPhoto("http://www.sample.com");
		com2.setSurname("Sample surname");

		final Object testingData[][] = {

			/**
			 * TESTING REQUIREMENT #10.2
			 * POSITIVE TEST
			 * COVERED INSTRUCTIONS: 100%
			 * COVERED DATA: 25%
			 * */
			{
				"petOwner1", compa1, null
			}, {
				/**
				 * TESTING REQUIREMENT #10.2
				 * NEGATIVE TEST (YOU CAN NOT CREATE A PETOWNER WITH NO NAME)
				 * (Expected ConstraintViolationException)
				 * COVERED INSTRUCTIONS: 100%
				 * COVERED DATA: 10%
				 * */
				"petOwner2", com2, ConstraintViolationException.class
			},
		};

		for (int i = 0; i < testingData.length; i++)
			this.template((String) testingData[i][0], (PetOwner) testingData[i][1], (Class<?>) testingData[i][2]);
	}
	protected void template(final String username, final PetOwner comp, final Class<?> expected) {

		Class<?> caught;

		caught = null;

		this.startTransaction();

		try {
			this.authenticate(username);

			this.petOwnerService.save(comp);

			this.petOwnerService.flush();
			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
		this.rollbackTransaction();
	}
}
