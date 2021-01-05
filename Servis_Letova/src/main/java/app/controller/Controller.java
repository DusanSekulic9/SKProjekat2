package app.controller;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	@Autowired
	JmsTemplate jmsTemplate;
	@Autowired
	Queue emailQueue;
	@Autowired
	Queue karteQueue;

	@GetMapping("/pretraga")
	public ResponseEntity<String> pretraziLetove(@RequestBody PretragaLetaForm pretraga) {
		try {
			List<Let> searched = letRepo.findAll();

			if (pretraga.getAvion() != null) {
				List<Let> letovi = letRepo.findByAvion(pretraga.getAvion());
				for (int i = 0; i < searched.size(); i++) {
					if (!letovi.contains(searched.get(i))) {
						searched.remove(i);
						i--;
					}
				}
			}
			if (pretraga.getPocetnaDestinacija() != null) {
				List<Let> letovi = letRepo.findByPocetnaDestinacija(pretraga.getPocetnaDestinacija());
				for (int i = 0; i < searched.size(); i++) {
					if (!letovi.contains(searched.get(i))) {
						searched.remove(i);
						i--;
					}
				}
			}
			if (pretraga.getKrajnjaDestinacija() != null) {
				List<Let> letovi = letRepo.findByKrajnjaDestinacija(pretraga.getKrajnjaDestinacija());
				for (int i = 0; i < searched.size(); i++) {
					if (!letovi.contains(searched.get(i))) {
						searched.remove(i);
						i--;
					}
				}
			}
			if (pretraga.getDuzinaLeta() != 0) {
				List<Let> letovi = letRepo.findByDuzinaLeta(pretraga.getDuzinaLeta());
				for (int i = 0; i < searched.size(); i++) {
					if (!letovi.contains(searched.get(i))) {
						searched.remove(i);
						i--;
					}
				}
			}
			if (pretraga.getCena() != 0) {
				List<Let> letovi = letRepo.findByCena(pretraga.getCena());
				for (int i = 0; i < searched.size(); i++) {
					if (!letovi.contains(searched.get(i))) {
						searched.remove(i);
						i--;
					}
				}
			}

			if (searched.isEmpty()) {
				return new ResponseEntity<String>("Nema aviona za trazenu pretragu", HttpStatus.BAD_REQUEST);
			}

			// prikazi na gui searched
			for (Let l : searched)
				System.out.println(l.getPocetnaDestinacija());

			return new ResponseEntity<String>("Avioni pronadjeni", HttpStatus.ACCEPTED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/dodajLet")
	public ResponseEntity<String> dodajLet(@RequestBody LetForm let,
			@RequestHeader(value = "Authorization") String token) {
		try {
			ResponseEntity<Boolean> response = UtilsMethods.sendGet("http://localhost:8080/isAdmin", token);

			if (!response.getBody().booleanValue()) {
				return new ResponseEntity<String>(HttpStatus.FORBIDDEN);
			}

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

	@DeleteMapping("/obrisiLet")
	public ResponseEntity<String> obrisiLet(@RequestBody LetForm pretraga,
			@RequestHeader(value = "Authorization") String token) {
		try {
			ResponseEntity<Boolean> response = UtilsMethods.sendGet("http://localhost:8080/isAdmin", token);
			
			System.out.println("Provera admina");
			
			if (!response.getBody().booleanValue()) {
				return new ResponseEntity<String>(HttpStatus.FORBIDDEN);
			}
			
			System.out.println("Proverio admina");

			List<Let> searched = letRepo.findAll();

			Avion avion = avionRepo.findByNaziv(pretraga.getAvion());

			if (pretraga.getAvion() != null) {
				List<Let> letovi = letRepo.findByAvion(avion);
				for (int i = 0; i < searched.size(); i++) {
					if (!letovi.contains(searched.get(i))) {
						searched.remove(i);
						i--;
					}
				}
			}
			if (pretraga.getPocetnaDestinacija() != null) {
				List<Let> letovi = letRepo.findByPocetnaDestinacija(pretraga.getPocetnaDestinacija());
				for (int i = 0; i < searched.size(); i++) {
					if (!letovi.contains(searched.get(i))) {
						searched.remove(i);
						i--;
					}
				}
			}
			if (pretraga.getKrajnjaDestinacija() != null) {
				List<Let> letovi = letRepo.findByKrajnjaDestinacija(pretraga.getKrajnjaDestinacija());
				for (int i = 0; i < searched.size(); i++) {
					if (!letovi.contains(searched.get(i))) {
						searched.remove(i);
						i--;
					}
				}
			}
			if (pretraga.getDuzinaLeta() != 0) {
				List<Let> letovi = letRepo.findByDuzinaLeta(pretraga.getDuzinaLeta());
				for (int i = 0; i < searched.size(); i++) {
					if (!letovi.contains(searched.get(i))) {
						searched.remove(i);
						i--;
					}
				}
			}
			if (pretraga.getCena() != 0) {
				List<Let> letovi = letRepo.findByCena(pretraga.getCena());
				for (int i = 0; i < searched.size(); i++) {
					if (!letovi.contains(searched.get(i))) {
						searched.remove(i);
						i--;
					}
				}
			}

			Let brisi = searched.get(0);

			if (brisi.isKupiLet()) {
				System.out.println("Pokusaj otkazivanja");
				ResponseEntity<String> ids = UtilsMethods.sendPost2("http://localhost:8082/otkazanLet", brisi.getIdLet());
				
				System.out.println("Otkazan let");
				
				String[] split = ids.getBody().split(" ");
				List<Long> idUser = new ArrayList<Long>();
				for (String s : split)
					idUser.add(Long.parseLong(s));

				List<Object> zaSlanje = new ArrayList<Object>();
				
				zaSlanje.add(idUser);
				zaSlanje.add(brisi.getDuzinaLeta());
				
				jmsTemplate.convertAndSend(karteQueue, zaSlanje);
				jmsTemplate.convertAndSend(emailQueue, idUser);
			}

			letRepo.delete(brisi);
			
			System.out.println("Let obrisan");
			
			return new ResponseEntity<String>("Let je obrisan!", HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/dodajAvion")
	public ResponseEntity<String> dodajAvion(@RequestBody AvionForm avion,
			@RequestHeader(value = "Authorization") String token) {
		try {
			ResponseEntity<Boolean> response = UtilsMethods.sendGet("http://localhost:8080/isAdmin", token);

			if (!response.getBody().booleanValue()) {
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

	@DeleteMapping("/obrisiAvion")
	public ResponseEntity<String> obrisiAvion(@RequestBody AvionForm avion,
			@RequestHeader(value = "Authorization") String token) {
		try {
			ResponseEntity<Boolean> response = UtilsMethods.sendGet("http://localhost:8080/isAdmin", token);

			if (!response.getBody().booleanValue()) {
				return new ResponseEntity<String>(HttpStatus.FORBIDDEN);
			}

			Avion brisi = avionRepo.findByNaziv(avion.getNaziv());

			if (brisi.getLetovi().isEmpty()) {
				avionRepo.delete(brisi);
				return new ResponseEntity<String>("Avion je obrisan", HttpStatus.ACCEPTED);
			}

			return new ResponseEntity<String>("Avion je zauzet, ne moze biti obrisan", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/kapacitet")
	public ResponseEntity<Long> kapacitet(@RequestBody LetForm let) {
		try {
			Avion avion = avionRepo.findByNaziv(let.getAvion());
			System.out.println("nasao avion");
			List<Let> searched = letRepo.findAll();

			if (let.getAvion() != null) {
				List<Let> letovi = letRepo.findByAvion(avion);
				for (int i = 0; i < searched.size(); i++) {
					if (!letovi.contains(searched.get(i))) {
						searched.remove(i);
						i--;
					}
				}
			}
			if (let.getPocetnaDestinacija() != null) {
				List<Let> letovi = letRepo.findByPocetnaDestinacija(let.getPocetnaDestinacija());
				for (int i = 0; i < searched.size(); i++) {
					if (!letovi.contains(searched.get(i))) {
						searched.remove(i);
						i--;
					}
				}
			}
			if (let.getKrajnjaDestinacija() != null) {
				List<Let> letovi = letRepo.findByKrajnjaDestinacija(let.getKrajnjaDestinacija());
				for (int i = 0; i < searched.size(); i++) {
					if (!letovi.contains(searched.get(i))) {
						searched.remove(i);
						i--;
					}
				}
			}
			if (let.getDuzinaLeta() != 0) {
				List<Let> letovi = letRepo.findByDuzinaLeta(let.getDuzinaLeta());
				for (int i = 0; i < searched.size(); i++) {
					if (!letovi.contains(searched.get(i))) {
						searched.remove(i);
						i--;
					}
				}
			}
			if (let.getCena() != 0) {
				List<Let> letovi = letRepo.findByCena(let.getCena());
				for (int i = 0; i < searched.size(); i++) {
					if (!letovi.contains(searched.get(i))) {
						searched.remove(i);
						i--;
					}
				}
			}

			if (searched.get(0).getAvion().getKapacitetPutnika() > searched.get(0).getKupljeneKarte()) {
				return new ResponseEntity<Long>(searched.get(0).getIdLet(), HttpStatus.ACCEPTED);
			}

			return new ResponseEntity<Long>(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED);
		} catch (Exception e) {
			return new ResponseEntity<Long>(HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping("/updateLet")
	public ResponseEntity<String> updateLet(@RequestBody LetForm let) {
		try {
			Avion avion = avionRepo.findByNaziv(let.getAvion());

			List<Let> searched = letRepo.findAll();

			if (let.getAvion() != null) {
				List<Let> letovi = letRepo.findByAvion(avion);
				for (int i = 0; i < searched.size(); i++) {
					if (!letovi.contains(searched.get(i))) {
						searched.remove(i);
						i--;
					}
				}
			}
			if (let.getPocetnaDestinacija() != null) {
				List<Let> letovi = letRepo.findByPocetnaDestinacija(let.getPocetnaDestinacija());
				for (int i = 0; i < searched.size(); i++) {
					if (!letovi.contains(searched.get(i))) {
						searched.remove(i);
						i--;
					}
				}
			}
			if (let.getKrajnjaDestinacija() != null) {
				List<Let> letovi = letRepo.findByKrajnjaDestinacija(let.getKrajnjaDestinacija());
				for (int i = 0; i < searched.size(); i++) {
					if (!letovi.contains(searched.get(i))) {
						searched.remove(i);
						i--;
					}
				}
			}
			if (let.getDuzinaLeta() != 0) {
				List<Let> letovi = letRepo.findByDuzinaLeta(let.getDuzinaLeta());
				for (int i = 0; i < searched.size(); i++) {
					if (!letovi.contains(searched.get(i))) {
						searched.remove(i);
						i--;
					}
				}
			}
			if (let.getCena() != 0) {
				List<Let> letovi = letRepo.findByCena(let.getCena());
				for (int i = 0; i < searched.size(); i++) {
					if (!letovi.contains(searched.get(i))) {
						searched.remove(i);
						i--;
					}
				}
			}

			searched.get(0).setKupljeneKarte(searched.get(0).getKupljeneKarte() + 1);
			searched.get(0).setKupiLet(true);
			
			letRepo.saveAndFlush(searched.get(0));

			return new ResponseEntity<String>(HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}

	}

}
