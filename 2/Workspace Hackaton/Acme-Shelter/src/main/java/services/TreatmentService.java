
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.TreatmentRepository;
import domain.MedicalCheckUp;
import domain.Treatment;

@Service
@Transactional
public class TreatmentService {

	@Autowired
	TreatmentRepository	treatmentRepository;


	public void delete(final MedicalCheckUp m) {
		for (final Treatment t : this.treatmentRepository.findByMedicalCheckUp(m.getId()))
			this.treatmentRepository.delete(t.getId());
	}
}
