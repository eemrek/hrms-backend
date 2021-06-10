package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.entities.concretes.JobTitle;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;

public interface JobTitleService {
	DataResult<List<JobTitle>> getAll();

	Result add(JobTitle jobTitle);
}
