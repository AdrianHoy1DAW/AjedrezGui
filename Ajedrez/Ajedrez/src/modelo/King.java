
package modelo;

import entrada.Coordenada;

public class King extends Pieza{

	public King(Color color,Coordenada posicion, Tablero tablero) {
		super(posicion, tablero);
		// TODO Auto-generated constructor stub
		
		if(color == Color.WHITE) {
			tipo = Tipo.WHITE_KING;
		} else {
			tipo = Tipo.BLACK_KING;
		}
	}
	
	public King(Color color) {
		if(color == Color.WHITE) {
			tipo = Tipo.WHITE_KING;
		} else {
			tipo = Tipo.BLACK_KING;
		}
	}

	@Override
	public Lista<Coordenada> getNextMoves() {
		
		Lista<Coordenada> lista = new Lista<Coordenada>();
		
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

	private void addCoordenada(Coordenada p, Lista<Coordenada> lista) {
		if(tablero.coordenadaEnTablero(p)) {
			if(tablero.getCelda(p).contienePieza()) {
				if(tablero.getCelda(p).getPieza().getColor() != getColor()) {
					lista.addHead(p);
				}
					
			} else {
				lista.addHead(p);
			}
		}
		
	}
	
}
