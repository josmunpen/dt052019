
package controllers;

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

import services.ActorService;
import services.TreatmentService;
import domain.Actor;
import domain.Treatment;

@Controller
@RequestMapping("/treatment/veterinarian")
public class TreatmentVeterinarianController extends AbstractController {

	@Autowired
	private TreatmentService	treatmentService;

	@Autowired
	private ActorService		actorService;


	// Constructors -----------------------------------------------------------

	public TreatmentVeterinarianController() {
		super();
	}

	// List---------------------------------------------------------------		

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		final Actor a = this.actorService.findByPrincipal();

		final List<Treatment> treatments = this.treatmentService.findAllByVeterinarian(a.getId());

		result = new ModelAndView("treatment/list");
		result.addObject("treatments", treatments);
		result.addObject("requestURI", "/treatment/veterinarian/list.do");

		return result;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int treatmentId) {
		final ModelAndView result;
		Treatment treatment;

		treatment = this.treatmentService.findOne(treatmentId);

		Assert.notNull(treatment);

		result = new ModelAndView("treatment/show");
		result.addObject("treatment", treatment);

		return result;
	}

	//crear
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int medicalCheckUpId) {
		final ModelAndView res;
		final Treatment t = this.treatmentService.create(medicalCheckUpId);

		res = this.createEditModelAndView(t);

		return res;
	}

	private ModelAndView createEditModelAndView(final Treatment t) {
		ModelAndView res;
		res = this.createEditModelAndView(t, null);

		return res;
	}

	private ModelAndView createEditModelAndView(final Treatment t, final String messageCode) {
		ModelAndView res;
		res = new ModelAndView("treatment/edit");

		res.addObject("treatment", t);
		res.addObject("message", messageCode);

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView save(@RequestParam final int treatmentId) {
		ModelAndView res;
		final Treatment t = this.treatmentService.findOne(treatmentId);

		res = new ModelAndView("treatment/edit");

		Assert.notNull(t);
		res = this.createEditModelAndView(t);

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(Treatment t, final BindingResult binding) {
		ModelAndView res;

		try {
			t = this.treatmentService.reconstruct(t, binding);
			this.treatmentService.save(t);

			res = new ModelAndView("redirect:list.do");
		} catch (final ValidationException oops) {
			res = this.createEditModelAndView(t);
		}
		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Treatment t, final BindingResult binding) {
		ModelAndView res;

		try {
			final Treatment t1 = this.treatmentService.findOne(t.getId());
			this.treatmentService.delete(t1);
			res = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			res = this.createEditModelAndView(t, "error.treatment");
		}

		return res;
	}

}
