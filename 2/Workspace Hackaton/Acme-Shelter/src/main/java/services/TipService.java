
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.TipRepository;
import domain.PetType;
import domain.Tip;
import domain.Veterinarian;

@Service
@Transactional
public class TipService {

	@Autowired
	private TipRepository		tipRepository;

	@Autowired
	private VeterinarianService	vs;


	public Tip create() {
		final Tip res = new Tip();
		res.setPetTypes(new ArrayList<PetType>());
		final Veterinarian v = this.vs.findByPrincipal();
		res.setVeterinarian(v);
		res.setMoment(new Date());
		return res;
	}

	public Tip save(final Tip pt) {
		this.tipRepository.flush();
		return this.tipRepository.save(pt);
	}
	public Tip findOne(final int id) {
		return this.tipRepository.findOne(id);
	}

	public void delete(final Tip petType) {
		this.tipRepository.delete(petType.getId());

	}
	public List<Tip> findAll() {
		return this.tipRepository.findAll();
	}

	public Collection<Tip> findAllByVeterinarian(final int idVeterinarian) {
		final Collection<Tip> allTips = this.tipRepository.findAll();
		final Collection<Tip> res = new ArrayList<Tip>();
		for (final Tip t : allTips)
			if (t.getVeterinarian().getId() == idVeterinarian)
				res.add(t);
		return res;
	}
}
