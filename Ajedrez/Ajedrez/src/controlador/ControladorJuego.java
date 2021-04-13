package controlador;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JColorChooser;

import configuracion.MyConfig;
import entrada.Coordenada;
import entrada.Herramientas;
import modelo.Celda;
import modelo.Color;
import modelo.Pieza;
import modelo.Player;
import modelo.Tablero;
import vista.VistaChess;
import vista.VistaPropiedades;

public class ControladorJuego implements ActionListener{


	
	private Color turno;
	private VistaChess vista = new VistaChess();
	private VistaPropiedades propiedades;
	private Pieza piezaSeleccionada;
	
	
	public ControladorJuego(VistaChess vista) {

		
		
		this.vista = vista;
		
		inicializar();
	}
	
	private void inicializar() {
		
		turno = Color.WHITE;
		
		Component[] components = vista.getPanelTablero().getComponents();
		
		for(Component component : components) {
			
			if(component instanceof Celda) {
				((Celda) component).addActionListener(this);
			}
			
		}
		
		vista.getMntmProperties().addActionListener(this);
		
		vista.getMntmProperties().setActionCommand("Abrir preferencias");
		
	}
	

	
	public void go() {
		
		vista.setVisible(true);
//		while(tablero.contieneReyBlanco() == false && tablero.contieneReyNegro() == false) {
//			switch(turno) {
//			case WHITE:
//				turnoJugador(Color.WHITE);
//				Herramientas.clear();
//				if(tablero.whiteCheck() == true) {
//					Herramientas.Mensaje("El rey negro esta en jaque");
//				}
//				break;
//			case BLACK:
//				turnoJugador(Color.BLACK);
//				Herramientas.clear();
//				if(tablero.blackCheck() == true) {
//					Herramientas.Mensaje("El rey blanco esta en jaque");
//				}
//				break;
//			}
//		}
//		if(tablero.contieneReyBlanco() == false) {
//			Herramientas.Mensaje("Las blancas ganan");
//			System.out.println(tablero.Print(turno));
//		} else {
//			Herramientas.Mensaje("Las negras ganan");
//			System.out.println(tablero.Print(turno));
//		}
//		
	}
//	
//	public void turnoJugador(Color color) {
//		Coordenada coordenada = null;
//		Coordenada destino = null;
//		boolean sePuedeMover = false;
//		
//		while(sePuedeMover == false) {
//			System.out.println(tablero.Print(turno));
//			Herramientas.Mensaje("Qué ficha quieres mover");
//			coordenada = Herramientas.obtenerCoordenada();
//			if(tablero.getCelda(coordenada).contienePieza() == true) {
//				if(tablero.getCelda(coordenada).getPieza().getColor() == turno) {
//					if(tablero.getCelda(coordenada).getPieza().canMove() == true) {
//						sePuedeMover = true;
//					} else {
//						Herramientas.clear();
//						Herramientas.Mensaje("Esa ficha no se puede mover");
//					}
//				} else {
//					Herramientas.clear();
//					Herramientas.Mensaje("Esa ficha no es de tu color");
//				} 
//			}	else {
//				Herramientas.clear();
//				Herramientas.Mensaje("En esa posición no hay una ficha");
//			
//				} 
//
//		}
//		
//		
//		while(turno == color) {
//					Herramientas.Mensaje("A donde la quieres mover");
//					destino = Herramientas.obtenerCoordenada();
//					if(tablero.getCelda(coordenada).getPieza().cantMoveTo(destino)) {
//						tablero.move(coordenada, destino);
//						CambiarTurno();
//					} else {
//						Herramientas.clear();
//						Herramientas.Mensaje("No se puede mover ahí");
//					}
//			
//					
//			} 
//		}
	
	
	
	
	private void CambiarTurno() {
		turno = Color.values()[(turno.ordinal() +1) % Color.values().length];
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String comando = arg0.getActionCommand();
		
		if(comando.equals("Abrir preferencias")) {
			
			abrirPreferencias();
			
		} else if(comando.equals("Cambiar color celda blanca")) {
			
			cambiarColorCeldaBlanca();
			
		}
		
		
	}

	private void cambiarColorCeldaBlanca() {
		
		java.awt.Color color = JColorChooser.showDialog(propiedades.getBtnColorCeldaBlanca(),"Selecciona un color",propiedades.getBtnColorCeldaBlanca().getBackground());
		
		if(color != null) {
			
			propiedades.getBtnColorCeldaBlanca().setBackground(color);
			MyConfig.getInstance().setWhiteCellColor(color);
			
		}
		
	}

	private void abrirPreferencias() {
		
		propiedades = new VistaPropiedades();
		
		propiedades.setVisible(true);
		
		//Add Action Listener
		propiedades.getBtnColorCeldaBlanca().addActionListener(this);
		propiedades.getBtnColorCeldaNegra().addActionListener(this);
		propiedades.getBtnBordeNormal().addActionListener(this);
		propiedades.getBtnBordeComer().addActionListener(this);
		
		//Add Action Command
		propiedades.getBtnColorCeldaBlanca().setActionCommand("Cambiar color celda blanca");
		
		
	}
	
	
	
	
	
	
	
}
