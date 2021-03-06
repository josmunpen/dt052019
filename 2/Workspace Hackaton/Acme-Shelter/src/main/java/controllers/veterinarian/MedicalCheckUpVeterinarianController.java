
package controllers.veterinarian;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import repositories.MedicalCheckUpRepository;
import repositories.TreatmentRepository;
import services.MedicalCheckUpService;
import services.PetService;
import services.VeterinarianService;
import controllers.AbstractController;
import domain.MedicalCheckUp;
import domain.Veterinarian;

@Controller
@RequestMapping("/checkup/veterinarian")
public class MedicalCheckUpVeterinarianController extends AbstractController {

	@Autowired
	private MedicalCheckUpService		medicalCheckUpService;

	@Autowired
	private VeterinarianService			veterinarianService;

	@Autowired
	private MedicalCheckUpRepository	medicalCheckUpRepository;

	@Autowired
	private PetService					petService;

	@Autowired
	private TreatmentRepository			treatmentRepository;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		final Veterinarian logVet = this.veterinarianService.findByPrincipal();

		final Collection<MedicalCheckUp> medicalCheckUps = this.medicalCheckUpRepository.findByVeterinarian(logVet.getId());

		result = new ModelAndView("checkup/veterinarian/list");
		result.addObject("medicalCheckUps", medicalCheckUps);
		result.addObject("requestURI", "/checkup/veterinarian/list.do");

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		MedicalCheckUp medicalCheckUp;

		medicalCheckUp = this.medicalCheckUpService.create();

		result = this.createEditModelAndView(medicalCheckUp);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int medicalCheckUpId) {
		ModelAndView result;
		MedicalCheckUp medicalCheckUp;

		medicalCheckUp = this.medicalCheckUpService.findOne(medicalCheckUpId);
		final Veterinarian v = this.veterinarianService.findByPrincipal();
		Assert.isTrue(medicalCheckUp.getVeterinarian().getId() == v.getId());
		result = this.createEditModelAndView(medicalCheckUp);

		return result;
	}
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid MedicalCheckUp medicalCheckUp, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(medicalCheckUp);
		else
			try {
				medicalCheckUp = this.medicalCheckUpService.reconstruct(medicalCheckUp, binding);
				this.medicalCheckUpService.save(medicalCheckUp);
				result = new ModelAndView("redirect:list.do");
				result.addObject("medicalCheckUpId", medicalCheckUp.getId());

			} catch (final Throwable oops) {
				result = this.createEditModelAndView(medicalCheckUp, "checkup.commit.error");
			}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final MedicalCheckUp medicalCheckUp, final BindingResult binding) {
		ModelAndView result;
		final MedicalCheckUp h = this.medicalCheckUpService.reconstruct(medicalCheckUp, binding);

		try {
			this.medicalCheckUpService.delete(h);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(medicalCheckUp);
		}

		return result;
	}

	protected ModelAndView createEditModelAndView(final MedicalCheckUp medicalCheckUp) {
		ModelAndView result;
		result = this.createEditModelAndView(medicalCheckUp, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final MedicalCheckUp medicalCheckUp, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("checkup/veterinarian/edit");
		result.addObject("pets", this.petService.findWithoutMedicalCheckUp());
		result.addObject("medicalCheckUp", medicalCheckUp);
		result.addObject("message", messageCode);

		return result;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int medicalCheckUpId) {
		ModelAndView result;

		final MedicalCheckUp checkup = this.medicalCheckUpService.findOne(medicalCheckUpId);
		final Veterinarian v = this.veterinarianService.findByPrincipal();
		Assert.isTrue(checkup.getVeterinarian().getId() == v.getId());

		result = new ModelAndView("checkup/veterinarian/show");
		result.addObject("checkup", checkup);
		result.addObject("treatments", this.treatmentRepository.findByMedicalCheckUp(medicalCheckUpId));
		return result;

	}
}
