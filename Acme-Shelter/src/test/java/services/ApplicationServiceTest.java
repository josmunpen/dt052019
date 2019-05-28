package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import repositories.ApplicationRepository;

import utilities.AbstractTest;
import domain.Adopter;
import domain.Application;
import domain.Pet;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class ApplicationServiceTest extends AbstractTest {
	
	@Autowired
	ApplicationService applicationService;
	@Autowired
	AdopterService adopterService;
	@Autowired
	PetService petService;
	@Autowired
	ApplicationRepository applicationRepository;
	
	@Test
	public void createApplicationGood() {
		super.authenticate("adopter1");
		Application ap1 = applicationService.create();
		int aid = super.getEntityId("adopter1");
		Adopter a = adopterService.findOne(aid);
		ap1.setAdopter(a);
		int pid = super.getEntityId("pet2");
		Pet p1 = petService.findOne(pid);
		ap1.setPet(p1);
		
		applicationService.save(ap1);
		super.unauthenticate();
	}

	
	@Test(expected=IllegalArgumentException.class)
	public void createApplicationBad() {
		super.authenticate("adopter1");
		int aid = super.getEntityId("adopter1");
		Adopter a = adopterService.findOne(aid);
		Application ap2 = applicationService.create();
		ap2.setAdopter(a);
		int pid = super.getEntityId("pet1");
		Pet p2 = petService.findOne(pid);
		ap2.setPet(p2);
		
		applicationService.save(ap2);
		applicationRepository.flush();
		super.unauthenticate();
	}
}
