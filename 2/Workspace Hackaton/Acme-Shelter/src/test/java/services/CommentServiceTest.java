
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Adopter;
import domain.Comment;
import domain.Tip;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class CommentServiceTest extends AbstractTest {

	@Autowired
	TipService		tipService;

	@Autowired
	CommentService	commentService;

	@Autowired
	AdopterService	adopterService;


	/**
	 * // * TESTING REQUIREMENT #32 (Create a comment)
	 * // * COVERED INSTRUCTIONS IN THIS TEST: 100%;
	 * // * COVERED INSTRUCTIONS IN CommentService: 45.9%;
	 * 
	 * // * COVERED DATA: 50%
	 * //
	 **/

	@Test
	public void createCommentGood() {
		/**
		 * TESTING REQUIREMENT #33.3
		 * POSITIVE TEST
		 * COVERED INSTRUCTIONS: 100%
		 * COVERED DATA: 10%
		 * */
		super.authenticate("adopter1");
		final int tipId = super.getEntityId("tip1");
		final Tip t = this.tipService.findOne(tipId);

		final int adopterId = super.getEntityId("adopter1");
		final Adopter a = this.adopterService.findOne(adopterId);

		final Comment c = this.commentService.create();
		c.setCommentText("Muy bueno");
		c.setScore(3);
		c.setTip(t);
		c.setAdopter(a);
		this.commentService.save(c);
		super.unauthenticate();
	}

	/**
	 * // * TESTING REQUIREMENT #32 (Create a comment)
	 * // * NEGATIVE TEST, YOU CAN NOT SAVE A COMMENT WITH SCORE > 5;
	 * (Expected IllegalArgumentException)
	 * // * COVERED INSTRUCTIONS IN THIS TEST: 100%;
	 * // * COVERED INSTRUCTIONS IN CommentService: 45.6%;
	 * 
	 * // * COVERED DATA: 50%
	 * //
	 **/

	@Test(expected = IllegalArgumentException.class)
	public void createCommentBad() {
		super.authenticate("adopter1");
		final int tipId = super.getEntityId("tip1");
		final Tip t = this.tipService.findOne(tipId);

		final int adopterId = super.getEntityId("adopter1");
		final Adopter a = this.adopterService.findOne(adopterId);

		final Comment c = this.commentService.create();
		c.setCommentText("Muy bueno");
		c.setScore(123123);
		c.setTip(t);
		c.setAdopter(a);
		this.commentService.save(c);
		super.unauthenticate();
	}
	/**
	 * // * TESTING REQUIREMENT #32 (Create a comment)
	 * // * NEGATIVE TEST, YOU CAN NOT SAVE A COMMENT WITH NO MOMENT > 5;
	 * (Expected IllegalArgumentException)
	 * // * COVERED INSTRUCTIONS IN THIS TEST: 100%;
	 * // * COVERED INSTRUCTIONS IN CommentService: 45.9%;
	 * 
	 * // * COVERED DATA: 50%
	 * //
	 **/
	@Test(expected = IllegalArgumentException.class)
	public void createCommentBad2() {

		final int tipId = super.getEntityId("tip1");
		final Tip t = this.tipService.findOne(tipId);

		final int adopterId = super.getEntityId("adopter1");
		final Adopter a = this.adopterService.findOne(adopterId);

		final Comment c = this.commentService.create();
		c.setCommentText("");
		c.setScore(1);
		c.setTip(null);
		c.setAdopter(null);
		c.setMoment(null);
		Assert.isTrue(c.getMoment() != null);
		this.commentService.save(c);

	}
}
