
package controllers.petOwner;

import java.util.concurrent.TimeUnit;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import security.UserAccount;
import services.PetOwnerService;
import controllers.AbstractController;
import domain.PetOwner;

@Controller
@RequestMapping("/petowner/petowner")
public class PetOwnerPetOwnerController extends AbstractController {

	@Autowired
	PetOwnerService	petOwnerService;


	// Constructors -----------------------------------------------------------

	public PetOwnerPetOwnerController() {
		super();
	}

	////////////////////////////
	//////////EDIT//////////////
	////////////////////////////

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView editEdit() {
		ModelAndView res;
		PetOwner petOwner;

		petOwner = this.petOwnerService.findByPrincipal();
		//petOwner = this.petOwnerService.findOne(petOwnerId);
		res = this.createEditEditModelAndView(petOwner);
		return res;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@ModelAttribute PetOwner petOwner, final BindingResult binding) {
		ModelAndView result;

		try {
			final String vacia = "";
			if (!petOwner.getEmail().isEmpty() || petOwner.getEmail() != vacia)
				Assert.isTrue(petOwner.getEmail().matches("^[A-z0-9]+@[A-z0-9.]+$") || petOwner.getEmail().matches("^[A-z0-9 ]+ <[A-z0-9]+@[A-z0-9.]+>$"), "Wrong email");

			petOwner = this.petOwnerService.reconstruct(petOwner, binding);
			TimeUnit.SECONDS.sleep(1);

			this.petOwnerService.save(petOwner);
			result = new ModelAndView("redirect:/welcome/index.do");
		} catch (final ValidationException oops) {
			result = this.createEditEditModelAndView(petOwner);
		} catch (final Throwable oops) {
			if (oops.getMessage() == "Wrong email")
				result = this.createEditEditModelAndView(petOwner, "petOwner.email.error");
			else
				result = this.createEditEditModelAndView(petOwner, "petOwner.comit.error");
		}

		return result;

	}
	protected ModelAndView createEditEditModelAndView(final PetOwner petOwner) {
		ModelAndView result;

		result = this.createEditEditModelAndView(petOwner, null);

		return result;

	}

	protected ModelAndView createEditEditModelAndView(final PetOwner petOwner, final String message) {
		ModelAndView result;

		UserAccount userAccount;
		userAccount = petOwner.getUserAccount();

		result = new ModelAndView("petowner/edit");
		result.addObject("petOwner", petOwner);
		result.addObject("message", message);
		result.addObject("userAccount", userAccount);
		return result;
	}

	////////////////////////////
	//////////SHOW//////////////
	////////////////////////////

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show() {
		ModelAndView res;
		PetOwner petOwner;

		petOwner = this.petOwnerService.findByPrincipal();
		//petOwner = this.petOwnerService.findOne(petOwnerId);
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
		//		Collection<Box> boxes;
		//		final Collection<SocialProfile> socialProfiles;
		//		final Collection<Endorsement> endorsements;
		//		final Collection<FixUpTask> fixUpTasks;
		UserAccount userAccount;

		userAccount = petOwner.getUserAccount();

		result = new ModelAndView("petowner/show");
		result.addObject("petOwner", petOwner);
		result.addObject("message", message);
		result.addObject("userAccount", userAccount);
		return result;
	}

	////////////////////////////
	//////////DELETE//////////////
	////////////////////////////

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "leave")
	public ModelAndView saveLeave(final PetOwner petOwner, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditEditModelAndView(petOwner);
		else
			try {

				this.petOwnerService.leave();
				result = new ModelAndView("redirect:../../j_spring_security_logout");
			} catch (final Throwable error) {
				if (error.getMessage() == "Wrong email")
					result = this.createEditEditModelAndView(petOwner, "petOwner.email.error");
				else
					result = this.createEditEditModelAndView(petOwner, "petOwner.comit.error");
				System.out.println(error.getMessage());
			}

		return result;

	}
}
