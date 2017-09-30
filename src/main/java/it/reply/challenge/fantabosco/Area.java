package it.reply.challenge.fantabosco;

public class Area {
	
	private int rows;
	private int cols;
	private Cell[][] matrix;
	
	public Area(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		matrix = new Cell[rows][cols];
		for (int i = 0; i < cols; i++) {
			matrix[i] = new Cell[cols];
		}
	}
	
	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getCols() {
		return cols;
	}

	public void setCols(int cols) {
		this.cols = cols;
	}
	
	public Cell[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(Cell[][] matrix) {
		this.matrix = matrix;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append((System.getProperty("line.separator")));
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				buffer.append(matrix[i][j]);
				buffer.append(" ");
			}
			buffer.append((System.getProperty("line.separator")));
		}
		return buffer.toString();
	}
}
