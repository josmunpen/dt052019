
package controllers.adopter;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.FinderService;
import services.PetTypeService;
import controllers.AbstractController;
import domain.Finder;
import domain.PetType;

@Controller
@RequestMapping("/finder/adopter")
public class FinderAdopterController extends AbstractController {

	@Autowired
	FinderService	finderService;

	@Autowired
	PetTypeService	petTypeService;


	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show() {
		final ModelAndView res;
		Finder finder;
		finder = this.finderService.getFinder();
		if (this.finderService.checkCache(finder))
			finder = this.finderService.clear(finder);
		res = new ModelAndView("finder/show");

		res.addObject("finder", finder);
		//		res.addObject("positions", finder.getPositions());
		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView res;
		Finder finder;

		finder = this.finderService.getFinder();
		Assert.notNull(finder);
		res = this.createEditModelAndView(finder);
		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(Finder finder, final BindingResult binding) {
		ModelAndView res;
		try {
			finder = this.finderService.reconstruct(finder, binding);
			finder = this.finderService.save(finder);
			res = new ModelAndView("redirect:show.do");
		} catch (final ValidationException oops) {
			res = this.createEditModelAndView(finder);
		} catch (final Throwable oops) {
			res = this.createEditModelAndView(finder, "finder.error");
		}
		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "clear")
	public ModelAndView clear(Finder finder, final BindingResult binding) {
		ModelAndView res;
		finder = this.finderService.findOne(finder.getId());
		try {
			this.finderService.clear(finder);
			res = new ModelAndView("redirect:show.do");
		} catch (final Throwable oops) {
			res = this.createEditModelAndView(finder, "finder.error");
		}
		return res;
	}

	//Ancillary methods
	protected ModelAndView createEditModelAndView(final Finder finder) {
		final ModelAndView res;

		res = this.createEditModelAndView(finder, null);
		return res;
	}
	protected ModelAndView createEditModelAndView(final Finder finder, final String message) {
		final ModelAndView res;
		res = new ModelAndView("finder/edit");
		final List<PetType> pt = this.petTypeService.findAll();
		final List<String> ptnombres = new ArrayList<>();
		final List<String> ptnames = new ArrayList<>();
		for (final PetType p : pt) {
			ptnombres.add(p.getNombre());
			ptnames.add(p.getName());
		}
		res.addObject("finder", finder);
		res.addObject("message", message);
		res.addObject("ptnombres", ptnombres);
		res.addObject("ptnames", ptnames);
		return res;
	}
}
