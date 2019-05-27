package services;
import java.text.ParseException;


import javax.transaction.Transactional;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import domain.Application;



import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class ApplicationStatusServiceTest extends AbstractTest{
	
	@Autowired
	ApplicationService applicationService;
	
	@Autowired
	PetService petService;
	
	/**
	 * // * TESTING REQUIREMENT #11.2 (Manage Application : edit Status)
	 * // * POSITIVETEST
	 * // * COVERED INSTRUCTIONS: 33.1%
	 * // * COVERED DATA: 50%
	 * // 
	 * @throws ParseException *
	 */
	@Test
	public void esditStatusGood(){
		
		Application a = this.applicationService.findOne(269);
		
		super.authenticate("petowner1");
		a.setStatus("REJECTED");
		a.setRejectCause("No me convence");
		
		this.applicationService.saveStatus(a);
		super.unauthenticate();
	}
	
	/**
	 * // * TESTING REQUIREMENT #11.2
	 * // * NEGATIVE TEST:YOU CANNOT EDIT STATUS TO REJECTED WITHOUT A REJECT CAUSE
	 * (Expected IllegalArgumentException)
	 * // * COVERED INSTRUCTIONS: 23.7%
	 * // * COVERED DATA: 50%
	 * // 
	 * @throws ParseException *
	 */
	@Test(expected = IllegalArgumentException.class)
	public void ediStatusBad() {

		Application a = this.applicationService.findOne(269);
		
		super.authenticate("petowner1");
		a.setStatus("REJECTED");
		a.setRejectCause("");
		
		this.applicationService.saveStatus(a);
		super.unauthenticate();
	}

}
