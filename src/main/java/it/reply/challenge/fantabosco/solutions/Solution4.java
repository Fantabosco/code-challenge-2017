package it.reply.challenge.fantabosco.solutions;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import it.reply.challenge.fantabosco.common.Utils;
import it.reply.challenge.fantabosco.model.Event;
import it.reply.challenge.fantabosco.model.Room;

public class Solution4 {

	private Solution4() {
	}

	public static List<Room> solve(List<Event> e, List<Room> r, long totalDuration) {

		Collections.sort(e, new Comparator<Event>() {
			public int compare(Event p1, Event p2) {
//				return p2.getPoints() - p1.getPoints();
				return (int)((p2.getEndTime()-p2.getStartTime()) - (p1.getEndTime()-p1.getStartTime()));
			}
		});

		for (Event evento : e) {
			for (Room stanza : r) {
				if ((stanza.getCapacity() >= evento.getPartecipants())
						&& (stanza.getEvents().isEmpty() || !Utils.checkCollision(evento, r))) {
					stanza.getEvents().add(evento);
					break;
				}
			}
//			e.removeAll(stanza.getEvents());
		}
		for (Room room : r) {
			Collections.sort(room.getEvents(), new Comparator<Event>() {
				public int compare(Event p1, Event p2) {
					return (int) (p1.getStartTime() - p2.getStartTime());
				}
			});
		}
		return r;
	}

}
