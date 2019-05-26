
package controllers;

import java.util.Collection;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.Authority;
import security.UserAccount;
import services.AdopterService;
import domain.Adopter;
import forms.AdopterForm;

@Controller
@RequestMapping("/adopter")
public class AdopterController extends AbstractController {

	@Autowired
	AdopterService	adopterService;


	// Constructors -----------------------------------------------------------

	public AdopterController() {
		super();
	}

	// Edition ------------------------------
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView res;

		Authority authority;
		Collection<Authority> authorities;
		UserAccount userAccount;
		userAccount = new UserAccount();
		authorities = userAccount.getAuthorities();
		authority = new Authority();
		authority.setAuthority(Authority.ADOPTER);
		authorities.add(authority);
		userAccount.setAuthorities(authorities);

		final AdopterForm adopterForm = new AdopterForm();
		adopterForm.setUserAccount(userAccount);
		res = this.createEditModelAndView(adopterForm);

		//adopter = this.adopterService.create();
		//res = this.createEditModelAndView(adopter);
		return res;

	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@ModelAttribute final AdopterForm adopterForm, final BindingResult binding) {
		ModelAndView result;

		try {
			Assert.isTrue(adopterForm.isConditionsAccepted(), "conditionsAccepted");
			final Adopter adopter = this.adopterService.reconstruct(adopterForm, binding);
			final String vacia = "";
			if (!adopter.getEmail().isEmpty() || adopter.getEmail() != vacia)
				Assert.isTrue(adopter.getEmail().matches("^[A-z0-9]+@[A-z0-9.]+$") || adopter.getEmail().matches("^[A-z0-9 ]+ <[A-z0-9]+@[A-z0-9.]+>$"), "Wrong email");

			this.adopterService.save(adopter);
			result = new ModelAndView("redirect:/welcome/index.do");
		} catch (final ValidationException oops) {
			result = this.createEditModelAndView(adopterForm);
		} catch (final Throwable oops) {
			if (oops.getMessage() == "Wrong email")
				result = this.createEditModelAndView(adopterForm, "adopter.email.error");
			else if (oops.getMessage() == "conditionsAccepted")
				result = this.createEditModelAndView(adopterForm, "adopter.conditionsError");
			else
				result = this.createEditModelAndView(adopterForm, "adopter.comit.error");
		}
		return result;

	}

	// Ancillary methods ------------------------------
	protected ModelAndView createEditModelAndView(final AdopterForm adopterForm) {
		ModelAndView result;

		result = this.createEditModelAndView(adopterForm, null);

		return result;

	}

	protected ModelAndView createEditModelAndView(final AdopterForm adopterForm, final String message) {
		ModelAndView result;

		result = new ModelAndView("adopter/register");
		result.addObject("adopterForm", adopterForm);
		result.addObject("message", message);

		return result;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView res;

		final Collection<Adopter> adopters = this.adopterService.findAll();

		res = new ModelAndView("adopter/list");
		res.addObject("requestURI", "adopter/list.do");
		res.addObject("adopters", adopters);
		return res;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int adopterId) {
		ModelAndView res;
		Adopter adopter;

		adopter = this.adopterService.findOne(adopterId);
		res = this.createShowModelAndView(adopter);
		return res;

	}

	protected ModelAndView createShowModelAndView(final Adopter adopter) {
		ModelAndView result;

		result = this.createShowModelAndView(adopter, null);

		return result;

	}

	protected ModelAndView createShowModelAndView(final Adopter adopter, final String message) {
		ModelAndView result;

		result = new ModelAndView("adopter/show");
		result.addObject("adopter", adopter);
		//		result.addObject("boxes", boxes);
		//		result.addObject("socialProfiles", socialProfiles);
		result.addObject("message", message);

		return result;
	}

}
