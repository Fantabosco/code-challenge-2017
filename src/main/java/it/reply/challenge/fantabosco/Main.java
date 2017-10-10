package it.reply.challenge.fantabosco;

import it.reply.challenge.fantabosco.model.Event;
import it.reply.challenge.fantabosco.model.Room;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

	private static String FILE_1 = "data_50000_10.in";
	private static String FILE_2 = "data_50000_100.in";
	private static String FILE_3 = "data_5000_3.in";
	private static String FILE_4 = "data_5000_10.in";

	private static List<Event> events;
	private static List<Room> rooms;

	public static void main(String[] args) {
		readFile(FILE_1);

		// TODO algoritmo

	}

	private static void readFile(String fileName) {
		BufferedReader bufferedReader = null;
		FileReader fileReader = null;
		try {
			fileReader = new FileReader("src/main/resources/" + fileName);
			bufferedReader = new BufferedReader(fileReader);
			String currentLine = bufferedReader.readLine();
			String[] firstLine = currentLine.split(" ");
			
			int numEvents=Integer.parseInt(firstLine[0]);
			int numRooms=Integer.parseInt(firstLine[1]);
			
			events = new ArrayList<Event>();
			rooms = new ArrayList<Room>();
			
			// Es.: student-tech-clash 1494061200 1494068400 5
			for (int i = 0; i<numEvents; i++) {
				currentLine = bufferedReader.readLine();
				String[] splittedLine = currentLine.split(" ");
				Event e = new Event();
				e.setTopic(splittedLine[0]);
				e.setStartTime(Long.parseLong(splittedLine[1]));
				e.setEndTime(Long.parseLong(splittedLine[2]));
				e.setPartecipants(Integer.parseInt(splittedLine[3]));
				events.add(e);
			}
			
			//Es.: solar 80
			for (int i = 0; i<numRooms; i++) {
				currentLine = bufferedReader.readLine();
				String[] splittedLine = currentLine.split(" ");
				Room r = new Room();
				r.setName(splittedLine[0]);
				r.setCapacity(Integer.parseInt(splittedLine[1]));
				rooms.add(r);
			}
			
			System.out.println("Loaded:" + numRooms + " rooms, "+ numEvents + " events");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
