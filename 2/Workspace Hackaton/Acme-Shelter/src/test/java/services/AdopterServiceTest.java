
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import security.Authority;
import security.UserAccount;
import utilities.AbstractTest;
import domain.Adopter;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class AdopterServiceTest extends AbstractTest {

	//SUT
	@Autowired
	private AdopterService	adopterService;


	/**
	 * TESTING REQUIREMENT #7.1 (Register as a adopter)
	 * POSITIVE TEST
	 * COVERED INSTRUCTIONS IN THIS TEST: 100%
	 * COVERED INSTRUCTIONS IN AdopterService: 31.1%
	 * COVERED INSTRUCTIONS IN ActorService: 35.6%
	 * COVERED DATA IN THIS TEST: 12%
	 * */

	@Test
	public void createAdopter() {
		final UserAccount ua = new UserAccount();
		ua.setPassword("adopter5");
		ua.setUsername("adopter5");
		final Collection<Authority> coll = new ArrayList<Authority>();
		final Authority a = new Authority();
		a.setAuthority(Authority.ADOPTER);
		coll.add(a);
		ua.setAuthorities(coll);
		final Adopter nc = this.adopterService.create();
		nc.setAddress("Sample address");
		nc.setEmail("newAdopter@gmail.com");
		nc.setName("Sample");
		nc.setPhoneNumber("+34 1231456789");
		nc.setPhoto("http://www.sample.com");
		nc.setSurname("Sample surname");
		nc.setUserAccount(ua);
		nc.setCvv(123);
		nc.setExpirationMonth(12);
		nc.setExpirationYear(2019);
		nc.setHolderName("sampleHolderName");
		nc.setMakeName("VISA");
		nc.setNumber("4929094533598606");
		nc.setDescription("Sample description");

		final UserAccount ua2 = new UserAccount();
		ua2.setPassword("adopter6");
		ua2.setUsername("adopter6");
		final Collection<Authority> coll2 = new ArrayList<Authority>();
		final Authority a2 = new Authority();
		a.setAuthority(Authority.PETOWNER);
		coll2.add(a2);
		ua2.setAuthorities(coll2);
		final Adopter nc2 = this.adopterService.create();
		nc2.setAddress("Sample address");
		nc2.setEmail("sad");
		nc2.setName(null);
		nc2.setPhoneNumber("+34 1231456789");
		nc2.setPhoto("sample");
		nc2.setSurname("");
		nc2.setUserAccount(ua2);
		nc2.setCvv(123);
		nc2.setExpirationMonth(23);
		nc2.setExpirationYear(1);
		nc2.setHolderName("sampleHolderName");
		nc2.setMakeName("AA");
		nc2.setNumber("123");
		nc2.setDescription("Sample description");

		final Object testingData[][] = {

			/**
			 * TESTING REQUIREMENT #7.1
			 * POSITIVE TEST
			 * COVERED INSTRUCTIONS: 100%
			 * COVERED DATA: 10%
			 * */
			{
				nc, null
			},

			/**
			 * TESTING REQUIREMENT #7.1
			 * NEGATIVE TEST: YOU CANNOT REGISTER WITH SOME NULL VALUES
			 * (Expected ConstraintViolationException)
			 * COVERED INSTRUCTIONS: 100%
			 * COVERED DATA: 10%
			 * */
			{
				nc2, javax.validation.ValidationException.class
			},
		};

		for (int i = 0; i < testingData.length; i++)
			this.template2((Adopter) testingData[i][0], (Class<?>) testingData[i][1]);
	}
	protected void template2(final Adopter lr, final Class<?> expected) {

		Class<?> caught;

		caught = null;

		//this.startTransaction();

		try {
			this.adopterService.save(lr);
			this.adopterService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);

		//this.commitTransaction();
	}

	/**
	 * THIS TEST IS FOR TESTING THE REQUIREMENT #8.2 (EDIT PERSONAL DATA)
	 * COVERED INSTRUCTIONS IN THIS TEST: 100%
	 * COVERED INSTRUCTIONS IN AdopterService: 31.1%
	 * COVERED INSTRUCTIONS IN ActorService: 35.6%
	 * COVERED DATA IN THIS TEST: 70%
	 * */

	@Test
	public void editAdopter() {

		final List<Adopter> companies = (List<Adopter>) this.adopterService.findAll();

		final Adopter compa1 = companies.get(0);

		compa1.setAddress("Sample address");
		compa1.setEmail("newAdopter@gmail.com");
		compa1.setName("Sample");
		compa1.setPhoneNumber("+34 123145689");
		compa1.setPhoto("http://www.sample.com");
		compa1.setSurname("Sample surname");

		final Adopter com2 = companies.get(1);

		com2.setAddress(null);
		com2.setEmail(null);
		com2.setName(null);
		com2.setPhoneNumber("+34 1231456789");
		com2.setPhoto("http://www.sample.com");
		com2.setSurname("Sample surname");

		final Object testingData[][] = {

			/**
			 * TESTING REQUIREMENT #1
			 * POSITIVE TEST
			 * COVERED INSTRUCTIONS: 100%
			 * COVERED DATA: 25%
			 * */
			{
				"adopter1", compa1, null
			}, {
				"adopter2", com2, ConstraintViolationException.class
			},
		};

		for (int i = 0; i < testingData.length; i++)
			this.template((String) testingData[i][0], (Adopter) testingData[i][1], (Class<?>) testingData[i][2]);
	}
	protected void template(final String username, final Adopter comp, final Class<?> expected) {

		Class<?> caught;

		caught = null;

		this.startTransaction();

		try {
			this.authenticate(username);

			this.adopterService.save(comp);

			this.adopterService.flush();
			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
		this.rollbackTransaction();
	}

}
