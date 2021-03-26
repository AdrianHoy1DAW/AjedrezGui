package test;


import entrada.Coordenada;
import entrada.Herramientas;
import modelo.Color;
import modelo.Pawn;
import modelo.Pieza;
import modelo.Tablero;
import modelo.Tipo;


public class test {

	public static void main(String[] args) {
		
		Coordenada origen = new Coordenada('A',7);
		Coordenada destino = new Coordenada('A',8);
		Coordenada origen2 = new Coordenada('A', 2);
		Coordenada destino2 = new Coordenada('A', 1);
		Coordenada destino3 = new Coordenada('D', 2);
		Tablero t = new Tablero();
		Pieza p = new Pawn(Color.WHITE,new Coordenada('A',2),t);

		
		
//		System.out.println(t.getCelda(c).getPieza().getNextMoves());
		
			
		
		
		

	}

}
