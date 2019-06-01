
package controllers.administrator;

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

import repositories.PetTypeRepository;
import services.PetTypeService;
import controllers.AbstractController;
import domain.PetType;

@Controller
@RequestMapping("/type/administrator")
public class PetTypeAdministratorController extends AbstractController {

	@Autowired
	private PetTypeService		pts;

	@Autowired
	private PetTypeRepository	ptr;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView result;
		final Collection<PetType> types;

		types = this.pts.findAll();

		result = new ModelAndView("type/list");

		result.addObject("types", types);

		result.addObject("requestURI", "/type/administrator/list.do");

		return result;

	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView result;
		final PetType pt = this.pts.create();
		result = this.createEditModelAndView(pt);
		return result;
	}
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int typeId) {

		ModelAndView result;
		final PetType pt = this.pts.findOne(typeId);
		try {
			Assert.isTrue(pt.isFinalMode() == false);
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:list.do");
			return result;
		}
		result = this.createEditModelAndView(pt);
		return result;
	}

	protected ModelAndView createEditModelAndView(final PetType c) {
		ModelAndView result;
		result = this.createEditModelAndView(c, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final PetType c, final String messageCode) {
		ModelAndView result;
		Collection<PetType> typelist;

		typelist = this.pts.findPetsFinalMode();

		if (typelist.contains(c))
			typelist.remove(c);

		result = new ModelAndView("type/edit");
		result.addObject("type", c);
		result.addObject("list", typelist);

		result.addObject("message", messageCode);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid @ModelAttribute("type") final PetType type, @Valid final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(type);
		else
			try {
				this.pts.save(type);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(type, "type.commit.error");
			}

		return result;
	}

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int typeId) {
		ModelAndView result;
		final PetType pt = this.pts.findOne(typeId);
		result = new ModelAndView("type/display");
		result.addObject("type", pt);
		return result;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid final PetType type, final BindingResult binding) {
		ModelAndView result;

		final Collection<PetType> children = this.ptr.findChilds(type.getName());
		if (children != null)
			try {
				Assert.isTrue(children != null);
				Assert.isTrue(children.isEmpty());
			} catch (final Exception e) {
				result = this.createEditModelAndView(type, "type.commit.error.children");
				return result;
			}

		this.pts.delete(type);
		result = new ModelAndView("redirect:list.do");
		return result;

	}
}
