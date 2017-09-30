package it.reply.challenge.fantabosco;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Logger;

import org.jgrapht.WeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

public class FileManager {
	
	private final static Logger LOGGER = Logger.getLogger(FileManager.class.getName());
	
	/**
	 * Reads a file having in the first row the 
	 * dimensions of the matrix and then the matrix itself
	 * 
	 * @param fileName
	 * @return a matrix of cells that can be in a full or empty state
	 * @throws IOException if there was a problem while reading the file
	 */
	public Area readMappedArea(String fileName) throws IOException {
		int row = 0;
		int rows = 0;
		int col = 0;
		int cols = 0;
		Area area = null;
		BufferedReader bufferedReader = null;
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(fileName);
			bufferedReader = new BufferedReader(fileReader);
			String currentLine;
			while ((currentLine = bufferedReader.readLine()) != null) {
				String[] splittedLine = currentLine.split(" ");
				// First row containing matrix dimension
				if (row == 0) {
					if (splittedLine.length == 2) {
						rows = Integer.valueOf(splittedLine[0]);
						cols = Integer.valueOf(splittedLine[1]);
						area = new Area(rows, cols);
					}
					else {
						throw new IOException();
					}
				}
				// The rest of the file contains the matrix
				else {
					if (splittedLine.length == cols) {
						col = 0;
						Cell[][] matrix = area.getMatrix();
						for (String value : splittedLine) {
							int intValue = Integer.valueOf(value);
							Cell cell = new Cell(CellType.getEnumValue(intValue));
							matrix[row - 1][col] = cell;
							col++;
						}
					}
					else {
						throw new IOException();
					}
				}
				row++;
			}
		} catch (Exception e) {
			LOGGER.severe("Error while reading file");
			throw new IOException();
		} finally {
			try {
				if (bufferedReader != null)
					bufferedReader.close();
				if (fileReader != null)
					fileReader.close();
			} catch (IOException ex) {
				LOGGER.severe("Error while closing file");
				throw new IOException();
			}
		}
		return area;
	}
	
	/**
	 * Reads a file having on each line an edge and its weight
	 * 
	 * @param fileName
	 * @return a weighted graph
	 * @throws IOException if there was a problem while reading the file
	 */
	
	public WeightedGraph<Node, DefaultWeightedEdge> readWeightedGraph(String fileName) throws IOException {
		WeightedGraph<Node, DefaultWeightedEdge> graph = new SimpleWeightedGraph<Node, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		BufferedReader bufferedReader = null;
		FileReader fileReader = null;
		HashMap<String, Node> addedNodes = new HashMap<String, Node>();
		try {
			fileReader = new FileReader(fileName);
			bufferedReader = new BufferedReader(fileReader);
			String currentLine;
			while ((currentLine = bufferedReader.readLine()) != null) {
				String[] splittedLine = currentLine.split(" ");
				if (splittedLine.length == 3) {
					Node node1 = new Node(splittedLine[0], NodeState.NOT_VISITED);
					Node node2 = new Node(splittedLine[1], NodeState.NOT_VISITED);
					int weight = Integer.valueOf(splittedLine[2]);
					if (!addedNodes.containsKey(node1.getName())) {
						graph.addVertex(node1);
						addedNodes.put(node1.getName(), node1);
					}
					else {
						node1 = addedNodes.get(node1.getName());
					}
					if (!addedNodes.containsKey(node2.getName())) {
						graph.addVertex(node2);
						addedNodes.put(node2.getName(), node2);
					}
					else {
						node2 = addedNodes.get(node2.getName());
					}
					DefaultWeightedEdge edge = graph.addEdge(node1, node2);
					graph.setEdgeWeight(edge, weight);
				}
				else {
					throw new IOException();
				}
			}
		} catch (Exception e) {
			LOGGER.severe("Error while reading file");
			throw new IOException();
		} finally {
			try {
				if (bufferedReader != null)
					bufferedReader.close();
				if (fileReader != null)
					fileReader.close();
			} catch (IOException ex) {
				LOGGER.severe("Error while closing file");
				throw new IOException();
			}
		}
		return graph;
	}
}
