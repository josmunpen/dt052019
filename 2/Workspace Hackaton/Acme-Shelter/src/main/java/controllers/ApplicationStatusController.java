
package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ApplicationService;
import services.HistoryService;
import domain.Application;

@Controller
@RequestMapping("/application/petowner")
public class ApplicationStatusController extends AbstractController {

	@Autowired
	private ApplicationService	as;

	@Autowired
	private HistoryService		hs;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		final Collection<Application> applications = this.as.myApplicationList();

		result = new ModelAndView("application/petowner/list");
		result.addObject("applications", applications);
		result.addObject("requestURI", "/application/petowner/list.do");

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int applicationId) {
		ModelAndView result;
		Application application;
		application = this.as.findOne(applicationId);
		if (application.getPet().getPetOwner().getId() != this.hs.getThisPetOwner().getId())
			return new ModelAndView("redirect:/welcome/index.do");

		if (application.getStatus().equals("ACCEPTED") || application.getStatus().equals("REJECTED"))
			return this.list();

		application.setRejectCause("");
		result = this.createEditModelAndView(application);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Application application, final BindingResult binding) {
		ModelAndView result;
		final Application check = this.as.findOne(application.getId());
		if (check.getStatus().equals("ACCEPTED") || check.getStatus().equals("REJECTED"))
			return this.list();

		if (binding.hasErrors())
			result = this.createEditModelAndView(application);
		else
			try {
				final char comma2 = ',';
				if (application.getStatus() == "REJECTED" || application.getStatus().contains("REJECTED"))
					if (application.getRejectCause().length() == 1)
						Assert.isTrue(application.getRejectCause().charAt(0) != comma2, "mandatoryRejectReason");

				final Application a = this.as.reconstructStatus(application);

				this.as.saveStatus(a);
				result = new ModelAndView("redirect:list.do");

			} catch (final Throwable oops) {
				if (oops.getMessage() == "mandatoryRejectReason")
					result = this.createEditModelAndView(application, "application.error.reject");
				else
					result = this.createEditModelAndView(application, "application.commit.error");
			}

		return result;
	}
	protected ModelAndView createEditModelAndView(final Application application) {
		ModelAndView result;
		result = this.createEditModelAndView(application, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Application application, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("application/petowner/edit");
		result.addObject("application", application);
		result.addObject("message", messageCode);

		return result;
	}

}
