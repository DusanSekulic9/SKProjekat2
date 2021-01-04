package app.listener;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import app.email.SendEmail;
import app.entities.User;
import app.repository.UserRepository;

@Component
public class Consumer {
	
	@Autowired
	UserRepository userRepo;
	
	
	@JmsListener(destination = "email.queue")
	public void consume(List<Long> list) {
		for(Long l : list) {
			User u = userRepo.findById(l).get();
			SendEmail.sendEmail(u.getEmail());
		}
		
	}

}
