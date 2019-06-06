
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class ApplicationStatusServiceTest extends AbstractTest {

	@Autowired
	ApplicationService	applicationService;

	@Autowired
	PetService			petService;


	/**
	 * // * TESTING REQUIREMENT #11.2 (Manage Application : Edit Status)
	 * // * COVERED INSTRUCTIONS IN THIS TEST: 100%
	 * // * COVERED INSTRUCTIONS IN ApplicationService: 21.0%
	 * // * COVERED INSTRUCTIONS IN PetService: 0.8%
	 * // * COVERED DATA IN THIS TEST: 17%
	 * //
	 */
	@Test
	public void editStatusGood() {

		/**
		 * TESTING REQUIREMENT #11.2
		 * POSITIVE TEST
		 * COVERED INSTRUCTIONS: 100%
		 * COVERED DATA: 15%
		 * */

		super.authenticate("petowner1");

		final int id = super.getEntityId("application1");
		final Application a = this.applicationService.findOne(id);

		a.setStatus("REJECTED");
		a.setRejectCause("No me convence");

		this.applicationService.checkRejectedTest(a);
		this.applicationService.saveStatus(a);
		super.unauthenticate();
	}

	/**
	 * // * TESTING REQUIREMENT #11.2
	 * // * NEGATIVE TEST:YOU CANNOT EDIT STATUS TO REJECTED WITHOUT A REJECT CAUSE
	 * (Expected IllegalArgumentException)
	 * // * COVERED INSTRUCTIONS: 100%
	 * // * COVERED DATA: 50%
	 * //
	 */
	@Test(expected = IllegalArgumentException.class)
	public void editStatusBad() {

		super.authenticate("petowner1");

		final int id = super.getEntityId("application1");
		final Application a = this.applicationService.findOne(id);

		a.setStatus("REJECTED");
		a.setRejectCause("");

		this.applicationService.checkRejectedTest(a);
		this.applicationService.saveStatus(a);
		super.unauthenticate();
	}

	/**
	 * // * TESTING REQUIREMENT #11.2
	 * // * NEGATIVE TEST:YOU CANNOT EDIT STATUS TO ANOTHER STATUS THAT FAILS THE PATTERN
	 * // (Expected NullPointerException)
	 * // * COVERED INSTRUCTIONS: 100%
	 * // * COVERED DATA: 50%
	 * //
	 */
	@Test(expected = NullPointerException.class)
	public void editStatusBad2() {

		super.authenticate("petowner1");

		final int id = super.getEntityId("application1");
		final Application a = this.applicationService.findOne(id);

		a.setStatus("Nuevo estado");

		this.applicationService.saveStatus(a);
		super.unauthenticate();
	}
}
