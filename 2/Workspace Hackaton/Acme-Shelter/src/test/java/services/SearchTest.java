
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


	@Test
	public void testSearch() {
		final List<Pet> search = this.petService.searchPet("a");
		Assert.isTrue(search.size() >= 4 && !search.isEmpty());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testBadSearch() {
		final List<Pet> search = this.petService.searchPet("estacadenanosacaningunresultado");
		Assert.isTrue(!search.isEmpty());
	}
}
