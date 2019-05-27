
package controllers.administrator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.DashboardService;
import controllers.AbstractController;

@Controller
@RequestMapping("/dashboard/administrator")
public class DashboardAdministratorController extends AbstractController {

	@Autowired
	DashboardService	dashboardService;


	DashboardAdministratorController() {
		super();
	}

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView dashboard() {
		final ModelAndView res;
		res = new ModelAndView("dashboard/dashboard");

		final Double avgPets = this.dashboardService.avgPets();
		final Integer minPets = this.dashboardService.minPets();
		final Integer maxPets = this.dashboardService.maxPets();
		final Double stddevPets = this.dashboardService.stddevPets();

		final Double avgApplications = this.dashboardService.avgApplications();
		final Integer minApplications = this.dashboardService.minApplications();
		final Integer maxApplications = this.dashboardService.maxApplications();
		final Double stddevApplications = this.dashboardService.stddevApplications();

		final Double avgAge = this.dashboardService.avgAge();
		final Integer minAge = this.dashboardService.minAge();
		final Integer maxAge = this.dashboardService.maxAge();
		final Double stddevAge = this.dashboardService.stddevAge();

		final Double avgHistories = this.dashboardService.avgHistories();
		final Integer minHistories = this.dashboardService.minHistories();
		final Integer maxHistories = this.dashboardService.maxHistories();
		final Double stddevHistories = this.dashboardService.stddevHistories();

		final Double avgMedicalCheckUp = this.dashboardService.avgMedicalCheckUp();
		final Integer minMedicalCheckUp = this.dashboardService.minMedicalCheckUp();
		final Integer maxMedicalCheckUp = this.dashboardService.maxMedicalCheckUp();
		final Double stddevMedicalCheckUp = this.dashboardService.stddevMedicalCheckUp();

		final List<String> top3TypesEng = this.dashboardService.top3TypesEng();
		final List<String> top3TypesEsp = this.dashboardService.top3TypesEsp();
		final List<String> top3Veterinarians = this.dashboardService.top3Veterinarians();
		final List<String> top3Adopters = this.dashboardService.top3Adopters();

		final Double ratioAcceptedApplications = this.dashboardService.ratioAcceptedApplications();
		final Double ratioRejectedApplications = this.dashboardService.ratioRejectedApplications();
		final Double ratioPendingApplications = this.dashboardService.ratioPendingApplications();
		final Double ratioPetsWithMedicalCheckUp = this.dashboardService.ratioPetsWithMedicalCheckUp();

		res.addObject("avgPets", avgPets);
		res.addObject("minPets", minPets);
		res.addObject("maxPets", maxPets);
		res.addObject("stddevPets", stddevPets);
		res.addObject("avgApplications", avgApplications);
		res.addObject("minApplications", minApplications);
		res.addObject("maxApplications", maxApplications);
		res.addObject("stddevApplications", stddevApplications);
		res.addObject("avgAge", avgAge);
		res.addObject("minAge", minAge);
		res.addObject("maxAge", maxAge);
		res.addObject("stddevAge", stddevAge);
		res.addObject("avgHistories", avgHistories);
		res.addObject("minHistories", minHistories);
		res.addObject("maxHistories", maxHistories);
		res.addObject("stddevHistories", stddevHistories);
		res.addObject("avgMedicalCheckUp", avgMedicalCheckUp);
		res.addObject("minMedicalCheckUp", minMedicalCheckUp);
		res.addObject("maxMedicalCheckUp", maxMedicalCheckUp);
		res.addObject("stddevMedicalCheckUp", stddevMedicalCheckUp);
		res.addObject("top3TypesEng", top3TypesEng);
		res.addObject("top3TypesEsp", top3TypesEsp);
		res.addObject("top3Veterinarians", top3Veterinarians);
		res.addObject("top3Adopters", top3Adopters);
		res.addObject("ratioPendingApplications", ratioPendingApplications);
		res.addObject("ratioAcceptedApplications", ratioAcceptedApplications);
		res.addObject("ratioRejectedApplications", ratioRejectedApplications);
		res.addObject("ratioPetsWithMedicalCheckUp", ratioPetsWithMedicalCheckUp);

		return res;
	}
}
