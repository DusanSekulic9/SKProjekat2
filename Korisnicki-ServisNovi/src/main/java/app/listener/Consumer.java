package app.listener;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import app.constants.MailConstants;
import app.email.SendEmail;
import app.entities.User;
import app.repository.UserRepository;

@Component
public class Consumer {
	
	@Autowired
	UserRepository userRepo;
	
	
	@JmsListener(destination = "email.queue")
	public void consume(String users) {
		System.out.println("uslo");
		String[] split = users.split("\n");
		Integer duzina = Integer.parseInt(split[1]);
		String[] ids = split[0].split(" ");
		for(String s : ids) {
			Long l = Long.parseLong(s);
			User u = userRepo.findById(l).get();
			SendEmail.sendEmail(u.getEmail(), MailConstants.OTKAZAN_LET);
			u.setPredjeneMilje(u.getPredjeneMilje() - duzina);
			userRepo.saveAndFlush(u);
		}
		
	}

}
