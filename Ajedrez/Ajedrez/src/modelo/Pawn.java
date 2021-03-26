package modelo;

import entrada.Coordenada;

public class Pawn extends Pieza{

	public Pawn(Color color,Coordenada posicion, Tablero tablero) {
		super(posicion, tablero);
		// TODO Auto-generated constructor stub
	
		if(color == Color.WHITE) {
			tipo = Tipo.WHITE_PAWN;
		} else {
			tipo = Tipo.BLACK_PAWN;
		}
	}
	
	public Pawn(Color color) {
		super();
		// TODO Auto-generated constructor stub
	
		if(color == Color.WHITE) {
			tipo = Tipo.WHITE_PAWN;
		} else {
			tipo = Tipo.BLACK_PAWN;
		}
	}

	@Override
	public Lista<Coordenada> getNextMoves() {
		
		Lista<Coordenada> lista = new Lista<Coordenada>();
		
		if(this.getColor() == Color.WHITE) {
			if(tablero.coordenadaEnTablero(posicion.up().left()))
				if(tablero.getCelda(posicion.up().left()).contienePieza()) {
					addCoordenada(posicion.up().left(), lista);
				}
			
			if(tablero.coordenadaEnTablero(posicion.up().right())) 
				if (tablero.getCelda(posicion.up().right()).contienePieza()) {
					addCoordenada(posicion.up().right(), lista);
				}
				
				
			if(tablero.coordenadaEnTablero(posicion.up())) {
				if(tablero.getCelda(posicion.up()).contienePieza()) {
					
				} else {
					addCoordenada(posicion.up(), lista);
					if(posicion.getEjeY() == 2) {
						if(tablero.getCelda(posicion.up().up()).contienePieza() == false)
							addCoordenada(posicion.up().up(), lista);
					} 
				}
			}
			

			
			
		}
		
		
		if(this.getColor() == Color.BLACK) {
			if(tablero.coordenadaEnTablero(posicion.down().left()))
				if(tablero.getCelda(posicion.down().left()).contienePieza()) {
					addCoordenada(posicion.down().left(), lista);
				}
			
			if(tablero.coordenadaEnTablero(posicion.down().right()))
				if (tablero.getCelda(posicion.down().right()).contienePieza()) {
					addCoordenada(posicion.down().right(), lista);
				} 
			
			if(posicion.getEjeY() == 7) {
				if(tablero.getCelda(posicion.down().down()).contienePieza() == false)
					addCoordenada(posicion.down().down(), lista);
			} 
			
			if(tablero.coordenadaEnTablero(posicion.down())) {
				if(tablero.getCelda(posicion.down()).contienePieza()) {
					
				} else {
					addCoordenada(posicion.down(), lista);
				}
			}
		}
		
		
		
		
		
		return lista;
	}
	

	
	@Override
	public void move(Coordenada c) {
		super.move(c);
		if(getColor() == Color.WHITE && posicion.getEjeY() == 8) {
			tablero.saveRemovedPiece(this);
			tablero.getBlancas().addHead(new Queen(getColor(),posicion,tablero));
		} else if(getColor() == Color.BLACK && posicion.getEjeY() == 1) {
			tablero.saveRemovedPiece(this);
			tablero.getNegras().addHead(new Queen(getColor(),posicion,tablero));
		}
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
