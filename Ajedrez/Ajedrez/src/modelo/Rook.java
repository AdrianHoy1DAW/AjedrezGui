package modelo;

import java.util.HashSet;
import java.util.Set;

import entrada.Coordenada;

public class Rook extends Pieza {

	public Rook(Color color,Coordenada posicion, Tablero tablero) {
		super(posicion, tablero);
		
		if(color == Color.WHITE) {
			tipo = Tipo.WHITE_ROOK;
		} else {
			tipo = Tipo.BLACK_ROOK;
		}
		colocate(posicion);
		
	}
	


	@Override
	public Set<Coordenada> getNextMoves() {
		
		return movementAsRook(tablero,this);

	}
	
	public static Set<Coordenada> movementAsRook(Tablero tablero, Pieza p) {
		
		Set<Coordenada> lista = new HashSet<Coordenada>();
		Coordenada aux = p.posicion;
		
		while(tablero.coordenadaEnTablero(aux.up()) == true && tablero.getCelda(aux.up()).contienePieza() == false) {
			lista.add(aux.up());
			aux = aux.up();
		}
		
		if(tablero.coordenadaEnTablero(aux.up()))
				if(tablero.getCelda(aux.up()).getPieza().getColor() != p.getColor())
					lista.add(aux.up());
		
		aux = p.posicion;
		
		while(tablero.coordenadaEnTablero(aux.right()) == true && tablero.getCelda(aux.right()).contienePieza() == false) {
			lista.add(aux.right());
			aux = aux.right();
		}
		
		if(tablero.coordenadaEnTablero(aux.right()))
				if(tablero.getCelda(aux.right()).getPieza().getColor() != p.getColor())
					lista.add(aux.right());
		
		aux = p.posicion;
		
		while(tablero.coordenadaEnTablero(aux.down()) == true && tablero.getCelda(aux.down()).contienePieza() == false) {
			lista.add(aux.down());
			aux = aux.down();
		}
		
		if(tablero.coordenadaEnTablero(aux.down()))
				if(tablero.getCelda(aux.down()).getPieza().getColor() != p.getColor())
					lista.add(aux.down());
			
		aux = p.posicion;
		
		while(tablero.coordenadaEnTablero(aux.left()) == true && tablero.getCelda(aux.left()).contienePieza() == false) {
			lista.add(aux.left());
			aux = aux.left();
		}
		
		if(tablero.coordenadaEnTablero(aux.left()))
				if(tablero.getCelda(aux.left()).getPieza().getColor() != p.getColor())
					lista.add(aux.left());
		
		aux = p.posicion;
		
		return lista;
		
	}
	
	

	
	
}
