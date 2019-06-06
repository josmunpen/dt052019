
package services;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Pet;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class SearchTest extends AbstractTest {

	@Autowired
	PetService	petService;


	/**
	 * TESTING REQUIREMENT #9.2 (Search)
	 * COVERED INSTRUCTIONS IN THIS TEST: 100%
	 * COVERED INSTRUCTIONS IN PetService: 33.0%
	 * COVERED DATA IN THIS TEST: 40%
	 * */

	@Test
	public void testSearch() {
		/**
		 * TESTING REQUIREMENT #9.2
		 * POSITIVE TEST
		 * COVERED INSTRUCTIONS: 100%
		 * COVERED DATA: 10%
		 * */
		final List<Pet> search = this.petService.searchPet("a");
		Assert.isTrue(search.size() >= 4 && !search.isEmpty());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testBadSearch() {
		/**
		 * TESTING REQUIREMENT #9.2
		 * NEGATIVE TEST (IF YOU SEARCH FOR SOMETHING THAT IS NOT IN THE DATABASE)
		 * (Expected IllegalArgumentException)
		 * COVERED INSTRUCTIONS: 100%
		 * COVERED DATA: 10%
		 * */
		final List<Pet> search = this.petService.searchPet("estacadenanosacaningunresultado");
		Assert.isTrue(!search.isEmpty());
	}
}
