package modelo;

import java.util.HashSet;
import java.util.Set;

import entrada.Coordenada;

public class Knight extends Pieza {

	public Knight(Color color,Coordenada posicion, JPTablero tablero) {
		super(posicion, tablero);
		// TODO Auto-generated constructor stub
		
		if(color == Color.WHITE) {
			tipo = Tipo.WHITE_KNIGHT;
		} else {
			tipo = Tipo.BLACK_KNIGHT;
		}
		colocate(posicion);
	}
	


	@Override
	public Set<Coordenada> getNextMoves() {
		
		Set<Coordenada> lista = new HashSet<Coordenada>();
		
		addCoordenada(posicion.up().up().right(), lista);
		addCoordenada(posicion.up().right().right(), lista);
		addCoordenada(posicion.down().right().right(), lista);
		addCoordenada(posicion.down().down().right(), lista);
		addCoordenada(posicion.down().down().left(), lista);
		addCoordenada(posicion.down().left().left(), lista);
		addCoordenada(posicion.up().left().left(), lista);
		addCoordenada(posicion.up().up().left(), lista);
		
		
		
		return lista;
	}
	
	private void addCoordenada(Coordenada c, Set<Coordenada> lista) {
		if(tablero.coordenadaEnTablero(c)) {
			if(tablero.getCelda(c).contienePieza()) {
				if(tablero.getCelda(c).getPieza().getColor() != getColor()) {
					lista.add(c);
				}
					
			} else {
				lista.add(c);
			}
		}
	}

}
