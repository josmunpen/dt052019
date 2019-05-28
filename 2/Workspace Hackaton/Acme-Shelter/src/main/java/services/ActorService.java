
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ActorRepository;
import security.LoginService;
import security.UserAccount;
import security.UserAccountRepository;
import domain.Actor;
import domain.Box;
import domain.Message;

@Service
@Transactional
public class ActorService {

	//Repository
	@Autowired
	public ActorRepository			actorRepository;

	@Autowired
	public UserAccountRepository	userAccountRepository;

	@Autowired
	private BoxService				mbs;


	//Constructor
	public ActorService() {
		super();
	}

	//Simple CRUD methods
	//Returns logged actor
	public Actor findByPrincipal() {
		Actor res;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		res = this.findByUserAccount(userAccount);
		Assert.notNull(res);

		return res;
	}

	//Returns logged actor from his or her userAccount
	public Actor findByUserAccount(final UserAccount userAccount) {
		Actor res;
		Assert.notNull(userAccount);
		Assert.notNull(userAccount.getId());

		res = this.actorRepository.findByUserAccountId(userAccount.getId());

		return res;
	}
	public Collection<Actor> findAll() {
		return this.actorRepository.findAll();
	}
	public Actor findOne(final int actorId) {
		return this.actorRepository.findOne(actorId);
	}
	public Actor save(final Actor actor) {
		return this.actorRepository.save(actor);
	}
	public void delete(final Actor actor) {
		this.actorRepository.delete(actor);
	}

	public Collection<Box> getMyBoxes() {
		final UserAccount actual = LoginService.getPrincipal();
		final Actor a = this.actorRepository.getActor(actual);
		return a.getBoxes();
	}

	public Box editBox(final Box m) {
		final Box result = this.mbs.save(m);

		return result;

	}

	public void deleteMessageBox(final Box m) {
		final UserAccount actual = LoginService.getPrincipal();
		final Actor a = this.actorRepository.getActor(actual);

		Assert.isTrue(a.getBoxes().contains(m));

		Assert.isTrue(!m.getPredefined());
		final Collection<Box> actorBoxes = a.getBoxes();
		actorBoxes.remove(m);
		a.setBoxes(actorBoxes);
		this.mbs.delete(m);

	}

	public Collection<Box> createPredefinedBoxes() {
		final Collection<Box> result = new ArrayList<Box>();
		//Creo las cajas predeterminadas del sistema
		final Box inbox = new Box();
		inbox.setMessages(new ArrayList<Message>());
		inbox.setName("in box");
		inbox.setPredefined(true);
		final Box inbox1 = this.mbs.saveInitial(inbox);
		result.add(inbox1);

		final Box notificationbox = new Box();
		notificationbox.setMessages(new ArrayList<Message>());
		notificationbox.setName("notification box");
		notificationbox.setPredefined(true);
		final Box notificationbox1 = this.mbs.saveInitial(notificationbox);
		result.add(notificationbox1);

		final Box outbox = new Box();
		outbox.setMessages(new ArrayList<Message>());
		outbox.setName("out box");
		outbox.setPredefined(true);
		final Box outbox1 = this.mbs.saveInitial(outbox);
		result.add(outbox1);

		final Box trashbox = new Box();
		trashbox.setMessages(new ArrayList<Message>());
		trashbox.setName("trash box");
		trashbox.setPredefined(true);
		final Box trashbox1 = this.mbs.saveInitial(trashbox);
		result.add(trashbox1);
		return result;
	}

}
