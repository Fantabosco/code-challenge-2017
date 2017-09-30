package it.reply.challenge.fantabosco;

public class Cell {
	
	private CellType type;
	
	public Cell(CellType type) {
		this.type = type;
	}
	
	public CellType getType() {
		return type;
	}

	public void setType(CellType type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return Integer.toString(type.getType());
	}
}
