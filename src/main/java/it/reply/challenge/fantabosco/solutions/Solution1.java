package it.reply.challenge.fantabosco.solutions;

import it.reply.challenge.fantabosco.common.Utils;
import it.reply.challenge.fantabosco.model.Event;
import it.reply.challenge.fantabosco.model.Room;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class Solution1 {

	private Solution1() {
	}

	public static List<Room> solve(List<Event> events, List<Room> rooms,
			long totalDuration) {
		
		for(Room room: rooms) {
			room.setEvents(new ArrayList<Event>());
		}
		

		// Ordino gli eventi per numero partecipanti
		Collections.sort(events, new Comparator<Event>() {
			@Override
			public int compare(Event e1, Event e2) {
				return (int) (e2.getWeight() - e1.getWeight());
			}
		});
		
		List<Event> scartati = new ArrayList<Event>();
		for (Event e : events) {
			if (e.getWeight() == 0) {
				continue;
			}
//			Room room = Utils.getRoomWithMinCapacityPerEvent(e, rooms);
			Room room = Utils.getRoomWithCloserEvent(e, rooms);
			if (room != null) {
				room.getEvents().add(e);
			} else {
				scartati.add(e);
			}
		}

		
		if(false){
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

		// Prova a sostituire eventi inseriti
		for(Event ev: scartati) {
			boolean replaced = false;
			for (Room stanza : rooms) {
				if(stanza.getCapacity() < ev.getPartecipants() || replaced) {
					continue;
				}
				List<Event> tmp = new ArrayList<Event>(stanza.getEvents());
				for(Iterator<Event> i=stanza.getEvents().iterator(); i.hasNext() && !replaced;) {
					stanza.setEvents(new ArrayList<Event>(tmp));
					Event remove=i.next();
					if(Utils.checkCollision(ev,remove) && ev.getWeight()>remove.getWeight()) {
						stanza.getEvents().remove(remove);
						if(Utils.checkEvent(ev, stanza)) {
							System.out.println("Replaced");
							tmp.remove(remove);
							tmp.add(ev);
							replaced = true;
						}
					}
				}
				stanza.setEvents(tmp);
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
			
			
	
//		Collections.sort(e, new Comparator<Event>() {
//			public int compare(Event p1, Event p2) {
//				return (int) (p2.getWeight() - p1.getWeight());
//			}
//		});
//
//		
//		List<Room> compatibili = new ArrayList<Room>();
//		for (Event evento : e) {
//			compatibili = new ArrayList<Room>();
//			for (Room stanza : r) {
//				if ((stanza.getCapacity() >= evento.getPartecipants())
//						&& (stanza.getEvents().isEmpty() || !Utils.checkCollision(evento, r))) {
//					compatibili.add(stanza);
//				}
//
//			}
//			Collections.sort(compatibili, new Comparator<Room>() {
//				public int compare(Room p1, Room p2) {
//					return p2.getCapacity() - p2.getCapacity();
//				}
//			});
//			if (!compatibili.isEmpty()) {
//				compatibili.get(0).getEvents().add(evento);
//			} else {
//				scartati.add(evento);
//			}
//		}
//		

//		
//		
//		
//		
//		for (Room room : r) {
//			Collections.sort(room.getEvents(), new Comparator<Event>() {
//				public int compare(Event p1, Event p2) {
//					return (int) (p1.getStartTime() - p2.getStartTime());
//				}
//			});
//		}
//		return r;
//	}


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
