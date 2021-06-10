package kodlamaio.hrms.business.concretes;

import java.util.List;

import kodlamaio.hrms.business.abstracts.JobTitleService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataaccess.abstracts.JobTitleDao;
import kodlamaio.hrms.entities.concretes.JobTitle;

public class JobTitleManager implements JobTitleService {

	private JobTitleDao jobTitleDao;
	
	public JobTitleManager(JobTitleDao jobTitleDao) {
		super();
		this.jobTitleDao = jobTitleDao;
	}

	@Override
	public DataResult<List<JobTitle>> getAll() {
		return new SuccessDataResult<List<JobTitle>>(jobTitleDao.findAll(),"Bütün İş Pozisyonları Listelendi");
	}

	@Override
	public Result add(JobTitle jobTitle) {
		jobTitleDao.save(jobTitle);
		return new SuccessResult("Pozisyon Eklendi");
	}

}
