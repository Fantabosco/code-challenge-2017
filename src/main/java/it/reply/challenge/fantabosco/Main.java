package it.reply.challenge.fantabosco;

import it.reply.challenge.fantabosco.model.Event;
import it.reply.challenge.fantabosco.model.Room;
import it.reply.challenge.fantabosco.solutions.Solution1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {

	private static String FILE_EXAMPLE = "data_8_3.in";
	private static String FILE_1 = "data_5000_3.in";
	private static String FILE_2 = "data_5000_10.in";
	private static String FILE_3 = "data_50000_10.in";
	private static String FILE_4 = "data_50000_100.in";

	private static String inputFile;
	private static List<Event> events;
	private static List<Room> rooms;
	
	private static long totalDuration;

	public static void main(String[] args) {
		readFile(FILE_EXAMPLE);

		rooms = Solution1.solve(events, rooms, totalDuration);
		
		evaluateSolution();
		writeSolution();
	}

	private static void readFile(String fileName) {
		BufferedReader bufferedReader = null;
		FileReader fileReader = null;
		try {
			inputFile = "src/main/resources/" + fileName;
			fileReader = new FileReader(inputFile);
			bufferedReader = new BufferedReader(fileReader);
			String currentLine = bufferedReader.readLine();
			String[] firstLine = currentLine.split(" ");
			
			int numEvents=Integer.parseInt(firstLine[0]);
			int numRooms=Integer.parseInt(firstLine[1]);
			
			events = new ArrayList<Event>();
			rooms = new ArrayList<Room>();
			
			long minTime = Long.MAX_VALUE;
			long maxTime = Long.MIN_VALUE;
			
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
				
				// Aggiornamento min e max time
				if (e.getStartTime() < minTime) {
					minTime = e.getStartTime();
				}
				if (e.getEndTime() > maxTime) {
					maxTime = e.getEndTime();
				}
			}
			
			totalDuration = maxTime - minTime;
			System.out.println("Durata totale = " + totalDuration);
			
			//Es.: solar 80
			for (int i = 0; i<numRooms; i++) {
				currentLine = bufferedReader.readLine();
				String[] splittedLine = currentLine.split(" ");
				Room r = new Room();
				r.setName(splittedLine[0]);
				r.setCapacity(Integer.parseInt(splittedLine[1]));
				rooms.add(r);
			}
			
			System.out.println("Loaded: " + numRooms + " rooms, "+ numEvents + " events");
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fileReader.close();
				bufferedReader.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void writeSolution() {
		BufferedWriter bufferedWriter = null;
		FileWriter fileWriter = null;
		try {
			String outputFile = inputFile.replace(".in", ".out");
			fileWriter = new FileWriter(outputFile);
			bufferedWriter = new BufferedWriter(fileWriter);
			
			for(Room r: rooms) {
				StringBuilder solution = new StringBuilder();
				solution.append(r.getName());
				solution.append(":");
				if(r.getEvents() != null) {
					for(Event e: r.getEvents()) {
						// Check consistency
						if(e.getPartecipants() > r.getCapacity()) {
							throw new RuntimeException("Partecipati > capacità");
						}
						solution.append(e.getTopic());
						solution.append(" ");
					}
				}
				bufferedWriter.append(solution);
				bufferedWriter.append("\r\n");
			}
			System.out.println("Soluzion wrote to: " + outputFile);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				bufferedWriter.close();
				fileWriter.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void evaluateSolution(){
		long value = 0;
		for(Room r : rooms) {
			if(r.getEvents() == null) {
				continue;
			}
			for(Event e : r.getEvents()) {
				value = e.getPartecipants() * (e.getEndTime() - e.getStartTime()) / r.getCapacity();
			}
		}
		System.out.println("Score: " + value);
	}
}
