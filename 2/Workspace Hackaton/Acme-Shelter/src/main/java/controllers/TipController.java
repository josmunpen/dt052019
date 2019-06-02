
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.PetTypeService;
import services.TipService;
import domain.Tip;

@Controller
@RequestMapping("/tip")
public class TipController extends AbstractController {

	@Autowired
	private TipService		ts;

	@Autowired
	private PetTypeService	pettypeservice;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView result;
		final Collection<Tip> tips;

		tips = this.ts.findAll();

		result = new ModelAndView("tips/listAll");

		result.addObject("tips", tips);

		result.addObject("requestURI", "/tip/list.do");

		return result;

	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int tipId) {

		ModelAndView result;

		final Tip tip = this.ts.findOne(tipId);

		result = new ModelAndView("tips/show");

		result.addObject("tip", tip);
		return result;

	}

}
