
package controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import repositories.PetRepository;
import services.ActorService2;
import services.AdopterService;
import services.PetOwnerService;
import services.PetService;
import services.PetTypeService;
import domain.Pet;

@Controller
@RequestMapping("/pet")
public class PetAnonController extends AbstractController {

	@Autowired
	private PetService		petService;

	@Autowired
	private PetRepository	petRepository;

	@Autowired
	private PetTypeService	petTypeService;

	@Autowired
	private PetOwnerService	petOwnerService;

	@Autowired
	private ActorService2	actorService2;

	@Autowired
	private AdopterService	adopterService;


	// Constructors -----------------------------------------------------------

	public PetAnonController() {
		super();
	}

	// List---------------------------------------------------------------		

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		List<Pet> pets = new ArrayList<Pet>(this.petService.findAll());

		//Si el actor logeado es un adopter
		//if (this.actorService2.checkAdopter())
		//		if (this.actorService2.findByPrincipal2() != null)
		//			pets = new ArrayList<Pet>(this.petRepository.findWithoutApplicationAccepted());

		try {
			if (this.actorService2.findByPrincipal() != null)
				pets = new ArrayList<Pet>(this.petRepository.findWithoutApplicationAccepted());
		} catch (final Throwable oops) {
			pets = new ArrayList<Pet>(this.petService.findAll());
		}

		result = new ModelAndView("pet/list");
		result.addObject("pets", pets);
		result.addObject("Anon", true);
		result.addObject("requestURI", "/pet/list.do");

		return result;
	}
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int petId) {
		final ModelAndView result;
		Pet pet;

		pet = this.petService.findOne(petId);

		Assert.notNull(pet);

		result = new ModelAndView("pet/show");
		result.addObject("pet", pet);

		return result;
	}

}
