package pl.coderslab.jms.service;

public interface MessageService {

	void sendPointToPointMessage(String queueName, String message);

	String fetchPointToPointMessages(String queueName);

	void publishMessage(String topicName, String message);
}
