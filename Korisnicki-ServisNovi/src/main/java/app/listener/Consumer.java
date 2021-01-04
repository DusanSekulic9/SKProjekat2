package app.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import app.email.SendEmail;

@Component
public class Consumer {
	
	
	@JmsListener(destination = "email.queue")
	public void consume(String message) {
		SendEmail.sendEmail(message);
	}

}
