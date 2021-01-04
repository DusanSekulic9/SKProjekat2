package app.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

	
	@JmsListener(destination = "karte.queue")
	public void consume(String message) {
		
	}
}
