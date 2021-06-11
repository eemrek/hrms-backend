package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.AuthService;
import kodlamaio.hrms.business.abstracts.CandidateService;
import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.core.validation.VerificationService;
import kodlamaio.hrms.entities.concretes.Candidate;
import kodlamaio.hrms.entities.concretes.Employer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class AuthManager implements AuthService {

    private EmployerService employerService;
    private CandidateService candidateService;
    private VerificationService verificationService;

    @Autowired
    public AuthManager(EmployerService employerService, CandidateService candidateService, VerificationService verificationService) {
        super();
        this.employerService = employerService;
        this.candidateService = candidateService;
        this.verificationService = verificationService;
    }


    public static boolean isEmailValidation(String email) {
        final Pattern EMAIL_REGEX = Pattern.compile("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", Pattern.CASE_INSENSITIVE);
        return EMAIL_REGEX.matcher(email).matches();
    }



   
    @Override
    public Result registerEmployer(Employer employer, String confirmPassword) {
        if(!isEmailValidation(employer.getEmail())){
            return new ErrorResult("Yanlış email.");

        }else if(!employer.getPassword().equals(confirmPassword)){
            System.out.println(employer.getPassword());
            System.out.println(confirmPassword);

            return new ErrorResult("Yanlış şifre");
        }
        var result = this.employerService.add(employer);

        if(result.isSuccess()){
            if(this.verificationService.sendVerificationCode(employer.getEmail())){
                return new SuccessResult("İşveren olarak giriş yaptınız.");
            }
        }

        return new ErrorResult();

    }

    @Override
    public Result registerCandidate(Candidate candidate, String confirmPassword) {
        if(!isEmailValidation(candidate.getEmail()))
        {
            return new ErrorResult("Yanlış email.");
        }
        else if(!candidate.getPassword().equals(confirmPassword)) {
            return new ErrorResult("Yanlış şifre.");
        }
        var result = this.candidateService.add(candidate);

        if(result.isSuccess()) {
            if(this.verificationService.sendVerificationCode(candidate.getEmail())) {
                return new SuccessResult("Aday olarak giriş yaptınız.");
            }
        }

        return new ErrorResult();
    }
}