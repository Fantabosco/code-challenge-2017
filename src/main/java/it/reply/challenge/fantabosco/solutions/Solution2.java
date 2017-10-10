package it.reply.challenge.fantabosco.solutions;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import it.reply.challenge.fantabosco.model.Event;
import it.reply.challenge.fantabosco.model.Room;

// Greedy as fuck
public class Solution2 {
	
	private Solution2(){
	}
	
	public static List<Room> solve(List<Event> e, List<Room> r, long totalDuration) {
		List<Event> sortedEvents = sort(e);
		
		for (Event event : sortedEvents) {
			for (Room room : r) {
				if (event.getPartecipants() <= room.getCapacity()) {
					sortedEvents.remove(event);
					if (room.getEvents() == null) {
						room.setEvents(new ArrayList<Event>());
					}
					room.getEvents().add(event);
				}
			}
		}
		
		return r;
	}
	
	public static List<Event> sort(List<Event> e) {
		e.sort(new Comparator<Event>() {
			@Override
			public int compare(Event event1, Event event2) {
				long diff = event1.getWeight() - event2.getWeight();
				if (diff > 0) {
					return 1;
				}
				else if (diff < 0) {
					return -1;
				}
				else {
					return 0;
				}
			}
		});
		return e;
	}
}
