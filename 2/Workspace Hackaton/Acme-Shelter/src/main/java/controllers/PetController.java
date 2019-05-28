package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.Pet;

import services.PetService;

@Controller
@RequestMapping("/pet")
public class PetController {
	@Autowired
	private PetService petService;
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView res;
		Collection<Pet> pets = petService.findAll();
		
		res = new ModelAndView("pet/list");
		res.addObject("pets", pets);
		res.addObject("requestURI", "pet/list.do");
		final boolean showError = false;
		res.addObject("showError", showError);
		
		return res;
	}

}
