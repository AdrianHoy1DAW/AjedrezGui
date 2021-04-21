
package modelo;

import java.util.HashSet;
import java.util.Set;

import entrada.Coordenada;

public abstract class Pieza {

	protected Tipo tipo;
	protected JPTablero tablero;
	protected Coordenada posicion;

	
	public Pieza(Coordenada posicion, JPTablero tablero) {
		super();
		this.posicion = posicion;
		this.tablero = tablero;
		
	}
	

	protected void colocate(Coordenada c) {
		
		tablero.getCelda(posicion).setPieza(null);
		posicion = c;
		tablero.getCelda(posicion).setPieza(this);
		
	}
	
	public boolean cantMoveTo(Coordenada c) {
		
		return getNextMoves().contains(c);
		
	}
	
	public boolean canMove() {
		
		if(getNextMoves().isEmpty()) {
			return false;
		} else {
			return true;
		}
		
	}
	
	public void move(Coordenada c) {
		
		if(tablero.getCelda(c).contienePieza()) 
			tablero.saveRemovedPiece(tablero.getCelda(c).getPieza());
			
			

		colocate(c);
		
			
			
			
		
		
	}
	
	
	
	public Color getColor() {
		return tipo.getColor();
	}
	
	


	public Coordenada getPosicion() {
		return posicion;
	}
	
	
	
	
	public Tipo getTipo() {
		return tipo;
	}

//	@Override
//	public boolean equals(Object o) {
//		if(o instanceof Pieza) {
//			Pieza p = (Pieza) o;
//			if(tipo == p.tipo && posicion.equals(p.posicion)) {
//				return true;
//			}
//		} else {
//			return false;
//		}
//		return false;
//	}
	
	
	

	@Override
	public String toString() {
		return tipo.getForma();
	}
	
	public void setPosicion(Coordenada posicion) {
		this.posicion = posicion;
	}


	public abstract Set<Coordenada> getNextMoves();

	
	
	
	
	
}
