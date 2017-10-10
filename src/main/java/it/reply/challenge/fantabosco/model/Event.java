package it.reply.challenge.fantabosco.model;

import java.util.Date;

public class Event {
	
	String topic;
	Date startTime;
	Date endTime;
	int partecipants;
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public int getPartecipants() {
		return partecipants;
	}
	public void setPartecipants(int partecipants) {
		this.partecipants = partecipants;
	}
	
	

}
