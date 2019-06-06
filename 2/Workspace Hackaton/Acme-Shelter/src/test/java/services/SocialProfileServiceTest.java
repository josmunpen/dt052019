
package services;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import repositories.SocialProfileRepository;
import utilities.AbstractTest;
import domain.SocialProfile;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class SocialProfileServiceTest extends AbstractTest {

	@Autowired
	SocialProfileRepository	socialProfileRepository;

	@Autowired
	SocialProfileService	socialProfileService;


	/**
	 * // * TESTING REQUIREMENT #10.5 (Manage Social Profiles : create)
	 * // * COVERED INSTRUCTIONS: 100%
	 * // * COVERED INSTRUCTIONS IN SocilaProfileService: 2.9%
	 * // * COVERED DATA: 100%
	 * // *
	 */
	@Test
	public void createSocialProfileGood() {
		/**
		 * TESTING REQUIREMENT #10.5
		 * POSITIVE TEST
		 * COVERED INSTRUCTIONS: 100%
		 * COVERED DATA: 10%
		 * */
		this.authenticate("adopter1");
		final SocialProfile s = this.socialProfileService.create();
		s.setNick("sp1");
		s.setLink("http://sp1.com");
		s.setSocialNetwork("sn1");

		this.socialProfileService.save(s);
		super.unauthenticate();
	}

	/**
	 * // * TESTING REQUIREMENT #10.5
	 * // * NEGATIVE TEST:YOU CANNOT CREATE A SOCIAL PROFILE WITHOUT A NICK
	 * (Expected ConstraintViolationException)
	 * // * COVERED INSTRUCTIONS: 100%
	 * // * COVERED DATA: 100%
	 * // *
	 */
	@Test(expected = ConstraintViolationException.class)
	public void createSocialProfileBad() {
		/**
		 * TESTING REQUIREMENT #25
		 * NEGATIVE TEST (YOU CAN NOT CREATE A SOCIAL PROFILE WITHOUT WRITE THE SOCIAL NETWORK WHERE IT IS)
		 * (Expected ConstraintViolationException)
		 * COVERED INSTRUCTIONS: 100%
		 * COVERED DATA: 10%
		 * */
		this.authenticate("adopter1");
		final SocialProfile s = this.socialProfileService.create();
		s.setNick("");
		s.setLink("http://sp1.com");
		s.setSocialNetwork("sn1");

		this.socialProfileService.save(s);
		this.socialProfileRepository.flush();
		super.unauthenticate();
	}

	@Test(expected = ConstraintViolationException.class)
	public void createSocialProfileBad2() {
		this.authenticate("adopter1");
		final SocialProfile s = this.socialProfileService.create();
		s.setNick("TC");
		s.setLink("http://sp1.com");
		s.setSocialNetwork("");

		this.socialProfileService.save(s);
		this.socialProfileRepository.flush();
		super.unauthenticate();
	}

}
