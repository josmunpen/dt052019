package controllers;

import java.util.Collection;

import javax.validation.Valid;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.History;
import domain.Pet;

import services.HistoryService;
import services.PetService;

@Controller
@RequestMapping("/history/petowner")
public class HistoryController extends AbstractController{
	
	@Autowired
	private HistoryService	historyService;
	
	@Autowired
	private PetService petService;
	
	private Pet thisPet;
	
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int petId) {
		ModelAndView result;
		this.thisPet = this.petService.findOne(petId);
		
		if(this.thisPet.getPetOwner().getId()!=this.historyService.getThisPetOwner().getId()){
			return new ModelAndView("redirect:/welcome/index.do");
		}

		final Collection<History> histories = this.historyService.getHistoriesByPet(this.thisPet);

		result = new ModelAndView("history/petowner/list");
		result.addObject("histories", histories);
		result.addObject("requestURI", "/history/petowner/list.do");

		return result;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		History history;

		history = this.historyService.create();

		result = this.createEditModelAndView(history);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int historyId) {
		ModelAndView result;
		History history;

		if(this.thisPet.getPetOwner().getId()!=this.historyService.getThisPetOwner().getId()){
			result = new ModelAndView("redirect:/welcome/index.do");
		}
		
		history = this.historyService.findOne(historyId);

		result = this.createEditModelAndView(history);
		
		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final History history, final BindingResult binding) {
		ModelAndView result;
		History h = history;
		if(history.getStartMoment()==null){
			return this.createEditModelAndView(history,"history.commit.error");
		}
		
		
		try{
			h = this.historyService.reconstructHistory(history, binding);
		
		}catch (final ValidationException oops) {
			result = this.createEditModelAndView(history);
		}
			try {
				
				this.thisPet=this.historyService.save(h, this.thisPet);
				result = new ModelAndView("redirect:list.do");
				result.addObject("petId", this.thisPet.getId());

			} catch (final Throwable oops) {
				result = this.createEditModelAndView(history, "history.commit.error");
			}
		
		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(History history, final BindingResult binding) {
		ModelAndView result;
		History h = this.historyService.reconstructHistory(history,binding);

		try {
			this.historyService.deleteHistoryOfPet(h, this.thisPet);
			result = new ModelAndView("redirect:list.do");
			result.addObject("petId", this.thisPet.getId());
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(history);
		}

		return result;
	}


	
	protected ModelAndView createEditModelAndView(History history) {
		ModelAndView result;
		result = this.createEditModelAndView(history, null);

		return result;
	}
	
	
	protected ModelAndView createEditModelAndView(History history, String messageCode) {
		ModelAndView result;

		result = new ModelAndView("history/petowner/edit");
		result.addObject("history", history);
		result.addObject("petId", this.thisPet.getId());
		result.addObject("message", messageCode);

		return result;
	}


}
