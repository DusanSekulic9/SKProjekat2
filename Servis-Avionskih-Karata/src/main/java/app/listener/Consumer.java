package app.listener;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import app.entity.Karta;
import app.repositories.KarteRepository;

@Component
public class Consumer {
	
	@Autowired
	KarteRepository karteRepo;

	
	@JmsListener(destination = "karte.queue")
	public void consume(String id) {
		Long ID = Long.parseLong(id);
		List<Karta> karte = karteRepo.findAllByIdLet(ID);
		for(Karta k : karte) {
			k.setCanceled(true);
			karteRepo.save(k);
		}
	}
	
	
}
