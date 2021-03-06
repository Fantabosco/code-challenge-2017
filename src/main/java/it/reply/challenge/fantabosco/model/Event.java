package it.reply.challenge.fantabosco.model;


public class Event implements Comparable<Event> {
	
	String topic;
	long startTime;
	long endTime;
	int partecipants;
	long weight;
	
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
	
	@Override
	public String toString() {
		return this.topic + " (" + this.partecipants + ") " + startTime + " -> " + endTime;
	}
	
	@Override
	public int compareTo(Event o) {
		return (int) (o.startTime - this.startTime);
	}
	
	public int getPoints() {
		return (int) (this.endTime - this.startTime)*partecipants;
	}

	public boolean equals(Object o) {
		if(!(o instanceof Event)) {
			return false;
		}
		return ((Event)o).getTopic().equals(this.getTopic());
	}
	public long getWeight() {
		return weight;
	}
	public void setWeight(long weight) {
		this.weight = weight;
	}

	
}
