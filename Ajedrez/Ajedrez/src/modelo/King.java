
package modelo;

import java.util.HashSet;
import java.util.Set;

import entrada.Coordenada;

public class King extends Pieza{

	public King(Color color,Coordenada posicion, JPTablero tablero) {
		super(posicion, tablero);
		// TODO Auto-generated constructor stub
		
		if(color == Color.WHITE) {
			tipo = Tipo.WHITE_KING;
		} else {
			tipo = Tipo.BLACK_KING;
		}
		colocate(posicion);
	}
	


	@Override
	public Set<Coordenada> getNextMoves() {
		
		Set<Coordenada> lista = new HashSet<Coordenada>();
		
		addCoordenada(posicion.up(), lista);
		addCoordenada(posicion.rightUp(), lista);
		addCoordenada(posicion.right(), lista);
		addCoordenada(posicion.rightDown(), lista);
		addCoordenada(posicion.down(), lista);
		addCoordenada(posicion.leftDown(), lista);
		addCoordenada(posicion.left(), lista);
		addCoordenada(posicion.leftUp(), lista);
		
		return lista;
	}

	private void addCoordenada(Coordenada p, Set<Coordenada> lista) {
		if(tablero.coordenadaEnTablero(p)) {
			if(tablero.getCelda(p).contienePieza()) {
				if(tablero.getCelda(p).getPieza().getColor() != getColor()) {
					lista.add(p);
				}
					
			} else {
				lista.add(p);
			}
		}
		
	}
	
}
