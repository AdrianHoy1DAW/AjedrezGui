package modelo;

public enum Tipo {

	WHITE_KING(Color.WHITE,"\u2654"),
	WHITE_QUEEN(Color.WHITE,"\u2655"),
	WHITE_BISHOP(Color.WHITE,"\u2657"),
	WHITE_KNIGHT(Color.WHITE,"\u2658"),
	WHITE_ROOK(Color.WHITE,"\u2656"),
	WHITE_PAWN(Color.WHITE,"\u2659"),
	BLACK_KING(Color.BLACK,"\u265A"),
	BLACK_QUEEN(Color.BLACK,"\u265B"),
	BLACK_BISHOP(Color.BLACK,"\u265D"),
	BLACK_KNIGHT(Color.BLACK,"\u265E"),
	BLACK_ROOK(Color.BLACK,"\u265C"),
	BLACK_PAWN(Color.BLACK,"\u265F");
	
	
	
	private Color color;
	private String forma;
	
	Tipo(Color color, String forma) {
		this.color = color;
		this.forma = forma;
	}

	public Color getColor() {
		return color;
	}

	public String getForma() {
		return forma;
	}

	
	
}
