package it.reply.challenge.fantabosco.model;


public class Event implements Comparable<Event> {
	
	String topic;
	long startTime;
	long endTime;
	int partecipants;
	float weight;
	
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
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	@Override
	public String toString() {
		return this.topic + " (" + this.partecipants + ") " + startTime + " -> " + endTime;
	}
	
	@Override
	public int compareTo(Event o) {
		return (int) (o.startTime - this.startTime);
	}
	
	public boolean equals(Object o) {
		if(o instanceof Event) {
			return false;
		}
		return ((Event)o).getTopic().equals(this.getTopic());
	}
}
