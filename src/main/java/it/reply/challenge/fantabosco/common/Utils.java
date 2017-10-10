package it.reply.challenge.fantabosco.common;

import java.util.List;

import it.reply.challenge.fantabosco.model.Event;
import it.reply.challenge.fantabosco.model.Room;

public class Utils {
	
	private Utils() {
	}
	
	/**
	 * @return true if room "r" can host event "e"
	 */
	public static boolean checkEvent(Event e1, Room r) {
		if(e1.getPartecipants() > r.getCapacity()) {
			return false;
		}
		if(r.getEvents() != null) {
			for(Event e2: r.getEvents()) {
				if(checkCollision(e1, e2)) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * @return true if event "e1" is at the same time with event "e2"
	 */
	public static boolean checkCollision(Event e1, Event e2) {
		if(e1.getStartTime() == e2.getStartTime() || e1.getEndTime() == e2.getEndTime()) {
			return true;
		}
		if(e1.getStartTime() < e2.getStartTime()) {
			// e1 comincia prima, verifica se finisce dopo dell'inizio di e2
			return e1.getEndTime() > e2.getStartTime();
		} else {
			// e2 comincia prima, verifica se finisce dopo dell'inizio di e1
			return e2.getEndTime() > e1.getStartTime();
		}
	}
	
	/**
	 * @return true if event "e1" is at the same time with event "e2"
	 */
	public static boolean checkCollision(Event e, Room r) {
		for (Event evento : r.getEvents()){
			if (checkCollision(evento, e)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @return true if event "e1" is at the same time with event "e2"
	 */
	public static boolean checkCollision(Event e,List<Room> r) {
		for (Room stanza:r){
			if (checkCollision(e, stanza)) {
				return true;
			}
		}
		return false;
	}
	
	public static Room getRoomWithMinCapacityPerEvent(Event e, List<Room> rooms) {
		Room room = null;
		for (Room r : rooms) {
			if (e.getPartecipants() <= r.getCapacity() && r.getCapacity() != 0) {
				if (room == null || r.getCapacity() < room.getCapacity()) {
					if (checkEvent(e, r)) {
						room = r;
					}
				}
			}
		}
		return room;
	}

	public static Room getRoomWithCloserEvent(Event e, List<Room> rooms) {
		Room room = null;
		int min = 9999999;
		for (Room r : rooms) {
			if(!Utils.checkEvent(e, r)) {
				continue;
			}
			for(Event ev : r.getEvents()) {
				int tmp;
				if(!Utils.checkCollision(e, ev)) {
					if(e.getStartTime()<ev.getStartTime()) {
						tmp = (int) (ev.getStartTime() - e.getEndTime());
					} else {
						tmp = (int) (e.getStartTime() - ev.getEndTime());
					}
					if(tmp/e.getPartecipants() < min) {
						min = (int) (tmp/e.getPartecipants());
						room =r;
					}
				}
			}
		}
		if(room == null){
			return getRoomWithMinCapacityPerEvent(e, rooms);
		}
		return room;
	}
	
}
