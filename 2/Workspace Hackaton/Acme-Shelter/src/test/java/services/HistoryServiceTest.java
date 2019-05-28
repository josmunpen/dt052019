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
import domain.History;
import domain.Pet;


import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class HistoryServiceTest extends AbstractTest{
	
	@Autowired
	HistoryService historyService;
	
	@Autowired
	PetService petService;
	
	@Autowired
	ActorService actorService;
	
	/**
	 * // * TESTING REQUIREMENT #11.3 (Manage Histories : create)
	 * // * POSITIVETEST
	 * // * COVERED INSTRUCTIONS: 45.5%
	 * // * COVERED DATA: 50%
	 * // 
	 * @throws ParseException *
	 */
	@Test
	public void createHistoryGood() throws ParseException {
		final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		super.authenticate("petowner1");
		
		int id = super.getEntityId("pet1");
		
		Pet pet = this.petService.findOne(id);
		
		
		History h = this.historyService.create();
		h.setDescription("History 1");
		final Date d1 = sdf.parse("12/12/2015");
		h.setStartMoment(d1);
		final Date d2 = sdf.parse("12/12/2018");
		h.setEndMoment(d2);
		int id2 = super.getEntityId("petowner1");
		h.setActor(this.actorService.findOne(id2));
		
		
		this.historyService.save(h, pet);
		super.unauthenticate();
	}
	
	/**
	 * // * TESTING REQUIREMENT #11.3
	 * // * NEGATIVE TEST:YOU CANNOT CREATE A HISTORY WITHOUT A DATE
	 * (Expected IllegalArgumentException)
	 * // * COVERED INSTRUCTIONS: 26.1%
	 * // * COVERED DATA: 50%
	 * // 
	 * @throws ParseException *
	 */
	@Test(expected = IllegalArgumentException.class)
	public void createHistoryBad() throws ParseException {
		final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		super.authenticate("petowner1");
		
		
		int id = super.getEntityId("pet1");
		Pet pet = this.petService.findOne(id);
		
		
		History h = this.historyService.create();
		h.setDescription("History 1");
		final Date d3 = sdf.parse("12/12/2018");
		h.setStartMoment(d3);
		final Date d4 = sdf.parse("12/12/2015");
		h.setEndMoment(d4);
		int id2 = super.getEntityId("petowner1");
		h.setActor(this.actorService.findOne(id2));
		
		this.historyService.save(h, pet);
		super.unauthenticate();
	}


}
