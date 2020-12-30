package app.controller;

import static app.security.SecurityConstants.HEADER_STRING;
import static app.security.SecurityConstants.SECRET;
import static app.security.SecurityConstants.TOKEN_PREFIX;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import app.email.SendEmail;
import app.entities.User;
import app.forms.RegistrationForm;
import app.repository.UserRepository;

@RestController
@RequestMapping("")
public class Controller {

	private BCryptPasswordEncoder encoder;
	private UserRepository userRepo;

	@Autowired
	public Controller(BCryptPasswordEncoder encoder, UserRepository userRepo) {
		this.encoder = encoder;
		this.userRepo = userRepo;
	}

	@PostMapping("/register")
	public ResponseEntity<String> subtractionPost(@RequestBody RegistrationForm registrationForm) {

		try {

			// iscitavamo entitet iz registracione forme
			User user = new User(registrationForm.getIme(), registrationForm.getPrezime(), registrationForm.getEmail(),
					encoder.encode(registrationForm.getPassword()), registrationForm.getBrojPasosa());

			// cuvamo u nasoj bazi ovaj entitet
			userRepo.saveAndFlush(user);
			
			//SendEmail.sendEmail(user.getEmail());

			return new ResponseEntity<>("success", HttpStatus.ACCEPTED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/whoAmI")
	public ResponseEntity<String> whoAmI(@RequestHeader(value = HEADER_STRING) String token) {
		try {

			// izvlacimo iz tokena subject koj je postavljen da bude email
			String email = JWT.require(Algorithm.HMAC512(SECRET.getBytes())).build()
					.verify(token.replace(TOKEN_PREFIX, "")).getSubject();

			User user = userRepo.findByEmail(email);

			return new ResponseEntity<>(user.getIme() + " " + user.getPrezime(), HttpStatus.ACCEPTED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@PutMapping("/editProfile")
	public ResponseEntity<String> editProfile(@RequestBody RegistrationForm registrationForm, @RequestHeader(value = HEADER_STRING) String token) {

		try {
				
			String email = JWT.require(Algorithm.HMAC512(SECRET.getBytes())).build()
					.verify(token.replace(TOKEN_PREFIX, "")).getSubject();
			
			User user = userRepo.findByEmail(email);
			
			if(registrationForm.getIme() != null && !registrationForm.getIme().trim().equals(user.getIme()))
				user.setIme(registrationForm.getIme());
			
			if(registrationForm.getPrezime() != null && !registrationForm.getPrezime().trim().equals(user.getPrezime()))
				user.setPrezime(registrationForm.getPrezime());
			
			if(registrationForm.getEmail() != null && !registrationForm.getEmail().trim().equals(user.getEmail())) {
				user.setEmail(registrationForm.getEmail());
				//SendEmail.sendEmail(user.getEmail());
			}
			
			if(registrationForm.getBrojPasosa() != null && !registrationForm.getBrojPasosa().trim().equals(user.getBrojPasosa()))
				user.setBrojPasosa(registrationForm.getBrojPasosa());
			
			if(registrationForm.getPassword() != null && !encoder.encode(registrationForm.getPassword()).equals(user.getPassword()))
				user.setPassword(registrationForm.getBrojPasosa());
			
			userRepo.saveAndFlush(user);
			
			return new ResponseEntity<>("success", HttpStatus.ACCEPTED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

}
