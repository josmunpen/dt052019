
package controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.PetService;
import domain.Pet;

@Controller
@RequestMapping("/search")
public class SearchPetController extends AbstractController {

	@Autowired
	PetService	petService;


	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView searchFormAndResults(final Model model) {
		final ModelAndView res = new ModelAndView("search/search");
		return res;
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET, params = "search")
	public ModelAndView search(@RequestParam("keyword") final String keyword, final HttpServletRequest request, final HttpServletResponse response) {
		final ModelAndView res = new ModelAndView("search/search");
		final List<Pet> pets = this.petService.searchPet(keyword);
		final boolean flagSearch = true;
		res.addObject("keyword", keyword);
		res.addObject("pets", pets);
		res.addObject("flagSearch", flagSearch);
		return res;
	}
}
