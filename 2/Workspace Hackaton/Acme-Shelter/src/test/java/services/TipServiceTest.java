
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.Tip;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class TipServiceTest extends AbstractTest {

	@Autowired
	TipService	tipService;


	/**
	 * // * TESTING REQUIREMENT #31 (Create a tip)
	 * // * POSITIVE TEST
	 * // * COVERED INSTRUCTIONS IN THIS TEST: 100%;
	 * // * COVERED INSTRUCTIONS IN CommentService: 46.1%;
	 * 
	 * // * COVERED DATA: 50%
	 * //
	 **/

	@Test
	public void createTipGood() {
		super.authenticate("veterinarian1");
		final Tip t = this.tipService.create();
		t.setBody("Recuerda no lavar a tu mascota mas de una vez al dia");
		t.setTitle("Exceso de lavado");
		this.tipService.save(t);
		super.unauthenticate();
	}

	/**
	 * // * TESTING REQUIREMENT #31 (Create a tip)
	 * // * NEGATIVE TEST, YOU CAN NOT CREATE A TIP WITH NO TITLE;
	 * // * COVERED INSTRUCTIONS IN THIS TEST: 100%;
	 * // * COVERED INSTRUCTIONS IN CommentService: 46.1%;
	 * 
	 * // * COVERED DATA: 50%
	 * //
	 **/
	@Test(expected = IllegalArgumentException.class)
	public void createTipBad2() {
		super.authenticate("veterinarian1");
		final Tip t = this.tipService.create();
		t.setBody("Recuerda no lavar a tu mascota mas de una vez al dia");
		t.setTitle(null);
		this.tipService.save(t);
		super.unauthenticate();
	}
}
