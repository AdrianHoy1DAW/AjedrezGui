package modelo;

import entrada.Coordenada;

public class Rook extends Pieza {

	public Rook(Color color,Coordenada posicion, Tablero tablero) {
		super(posicion, tablero);
		
		if(color == Color.WHITE) {
			tipo = Tipo.WHITE_ROOK;
		} else {
			tipo = Tipo.BLACK_ROOK;
		}
		
	}
	
	public Rook(Color color) {
		if(color == Color.WHITE) {
			tipo = Tipo.WHITE_ROOK;
		} else {
			tipo = Tipo.BLACK_ROOK;
		}
	}

	@Override
	public Lista<Coordenada> getNextMoves() {
		
		return movementAsRook(tablero,this);

	}
	
	public static Lista<Coordenada> movementAsRook(Tablero tablero, Pieza p) {
		
		Lista<Coordenada> lista = new Lista<Coordenada>();
		Coordenada aux = p.posicion;
		
		while(tablero.coordenadaEnTablero(aux.up()) == true && tablero.getCelda(aux.up()).contienePieza() == false) {
			lista.addHead(aux.up());
			aux = aux.up();
		}
		
		if(tablero.coordenadaEnTablero(aux.up()))
				if(tablero.getCelda(aux.up()).getPieza().getColor() != p.getColor())
					lista.addHead(aux.up());
		
		aux = p.posicion;
		
		while(tablero.coordenadaEnTablero(aux.right()) == true && tablero.getCelda(aux.right()).contienePieza() == false) {
			lista.addHead(aux.right());
			aux = aux.right();
		}
		
		if(tablero.coordenadaEnTablero(aux.right()))
				if(tablero.getCelda(aux.right()).getPieza().getColor() != p.getColor())
					lista.addHead(aux.right());
		
		aux = p.posicion;
		
		while(tablero.coordenadaEnTablero(aux.down()) == true && tablero.getCelda(aux.down()).contienePieza() == false) {
			lista.addHead(aux.down());
			aux = aux.down();
		}
		
		if(tablero.coordenadaEnTablero(aux.down()))
				if(tablero.getCelda(aux.down()).getPieza().getColor() != p.getColor())
					lista.addHead(aux.down());
			
		aux = p.posicion;
		
		while(tablero.coordenadaEnTablero(aux.left()) == true && tablero.getCelda(aux.left()).contienePieza() == false) {
			lista.addHead(aux.left());
			aux = aux.left();
		}
		
		if(tablero.coordenadaEnTablero(aux.left()))
				if(tablero.getCelda(aux.left()).getPieza().getColor() != p.getColor())
					lista.addHead(aux.left());
		
		aux = p.posicion;
		
		return lista;
		
	}
	
	

	
	
}
