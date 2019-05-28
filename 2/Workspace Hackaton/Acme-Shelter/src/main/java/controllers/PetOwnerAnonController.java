
package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.PetOwnerService;
import services.PetService;
import services.PetTypeService;
import domain.PetOwner;

@Controller
@RequestMapping("/petOwner")
public class PetOwnerAnonController {

	@Autowired
	private PetService		petService;

	@Autowired
	private PetTypeService	petTypeService;

	@Autowired
	private PetOwnerService	petOwnerService;

	@Autowired
	private ActorService	actorService;


	// Constructors -----------------------------------------------------------

	public PetOwnerAnonController() {
		super();
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int petOwnerId) {
		final ModelAndView result;
		PetOwner petowner;

		petowner = this.petOwnerService.findOne(petOwnerId);

		Assert.notNull(petowner);

		result = new ModelAndView("petOwner/show");
		result.addObject("petowner", petowner);

		return result;
	}
}
