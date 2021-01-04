package app.config;


import javax.jms.Queue;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

@Configuration
public class Config {
	
	@Value("${activemq.broker-url}")
	public String brokerUrl;

	@Bean
	public Queue emailQueue() {
		return new ActiveMQQueue("email.queue");
	}
	
	@Bean
	public Queue karteQueue() {
		return new ActiveMQQueue("karte.queue");
	}
	
	
	
	@Bean
	public ActiveMQConnectionFactory activeMQConnectionFactory() {
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
		factory.setBrokerURL(brokerUrl);
		return factory;
	}
	
	@Bean
	public JmsTemplate jmsTemplate() {
		return new JmsTemplate(activeMQConnectionFactory());
	}
	
	

}
