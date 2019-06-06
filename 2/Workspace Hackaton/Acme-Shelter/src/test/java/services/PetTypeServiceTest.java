
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.PetType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class PetTypeServiceTest extends AbstractTest {

	//SUT
	@Autowired
	private PetTypeService	pts;


	/**
	 * TESTING REQUIREMENT #13.6 (Manages PetTypes)
	 * COVERED INSTRUCTIONS IN THIS TEST: 100%
	 * COVERED INSTRUCTIONS IN PetTypeService: 33.0%
	 * COVERED DATA IN THIS TEST: 40%
	 * */

	@Test
	public void createPetType() {
		this.authenticate("admin");
		final PetType p1 = this.pts.create();
		//p1.setChilds(new ArrayList<PetType>());
		p1.setDescription("Description");
		p1.setFinalMode(false);
		p1.setName("Name");
		p1.setNombre("Nombre");
		p1.setScientificTerm("Namus");
		p1.setZone("zone");

		final PetType p2 = this.pts.create();
		//p2.setChilds(new ArrayList<PetType>());
		p2.setDescription("Description");
		p2.setFinalMode(false);
		p2.setName("");
		p2.setNombre(null);
		p2.setScientificTerm("Namus");
		p2.setZone("zone");

		this.unauthenticate();

		final Object testingData[][] = {

			/**
			 * TESTING REQUIREMENT #13.6
			 * POSITIVE TEST
			 * COVERED INSTRUCTIONS: 100%
			 * COVERED DATA: 21%
			 * */
			{
				"admin", p1, null
			},

			/**
			 * TESTING REQUIREMENT #13.6
			 * NEGATIVE TEST (YOU CAN NOT CREATE A PetType WITH NO NAME)
			 * (Expected IllegalArgumentException)
			 * COVERED INSTRUCTIONS: 100%
			 * COVERED DATA: 10%
			 * */

			{
				"admin", p2, IllegalArgumentException.class
			},

		};

		for (int i = 0; i < testingData.length; i++)
			this.template2((String) testingData[i][0], (PetType) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	protected void template2(final String username, final PetType p, final Class<?> expected) {

		Class<?> caught;

		caught = null;

		try {
			this.authenticate(username);
			this.pts.save(p);

			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
	}

}
