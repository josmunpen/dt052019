package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Application;


import services.ApplicationService;
import services.HistoryService;


@Controller
@RequestMapping("/application/petowner")
public class ApplicationStatusController extends AbstractController{
	
	@Autowired
	private ApplicationService as;
	
	@Autowired
	private HistoryService hs;
	
	
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
		if(application.getPet().getPetOwner().getId()!= this.hs.getThisPetOwner().getId()){
			return new ModelAndView("redirect:/welcome/index.do");
		}
		
		if(application.getStatus().equals("ACCEPTED") || application.getStatus().equals("REJECTED")){
			return this.list();
		}
		
		application.setRejectCause("");
		result = this.createEditModelAndView(application);
		
		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Application application, final BindingResult binding) {
		ModelAndView result;
		Application a = this.as.reconstructStatus(application);

		if (binding.hasErrors()){
			result = this.createEditModelAndView(a);
		}
		else
			try {
				
				this.as.saveStatus(a);
				result = new ModelAndView("redirect:list.do");

			} catch (final Throwable oops) {
				result = this.createEditModelAndView(application, "application.commit.error");
			}

		return result;
	}

	

	protected ModelAndView createEditModelAndView(Application application) {
		ModelAndView result;
		result = this.createEditModelAndView(application, null);

		return result;
	}
	
	protected ModelAndView createEditModelAndView(Application application, String messageCode) {
		ModelAndView result;

		result = new ModelAndView("application/petowner/edit");
		result.addObject("application", application);
		result.addObject("message", messageCode);

		return result;
	}
	

}
