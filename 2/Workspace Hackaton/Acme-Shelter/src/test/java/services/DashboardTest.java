
package services;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class DashboardTest extends AbstractTest {

	@Autowired
	DashboardService	service;


	/**
	 * TESTING REQUIREMENT #13.4 AND #36.2 (DISPLAY DASHBOARD)
	 * COVERED INSTRUCTIONS IN THIS TEST: 100%
	 * COVERED INSTRUCTIONS IN DashboardService: 26.8%
	 * COVERED DATA IN THIS TEST: 14%
	 * */

	@Test
	public void testDashboard() {
		/**
		 * TESTING REQUIREMENT #13.4 AND #36.2
		 * POSITIVE TEST
		 * COVERED INSTRUCTIONS: 100%
		 * COVERED DATA: 10%
		 * */
		super.authenticate("admin");
		final Double avgPets = this.service.avgPets();
		final List<String> top3Types = this.service.top3TypesEng();
		final Double ratioPetsMedical = this.service.ratioPetsWithMedicalCheckUp();

		Assert.isTrue(avgPets != 0.0 && avgPets != null && top3Types.size() <= 3 && ratioPetsMedical != 0.0 && ratioPetsMedical != null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testBadDashboard() {
		/**
		 * TESTING REQUIREMENT #13.4 AND #36.2
		 * NEGATIVE TEST (THE RESULT THAT THE TEST IS TRYING TO ASSERT IS NOT TRUE)
		 * (Expected IllegalArgumentException)
		 * COVERED INSTRUCTIONS: 100%
		 * COVERED DATA: 10%
		 * */
		super.authenticate("admin");
		final Double avgApplications = this.service.avgApplications();
		final List<String> top3 = this.service.top3Veterinarians();
		final Double ratioPetsMedical = this.service.ratioPetsWithMedicalCheckUp();

		Assert.isTrue(avgApplications == 0.0 || avgApplications == null || top3.size() > 3 || ratioPetsMedical == 0.0 || ratioPetsMedical == null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testBadDashboard2() {
		/**
		 * TESTING REQUIREMENT #13.4 AND #36.2
		 * NEGATIVE TEST (THE RESULT THAT THE TEST IS TRYING TO ASSERT IS NOT TRUE)
		 * (Expected IllegalArgumentException)
		 * COVERED INSTRUCTIONS: 100%
		 * COVERED DATA: 10%
		 * */
		super.authenticate("admin");
		final Double avgApplications = this.service.avgApplications();
		final List<String> top3 = this.service.top3Veterinarians();
		final Double ratioPetsMedical = this.service.ratioPetsWithMedicalCheckUp();

		Assert.isTrue(avgApplications == 0.1 || avgApplications == null || top3.size() > 3 || ratioPetsMedical == 0.0 || ratioPetsMedical == null);
	}
}
