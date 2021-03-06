
package kodlamaio.hrms.api.controllers;

import kodlamaio.hrms.business.abstracts.AuthService;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Candidate;
import kodlamaio.hrms.entities.concretes.Employer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private AuthService authService;

	public AuthController(AuthService authService) {
		super();
		this.authService = authService;
	}

	@PostMapping("/registerEmployer")
	public Result registerEmployer(@RequestBody Employer employer, String confirmPassword) {
		return this.authService.registerEmployer(employer, confirmPassword);
	}

	@PostMapping("/registerCandidate")
    public Result registerJobSeeker(@RequestBody Candidate candidate , String confirmPassword) {
        return this.authService.registerCandidate (candidate, confirmPassword);
    }
}
