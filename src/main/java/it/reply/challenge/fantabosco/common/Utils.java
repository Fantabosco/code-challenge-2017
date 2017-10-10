package it.reply.challenge.fantabosco.common;

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
}
