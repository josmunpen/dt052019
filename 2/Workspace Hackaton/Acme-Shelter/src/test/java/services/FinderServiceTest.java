
package services;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;
import org.springframework.validation.Validator;

import repositories.FinderRepository;
import utilities.AbstractTest;
import domain.Finder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class FinderServiceTest extends AbstractTest {

	@Autowired
	FinderService		finderService;

	@Autowired
	FinderRepository	repository;
	@Autowired
	Validator			validator;

	@Autowired
	PetService			petService;


	/**
	 * TESTING REQUIREMENT #34.1 (Manage Finder)
	 * COVERED INSTRUCTIONS IN THIS TEST: 100%
	 * COVERED INSTRUCTIONS IN FinderService: 54.1%
	 * COVERED INSTRUCTIONS IN PetService: 27.6%
	 * COVERED DATA IN THIS TEST: 12%
	 * */

	@Test
	public void testFinder() {
		/**
		 * TESTING REQUIREMENT #34.1
		 * POSITIVE TEST
		 * COVERED INSTRUCTIONS: 100%
		 * COVERED DATA: 10%
		 * */
		super.authenticate("adopter1");
		final int finderId = super.getEntityId("finder1");
		final Finder f = this.finderService.findOne(finderId);
		f.setSex("MALE");
		final Finder fsave = this.finderService.save(f);
		super.unauthenticate();
	}

	@Test(expected = ConstraintViolationException.class)
	public void testFinderBadSalary() {
		/**
		 * TESTING REQUIREMENT #34.1
		 * NEGATIVE TEST (YOU CAN NOT EDIT YOUR FINDER SETTING A SCRIPT AS ITS KEYWORD)
		 * (Expected ConstraintViolationException)
		 * COVERED INSTRUCTIONS: 100%
		 * COVERED DATA: 10%
		 * */
		super.authenticate("adopter1");
		final int finderId = super.getEntityId("finder1");
		final Finder f = this.finderService.findOne(finderId);
		f.setKeyword("<script></script>");

		final Finder fsave = this.finderService.save(f);
		this.repository.flush();
		super.unauthenticate();
	}

	@Test
	public void testFinderClear() {
		/**
		 * TESTING REQUIREMENT #34.2
		 * POSITIVE TEST
		 * COVERED INSTRUCTIONS: 100%
		 * COVERED DATA: 10%
		 * */
		super.authenticate("adopter1");
		final int finderId = super.getEntityId("finder1");
		final Finder f = this.finderService.findOne(finderId);
		f.setKeyword("testclear");
		f.setAge(123);
		f.setSex("FEMALE");
		f.setType("DOG");
		final Finder ftest = this.finderService.clear(f);
		Assert.isTrue(ftest.getKeyword() == "" && ftest.getSex() == "" && ftest.getAge() == null && ftest.getType() == "");
		super.unauthenticate();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFinderBadClear() {
		/**
		 * TESTING REQUIREMENT #34.2
		 * NEGATIVE TEST (YOU CANNOT CLEAR A FINDER BEING A PETOWNER)
		 * (Expected IllegalArgumentException)
		 * COVERED INSTRUCTIONS: 100%
		 * COVERED DATA: 10%
		 * */
		super.authenticate("petowner1");
		final int finderId = super.getEntityId("finder1");
		final Finder f = this.finderService.findOne(finderId);
		f.setKeyword("testclear");
		f.setAge(123);
		f.setSex("FEMALE");
		f.setType("DOG");
		final Finder ftest = this.finderService.clear(f);
		//		Assert.isTrue(ftest.getKeyword() == "" && ftest.getSex() == "" && ftest.getAge() == null && ftest.getType() == "");
		super.unauthenticate();
	}
}
