package com.javasampleapproach.restful.model;

public class Game {
	private int id;
	private String name;
	private int votes;
	
	public Game(int id, String name, int votes){
		this.id = id;
		this.name = name;
		this.votes = votes;
	}
	
	// Game id
	public void setId(int id){
		this.id = id;
	}
	
	public int getId(){
		return this.id;
	}
	
	// Game name
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	
	// Game votes
	public void setVotes(int votes){
		this.votes = votes;
	}
	
	public int getVotes(){
		return this.votes;
	}
	
}
