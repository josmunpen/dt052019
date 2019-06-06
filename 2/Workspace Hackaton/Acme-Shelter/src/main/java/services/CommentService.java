
package services;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.CommentRepository;
import domain.Comment;

@Service
@Transactional
public class CommentService {

	@Autowired
	private CommentRepository	commentRepository;

	@Autowired
	private VeterinarianService	vs;

	@Autowired
	private TipService			ts;


	public Comment create() {
		final Comment res = new Comment();
		res.setMoment(new Date());
		return res;
	}

	public Comment create(final int tipId) {
		final Comment res = new Comment();
		res.setTip(this.ts.findOne(tipId));
		res.setMoment(new Date());
		return res;
	}

	public Comment save(final Comment pt) {
		this.commentRepository.flush();
		Assert.isTrue(pt.getScore() < 6 && pt.getScore() > 0);
		return this.commentRepository.save(pt);
	}
	public Comment findOne(final int id) {
		return this.commentRepository.findOne(id);
	}

	public void delete(final Comment petType) {
		this.commentRepository.delete(petType.getId());

	}
	public List<Comment> findAll() {
		return this.commentRepository.findAll();
	}

	public List<Comment> getCommentsByTip(final int tipId) {
		return this.commentRepository.getCommentsByTip(tipId);
	}
}
