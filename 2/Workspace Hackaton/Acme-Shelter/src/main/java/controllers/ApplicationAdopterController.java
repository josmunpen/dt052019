
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
public class ApplicationAdopterController extends AbstractController {

	@Autowired
	private ApplicationService	applicationService;
	@Autowired
	private AdopterService		adopterService;
	@Autowired
	private PetService			petService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView res;
		Collection<Application> applications = new ArrayList<>();
		final UserAccount ua = LoginService.getPrincipal();
		final Adopter a = this.adopterService.findByUserAccount(ua);

		applications = this.applicationService.findAppsByAdopter(a);

		res = new ModelAndView("application/adopter/list");

		final String idioma = LocaleContextHolder.getLocale().getLanguage();
		res.addObject("idioma", idioma);

		res.addObject("applications", applications);
		res.addObject("requestURI", "application/adopter/list.do");

		return res;

	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int applicationId) {
		ModelAndView res;
		final UserAccount ua = LoginService.getPrincipal();
		final Adopter a = this.adopterService.findByUserAccount(ua);
		final Application ap = this.applicationService.findOne(applicationId);

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

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int petId) {
		ModelAndView res;
		final Pet p = this.petService.findOne(petId);
		final Adopter a = this.adopterService.findByUserAccount(LoginService.getPrincipal());

		final Application application = this.applicationService.create();
		try {
			Assert.isTrue(this.applicationService.checkAdopterPets(petId));
		} catch (final Throwable oops) {
			final Collection<Pet> pets = this.petService.findAll();
			res = new ModelAndView("pet/list");
			res.addObject("pets", pets);
			res.addObject("requestURI", "pet/list.do");
			//			res = new ModelAndView("redirect:/pet/list.do");
			final Boolean showError = true;
			res.addObject("showError", showError);

			return res;
		}

		application.setAdopter(a);
		application.setPet(p);

		res = this.createEditModelAndView(application);
		res.addObject(application);
		res.addObject("p", p);

		return res;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(Application application, final BindingResult binding, @RequestParam final int petId) {
		ModelAndView res;
		final Adopter a = this.adopterService.findByUserAccount(LoginService.getPrincipal());
		final Pet p = this.petService.findOne(petId);
		application.setAdopter(a);
		application.setPet(p);
		application = this.applicationService.reconstruct(application, binding);
		if (binding.hasErrors())
			res = this.createEditModelAndView(application);
		else
			try {
				final Application apli = this.applicationService.save(application);
				Assert.isTrue(this.applicationService.findAppsByAdopter(a).contains(apli));
				res = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(application, "application.commit.error");
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
