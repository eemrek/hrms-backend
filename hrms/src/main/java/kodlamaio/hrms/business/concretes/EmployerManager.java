package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.dataaccess.abstracts.EmployerDao;
import kodlamaio.hrms.entities.concretes.Employer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class EmployerManager implements EmployerService {

   private EmployerDao employerDao;

   @Autowired
    public EmployerManager(EmployerDao employerDao) {
        super();
        this.employerDao = employerDao;
    }

    private boolean checkIfEmailExists(String email) {
        if(this.employerDao.findByEmail(email) != null) {
            return false;
        }
        return true;
    }



    private boolean validationForEmployer(Employer employer) {
        if (Objects.isNull(employer.getCompanyName()) || Objects.isNull(employer.getWebAddress()) ||
                Objects.isNull(employer.getEmail())  || Objects.isNull(employer.getPhoneNumber()) ||
                Objects.isNull(employer.getPassword())){
            return false;
        }

        return true;
    }


    @Override
    public DataResult<List<Employer>> getAll() {
       return new SuccessDataResult<List<Employer>>(this.employerDao.findAll(),"İşveren listesi");
    }

    @Override
    public Result add(Employer employer) {
        if(!this.checkIfEmailExists(employer.getEmail())){
            return new ErrorResult("Kayıtlı email");
        }
        if(!this.validationForEmployer(employer)) {
            return new ErrorResult("Eksik bilgi girdiniz");
        }
        this.employerDao.save(employer);
        return new SuccessResult("İşveren eklendi");
    }

    @Override
    public DataResult<Employer> getByEmail(String email) {
        return new SuccessDataResult<Employer>(this.employerDao.findByEmail(email));
    }
}