
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


	/**
	 * TESTING REQUIREMENT #34.1 (Manage Customisation)
	 * COVERED INSTRUCTIONS IN THIS TEST: 100%
	 * COVERED INSTRUCTIONS IN CustomisationService: 66.2%
	 * COVERED DATA IN THIS TEST: 16%
	 * */

	@Test
	public void configTest() {
		/**
		 * TESTING REQUIREMENT #34.1
		 * POSITIVE TEST
		 * COVERED INSTRUCTIONS: 100%
		 * COVERED DATA: 12%
		 * */

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

		/**
		 * TESTING REQUIREMENT #34.1
		 * NEGATIVE TEST (YOU CAN NOT SET A FINDER DURATION THAT LONG)
		 * (Expected ConstraintViolationException)
		 * COVERED INSTRUCTIONS: 100%
		 * COVERED DATA: 10%
		 * */

		super.authenticate("admin");
		final Customisation cus = this.service.getCustomisation();
		cus.setFinderDuration(1293);
		final Customisation customtest = this.service.save(cus);
		this.repository.flush();
		this.unauthenticate();
	}

	@Test(expected = ConstraintViolationException.class)
	public void configBadSystemNameTest() {
		/**
		 * TESTING REQUIREMENT #34.1
		 * NEGATIVE TEST (YOU CAN NOT SET A SYSTEM NAME THAT CONTAINS A SCRIPT IN IT)
		 * (Expected ConstraintViolationException)
		 * COVERED INSTRUCTIONS: 100%
		 * COVERED DATA: 10%
		 * */
		super.authenticate("admin");
		final Customisation cus = this.service.getCustomisation();
		cus.setSystemName("<script></script>");
		final Customisation customtest = this.service.save(cus);
		this.repository.flush();
		this.unauthenticate();
	}

	@Test(expected = ConstraintViolationException.class)
	public void configBadVatPercentajeTest() {
		/**
		 * TESTING REQUIREMENT #34.1
		 * NEGATIVE TEST (YOU CAN NOT SET A VAT HIGHER THAN 100 OR LOWER THAN 0)
		 * (Expected ConstraintViolationException)
		 * COVERED INSTRUCTIONS: 100%
		 * COVERED DATA: 10%
		 * */
		super.authenticate("admin");
		final Customisation cus = this.service.getCustomisation();
		cus.setVat(-1231231);
		final Customisation customtest = this.service.save(cus);
		this.repository.flush();
		this.unauthenticate();
	}
}
