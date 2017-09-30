package it.reply.challenge.fantabosco;

public enum CellType {
	EMPTY(0),
	FULL(1);
	
	private int type;
	
	private CellType(int type) {
		this.type = type;
	}
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	public static CellType getEnumValue(int type) {
		for (CellType cellType : CellType.values()) {
			if (type == cellType.getType()) {
				return cellType;
			}
		}
		return null;
	}
}
