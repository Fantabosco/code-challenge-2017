package it.reply.challenge.fantabosco.solutions;

import it.reply.challenge.fantabosco.common.Utils;
import it.reply.challenge.fantabosco.model.Event;
import it.reply.challenge.fantabosco.model.Room;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Solution3 {
	
	private Solution3(){
	}
	
	public static List<Room> solve(List<Event> events, List<Room> rooms, long totalDuration) {
		// Ordino gli eventi per numero partecipanti
		Collections.sort(events, new Comparator<Event>() {
			@Override
			public int compare(Event e1, Event e2) {
				return (int) (e2.getWeight() - e1.getWeight());
			}
		});
		
		for (Event e : events) {
			if (e.getWeight() == 0) {
				continue;
			}
			Room room = Utils.getRoomWithMinCapacityPerEvent(e, rooms);
			if (room != null) {
				room.getEvents().add(e);
			}
		}
		
		for (Room r : rooms) {
			Collections.sort(r.getEvents(), new Comparator<Event>() {
				@Override
				public int compare(Event e1, Event e2) {
					return (int) (e1.getStartTime() - e2.getStartTime());
				}
			});
		}
		
		return rooms;
	}
	
	public static List<Room> solve2(List<Event> events, List<Room> rooms, long totalDuration) {
		// Ordino gli eventi per ora inizio
		Collections.sort(events, new Comparator<Event>() {
			@Override
			public int compare(Event e1, Event e2) {
				return (int) (e1.getStartTime() - e2.getStartTime());
			}
		});
		
		for (Event e : events) {
			Room room = Utils.getRoomWithMinCapacityPerEvent(e, rooms);
			if (room != null) {
				room.getEvents().add(e);
			}
		}
		
		return rooms;
	}

}
