package pl.coderslab.jms.controller;

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

import pl.coderslab.jms.service.MessageService;

@RestController
class MessageController {
	
	@Autowired
	private MessageService messageService;
	
	private final List<String> teams = Arrays.asList("Real", "Barcelona", "Legia", "Rakieta Skarżysko");
	
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
	
	class Match {
		
		private String team1;
		private String team2;
		
		private byte point1;
		private byte point2;
		
		public Match(String team1, String team2, byte point1, byte point2) {
			this.team1 = team1;
			this.team2 = team2;
			this.point1 = point1;
			this.point2 = point2;
		}

		public String getTeam1() {
			return team1;
		}

		public String getTeam2() {
			return team2;
		}

		public byte getPoint1() {
			return point1;
		}

		public byte getPoint2() {
			return point2;
		}
	}

    @GetMapping(path = "/matches2")
    List<Match> matches2() {

	    List<Match> matchList = new ArrayList<>();

	    for (int i=0; i<3; i++) {
            final Random random = new Random();

            int team1 = random.nextInt(4);
            int team2;

            while(true) {
                team2 = random.nextInt(4);
                if(team2 != team1) {
                    break;
                }
            }

            int point1 = random.nextInt(10);
            int point2 = random.nextInt(10);

            matchList.add(new Match(teams.get(team1), teams.get(team2), (byte)point1, (byte)point2));
        }

        return matchList;
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
