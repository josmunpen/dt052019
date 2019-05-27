
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


	@Test
	public void testDashboard() {
		super.authenticate("admin");
		final Double avgPets = this.service.avgPets();
		final List<String> top3Types = this.service.top3TypesEng();
		final Double ratioPetsMedical = this.service.ratioPetsWithMedicalCheckUp();

		Assert.isTrue(avgPets != 0.0 && avgPets != null && top3Types.size() <= 3 && ratioPetsMedical != 0.0 && ratioPetsMedical != null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testBadDashboard() {
		super.authenticate("admin");
		final Double avgApplications = this.service.avgApplications();
		final List<String> top3 = this.service.top3Veterinarians();
		final Double ratioPetsMedical = this.service.ratioPetsWithMedicalCheckUp();

		Assert.isTrue(avgApplications == 0.0 || avgApplications == null || top3.size() > 3 || ratioPetsMedical == 0.0 || ratioPetsMedical == null);
	}
}
