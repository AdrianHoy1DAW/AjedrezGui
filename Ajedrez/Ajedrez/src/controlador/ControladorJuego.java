package controlador;

import entrada.Coordenada;
import entrada.Herramientas;
import modelo.Color;

import modelo.Player;
import modelo.Tablero;

public class ControladorJuego {

	private Player player1;
	private Player player2;
	private Tablero tablero;
	private Color turno;
	
	
	public ControladorJuego(Player playerWhite, Player playerBlack) {
		super();
		this.player1 = playerWhite;
		this.player2 = playerBlack;
		tablero = new Tablero();
		turno = Color.WHITE;
	}
	

	
	public void go() {
		
		while(tablero.contieneReyBlanco() == false && tablero.contieneReyNegro() == false) {
			switch(turno) {
			case WHITE:
				turnoJugador(Color.WHITE);
				Herramientas.clear();
				if(tablero.whiteCheck() == true) {
					Herramientas.Mensaje("El rey negro esta en jaque");
				}
				break;
			case BLACK:
				turnoJugador(Color.BLACK);
				Herramientas.clear();
				if(tablero.blackCheck() == true) {
					Herramientas.Mensaje("El rey blanco esta en jaque");
				}
				break;
			}
		}
		if(tablero.contieneReyBlanco() == false) {
			Herramientas.Mensaje("Las blancas ganan");
			System.out.println(tablero.Print(turno));
		} else {
			Herramientas.Mensaje("Las negras ganan");
			System.out.println(tablero.Print(turno));
		}
		
	}
	
	public void turnoJugador(Color color) {
		Coordenada coordenada = null;
		Coordenada destino = null;
		boolean sePuedeMover = false;
		
		while(sePuedeMover == false) {
			System.out.println(tablero.Print(turno));
			Herramientas.Mensaje("Qué ficha quieres mover");
			coordenada = Herramientas.obtenerCoordenada();
			if(tablero.getCelda(coordenada).contienePieza() == true) {
				if(tablero.getCelda(coordenada).getPieza().getColor() == turno) {
					if(tablero.getCelda(coordenada).getPieza().canMove() == true) {
						sePuedeMover = true;
					} else {
						Herramientas.clear();
						Herramientas.Mensaje("Esa ficha no se puede mover");
					}
				} else {
					Herramientas.clear();
					Herramientas.Mensaje("Esa ficha no es de tu color");
				} 
			}	else {
				Herramientas.clear();
				Herramientas.Mensaje("En esa posición no hay una ficha");
			
				} 

		}
		
		
		while(turno == color) {
					Herramientas.Mensaje("A donde la quieres mover");
					destino = Herramientas.obtenerCoordenada();
					if(tablero.getCelda(coordenada).getPieza().cantMoveTo(destino)) {
						tablero.move(coordenada, destino);
						CambiarTurno();
					} else {
						Herramientas.clear();
						Herramientas.Mensaje("No se puede mover ahí");
					}
			
					
			} 
		}
	
	
	private void CambiarTurno() {
		turno = Color.values()[(turno.ordinal() +1) % Color.values().length];
	}
	
	
	
	
	
	
	
}
