package it.reply.challenge.fantabosco.model;


public class Event {
	
	String topic;
	long startTime;
	long endTime;
	int partecipants;
	
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public long getStartTime() {
		return startTime;
	}
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	public long getEndTime() {
		return endTime;
	}
	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}
	public int getPartecipants() {
		return partecipants;
	}
	public void setPartecipants(int partecipants) {
		this.partecipants = partecipants;
	}
	
	

}
