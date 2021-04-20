package modelo;

import java.util.HashSet;
import java.util.Set;

import entrada.Coordenada;

public class Bishop extends Pieza{

	public Bishop(Color color,Coordenada posicion, JPTablero tablero) {
		super(posicion, tablero);
		// TODO Auto-generated constructor stub
		
		if(color == Color.WHITE) {
			tipo = Tipo.WHITE_BISHOP;
		} else {
			tipo = Tipo.BLACK_BISHOP;
		}
		colocate(posicion);
	}
	
	


	@Override
	public Set<Coordenada> getNextMoves() {
		
		return movementAsBishop(tablero, this);
		
	}

	public  static Set<Coordenada> movementAsBishop(JPTablero tablero, Pieza p) {
	
	Set<Coordenada> lista = new HashSet<Coordenada>();
	Coordenada aux = p.posicion;
	
	while(tablero.coordenadaEnTablero(aux.rightUp()) == true && tablero.getCelda(aux.rightUp()).contienePieza() == false) {
		lista.add(aux.rightUp());
		aux = aux.rightUp();
	}
	
	if(tablero.coordenadaEnTablero(aux.rightUp()))
			if(tablero.getCelda(aux.rightUp()).getPieza().getColor() != p.getColor())
				lista.add(aux.rightUp());
	
	aux = p.posicion;
	
	while(tablero.coordenadaEnTablero(aux.rightDown()) == true && tablero.getCelda(aux.rightDown()).contienePieza() == false) {
		lista.add(aux.rightDown());
		aux = aux.rightDown();
	}
	
	if(tablero.coordenadaEnTablero(aux.rightDown()))
			if(tablero.getCelda(aux.rightDown()).getPieza().getColor() != p.getColor())
				lista.add(aux.rightDown());
	
	aux = p.posicion;
	
	while(tablero.coordenadaEnTablero(aux.leftDown()) == true && tablero.getCelda(aux.leftDown()).contienePieza() == false) {
		lista.add(aux.leftDown());
		aux = aux.leftDown();
	}
	
	if(tablero.coordenadaEnTablero(aux.leftDown()))
			if(tablero.getCelda(aux.leftDown()).getPieza().getColor() != p.getColor())
				lista.add(aux.leftDown());
	
	aux = p.posicion;
	
	while(tablero.coordenadaEnTablero(aux.leftUp()) == true && tablero.getCelda(aux.leftUp()).contienePieza() == false) {
		lista.add(aux.leftUp());
		aux = aux.leftUp();
	}
	
	if(tablero.coordenadaEnTablero(aux.leftUp()))
			if(tablero.getCelda(aux.leftUp()).getPieza().getColor() != p.getColor())
				lista.add(aux.leftUp());
	
	return lista;
	}
	
}
