package modelo;

import entrada.Coordenada;

public class Tablero {

	private Celda[][] tablero;
	private Lista<Pieza> blancas;
	private Lista<Pieza> negras;
	private Lista<Pieza> blancasEliminadas;
	private Lista<Pieza> negrasEliminadas;
	private Pieza blackKing;
	private Pieza whiteKing;
	
	public Tablero(){
		tablero = new Celda[8][8];
		blancas = new Lista<Pieza>();
		negras = new Lista<Pieza>();
		this.blancasEliminadas = new Lista<Pieza>();
		this.negrasEliminadas = new Lista<Pieza>();
		inicializar();
	}
	
	private void inicializar() {
		
		for(int fila = 0; fila < tablero.length; fila ++) {
			for(int col = 0; col < tablero[0].length; col++) {
				tablero[fila][col] = new Celda();
			}
		}
		
		//Colocar las piezas
				negras.addHead(new Rook(Color.BLACK,new Coordenada('A',8),this));
				negras.addHead(new Knight(Color.BLACK,new Coordenada('B',8),this));
				negras.addHead(new Bishop(Color.BLACK,new Coordenada('C',8),this));
				negras.addHead(new Queen(Color.BLACK,new Coordenada('D',8),this));
				blackKing=new King(Color.BLACK,new Coordenada('E',8),this);
				negras.addHead(blackKing);
				negras.addHead(new Bishop(Color.BLACK,new Coordenada('F',8),this));
				negras.addHead(new Knight(Color.BLACK,new Coordenada('G',8),this));
				negras.addHead(new Rook(Color.BLACK,new Coordenada('H',8),this));
				for(int i = 0; i < tablero.length; i ++) {
					negras.addHead(new Pawn(Color.BLACK,new Coordenada((char) ('A' + i),7),this));
			}
			
				
				blancas.addHead(new Rook(Color.WHITE,new Coordenada('A',1),this));
				blancas.addHead(new Knight(Color.WHITE,new Coordenada('B',1),this));
				blancas.addHead(new Bishop(Color.WHITE,new Coordenada('C',1),this));
				blancas.addHead(new Queen(Color.WHITE,new Coordenada('D',1),this));
				whiteKing = new King(Color.WHITE,new Coordenada('E',1),this);
				blancas.addHead(whiteKing);
				blancas.addHead(new Bishop(Color.WHITE,new Coordenada('F',1),this));
				blancas.addHead(new Knight(Color.WHITE,new Coordenada('G',1),this));
				blancas.addHead(new Rook(Color.WHITE,new Coordenada('H',1),this));
				for(int i = 0; i < tablero.length; i ++) {
					blancas.addHead(new Pawn(Color.WHITE,new Coordenada((char) ('A' + i),2),this));
				}
			
	
				
		
	}
	
	public void move(Coordenada origen, Coordenada destino) {
		this.getCelda(origen).getPieza().move(destino);
	}
	
	public boolean coordenadaEnTablero(Coordenada c) {
		return !(c.getEjeX() < 'A' || c.getEjeY() < 1 || c.getEjeX() > 'H' || c.getEjeY() > 8);
	}
	
