
package services;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import repositories.ApplicationRepository;
import utilities.AbstractTest;
import domain.Adopter;
import domain.Application;
import domain.Pet;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class ApplicationServiceTest extends AbstractTest {

	@Autowired
	ApplicationService		applicationService;
	@Autowired
	AdopterService			adopterService;
	@Autowired
	PetService				petService;
	@Autowired
	ApplicationRepository	applicationRepository;


	/**
	 * TESTING REQUIREMENT #12.1 (Manage Application:CREATE)
	 * COVERED INSTRUCTIONS IN THIS TEST: 100%
	 * COVERED INSTRUCTIONS IN ApplicationService: 31.8%
	 * COVERED INSTRUCTIONS IN AdopterService: 6.1%
	 * COVERED INSTRUCTIONS IN PetService: 3.1%
	 * COVERED DATA IN THIS TEST: 14%
	 * */

	@Test
	public void createApplicationGood() {
		/**
		 * TESTING REQUIREMENT #12.1
		 * POSITIVE TEST
		 * COVERED INSTRUCTIONS: 100%
		 * COVERED DATA: 12%
		 * */
		super.authenticate("adopter1");
		final Application ap1 = this.applicationService.create();
		final int aid = super.getEntityId("adopter1");
		final Adopter a = this.adopterService.findOne(aid);
		ap1.setAdopter(a);
		final int pid = super.getEntityId("pet2");
		final Pet p1 = this.petService.findOne(pid);
		ap1.setPet(p1);

		this.applicationService.save(ap1);
		super.unauthenticate();
	}

	@Test(expected = IllegalArgumentException.class)
	public void createApplicationBad() {
		/**
		 * TESTING REQUIREMENT #12.1
		 * NEGATIVE TEST (YOU CAN NOT CREATE AN APPLICATION WITH A PET YOU THAT HAVE ALREADY APPLICATE TO)
		 * (Expected IllegalArgumentException)
		 * COVERED INSTRUCTIONS: 100%
		 * COVERED DATA: 10%
		 * */
		super.authenticate("adopter1");
		final int aid = super.getEntityId("adopter1");
		final Adopter a = this.adopterService.findOne(aid);
		final Application ap2 = this.applicationService.create();
		ap2.setAdopter(a);
		final int pid = super.getEntityId("pet1");
		final Pet p2 = this.petService.findOne(pid);
		ap2.setPet(p2);

		this.applicationService.save(ap2);
		this.applicationRepository.flush();
		super.unauthenticate();
	}

	@Test(expected = ConstraintViolationException.class)
	public void createApplicationBad2() {
		/**
		 * TESTING REQUIREMENT #12.1
		 * NEGATIVE TEST (YOU CAN NOT CREATE AN APPLICATION WITH A COMMENT THAT CONTAINS A SCRIPT)
		 * (Expected ConstraintViolationException)
		 * COVERED INSTRUCTIONS: 100%
		 * COVERED DATA: 10%
		 * */
		super.authenticate("adopter1");
		final Application ap3 = this.applicationService.create();
		final int aid = super.getEntityId("adopter1");
		final Adopter a = this.adopterService.findOne(aid);
		ap3.setAdopter(a);
		final int pid = super.getEntityId("pet2");
		final Pet p1 = this.petService.findOne(pid);
		ap3.setPet(p1);
		ap3.setComment("<script></script>");

		this.applicationService.save(ap3);
		this.applicationRepository.flush();
		super.unauthenticate();
	}
}
