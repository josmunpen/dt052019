
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


	@Test
	public void testFinder() {
		super.authenticate("adopter1");
		final int finderId = super.getEntityId("finder1");
		final Finder f = this.finderService.findOne(finderId);
		f.setSex("MALE");
		final Finder fsave = this.finderService.save(f);
		super.unauthenticate();
	}

	@Test(expected = ConstraintViolationException.class)
	public void testFinderBadSalary() {
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
		super.authenticate("adopter1");
		final int finderId = super.getEntityId("finder1");
		final Finder f = this.finderService.findOne(finderId);
		f.setKeyword("testclear");
		f.setAge(123);
		f.setSex("FEMALE");
		f.setType("DOG");
		final Finder ftest = this.finderService.clear(f);
		Assert.isTrue(ftest.getAge() != null && ftest.getKeyword() != "" && ftest.getSex() != "" && ftest.getType() != "");
		//		Assert.isTrue(ftest.getKeyword() == "" && ftest.getSex() == "" && ftest.getAge() == null && ftest.getType() == "");
		super.unauthenticate();
	}
}
