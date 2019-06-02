
package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.PetTypeService;
import services.TipService;
import services.VeterinarianService;
import domain.PetType;
import domain.Tip;
import domain.Veterinarian;

@Controller
@RequestMapping("/tip/veterinarian")
public class TipVeterinarianController extends AbstractController {

	@Autowired
	private TipService			ts;

	@Autowired
	private PetTypeService		pettypeservice;

	@Autowired
	private VeterinarianService	vs;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView result;
		final Collection<Tip> tips;

		final Veterinarian v = this.vs.findByPrincipal();

		tips = this.ts.findAllByVeterinarian(v.getId());

		result = new ModelAndView("tips/list");

		result.addObject("tips", tips);

		result.addObject("requestURI", "/tip/veterinarian/list.do");

		return result;

	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView result;
		final Tip pt = this.ts.create();
		result = this.createCreateModelAndView(pt);
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int tipId) {
		final Veterinarian v = this.vs.findByPrincipal();
		ModelAndView result;
		final Tip pt = this.ts.findOne(tipId);

		Assert.isTrue(pt.getVeterinarian().getId() == v.getId());

		result = this.createEditModelAndView(pt);
		return result;
	}

	protected ModelAndView createEditModelAndView(final Tip c) {
		ModelAndView result;
		result = this.createEditModelAndView(c, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Tip c, final String messageCode) {
		ModelAndView result;
		Collection<Tip> tipslist;
		Collection<PetType> types;
		tipslist = this.ts.findAll();
		if (tipslist.contains(c))
			tipslist.remove(c);

		types = this.pettypeservice.findAll();

		result = new ModelAndView("tips/edit");
		result.addObject("tip", c);
		result.addObject("types", types);
		result.addObject("list", tipslist);

		result.addObject("message", messageCode);

		return result;
	}

	protected ModelAndView createCreateModelAndView(final Tip c) {
		ModelAndView result;
		result = this.createEditModelAndView(c, null);

		return result;
	}

	protected ModelAndView createCreateModelAndView(final Tip c, final String messageCode) {
		ModelAndView result;
		Collection<Tip> tipslist;
		Collection<PetType> types;
		tipslist = this.ts.findAll();
		if (tipslist.contains(c))
			tipslist.remove(c);

		types = this.pettypeservice.findAll();

		result = new ModelAndView("tips/create");
		result.addObject("tip", c);
		result.addObject("types", types);
		result.addObject("list", tipslist);

		result.addObject("message", messageCode);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid @ModelAttribute("tip") final Tip tip, @Valid final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(tip);
		else
			try {
				this.ts.save(tip);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(tip, "tip.commit.error");
			}

		return result;
	}

}
