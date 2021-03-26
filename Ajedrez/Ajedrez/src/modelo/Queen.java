package modelo;

import entrada.Coordenada;

public class Queen extends Pieza {

	public Queen(Color color,Coordenada posicion, Tablero tablero) {
		super(posicion, tablero);
		// TODO Auto-generated constructor stub
		if(color == Color.WHITE) {
			tipo = Tipo.WHITE_QUEEN;
		} else {
			tipo = Tipo.BLACK_QUEEN;
		}
	
	}
	
	public Queen(Color color) {
		if(color == Color.WHITE) {
			tipo = Tipo.WHITE_QUEEN;
		} else {
			tipo = Tipo.BLACK_QUEEN;
		}
	}

	@Override
	public Lista<Coordenada> getNextMoves() {
		
		return Rook.movementAsRook(tablero, this).juntarListas(Bishop.movementAsBishop(tablero, this));	
		
	}
	



	
}
