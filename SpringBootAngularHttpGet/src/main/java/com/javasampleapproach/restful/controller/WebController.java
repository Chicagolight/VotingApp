package com.javasampleapproach.restful.controller;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.javasampleapproach.restful.model.Game;

@RestController
@RequestMapping("/api")
public class WebController {
	private Map<String, LocalDateTime> hasVoted = new HashMap<String,LocalDateTime>();
	private Map<Integer, Game> games = new HashMap<Integer, Game>(){

		/**
		 * For games HashMap. Can be replaced with a query-based database, or read from a local file 
		 */
		private static final long serialVersionUID = 1422329826509347738L;

		{
	        put(1, new Game(1, "Overwatch", 0));
	        put(2, new Game(2, "World of Warcraft", 0));
	        put(3, new Game(3, "PUBG", 0));
	        put(4, new Game(4, "League of Legends", 0));
	        }
	    
	};
	
	/**
	 * Get a list of game objects
	 * @return Games as a list sorted by ID
	 */
	@GetMapping(value="/games")
	public List<Game> getAll(){
		List<Game> results = games.entrySet().stream()
									.map(entry ->entry.getValue())
									.collect(Collectors.toList());
		return results;
	}
	
/**
 * 	Attempts to cast a vote for the user
 * @param user IP address of the voter
 * @param voteChoice ID number of the game the user is voting for
 * @return redirection to the results page
 */
	@GetMapping("/vote")
	public void castVote(HttpServletRequest request, @RequestParam String voteChoice){
		
		//Attempt to cast vote and redirect user regardless of outcome
		castVoteCheck(request.getRemoteAddr(), Integer.parseInt(voteChoice));
	}
	
	/**
	 * Attempt to increment vote if user has not voted
	 */
	private void castVoteCheck(String user, Number voteChoice) {
		
		Game gameChoice = games.get(voteChoice.intValue());
		
		//If user has previously voted, check for 24 hour wait period.
		if(hasVoted.containsKey(user)) {
			//If allowed to vote, update date stamp and submit vote
			if(Duration.between(hasVoted.get(user), LocalDateTime .now()).toHours() >= 24) {
				hasVoted.replace(user, LocalDateTime .now());
				gameChoice.setVotes(gameChoice.getVotes()+1);
				
			}
		}
		//If user has not voted, log them and cast vote.
		else {
			hasVoted.put(user, LocalDateTime .now());
			gameChoice.setVotes(gameChoice.getVotes()+1);
		}
	}
	
	/**
	 * API call for resetting votes and user tracking during testing. Not accessible through front-end.
	 */
	@GetMapping(value="/reset")
	public void clearData(){
		hasVoted.clear();
		games.entrySet().forEach(	entry -> {entry.getValue().setVotes(0);}	);
	}
}



