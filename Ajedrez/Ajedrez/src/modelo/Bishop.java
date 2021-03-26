package modelo;

import entrada.Coordenada;

public class Bishop extends Pieza{

	public Bishop(Color color,Coordenada posicion, Tablero tablero) {
		super(posicion, tablero);
		// TODO Auto-generated constructor stub
		
		if(color == Color.WHITE) {
			tipo = Tipo.WHITE_BISHOP;
		} else {
			tipo = Tipo.BLACK_BISHOP;
		}
	}
	
	public Bishop(Color color) {
		if(color == Color.WHITE) {
			tipo = Tipo.WHITE_BISHOP;
		} else {
			tipo = Tipo.BLACK_BISHOP;
		}
	}

	@Override
	public Lista<Coordenada> getNextMoves() {
		
		return movementAsBishop(tablero, this);
		
	}

	public  static Lista<Coordenada> movementAsBishop(Tablero tablero, Pieza p) {
	
	Lista<Coordenada> lista = new Lista<Coordenada>();
	Coordenada aux = p.posicion;
	
	while(tablero.coordenadaEnTablero(aux.rightUp()) == true && tablero.getCelda(aux.rightUp()).contienePieza() == false) {
		lista.addHead(aux.rightUp());
		aux = aux.rightUp();
	}
	
	if(tablero.coordenadaEnTablero(aux.rightUp()))
			if(tablero.getCelda(aux.rightUp()).getPieza().getColor() != p.getColor())
				lista.addHead(aux.rightUp());
	
	aux = p.posicion;
	
	while(tablero.coordenadaEnTablero(aux.rightDown()) == true && tablero.getCelda(aux.rightDown()).contienePieza() == false) {
		lista.addHead(aux.rightDown());
		aux = aux.rightDown();
	}
	
	if(tablero.coordenadaEnTablero(aux.rightDown()))
			if(tablero.getCelda(aux.rightDown()).getPieza().getColor() != p.getColor())
				lista.addHead(aux.rightDown());
	
	aux = p.posicion;
	
	while(tablero.coordenadaEnTablero(aux.leftDown()) == true && tablero.getCelda(aux.leftDown()).contienePieza() == false) {
		lista.addHead(aux.leftDown());
		aux = aux.leftDown();
	}
	
	if(tablero.coordenadaEnTablero(aux.leftDown()))
			if(tablero.getCelda(aux.leftDown()).getPieza().getColor() != p.getColor())
				lista.addHead(aux.leftDown());
	
	aux = p.posicion;
	
	while(tablero.coordenadaEnTablero(aux.leftUp()) == true && tablero.getCelda(aux.leftUp()).contienePieza() == false) {
		lista.addHead(aux.leftUp());
		aux = aux.leftUp();
	}
	
	if(tablero.coordenadaEnTablero(aux.leftUp()))
			if(tablero.getCelda(aux.leftUp()).getPieza().getColor() != p.getColor())
				lista.addHead(aux.leftUp());
	
	return lista;
	}
	
}
