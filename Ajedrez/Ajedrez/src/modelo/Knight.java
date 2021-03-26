package modelo;

import entrada.Coordenada;

public class Knight extends Pieza {

	public Knight(Color color,Coordenada posicion, Tablero tablero) {
		super(posicion, tablero);
		// TODO Auto-generated constructor stub
		
		if(color == Color.WHITE) {
			tipo = Tipo.WHITE_KNIGHT;
		} else {
			tipo = Tipo.BLACK_KNIGHT;
		}
	}
	
	public Knight(Color color) {
		if(color == Color.WHITE) {
			tipo = Tipo.WHITE_KNIGHT;
		} else {
			tipo = Tipo.BLACK_KNIGHT;
		}
	}

	@Override
	public Lista<Coordenada> getNextMoves() {
		
		Lista<Coordenada> lista = new Lista<Coordenada>();
		
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
	
	private void addCoordenada(Coordenada c, Lista<Coordenada> lista) {
		if(tablero.coordenadaEnTablero(c)) {
			if(tablero.getCelda(c).contienePieza()) {
				if(tablero.getCelda(c).getPieza().getColor() != getColor()) {
					lista.addHead(c);
				}
					
			} else {
				lista.addHead(c);
			}
		}
	}

}
