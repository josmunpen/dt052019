
package services;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import repositories.CustomisationRepository;
import utilities.AbstractTest;
import domain.Customisation;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class ConfigurationTest extends AbstractTest {

	@Autowired
	CustomisationService	service;

	@Autowired
	CustomisationRepository	repository;


	@Test
	public void configTest() {
		super.authenticate("admin");
		final Customisation cus = this.service.getCustomisation();
		cus.setFinderDuration(4);
		cus.setResultsNumber(30);
		cus.setSystemName("zistema");
		final Customisation customtest = this.service.save(cus);
		this.unauthenticate();
	}

	@Test(expected = ConstraintViolationException.class)
	public void configBadFinderDurationTest() {
		super.authenticate("admin");
		final Customisation cus = this.service.getCustomisation();
		cus.setFinderDuration(1293);
		final Customisation customtest = this.service.save(cus);
		this.repository.flush();
		this.unauthenticate();
	}

	@Test(expected = ConstraintViolationException.class)
	public void configBadSystemNameTest() {
		super.authenticate("admin");
		final Customisation cus = this.service.getCustomisation();
		cus.setSystemName("<script></script>");
		final Customisation customtest = this.service.save(cus);
		this.repository.flush();
		this.unauthenticate();
	}

	@Test(expected = ConstraintViolationException.class)
	public void configBadVatPercentajeTest() {
		super.authenticate("admin");
		final Customisation cus = this.service.getCustomisation();
		cus.setVat(-1231231);
		final Customisation customtest = this.service.save(cus);
		this.repository.flush();
		this.unauthenticate();
	}
}
