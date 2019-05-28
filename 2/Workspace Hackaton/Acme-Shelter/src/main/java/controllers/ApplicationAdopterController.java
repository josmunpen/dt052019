package controllers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import security.UserAccount;
import services.AdopterService;
import services.ApplicationService;
import services.PetService;
import domain.Adopter;
import domain.Application;
import domain.Pet;

@Controller
@RequestMapping("/application/adopter")
public class ApplicationAdopterController {
	
	@Autowired
	private ApplicationService applicationService;
	@Autowired
	private AdopterService adopterService;
	@Autowired
	private PetService petService;
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView res;
		Collection<Application> applications = new ArrayList<>();
		UserAccount ua = LoginService.getPrincipal();
		Adopter a = adopterService.findByUserAccount(ua);
		
		applications = applicationService.findAppsByAdopter(a);
		
		res = new ModelAndView("application/adopter/list");
		
		final String idioma = LocaleContextHolder.getLocale().getLanguage();
		res.addObject("idioma", idioma);
		
		res.addObject("applications", applications);
		res.addObject("requestURI", "application/adopter/list.do");
		
		return res;
		
	}

	@RequestMapping(value="/show", method=RequestMethod.GET)
	public ModelAndView show(@RequestParam int applicationId) {
		ModelAndView res;
		UserAccount ua = LoginService.getPrincipal();
		Adopter a = adopterService.findByUserAccount(ua);
		Application ap = applicationService.findOne(applicationId);
		
		try {
			Assert.isTrue(ap.getAdopter().getId() == a.getId());
		} catch (final Throwable oops) {
			res = new ModelAndView("redirect:/application/adopter/list.do");
			return res;
		}
		
		res = new ModelAndView("application/adopter/show");
		final String idioma = LocaleContextHolder.getLocale().getLanguage();
		res.addObject("idioma", idioma);
		res.addObject(ap);

		return res;
	}
	
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public ModelAndView create(@RequestParam int petId) {
		ModelAndView res;
		Pet p = petService.findOne(petId);
		Adopter a = adopterService.findByUserAccount(LoginService.getPrincipal());
		
		Application application = applicationService.create();
		try {
			Assert.isTrue(applicationService.checkAdopterPets(petId));
		} catch (Throwable oops) {
			Collection<Pet> pets = petService.findAll();
			res = new ModelAndView("pet/list");
			res.addObject("pets", pets);
			res.addObject("requestURI", "pet/list.do");
//			res = new ModelAndView("redirect:/pet/list.do");
			Boolean showError = true;
			res.addObject("showError", showError);
			
			return res;
		}
		
		application.setAdopter(a);
		application.setPet(p);
		
		
		res = createEditModelAndView(application);
		res.addObject(application);
		res.addObject("p", p);
				
		return res;
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST, params="save")
	public ModelAndView save(Application application, BindingResult binding, @RequestParam int petId) {
		ModelAndView res;
		Adopter a = adopterService.findByUserAccount(LoginService.getPrincipal());
		Pet p = petService.findOne(petId);
		application.setAdopter(a);
		application.setPet(p);
		application = applicationService.reconstruct(application, binding);
		if (binding.hasErrors()) {
			res = this.createEditModelAndView(application);
		} else {
			try {
				Application apli = this.applicationService.save(application);
				Assert.isTrue(applicationService.findAppsByAdopter(a).contains(apli));
				res = new ModelAndView("redirect:list.do");
			} catch (Throwable oops) {
				res = createEditModelAndView(application, "application.commit.error");
			}
		}
		return res;
	}
	
	protected ModelAndView createEditModelAndView(final Application application) {
		ModelAndView result;
		result = this.createEditModelAndView(application, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Application application, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("application/adopter/create");
		result.addObject("application", application);
		result.addObject("comment", application.getComment());
		result.addObject("photos", application.getPhotos());

		result.addObject("message", messageCode);

		return result;
	}

}
