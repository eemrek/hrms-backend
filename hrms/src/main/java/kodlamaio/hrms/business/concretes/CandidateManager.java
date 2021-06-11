package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.CandidateService;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.dataaccess.abstracts.CandidateDao;
import kodlamaio.hrms.entities.concretes.Candidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CandidateManager implements CandidateService {

	private CandidateDao candidateDao;

	@Autowired
	public CandidateManager(CandidateDao candidateDao) {
		super();
		this.candidateDao = candidateDao;

	}

	private boolean validationForCandidate(Candidate candidate) {
		if (Objects.isNull(candidate.getNationalIdentity()) || Objects.isNull(candidate.getFirstName())
				|| Objects.isNull(candidate.getLastName()) || Objects.isNull(candidate.getEmail())
				|| Objects.isNull(candidate.getPassword()) || Objects.isNull(candidate.getBirthYear())) {

			return false;
		}
		return true;
	}

	private boolean checkIfEmailExists(String email) {
		if (this.candidateDao.findByEmail(email) != null) {
			return false;
		}
		return true;
	}

	private boolean checkIfNationalIdentityExists(String nationalIdentity) {
		if (this.candidateDao.findByNationalIdentity(nationalIdentity) != null) {
			return false;
		}
		return true;
	}

	@Override
	public DataResult<List<Candidate>> getAll() {
		return new SuccessDataResult<List<Candidate>>(candidateDao.findAll(), "Aday listesi");

	}

	@Override
	public Result add(Candidate candidate) {

		if (!validationForCandidate(candidate)) {
			return new ErrorResult("Eksik bilgi girdiniz");
		}
		if (!checkIfEmailExists(candidate.getEmail())) {
			return new ErrorResult("Kayıtlı email ");
		}
		if (!checkIfNationalIdentityExists(candidate.getNationalIdentity())) {
			return new ErrorResult("TC Kimlik No sistemde kayıtlı");
		}

		this.candidateDao.save(candidate);
		return new SuccessResult("Ekleme İşlemi Başarılı");
	}

	@Override
	public DataResult<Candidate> getByEmail(String email) {
		return new SuccessDataResult<Candidate>(this.candidateDao.findByEmail(email));
	}

	@Override
	public DataResult<Candidate> getByNationalIdentity(String nationalIdentity) {
		return new SuccessDataResult<Candidate>(this.candidateDao.findByNationalIdentity(nationalIdentity));
	}

	@Override
	public DataResult<Candidate> getById(int id) {
		return new SuccessDataResult<Candidate>(this.candidateDao.findById(id));
	}

	@Override
	public Result delete(int id) {
		this.candidateDao.deleteById(id);
		return new SuccessResult("Başarıyla Silindi");
	}

}