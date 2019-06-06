
package services;

import java.util.Calendar;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.Treatment;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class TreatmentServiceTest extends AbstractTest {

	//SUT
	@Autowired
	TreatmentService		treatmentService;

	@Autowired
	MedicalCheckUpService	medicalCheckUpService;


	/**
	 * TESTING REQUIREMENT #33.3 (Manage Treatment:CREATE)
	 * COVERED INSTRUCTIONS IN THIS TEST: 100%
	 * COVERED INSTRUCTIONS IN TreatmentService: 19.5%
	 * COVERED INSTRUCTIONS IN MedicalCheckUpService: 13.5%
	 * COVERED DATA IN THIS TEST: 12%
	 * */

	@Test
	public void createTreatment() {
		this.authenticate("veterinarian1");
		final Treatment t = this.treatmentService.create(this.getEntityId("checkUp1"));
		t.setMoment(Calendar.getInstance().getTime());
		t.setComment("Esto es un comment");
		t.setIllness("Tiene pulgas");
		t.setTreatmentC("Lavarlo con la pipeta");

		final Treatment t2 = this.treatmentService.create(this.getEntityId("checkUp1"));
		t2.setComment("Esto es un comment");
		t2.setIllness("");
		t2.setTreatmentC("Lavarlo con la pipeta");

		final Treatment t3 = this.treatmentService.create(this.getEntityId("checkUp1"));
		t3.setComment("Esto es un comment");
		t3.setIllness("Tiene muchas pulgas");
		t3.setTreatmentC("");

		this.unauthenticate();

		final Object testingData[][] = {

			/**
			 * TESTING REQUIREMENT #33.3
			 * POSITIVE TEST
			 * COVERED INSTRUCTIONS: 100%
			 * COVERED DATA: 10%
			 * */
			{
				"veterinarian1", t, null
			},
			/**
			 * TESTING REQUIREMENT #33.3
			 * NEGATIVE TEST (YOU CAN NOT CREATE A TREATMENT WITH NO ILLNESS)
			 * (Expected ConstraintViolationException)
			 * COVERED INSTRUCTIONS: 100%
			 * COVERED DATA: 10%
			 * */
			{

				"veterinarian1", t2, ConstraintViolationException.class
			}, {
				/**
				 * TESTING REQUIREMENT #33.3
				 * NEGATIVE TEST (YOU CAN NOT CREATE A TREATMENT WITH NO TREATMENT COMMENT)
				 * (Expected ConstraintViolationException)
				 * COVERED INSTRUCTIONS: 100%
				 * COVERED DATA: 10%
				 * */

				"veterinarian1", t3, ConstraintViolationException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.template2((String) testingData[i][0], (Treatment) testingData[i][1], (Class<?>) testingData[i][2]);
	}
	protected void template2(final String username, final Treatment t, final Class<?> expected) {

		Class<?> caught;

		caught = null;

		try {
			this.authenticate(username);
			this.treatmentService.save(t);
			this.treatmentService.flush();

			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
	}
}
