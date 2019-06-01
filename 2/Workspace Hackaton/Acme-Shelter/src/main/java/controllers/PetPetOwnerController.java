
package controllers;

import java.util.Collection;
import java.util.List;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import repositories.PetTypeRepository;
import services.ActorService;
import services.MedicalCheckUpService;
import services.PetService;
import services.PetTypeService;
import services.TreatmentService;
import domain.Actor;
import domain.MedicalCheckUp;
import domain.Pet;
import domain.PetType;
import domain.Treatment;

@Controller
@RequestMapping("/pet/petOwner")
public class PetPetOwnerController extends AbstractController {

	@Autowired
	private PetService				petService;

	@Autowired
	private MedicalCheckUpService	medicalCheckUpService;
	@Autowired
	private PetTypeService			petTypeService;

	@Autowired
	private TreatmentService		treatmentService;

	@Autowired
	private ActorService			actorService;

	@Autowired
	private PetTypeRepository		petTypeRepository;


	// Constructors -----------------------------------------------------------

	public PetPetOwnerController() {
		super();
	}

	// List---------------------------------------------------------------		

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		final Actor a = this.actorService.findByPrincipal();

		final List<Pet> pets = this.petService.findAllByPetOwner(a.getId());

		result = new ModelAndView("pet/list");
		result.addObject("pets", pets);
		result.addObject("requestURI", "/pet/petOwner/list.do");

		return result;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int petId) {
		final ModelAndView result;
		Pet pet;

		pet = this.petService.findOne(petId);
		final List<MedicalCheckUp> lm = this.medicalCheckUpService.findByPet(pet);
		final List<Treatment> ltt = this.treatmentService.findByPet(pet);
		Assert.notNull(pet);

		result = new ModelAndView("pet/show");

		Boolean lmsize = false;
		Boolean ltsize = false;
		if (lm.size() > 0)
			lmsize = true;
		if (ltt.size() > 0)
			ltsize = true;
		result.addObject("lmsize", lmsize);
		result.addObject("ltsize", ltsize);
		result.addObject("lm", lm);
		result.addObject("ltt", ltt);
		result.addObject("pet", pet);

		return result;
	}
	//crear
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		final ModelAndView res;
		final Pet p = this.petService.create();

		res = this.createEditModelAndView(p);

		return res;
	}

	private ModelAndView createEditModelAndView(final Pet p) {
		ModelAndView res;
		res = this.createEditModelAndView(p, null);

		return res;
	}

	private ModelAndView createEditModelAndView(final Pet p, final String messageCode) {
		ModelAndView res;
		res = new ModelAndView("pet/edit");

		final List<PetType> lpt = this.petTypeService.findPetsFinalMode();
		final Collection<PetType> pre = this.petTypeRepository.findStandard();

		lpt.removeAll(pre);

		res.addObject("pet", p);
		res.addObject("lpt", lpt);
		res.addObject("message", messageCode);

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView save(@RequestParam final int petId) {
		ModelAndView res;
		final Pet p = this.petService.findOne(petId);

		res = new ModelAndView("pet/edit");

		res.addObject("pet", p);
		Assert.notNull(p);
		res = this.createEditModelAndView(p);

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(Pet p, final BindingResult binding) {
		ModelAndView res;

		try {
			final Collection<String> photos = p.getPhotos();
			for (final String trozo : photos) {
				Assert.isTrue(trozo.matches("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]"), "errorPhotos");
				Assert.isTrue(!trozo.contains(";"), "errorPhotos2");
			}
			p = this.petService.reconstruct(p, binding);
			this.petService.save(p);

			res = new ModelAndView("redirect:list.do");
		} catch (final ValidationException oops) {
			res = this.createEditModelAndView(p);
		} catch (final Throwable oops) {
			if (oops.getMessage() == "errorPhotos")
				res = this.createEditModelAndView(p, "errorPhotos");
			else if (oops.getMessage() == "errorPhotos2")
				res = this.createEditModelAndView(p, "errorPhotos2");
			else
				res = this.createEditModelAndView(p, "error.pet");
		}

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Pet p, final BindingResult binding) {
		ModelAndView res;

		try {
			final Pet p1 = this.petService.findOne(p.getId());
			this.petService.delete(p1);
			res = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			res = this.createEditModelAndView(p, "error.pet");
		}

		return res;
	}

}
