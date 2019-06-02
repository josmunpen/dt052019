
package controllers;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;
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
import services.AdopterService;
import services.CommentService;
import services.PetOwnerService;
import services.TipService;
import domain.Actor;
import domain.Adopter;
import domain.Comment;
import domain.PetOwner;
import domain.Tip;

@Controller
@RequestMapping("/comment/tip")
public class CommentController extends AbstractController {

	@Autowired
	private TipService		ts;

	@Autowired
	private CommentService	cs;

	@Autowired
	private AdopterService	as;

	@Autowired
	private PetOwnerService	pos;

	@Autowired
	private ActorService	actorService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int tipId) {

		final Actor a = this.actorService.findByPrincipal();
		ModelAndView result;
		Collection<Comment> comments = new ArrayList<Comment>();

		final Tip tip = this.ts.findOne(tipId);

		if (tip != null)
			comments = this.cs.getCommentsByTip(tipId);

		result = new ModelAndView("comments/list");

		result.addObject("comments", comments);
		result.addObject("actorId", a.getId());
		result.addObject("idTip", tipId);

		result.addObject("requestURI", "/comment/tip/list.do");

		return result;

	}
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int tipId) {
		ModelAndView result;
		final Comment pt = this.cs.create(tipId);
		result = this.createEditModelAndView(pt);
		return result;
	}

	protected ModelAndView createEditModelAndView(final Comment c) {
		ModelAndView result;
		result = this.createEditModelAndView(c, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Comment c, final String messageCode) {
		ModelAndView result;
		result = new ModelAndView("comments/edit");
		result.addObject("comment", c);
		result.addObject("message", messageCode);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Comment comment, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(comment);
		else
			try {
				//Añado al comentario el usuario de manera manual para evitar hackeos
				final Adopter a = this.as.findByPrincipalNoAssert();
				if (a != null)
					comment.setAdopter(a);
				else {
					final PetOwner p = this.pos.findByPrincipal();
					if (p != null)
						comment.setPetOwner(p);
					else
						throw new ValidationException();
				}
				final Comment saved = this.cs.save(comment);
				result = new ModelAndView("redirect:list.do?tipId=" + saved.getTip().getId());
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(comment, "comment.commit.error");
			}

		return result;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int commentId) {
		ModelAndView result;
		final Actor a = this.actorService.findByPrincipal();
		final Comment comment = this.cs.findOne(commentId);
		try {
			if (comment.getAdopter() == null)
				Assert.isTrue(comment.getPetOwner().getId() == a.getId());
			else
				Assert.isTrue(comment.getAdopter().getId() == a.getId());
			//Assert.isTrue(comment.getAdopter().getId() == a.getId() || comment.getPetOwner().getId() == a.getId());
			this.cs.delete(comment);
			result = new ModelAndView("redirect:list.do?tipId=" + comment.getTip().getId());
			return result;
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:list.do?tipId=" + comment.getTip().getId());
			return result;
		}

	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int commentId) {
		ModelAndView result;
		final Comment comment = this.cs.findOne(commentId);
		result = new ModelAndView("comments/show");
		result.addObject("c", comment);
		return result;
	}

}
