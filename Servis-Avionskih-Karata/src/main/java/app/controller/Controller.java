package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.forms.LetForm;
import app.repositories.KarteRepository;

@RestController
@RequestMapping("")
public class Controller {

	private KarteRepository karteRepo;

	@Autowired
	public Controller(KarteRepository karteRepo) {
		this.karteRepo = karteRepo;
	}

	@PostMapping("/kupiKartu")
	public ResponseEntity<String> kupiKartu(@RequestBody LetForm let,
			@RequestHeader(value = "Authorization") String token) {
		try {
			
			

			return new ResponseEntity<String>("Uspesno kupljena karta", HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}
}
