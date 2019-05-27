
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.HistoryRepository;
import domain.History;

@Service
@Transactional
public class HistoryService {

	@Autowired
	HistoryRepository	historyRepository;


	public void delete(final History h) {
		this.historyRepository.delete(h.getId());

	}
}
