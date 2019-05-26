
package controllers.adopter;

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
import services.AdopterService;
import controllers.AbstractController;
import domain.Adopter;

@Controller
@RequestMapping("/adopter/adopter")
public class AdopterAdopterController extends AbstractController {

	@Autowired
	AdopterService	adopterService;


	// Constructors -----------------------------------------------------------

	public AdopterAdopterController() {
		super();
	}

	////////////////////////////
	//////////EDIT//////////////
	////////////////////////////

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView editEdit() {
		ModelAndView res;
		Adopter adopter;

		adopter = this.adopterService.findByPrincipal();
		//adopter = this.adopterService.findOne(adopterId);
		res = this.createEditEditModelAndView(adopter);
		return res;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@ModelAttribute Adopter adopter, final BindingResult binding) {
		ModelAndView result;

		try {
			final String vacia = "";
			if (!adopter.getEmail().isEmpty() || adopter.getEmail() != vacia)
				Assert.isTrue(adopter.getEmail().matches("^[A-z0-9]+@[A-z0-9.]+$") || adopter.getEmail().matches("^[A-z0-9 ]+ <[A-z0-9]+@[A-z0-9.]+>$"), "Wrong email");

			adopter = this.adopterService.reconstruct(adopter, binding);
			TimeUnit.SECONDS.sleep(1);

			this.adopterService.save(adopter);
			result = new ModelAndView("redirect:/welcome/index.do");
		} catch (final ValidationException oops) {
			result = this.createEditEditModelAndView(adopter);
		} catch (final Throwable oops) {
			if (oops.getMessage() == "Wrong email")
				result = this.createEditEditModelAndView(adopter, "adopter.email.error");
			else
				result = this.createEditEditModelAndView(adopter, "adopter.comit.error");
		}

		return result;

	}
	protected ModelAndView createEditEditModelAndView(final Adopter adopter) {
		ModelAndView result;

		result = this.createEditEditModelAndView(adopter, null);

		return result;

	}

	protected ModelAndView createEditEditModelAndView(final Adopter adopter, final String message) {
		ModelAndView result;

		UserAccount userAccount;
		userAccount = adopter.getUserAccount();

		result = new ModelAndView("adopter/edit");
		result.addObject("adopter", adopter);
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
		Adopter adopter;

		adopter = this.adopterService.findByPrincipal();
		//adopter = this.adopterService.findOne(adopterId);
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
		//		Collection<Box> boxes;
		//		final Collection<SocialProfile> socialProfiles;
		//		final Collection<Endorsement> endorsements;
		//		final Collection<FixUpTask> fixUpTasks;
		UserAccount userAccount;

		userAccount = adopter.getUserAccount();

		result = new ModelAndView("adopter/show");
		result.addObject("adopter", adopter);
		result.addObject("message", message);
		result.addObject("userAccount", userAccount);
		return result;
	}

	////////////////////////////
	//////////DELETE//////////////
	////////////////////////////

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "leave")
	public ModelAndView saveLeave(final Adopter adopter, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditEditModelAndView(adopter);
		else
			try {

				this.adopterService.leave();
				result = new ModelAndView("redirect:../../j_spring_security_logout");
			} catch (final Throwable error) {
				if (error.getMessage() == "Wrong email")
					result = this.createEditEditModelAndView(adopter, "adopter.email.error");
				else
					result = this.createEditEditModelAndView(adopter, "adopter.comit.error");
				System.out.println(error.getMessage());
			}

		return result;

	}

}