	public boolean contieneReyBlanco() {
		if(this.getBlancasEliminadas().contains(this.getWhiteKing())) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean contieneReyNegro() {
		
		if(this.getNegrasEliminadas().contains(this.getBlackKing())) {
			return true;
		} else {
			return false;
		}
	}
	

	
	public Celda getCelda(Coordenada coordenada) {
		
		if(this.coordenadaEnTablero(coordenada)) 
			return tablero[8 - coordenada.getEjeY()][coordenada.getEjeX() - 'A'];
		
		return null;
	}
	
	public void saveRemovedPiece(Pieza p) {
		
		if(p.getColor() == Color.WHITE) {
			blancas.getAndRemove(p);
			blancasEliminadas.addHead(p);
			
		} else {
			negras.getAndRemove(p);
			negrasEliminadas.addHead(p);
		}
		
	}
	
	
	
	public String Print(Color color) {
		switch(color) {
		case WHITE: 
			return PrintAsWhite();
			
		case BLACK: 
			return PrintAsBlack();
		default: 
			return "";
			
		}
	}
	
	private String PrintAsBlack() {
		int i = 1;
		String visualizar = "  A   B   C   D   E   F   G   H" + "\n";
		for(int fila = 8; fila > -2; fila --) {
			for(int col = 0; col <tablero[0].length +9; col++) {
				if(fila == -1) {
					visualizar += " ╚═══╧═══╧═══╧═══╧═══╧═══╧═══╧═══╝";
					col = tablero[0].length + 9;
				} else {
				if(fila == 8) {
					visualizar += " ╔═══╤═══╤═══╤═══╤═══╤═══╤═══╤═══╗";
					col = tablero[0].length + 9;
				} else {
					if(col == 0 || col == 16) {
						if(col == 0) {
							visualizar += i;
						}
						visualizar += "║";
					} else if (col%2 == 0){
						visualizar += "│";
					} else {
						if(col == 1) {
							
						}
						visualizar += " " + tablero[fila][col/2] + " ";
					}
					
				}
				
					
				
		
		}
			}
			if(fila == -1 ) {
				visualizar += "";
			} else if(fila == 8 || fila == 0) {
				visualizar += "\n";
			} else {
			
				visualizar += "\n";
				visualizar += " ╟───┼───┼───┼───┼───┼───┼───┼───╢";
				visualizar += "\n";
				i++;
				
			}
			
				
			
		}
		
		
		if(this.whiteCheck() == true ) {
			visualizar += pintarBajoRojoNegro();
		} else if(this.blackCheck() == true) {
			visualizar += pintarBajoRojoBlanco();
		} else {
			visualizar += pintarBajo();
		}
		

		
		return visualizar;
	
	
		
	}
	
	
	
	
	

	private String PrintAsWhite() {
		int i = 8;
		String visualizar = "   A   B   C   D   E   F   G   H" + "\n";
		for(int fila = -1; fila < tablero.length +1; fila ++) {
			for(int col = 0; col <tablero[0].length +9; col++) {
				if(fila == 8) {
					visualizar += " ╚═══╧═══╧═══╧═══╧═══╧═══╧═══╧═══╝";
					col = tablero[0].length + 9;
				} else {
				if(fila == -1) {
					visualizar += " ╔═══╤═══╤═══╤═══╤═══╤═══╤═══╤═══╗";
					col = tablero[0].length + 9;
				} else {
					if(col == 0 || col == 16) {
						if(col == 0) 
							visualizar += i;
						visualizar += "║";
					} else if (col%2 == 0){
						visualizar += "│";
					} else {
						visualizar += " " + tablero[fila][col/2] + " ";
					}
					
				}
				
					
				
		
		}
			}
			if(fila == 8 ) {
				visualizar += "";
			} else if(fila == -1 || fila == 7) {
				visualizar += "\n";
			} else {
			
				visualizar += "\n";
				visualizar += " ╟───┼───┼───┼───┼───┼───┼───┼───╢";
				visualizar += "\n";
				i --;
	
			}
			
			
			
				
			
		}
		
		if(this.whiteCheck() == true ) {
			visualizar += pintarBajoRojoNegro();
		} else if(this.blackCheck() == true) {
			visualizar += pintarBajoRojoBlanco();
		} else {
			visualizar += pintarBajo();
		}
		

		
		
		
		return visualizar;
	
	
		
	}
	
	public String pintarBajo() {
		String pintar = "";
		


		
		pintar = "\n╔═══╤═══╤═══╤═══╤═══╤═══╤═══╤═══╤═══╤═══╤═══╤═══╗ \n";
		for(int i = 0; i <= 24; i++) {
			if(i == 0) {
				pintar += "║";
			} else if(i == 24) {
				pintar += "║\n";
			} else if(i % 2 == 0){
				pintar += "│";
			} else if(i == 1) {
				pintar += " " + new Pawn(Color.WHITE) + " ";	
			} else if(i == 3){
				pintar += " " + new Rook(Color.WHITE) + " ";
			} else if(i == 5) {
				pintar += " " + new Bishop(Color.WHITE) + " ";
			} else if(i == 7) {
				pintar += " " + new Knight(Color.WHITE) + " ";
			} else if(i == 9) {
				pintar += " " + new Queen(Color.WHITE) + " ";
			} else if(i == 11) {
				pintar += " " + new King(Color.WHITE) + " ";
			} else if(i == 13) {
				pintar += " " + new Pawn(Color.BLACK) + " ";
			} else if(i == 15) {
				pintar += " " + new Rook(Color.BLACK) + " ";
			} else if(i == 17) {
				pintar += " " + new Bishop(Color.BLACK) + " ";
			} else if(i == 19) {
				pintar += " " + new Knight(Color.BLACK) + " ";
			} else if(i == 21) {
				pintar += " " + new Queen(Color.BLACK) + " ";
			} else if(i == 23) {
				pintar += " " + new King(Color.BLACK) + " ";
			}
		}
		
		pintar += "╟───┼───┼───┼───┼───┼───┼───┼───┼───┼───┼───┼───╢\n";
		
		
		
		for(int i = 0; i <= 24; i++) {
			if(i == 0) {
				pintar += "║";
			} else if(i == 24) {
				pintar += "║\n";
			} else if(i % 2 == 0){
				pintar += "│";
			}else if(i == 1) {
				pintar += " " + blancas.contar(new Pawn(Color.WHITE)) + " ";	
			} else if(i == 3){
				pintar += " " + blancas.contar(new Rook(Color.WHITE)) + " ";
			} else if(i == 5) {
				pintar += " " + blancas.contar(new Bishop(Color.WHITE)) + " ";
			} else if(i == 7) {
				pintar += " " + blancas.contar(new Knight(Color.WHITE)) + " ";
			} else if(i == 9) {
				pintar += " " + blancas.contar(new Queen(Color.WHITE)) + " ";
			} else if(i == 11) {
				pintar += " " + blancas.contar(new King(Color.WHITE)) + " ";
			} else if(i == 13) {
				pintar += " " +	negras.contar(new Pawn(Color.BLACK)) + " ";
			} else if(i == 15) {
				pintar += " " + negras.contar(new Rook(Color.BLACK)) + " ";
			} else if(i == 17) {
				pintar += " " + negras.contar(new Bishop(Color.BLACK)) + " ";
			} else if(i == 19) {
				pintar += " " + negras.contar(new Knight(Color.BLACK)) + " ";
			} else if(i == 21) {
				pintar += " " + negras.contar(new Queen(Color.BLACK)) + " ";
			} else if(i == 23) {
				pintar += " " + negras.contar(new King(Color.BLACK)) + " ";
			}	
		}
		
		pintar += "╚═══╧═══╧═══╧═══╧═══╧═══╧═══╧═══╧═══╧═══╧═══╧═══╝";
		
		return pintar;
		
		
	}
	
	public String pintarBajoRojoBlanco() {
		String pintar = "";
		


		
		pintar = "\n╔═══╤═══╤═══╤═══╤═══╤═══╤═══╤═══╤═══╤═══╤═══╤═══╗ \n";
		for(int i = 0; i <= 24; i++) {
			if(i == 0) {
				pintar += "║";
			} else if(i == 24) {
				pintar += "║\n";
			} else if(i % 2 == 0){
				pintar += "│";
			} else if(i == 1) {
				pintar += " " + new Pawn(Color.WHITE) + " ";	
			} else if(i == 3){
				pintar += " " + new Rook(Color.WHITE) + " ";
			} else if(i == 5) {
				pintar += " " + new Bishop(Color.WHITE) + " ";
			} else if(i == 7) {
				pintar += " " + new Knight(Color.WHITE) + " ";
			} else if(i == 9) {
				pintar += " " + new Queen(Color.WHITE) + " ";
			} else if(i == 11) {
				pintar += " " + "\u001B[31m" + new King(Color.WHITE) + " " + "\u001B[0m";
			} else if(i == 13) {
				pintar += " " + new Pawn(Color.BLACK) + " ";
			} else if(i == 15) {
				pintar += " " + new Rook(Color.BLACK) + " ";
			} else if(i == 17) {
				pintar += " " + new Bishop(Color.BLACK) + " ";
			} else if(i == 19) {
				pintar += " " + new Knight(Color.BLACK) + " ";
			} else if(i == 21) {
				pintar += " " + new Queen(Color.BLACK) + " ";
			} else if(i == 23) {
				pintar += " " + new King(Color.BLACK) + " ";
			}
		}
		
		pintar += "╟───┼───┼───┼───┼───┼───┼───┼───┼───┼───┼───┼───╢\n";
		
		
		
		for(int i = 0; i <= 24; i++) {
			if(i == 0) {
				pintar += "║";
			} else if(i == 24) {
				pintar += "║\n";
			} else if(i % 2 == 0){
				pintar += "│";
			}else if(i == 1) {
				pintar += " " + blancas.contar(new Pawn(Color.WHITE)) + " ";	
			} else if(i == 3){
				pintar += " " + blancas.contar(new Rook(Color.WHITE)) + " ";
			} else if(i == 5) {
				pintar += " " + blancas.contar(new Bishop(Color.WHITE)) + " ";
			} else if(i == 7) {
				pintar += " " + blancas.contar(new Knight(Color.WHITE)) + " ";
			} else if(i == 9) {
				pintar += " " + blancas.contar(new Queen(Color.WHITE)) + " ";
			} else if(i == 11) {
				pintar += " " + blancas.contar(new King(Color.WHITE)) + " ";
			} else if(i == 13) {
				pintar += " " +	negras.contar(new Pawn(Color.BLACK)) + " ";
			} else if(i == 15) {
				pintar += " " + negras.contar(new Rook(Color.BLACK)) + " ";
			} else if(i == 17) {
				pintar += " " + negras.contar(new Bishop(Color.BLACK)) + " ";
			} else if(i == 19) {
				pintar += " " + negras.contar(new Knight(Color.BLACK)) + " ";
			} else if(i == 21) {
				pintar += " " + negras.contar(new Queen(Color.BLACK)) + " ";
			} else if(i == 23) {
				pintar += " " + negras.contar(new King(Color.BLACK)) + " ";
			}	
		}
		
		pintar += "╚═══╧═══╧═══╧═══╧═══╧═══╧═══╧═══╧═══╧═══╧═══╧═══╝";
		
		return pintar;
		
		
	}
	
	public String pintarBajoRojoNegro() {
		String pintar = "";
		


		
		pintar = "\n╔═══╤═══╤═══╤═══╤═══╤═══╤═══╤═══╤═══╤═══╤═══╤═══╗ \n";
		for(int i = 0; i <= 24; i++) {
			if(i == 0) {
				pintar += "║";
			} else if(i == 24) {
				pintar += "║\n";
			} else if(i % 2 == 0){
				pintar += "│";
			} else if(i == 1) {
				pintar += " " + new Pawn(Color.WHITE) + " ";	
			} else if(i == 3){
				pintar += " " + new Rook(Color.WHITE) + " ";
			} else if(i == 5) {
				pintar += " " + new Bishop(Color.WHITE) + " ";
			} else if(i == 7) {
				pintar += " " + new Knight(Color.WHITE) + " ";
			} else if(i == 9) {
				pintar += " " + new Queen(Color.WHITE) + " ";
			} else if(i == 11) {
				pintar += " "  + new King(Color.WHITE) + " " ;
			} else if(i == 13) {
				pintar += " " + new Pawn(Color.BLACK) + " ";
			} else if(i == 15) {
				pintar += " " + new Rook(Color.BLACK) + " ";
			} else if(i == 17) {
				pintar += " " + new Bishop(Color.BLACK) + " ";
			} else if(i == 19) {
				pintar += " " + new Knight(Color.BLACK) + " ";
			} else if(i == 21) {
				pintar += " " + new Queen(Color.BLACK) + " ";
			} else if(i == 23) {
				pintar += " " + "\u001B[31m" + new King(Color.BLACK) + " " + "\u001B[0m";
			}
		}
		
		pintar += "╟───┼───┼───┼───┼───┼───┼───┼───┼───┼───┼───┼───╢\n";
		
		
		
		for(int i = 0; i <= 24; i++) {
			if(i == 0) {
				pintar += "║";
			} else if(i == 24) {
				pintar += "║\n";
			} else if(i % 2 == 0){
				pintar += "│";
			}else if(i == 1) {
				pintar += " " + blancas.contar(new Pawn(Color.WHITE)) + " ";	
			} else if(i == 3){
				pintar += " " + blancas.contar(new Rook(Color.WHITE)) + " ";
			} else if(i == 5) {
				pintar += " " + blancas.contar(new Bishop(Color.WHITE)) + " ";
			} else if(i == 7) {
				pintar += " " + blancas.contar(new Knight(Color.WHITE)) + " ";
			} else if(i == 9) {
				pintar += " " + blancas.contar(new Queen(Color.WHITE)) + " ";
			} else if(i == 11) {
				pintar += " " + blancas.contar(new King(Color.WHITE)) + " ";
			} else if(i == 13) {
				pintar += " " +	negras.contar(new Pawn(Color.BLACK)) + " ";
			} else if(i == 15) {
				pintar += " " + negras.contar(new Rook(Color.BLACK)) + " ";
			} else if(i == 17) {
				pintar += " " + negras.contar(new Bishop(Color.BLACK)) + " ";
			} else if(i == 19) {
				pintar += " " + negras.contar(new Knight(Color.BLACK)) + " ";
			} else if(i == 21) {
				pintar += " " + negras.contar(new Queen(Color.BLACK)) + " ";
			} else if(i == 23) {
				pintar += " " + negras.contar(new King(Color.BLACK)) + " ";
			}	
		}
		
		pintar += "╚═══╧═══╧═══╧═══╧═══╧═══╧═══╧═══╧═══╧═══╧═══╧═══╝";
		
		return pintar;
		
		
	}
	
	
	
	
	
	public Pieza getBlackKing() {
		return blackKing;
	}

	public Pieza getWhiteKing() {
		return whiteKing;
	}

	public Lista<Pieza> getBlancasEliminadas() {
		return blancasEliminadas;
	}

	public Lista<Pieza> getNegrasEliminadas() {
		return negrasEliminadas;
	}

	public Lista<Pieza> getBlancas() {
		return blancas;
	}

	public Lista<Pieza> getNegras() {
		return negras;
	}

	public void pruebas() {

		
		
	}
	
	
	public boolean blackCheck() {
		
		Lista<Coordenada> coor = new Lista<Coordenada>();
		
		for(int i =0; i < negras.getSize(); i++) {
			coor.juntarListas(negras.getSinBorrar(i).getNextMoves());
			
		}
		if(coor.contains(whiteKing.getPosicion()))
			return true;
		

			return false;
	}
	
	public boolean whiteCheck() {
		
		
		Lista<Coordenada> coor = new Lista<Coordenada>();
		
		for(int i =0; i < blancas.getSize(); i++) {
			coor.juntarListas(blancas.getSinBorrar(i).getNextMoves());
			
		}
		
		
		if(coor.contains(blackKing.getPosicion()))
			return true;
		

			return false;
		
	}
}
