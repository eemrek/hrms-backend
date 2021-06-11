package kodlamaio.hrms.core.validation;

import org.springframework.stereotype.Service;

@Service
public class VerificationManager implements VerificationService {
	
	public boolean sendVerificationCode(String emailAddress) {
		
		System.out.println(emailAddress+" Doğrulama kodu gönderildi.");
        return true;
    }
}
 