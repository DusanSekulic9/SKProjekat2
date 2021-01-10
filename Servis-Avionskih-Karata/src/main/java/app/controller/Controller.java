package app.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.entity.Karta;
import app.forms.LetForm;
import app.repositories.KarteRepository;
import app.utils.UtilsMethods;

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
			ResponseEntity<Long> imaLiMesta = UtilsMethods.sendGet("http://localhost:8081/kapacitet", let);
			if(!imaLiMesta.getStatusCode().equals(HttpStatus.ACCEPTED)) {
				return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
			}
			
			Long idLet = imaLiMesta.getBody();
			
			ResponseEntity<Long> userInfo = UtilsMethods.sendGetLong("http://localhost:8080/whoAmI", token);
			if(!userInfo.getStatusCode().equals(HttpStatus.ACCEPTED)) {
				return new ResponseEntity<String>("Nemate nijednu karticu!",HttpStatus.EXPECTATION_FAILED);
			}
			
			
			Long userId = userInfo.getBody();
			
			ResponseEntity<String> updateLet = UtilsMethods.sendPost("http://localhost:8081/updateLet", let);
			
			
			ResponseEntity<String> updateUser = UtilsMethods.sendPost("http://localhost:8080/updateUser", token, let.getDuzinaLeta());
			
			
			Karta karta = new Karta(new Date(), idLet, userId);
			
			karteRepo.save(karta);
			

			return new ResponseEntity<String>("Uspesno kupljena karta", HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@PostMapping("/otkazanLet")
	public ResponseEntity<String> otkazanLet(@RequestBody Long let) {
		try {
			System.out.println(let.getClass());
			System.out.println("trazenje karata");
			List<Karta> karte = karteRepo.findAllByIdLet(let);
			System.out.println("karte nadjene");
			String str="";
			for(Karta k : karte) {
				str += "" + k.getIdUser()+" ";
				System.out.println("Dodato");
			}
			System.out.println("proslo");
			return new ResponseEntity<String>(str, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}
}
