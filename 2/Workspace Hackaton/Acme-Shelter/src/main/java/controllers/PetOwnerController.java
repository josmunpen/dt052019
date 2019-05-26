
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
import services.PetOwnerService;
import domain.PetOwner;
import forms.PetOwnerForm;

@Controller
@RequestMapping("/petowner")
public class PetOwnerController extends AbstractController {

	@Autowired
	PetOwnerService	petOwnerService;


	// Constructors -----------------------------------------------------------

	public PetOwnerController() {
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
		authority.setAuthority(Authority.PETOWNER);
		authorities.add(authority);
		userAccount.setAuthorities(authorities);

		final PetOwnerForm petOwnerForm = new PetOwnerForm();
		petOwnerForm.setUserAccount(userAccount);
		res = this.createEditModelAndView(petOwnerForm);

		//petOwner = this.petOwnerService.create();
		//res = this.createEditModelAndView(petOwner);
		return res;

	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@ModelAttribute final PetOwnerForm petOwnerForm, final BindingResult binding) {
		ModelAndView result;

		try {
			Assert.isTrue(petOwnerForm.isConditionsAccepted(), "conditionsAccepted");
			final PetOwner petOwner = this.petOwnerService.reconstruct(petOwnerForm, binding);
			final String vacia = "";
			if (!petOwner.getEmail().isEmpty() || petOwner.getEmail() != vacia)
				Assert.isTrue(petOwner.getEmail().matches("^[A-z0-9]+@[A-z0-9.]+$") || petOwner.getEmail().matches("^[A-z0-9 ]+ <[A-z0-9]+@[A-z0-9.]+>$"), "Wrong email");

			this.petOwnerService.save(petOwner);
			result = new ModelAndView("redirect:/welcome/index.do");
		} catch (final ValidationException oops) {
			result = this.createEditModelAndView(petOwnerForm);
		} catch (final Throwable oops) {
			if (oops.getMessage() == "Wrong email")
				result = this.createEditModelAndView(petOwnerForm, "petOwner.email.error");
			else if (oops.getMessage() == "conditionsAccepted")
				result = this.createEditModelAndView(petOwnerForm, "petOwner.conditionsError");
			else
				result = this.createEditModelAndView(petOwnerForm, "petOwner.comit.error");
		}
		return result;

	}

	// Ancillary methods ------------------------------
	protected ModelAndView createEditModelAndView(final PetOwnerForm petOwnerForm) {
		ModelAndView result;

		result = this.createEditModelAndView(petOwnerForm, null);

		return result;

	}

	protected ModelAndView createEditModelAndView(final PetOwnerForm petOwnerForm, final String message) {
		ModelAndView result;

		result = new ModelAndView("petowner/register");
		result.addObject("petOwnerForm", petOwnerForm);
		result.addObject("message", message);

		return result;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView res;

		final Collection<PetOwner> petOwners = this.petOwnerService.findAll();

		res = new ModelAndView("petowner/list");
		res.addObject("requestURI", "petowner/list.do");
		res.addObject("petOwners", petOwners);
		return res;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int petOwnerId) {
		ModelAndView res;
		PetOwner petOwner;

		petOwner = this.petOwnerService.findOne(petOwnerId);
		res = this.createShowModelAndView(petOwner);
		return res;

	}

	protected ModelAndView createShowModelAndView(final PetOwner petOwner) {
		ModelAndView result;

		result = this.createShowModelAndView(petOwner, null);

		return result;

	}

	protected ModelAndView createShowModelAndView(final PetOwner petOwner, final String message) {
		ModelAndView result;

		result = new ModelAndView("petowner/show");
		result.addObject("petOwner", petOwner);
		//		result.addObject("boxes", boxes);
		//		result.addObject("socialProfiles", socialProfiles);
		result.addObject("message", message);

		return result;
	}

}
