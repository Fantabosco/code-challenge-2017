package it.reply.challenge.fantabosco.model;

import java.util.List;

public class Room {
	
	String name;
	int capacity;
	List<Event> events;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public List<Event> getEvents() {
		return events;
	}
	public void setEvents(List<Event> events) {
		this.events = events;
	}
	@Override
	public String toString() {
		return this.name + " (" + this.capacity + ")";
	}

}
