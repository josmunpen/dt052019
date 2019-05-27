
package services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.DashboardRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class DashboardService {

	@Autowired
	DashboardRepository	repository;


	private boolean checkAdmin() {
		final Authority a = new Authority();
		a.setAuthority(Authority.ADMIN);
		final UserAccount user = LoginService.getPrincipal();
		return user.getAuthorities().contains(a);
	}

	public Double avgPets() {
		Assert.isTrue(this.checkAdmin());
		return this.repository.avgPets();
	}

	public Integer minPets() {
		Assert.isTrue(this.checkAdmin());
		return this.repository.minPets();
	}

	public Integer maxPets() {
		Assert.isTrue(this.checkAdmin());
		return this.repository.maxPets();
	}

	public Double stddevPets() {
		Assert.isTrue(this.checkAdmin());
		return this.repository.stddevPets();
	}

	public Double avgApplications() {
		Assert.isTrue(this.checkAdmin());
		return this.repository.avgApplications();
	}

	public Integer minApplications() {
		Assert.isTrue(this.checkAdmin());
		return this.repository.minApplications();
	}

	public Integer maxApplications() {
		Assert.isTrue(this.checkAdmin());
		return this.repository.maxApplications();
	}

	public Double stddevApplications() {
		Assert.isTrue(this.checkAdmin());
		return this.repository.stddevApplications();
	}

	public Double avgAge() {
		Assert.isTrue(this.checkAdmin());
		return this.repository.avgAge();
	}

	public Integer minAge() {
		Assert.isTrue(this.checkAdmin());
		return this.repository.minAge();
	}

	public Integer maxAge() {
		Assert.isTrue(this.checkAdmin());
		return this.repository.maxAge();
	}

	public Double stddevAge() {
		Assert.isTrue(this.checkAdmin());
		return this.repository.stddevAge();
	}

	public List<String> top3TypesEng() {
		Assert.isTrue(this.checkAdmin());
		List<String> top3 = this.repository.top3TypesEng();
		if (top3.size() > 3)
			top3 = top3.subList(0, 3);
		return top3;
	}

	public List<String> top3TypesEsp() {
		Assert.isTrue(this.checkAdmin());
		List<String> top3 = this.repository.top3TypesEsp();
		if (top3.size() > 3)
			top3 = top3.subList(0, 3);
		return top3;
	}

	public Double ratioPendingApplications() {
		Assert.isTrue(this.checkAdmin());
		return this.repository.ratioPendingApplications();
	}

	public Double ratioRejectedApplications() {
		Assert.isTrue(this.checkAdmin());
		return this.repository.ratioRejectedApplications();
	}

	public Double ratioAcceptedApplications() {
		Assert.isTrue(this.checkAdmin());
		return this.repository.ratioAcceptedApplications();
	}

	public Double avgHistories() {
		Assert.isTrue(this.checkAdmin());
		return this.repository.avgHistories();
	}

	public Integer minHistories() {
		Assert.isTrue(this.checkAdmin());
		return this.repository.minHistories();
	}

	public Integer maxHistories() {
		Assert.isTrue(this.checkAdmin());
		return this.repository.maxHistories();
	}

	public Double stddevHistories() {
		Assert.isTrue(this.checkAdmin());
		return this.repository.stddevHistories();
	}

	public Double avgMedicalCheckUp() {
		Assert.isTrue(this.checkAdmin());
		return this.repository.avgMedicalCheckUp();
	}

	public Integer minMedicalCheckUp() {
		Assert.isTrue(this.checkAdmin());
		return this.repository.minMedicalCheckUp();
	}

	public Integer maxMedicalCheckUp() {
		Assert.isTrue(this.checkAdmin());
		return this.repository.maxMedicalCheckUp();
	}

	public Double stddevMedicalCheckUp() {
		Assert.isTrue(this.checkAdmin());
		return this.repository.stddevMedicalCheckUp();
	}

	public Double ratioPetsWithMedicalCheckUp() {
		Assert.isTrue(this.checkAdmin());
		return this.repository.ratioPetsWithMedicalCheckUp();
	}

	public List<String> top3Veterinarians() {
		Assert.isTrue(this.checkAdmin());
		List<String> top3 = this.repository.top3Veterinarians();
		if (top3.size() > 3)
			top3 = top3.subList(0, 3);
		return top3;
	}

	public List<String> top3Adopters() {
		Assert.isTrue(this.checkAdmin());
		List<String> top3 = this.repository.top3Adopters();
		if (top3.size() > 3)
			top3 = top3.subList(0, 3);
		return top3;
	}
}
