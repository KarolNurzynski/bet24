package pl.coderslab.thread.jms.service.impl;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jms.JmsProperties;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import pl.coderslab.thread.jms.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Override
	public void sendPointToPointMessage(final String queueName, final String message) {
		
		//jmsTemplate.convertAndSend(queueName, message);
		jmsTemplate.setExplicitQosEnabled(true);
		jmsTemplate.setPriority(10);
		jmsTemplate.send(queueName, new MessageCreator() {
			
			@Override
			public Message createMessage(final Session session) throws JMSException {
				
				final TextMessage jmsMessage = session.createTextMessage(message);
				jmsMessage.setJMSPriority(5);
				jmsMessage.setStringProperty("ala", "ma kota");

				return jmsMessage;
			}
		});
	}
	
	@Override
	public void publishMessage(final String topicName, final String message) {
		
		//final boolean pubSubDomain = jmsTemplate.isPubSubDomain();
		
		//jmsTemplate.setPubSubDomain(true);
		
		jmsTemplate.convertAndSend(topicName, message);
		
		//jmsTemplate.setPubSubDomain(pubSubDomain);
	}

	@Override
	public String fetchPointToPointMessages(final String queueName) {
		return (String)jmsTemplate.receiveAndConvert(queueName);
	}
}
