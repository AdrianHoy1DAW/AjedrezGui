package modelo;

import java.util.HashSet;
import java.util.Set;

import entrada.Coordenada;

public class Queen extends Pieza {

	public Queen(Color color,Coordenada posicion, JPTablero tablero) {
		super(posicion, tablero);
		// TODO Auto-generated constructor stub
		if(color == Color.WHITE) {
			tipo = Tipo.WHITE_QUEEN;
		} else {
			tipo = Tipo.BLACK_QUEEN;
		}
		colocate(posicion);
	
	}
	


	@Override
	public Set<Coordenada> getNextMoves() {
		
		Set<Coordenada> movimientos = new HashSet<Coordenada>(Rook.movementAsRook(tablero, this));
		
		movimientos.addAll(Bishop.movementAsBishop(tablero, this));
		
		return movimientos;
		
	}
	



	
}
