package app.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.entity.Avion;
import app.entity.Let;
import app.forms.AvionForm;
import app.forms.LetForm;
import app.forms.PretragaLetaForm;
import app.repositories.AvionRepository;
import app.repositories.LetRepository;
import app.utils.UtilsMethods;

@RestController
@RequestMapping("")
public class Controller {

	private LetRepository letRepo;
	private AvionRepository avionRepo;

	@Autowired
	public Controller(LetRepository letRepo, AvionRepository avionRepo) {
		this.letRepo = letRepo;
		this.avionRepo = avionRepo;
	}

	@PostMapping("/pretraga")
	public ResponseEntity<String> pretraziLetove(@RequestBody PretragaLetaForm pretraga) {
		try {
			//List<Let> nadjeniLetovi = letRepo.findByCena(pretraga.getAvion(), pretraga.getPocetnaDestinacija(),
					//pretraga.getKrajnjaDestinacija(), pretraga.getDuzinaLeta(), pretraga.getCena());

			// prikaz na gui?

			return new ResponseEntity<String>("Letovi su pronadjeni", HttpStatus.ACCEPTED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/dodajLet")
	public ResponseEntity<String> dodajLet(@RequestBody LetForm let) {
		try {
			Avion avion = avionRepo.findByNaziv(let.getAvion());

			Let noviLet = new Let(avion, let.getPocetnaDestinacija(), let.getKrajnjaDestinacija(), let.getDuzinaLeta(),
					let.getCena());

			letRepo.saveAndFlush(noviLet);

			return new ResponseEntity<String>("Let je napravljen", HttpStatus.ACCEPTED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping("/dodajAvion")
	public ResponseEntity<String> dodajAvion(@RequestBody AvionForm avion, @RequestHeader(value = "Authorization") String token) {
		try {
			ResponseEntity<Boolean> response = UtilsMethods
					.sendGet("http://localhost:8080/isAdmin", token);
			
			if(!response.getBody().booleanValue()) {
				return new ResponseEntity<String>(HttpStatus.FORBIDDEN);
			}
			
			Avion noviAvion = new Avion(avion.getNaziv(), avion.getKapacitetPutnika());

			avionRepo.saveAndFlush(noviAvion);

			return new ResponseEntity<String>("Avion je napravljen", HttpStatus.ACCEPTED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}

	}

}
