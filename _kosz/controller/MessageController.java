package pl.coderslab.thread.jms.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pl.coderslab.entity.Event;
import pl.coderslab.thread.jms.service.MessageService;
import pl.coderslab.service.EventService;

@RestController
class MessageController {
	
	@Autowired
	private MessageService messageService;

	@Autowired
    EventService eventService;
	
	@PostMapping(path = "/queue/{queueName}")
	ResponseEntity<?> sendPointToPointMessage(
			final @PathVariable("queueName") String queueName, 
			final @RequestParam("message") String message) {
		
		messageService.sendPointToPointMessage(queueName, message);
		
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@PostMapping(path = "/topic/{topicName}")
	ResponseEntity<?> publishMessage(
			final @PathVariable("topicName") String topicName, 
			final @RequestParam("message") String message) {
		
		messageService.publishMessage(topicName, message);
		
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@GetMapping(path = "/queue/{queueName}")
	String fetchPointToPointMessages(
			final @PathVariable("queueName") String queueName) {
	
		final String messages = messageService.fetchPointToPointMessages(queueName);
		
		return messages;
	}
	
	/*@PostMapping(path = "/topic/{topicName}")
	ResponseEntity<?> sendPublishSubscribeMessage(
			final @PathVariable("topicName") String channel, 
			final @RequestParam("message") String message) {
	
		messageService.sendPublishSubscripeMessage(channel, message);
		
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	
	
	@GetMapping(path = "/queue/{queueName}/messages")
	SseEmitter pointToPointMessageStream(
			final @PathVariable("queueName") String queueName) {
		
		return messageService.pointToPointMessageStream(queueName);
	}*/
	
}
