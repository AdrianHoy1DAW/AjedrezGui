package modelo;

import java.awt.GridLayout;
import java.awt.Rectangle;

import java.util.HashMap;


import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import entrada.Coordenada;




public class Tablero extends JPanel{

	private HashMap<Coordenada,Celda> tablero;
	private Lista<Pieza> blancas;
	private Lista<Pieza> negras;
	private Lista<Pieza> blancasEliminadas;
	private Lista<Pieza> negrasEliminadas;
	private Pieza blackKing;
	private Pieza whiteKing;
	
	public Tablero(){
		super();
		setBounds(new Rectangle(0, 0, 500, 500));
		setLayout(new GridLayout(10, 10, 0, 0));
		
		tablero = new HashMap<Coordenada,Celda>();
		blancas = new Lista<Pieza>();
		negras = new Lista<Pieza>();
		this.blancasEliminadas = new Lista<Pieza>();
		this.negrasEliminadas = new Lista<Pieza>();
		
		inicializar();
	}
	
	private void inicializar() {
		
		for (int fila = 0; fila < 8; fila++) {
			for (int col = 0; col < 8; col++)
				tablero.put(new Coordenada((char)('A'+col),1+fila),new Celda());
		}
		
		System.out.println(tablero);
		
		
		
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
				for(int i = 0; i < 8; i ++) {
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
				for(int i = 0; i < 8; i ++) {
					blancas.addHead(new Pawn(Color.WHITE,new Coordenada((char) ('A' + i),2),this));
				}
			
				addToPanel();
	
				
		
	}
	
	private void addToPanel() {
		
		add(getNewLabel(""));
		for(int i = 0;i<8;i++)
			add(getNewLabel(String.valueOf((char)('A'+i))));
		add(getNewLabel(""));
		
		for(int fil=8;fil>=1;fil--) {
			add(getNewLabel(String.valueOf(fil)));
			for(int col=0;col<8;col++) {
				Celda celda = tablero.get(new Coordenada((char)('A' + col),fil));
				if((fil+col)%2==0)
					celda.setCellBackground(Color.WHITE);
				else 
					celda.setCellBackground(Color.BLACK);
				
				add(celda);
			}
			add(getNewLabel(String.valueOf(fil)));			
		}
		
		
		add(getNewLabel(""));
		for(int i = 0;i<8;i++)
			add(getNewLabel(String.valueOf((char)('A'+i))));
		add(getNewLabel(""));
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
		return tablero.get(coordenada);
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
	
	private JLabel getNewLabel(String text) {
		
		JLabel label = new JLabel(text);
		label.setOpaque(true);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBackground(java.awt.Color.DARK_GRAY);
		label.setForeground(java.awt.Color.WHITE);
		return label;
		
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
