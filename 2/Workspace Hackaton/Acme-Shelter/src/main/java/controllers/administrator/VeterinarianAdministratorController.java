
package controllers.administrator;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import security.Authority;
import security.UserAccount;
import services.VeterinarianService;
import controllers.AbstractController;
import domain.Box;
import domain.SocialProfile;
import domain.Veterinarian;
import forms.VeterinarianForm;

@Controller
@RequestMapping("/veterinarian/administrator")
public class VeterinarianAdministratorController extends AbstractController {

	@Autowired
	private VeterinarianService	veterinarianService;


	// Constructors -----------------------------------------------------------
	public VeterinarianAdministratorController() {
		super();
	}

	////////////////////////////
	//////////EDIT//////////////
	////////////////////////////

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView res;
		final Veterinarian veterinarian;

		veterinarian = this.veterinarianService.findByPrincipal();
		//customer = this.customerService.findOne(customerId);
		res = this.createEditModelAndView(veterinarian);
		return res;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Veterinarian veterinarian, final BindingResult binding) {
		ModelAndView result;

		try {
			final String vacia = "";
			if (!veterinarian.getEmail().isEmpty() || veterinarian.getEmail() != vacia)
				Assert.isTrue(veterinarian.getEmail().matches("^[A-z0-9]+@$") || veterinarian.getEmail().matches("^[A-z0-9 ]+ <[A-z0-9]+@>$"), "Wrong email");

			veterinarian = this.veterinarianService.reconstruct(veterinarian, binding);
			TimeUnit.SECONDS.sleep(1);
			this.veterinarianService.save(veterinarian);
			result = new ModelAndView("redirect:/welcome/index.do");
		} catch (final ValidationException oops) {
			result = this.createEditModelAndView(veterinarian);
		} catch (final Throwable oops) {
			if (oops.getMessage() == "Wrong email")
				result = this.createEditModelAndView(veterinarian, "veterinarian.email.error");
			else
				result = this.createEditModelAndView(veterinarian, "veterinarian.comit.error");
		}

		return result;

	}
	protected ModelAndView createEditModelAndView(final Veterinarian veterinarian) {
		ModelAndView result;

		result = this.createEditModelAndView(veterinarian, null);

		return result;

	}

	protected ModelAndView createEditModelAndView(final Veterinarian veterinarian, final String message) {
		ModelAndView result;
		Collection<Box> boxes;
		final Collection<SocialProfile> socialProfiles;
		UserAccount userAccount;

		boxes = veterinarian.getBoxes();
		socialProfiles = veterinarian.getSocialProfiles();
		userAccount = veterinarian.getUserAccount();

		result = new ModelAndView("veterinarian/edit");
		result.addObject("veterinarian", veterinarian);
		result.addObject("boxes", boxes);
		result.addObject("socialProfiles", socialProfiles);
		result.addObject("message", message);
		result.addObject("userAccount", userAccount);
		return result;
	}

	////////////////////////////
	//////////CREATE////////////
	////////////////////////////
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView res;

		Authority authority;
		Collection<Authority> authorities;
		UserAccount userAccount;
		userAccount = new UserAccount();
		authorities = userAccount.getAuthorities();
		authority = new Authority();
		authority.setAuthority(Authority.VETERINARIAN);
		authorities.add(authority);
		userAccount.setAuthorities(authorities);

		final VeterinarianForm adminForm = new VeterinarianForm();
		adminForm.setUserAccount(userAccount);
		res = this.createCreateModelAndView(adminForm);

		//return res;

		//		veterinarian = this.veterinarianService.create();
		//		res = this.createCreateModelAndView(veterinarian);
		return res;

	}

	protected ModelAndView createCreateModelAndView(final VeterinarianForm veterinarianForm) {
		ModelAndView result;

		result = this.createCreateModelAndView(veterinarianForm, null);

		return result;

	}

	protected ModelAndView createCreateModelAndView(final VeterinarianForm veterinarianForm, final String message) {
		ModelAndView result;
		result = new ModelAndView("veterinarian/create");
		result.addObject("veterinarianForm", veterinarianForm);
		result.addObject("message", message);
		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView saveCreate(@Valid final VeterinarianForm veterinarianForm, final BindingResult binding) {
		ModelAndView result;

		try {
			Assert.isTrue(veterinarianForm.isConditionsAccepted(), "conditionsAccepted");
			final Veterinarian veterinarian = this.veterinarianService.reconstruct(veterinarianForm, binding);
			final String vacia = "";
			if (!veterinarian.getEmail().isEmpty() || veterinarian.getEmail() != vacia)
				Assert.isTrue(veterinarian.getEmail().matches("^[A-z0-9]+@$") || veterinarian.getEmail().matches("^[A-z0-9 ]+ <[A-z0-9]+@>$"), "Wrong email");

			this.veterinarianService.save(veterinarian);
			result = new ModelAndView("redirect:/welcome/index.do");
		} catch (final ValidationException oops) {
			result = this.createCreateModelAndView(veterinarianForm);
		} catch (final Throwable oops) {
			if (oops.getMessage() == "Wrong email")
				result = this.createCreateModelAndView(veterinarianForm, "veterinarian.email.error");
			else if (oops.getMessage() == "conditionsAccepted")
				result = this.createCreateModelAndView(veterinarianForm, "veterinarian.conditionsError");
			else
				result = this.createCreateModelAndView(veterinarianForm, "veterinarian.comit.error");
		}
		if (!veterinarianForm.isConditionsAccepted())
			result = this.createCreateModelAndView(veterinarianForm, "veterinarian.conditionsError");

		return result;

	}

	////////////////////////////
	//////////DELETE////////////
	////////////////////////////

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "leave")
	public ModelAndView saveLeave(final Veterinarian veterinarian, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(veterinarian);
		else
			try {

				this.veterinarianService.leave();
				result = new ModelAndView("redirect:../../j_spring_security_logout");
			} catch (final Throwable error) {
				if (error.getMessage() == "Wrong email")
					result = this.createEditModelAndView(veterinarian, "brotherhood.email.error");
				else
					result = this.createEditModelAndView(veterinarian, "brotherhood.comit.error");
				System.out.println(error.getMessage());
			}

		return result;

	}

	////////////////////////////
	///////////SHOW/////////////
	////////////////////////////

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show() {
		ModelAndView res;
		Veterinarian veterinarian;

		veterinarian = this.veterinarianService.findByPrincipal();
		//brotherhood = this.brotherhoodService.findOne(brotherhoodId);
		res = this.createShowModelAndView(veterinarian);
		return res;

	}

	protected ModelAndView createShowModelAndView(final Veterinarian veterinarian) {
		ModelAndView result;

		result = this.createShowModelAndView(veterinarian, null);

		return result;

	}

	protected ModelAndView createShowModelAndView(final Veterinarian veterinarian, final String message) {
		ModelAndView result;

		UserAccount userAccount;

		userAccount = veterinarian.getUserAccount();

		result = new ModelAndView("veterinarian/show");
		result.addObject("veterinarian", veterinarian);
		;
		result.addObject("message", message);
		result.addObject("userAccount", userAccount);
		return result;
	}
}
