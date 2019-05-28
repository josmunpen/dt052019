
package services;

import java.util.ArrayList;
import java.util.Date;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.Actor;
import domain.Message;
import domain.Veterinarian;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class MessageServiceTest extends AbstractTest {

	//SUT
	@Autowired
	private MessageService		messageService;

	@Autowired
	private VeterinarianService	hs;

	@Autowired
	private BoxService			bs;


	/**
	 * TESTING REQUIREMENT #23 (Messages)
	 * POSITIVE TEST
	 * COVERED INSTRUCTIONS IN THIS TEST: 100%
	 * COVERED INSTRUCTIONS IN MessageService: 7.1%
	 * COVERED INSTRUCTIONS IN BoxService: 15.8%
	 * COVERED DATA IN THIS TEST: 40%
	 * */

	@Test
	public void createMessage() {
		this.authenticate("veterinarian1");
		final Message m = this.messageService.create();
		final int idvet = super.getEntityId("veterinarian1");
		final Veterinarian v = this.hs.findOne(idvet);
		m.setSender(v);
		final int idvet2 = super.getEntityId("veterinarian2");
		final Veterinarian h2 = this.hs.findOne(idvet2);
		final ArrayList<Actor> actors = new ArrayList<Actor>();
		actors.add(h2);
		m.setRecipients(actors);
		m.setBody("test message");
		m.setBroadcast(false);
		m.setMoment(new Date());
		m.setSubject("test");
		m.setTag("TEST");
		m.setPriority("LOW");

		this.unauthenticate();

		this.authenticate("veterinarian1");
		final Message m2 = this.messageService.create();
		final int idvet3 = super.getEntityId("veterinarian1");
		final Veterinarian v2 = this.hs.findOne(idvet3);
		m.setSender(v2);
		final int idvet4 = super.getEntityId("veterinarian2");
		final Veterinarian h3 = this.hs.findOne(idvet4);
		final ArrayList<Actor> actors2 = new ArrayList<Actor>();
		actors2.add(h3);
		m2.setRecipients(actors2);
		m2.setBody(null);
		m2.setBroadcast(false);
		m2.setMoment(new Date());
		m2.setSubject("");
		m2.setTag("TEST");
		m2.setPriority(null);

		this.unauthenticate();

		final Object testingData[][] = {

			/**
			 * TESTING REQUIREMENT #23
			 * POSITIVE TEST
			 * COVERED INSTRUCTIONS: 100%
			 * COVERED DATA: 21%
			 * */
			{
				"veterinarian1", m, null
			},

			/**
			 * TESTING REQUIREMENT #23
			 * POSITIVE TEST
			 * COVERED INSTRUCTIONS: 100%
			 * COVERED DATA: 21%
			 * 
			 */

			{
				"veterinarian1", m2, IllegalArgumentException.class
			},

		};

		for (int i = 0; i < testingData.length; i++)
			this.template2((String) testingData[i][0], (Message) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	protected void template2(final String username, final Message p, final Class<?> expected) {

		Class<?> caught;

		caught = null;

		try {
			this.authenticate(username);
			this.bs.sendMessage(p);

			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
	}

}
