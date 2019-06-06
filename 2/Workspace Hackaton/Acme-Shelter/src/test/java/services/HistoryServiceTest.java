
package services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.History;
import domain.Pet;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class HistoryServiceTest extends AbstractTest {

	@Autowired
	HistoryService	historyService;

	@Autowired
	PetService		petService;

	@Autowired
	ActorService	actorService;


	/**
	 * // * TESTING REQUIREMENT #11.3 (Manage Histories : create)
	 * // * COVERED INSTRUCTIONS IN THIS TEST: 100%
	 * //* COVERED INSTRUCTIONS IN HistoryService: 30.3%
	 * //* COVERED INSTRUCTIONS IN PetService: 3.1%
	 * //COVERED DATA IN THIS TEST: 12%
	 */
	@Test
	public void createHistoryGood() throws ParseException {
		/**
		 * TESTING REQUIREMENT #11.3
		 * POSITIVE TEST
		 * COVERED INSTRUCTIONS: 100%
		 * COVERED DATA: 10%
		 * */
		final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		super.authenticate("petowner1");

		final int id = super.getEntityId("pet1");

		final Pet pet = this.petService.findOne(id);

		final History h = this.historyService.create();
		h.setDescription("History 1");
		final Date d1 = sdf.parse("12/12/2015");
		h.setStartMoment(d1);
		final Date d2 = sdf.parse("12/12/2018");
		h.setEndMoment(d2);
		final int id2 = super.getEntityId("petowner1");
		h.setActor(this.actorService.findOne(id2));

		this.historyService.save(h, pet);
		super.unauthenticate();
	}

	/**
	 * // * TESTING REQUIREMENT #11.3
	 * // * NEGATIVE TEST:YOU CANNOT CREATE A HISTORY WITHOUT A DATE
	 * (Expected IllegalArgumentException)
	 * // * COVERED INSTRUCTIONS: 100%
	 * // * COVERED DATA: 50%
	 */
	@Test(expected = IllegalArgumentException.class)
	public void createHistoryBad() throws ParseException {
		final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		super.authenticate("petowner1");

		final int id = super.getEntityId("pet1");
		final Pet pet = this.petService.findOne(id);

		final History h = this.historyService.create();
		h.setDescription("History 1");
		final Date d3 = sdf.parse("12/12/2018");
		h.setStartMoment(d3);
		final Date d4 = sdf.parse("12/12/2015");
		h.setEndMoment(d4);
		final int id2 = super.getEntityId("petowner1");
		h.setActor(this.actorService.findOne(id2));

		this.historyService.save(h, pet);
		super.unauthenticate();
	}

	/**
	 * // * TESTING REQUIREMENT #11.3
	 * // * NEGATIVE TEST:YOU CANNOT CREATE A HISTORY BEING AN ADOPTER
	 * (Expected IllegalArgumentException)
	 * // * COVERED INSTRUCTIONS: 100%
	 * // * COVERED DATA: 50%
	 */
	@Test(expected = IllegalArgumentException.class)
	public void createHistoryBad2() throws ParseException {
		final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		super.authenticate("adopter");

		final int id = super.getEntityId("pet1");
		final Pet pet = this.petService.findOne(id);

		final History h = this.historyService.create();
		h.setDescription("History 1");
		final Date d3 = sdf.parse("12/12/2018");
		h.setStartMoment(d3);
		final Date d4 = sdf.parse("12/12/2015");
		h.setEndMoment(d4);
		final int id2 = super.getEntityId("petowner1");
		h.setActor(this.actorService.findOne(id2));

		this.historyService.save(h, pet);
		super.unauthenticate();
	}

}
