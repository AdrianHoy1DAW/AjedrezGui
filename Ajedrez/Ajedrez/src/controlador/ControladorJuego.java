package controlador;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

import configuracion.MyConfig;
import entrada.Coordenada;
import entrada.Herramientas;
import modelo.Celda;
import modelo.Color;
import modelo.GestionFichasEliminadas;
import modelo.Movement;

import modelo.Pawn;
import modelo.Pieza;
import modelo.Player;
import modelo.Tablero;
import vista.JPTurno;
import vista.VistaChess;
import vista.VistaPropiedades;

public class ControladorJuego implements ActionListener{


	

	private VistaChess vista = new VistaChess();
	private VistaPropiedades propiedades;
	private Pieza piezaSeleccionada = null;
	private GestionFichasEliminadas gestionFichasEliminadas;
	private DefaultListModel<Movement> dlm;
	
	
	
	public ControladorJuego(VistaChess vista) {	
		this.vista = vista;
		
		inicializar();
	}
	
	private void inicializar() {
		
		
		
		gestionFichasEliminadas = new ControladorFichasEliminadas(vista.getPanelEliminadas());
		
		Component[] components = vista.getPanelTablero().getComponents();
		
		for(Component component : components) {
			
			if(component instanceof Celda) {
				((Celda) component).addActionListener(this);
			}
			
		}
		
		vista.getMntmProperties().addActionListener(this);
		
		vista.getMntmProperties().setActionCommand("Abrir preferencias");
		
		dlm = new DefaultListModel<Movement>();
		vista.getPanelMovements().getList().setModel(dlm);
		
		
		
	}
	

	
	public void go() {
		
		vista.setVisible(true);

	
	}
	
	


	@Override
	public void actionPerformed(ActionEvent arg0) {
		String comando = arg0.getActionCommand();
		
		if(comando.equals("Abrir preferencias")) {
			
			abrirPreferencias();
			
		} else if(comando.equals("Cambiar color celda blanca")) {
			
			cambiarColorCeldaBlanca();
			
		} else if(comando.equals("Cambiar color celda negra")) {
			
			cambiarColorCeldaNegra();
			
		} else if(arg0.getSource() instanceof Celda) {
			
			comprobarMovimiento((Celda)arg0.getSource());
			
		}
		
		
	}

	private void comprobarMovimiento(Celda c) {
		
		if(piezaSeleccionada==null) {
			movimientoSinPiezaSeleccionada(c);
		} else {
			movimientoConPiezaSeleccionada(c);
		}
		
	}

	private void movimientoConPiezaSeleccionada(Celda c) {
		Tablero tablero = (Tablero)vista.getPanelTablero();
		Coordenada coor = tablero.getCoordenadaFromCell(c);
		
		if(piezaSeleccionada.getNextMoves().contains(coor) == false) {
			JOptionPane.showMessageDialog(vista, "No puedes mover ahi", "Error", JOptionPane.ERROR_MESSAGE);

		} else {
			Movement m = null;
			desmarcarPosiblesDestinos(tablero);
			if(c.contienePieza()) {
				if((tablero.getCoordenadaFromCell(c).getEjeY() == 8 || tablero.getCoordenadaFromCell(c).getEjeY() == 1) && piezaSeleccionada instanceof Pawn) {
					m = new Movement(piezaSeleccionada.getPosicion(),tablero.getCoordenadaFromCell(c),Movement.RISE_AND_KILL,c.getPieza(),null,piezaSeleccionada);
					
				} else {
					m = new Movement(piezaSeleccionada.getPosicion(),tablero.getCoordenadaFromCell(c),Movement.KILL,c.getPieza(),null,null);
				}
				gestionFichasEliminadas.addPiece(c.getPieza());
			}
			if(m == null && (tablero.getCoordenadaFromCell(c).getEjeY() == 8 || tablero.getCoordenadaFromCell(c).getEjeY() == 1) && piezaSeleccionada instanceof Pawn) {
				
				m = new Movement(piezaSeleccionada.getPosicion(),tablero.getCoordenadaFromCell(c),Movement.RISE,null,null,piezaSeleccionada);
				
				
			} else if(m == null) {
				
				m = new Movement(piezaSeleccionada.getPosicion(),tablero.getCoordenadaFromCell(c),Movement.NOT_KILL,null,null,null);
			}
			
			dlm.addElement(m);
			piezaSeleccionada.move(coor);
			if(m.getTipoAccion() == Movement.RISE || m.getTipoAccion() == Movement.RISE_AND_KILL) {
				m.setFichaGenerada(c.getPieza());
			}
			
			piezaSeleccionada = null;
			borrarPiezaSeleccionada();
			vista.getPanelTurno().cambiarTurno();
			comprobacionesFinales(tablero);

			
		}
		
	}

	private void desmarcarPosiblesDestinos(Tablero tablero) {
		for(Coordenada coordenada : piezaSeleccionada.getNextMoves()) {
			tablero.getCelda(coordenada).setBorder(new LineBorder(java.awt.Color.gray,1));
		}
	}

	private void borrarPiezaSeleccionada() {
		
		vista.getPanelTurno().getLblSelectedPiece().setOpaque(false);
		vista.getPanelTurno().getLblSelectedPiece().setIcon(null);
		
	}

	private void comprobacionesFinales(Tablero tablero) {
		if(tablero.whiteCheck()) {
			JOptionPane.showMessageDialog(vista, "El rey negro esta en jaque", "Info", JOptionPane.INFORMATION_MESSAGE);

		}  
		if(tablero.blackCheck()) {
			JOptionPane.showMessageDialog(vista, "El rey blanco esta en jaque", "Info", JOptionPane.INFORMATION_MESSAGE);

		}
		if(!tablero.getBlancas().contains(tablero.getWhiteKing())) {
			JOptionPane.showMessageDialog(vista, "Las blancas pierden", "Info", JOptionPane.INFORMATION_MESSAGE);
			terminarJuego();
		} 
		if(!tablero.getNegras().contains(tablero.getBlackKing())) {
			JOptionPane.showMessageDialog(vista, "Las negras pierden", "Info", JOptionPane.INFORMATION_MESSAGE);
			terminarJuego();
		}
	}

	private void movimientoSinPiezaSeleccionada(Celda c) {
		
		if(!c.contienePieza()) {
			JOptionPane.showMessageDialog(vista, "Debes seleccionar una pieza", "Error", JOptionPane.ERROR_MESSAGE);
		} else if(c.getPieza().getColor()!=vista.getPanelTurno().getTurno()) {
			JOptionPane.showMessageDialog(vista, "Debes seleccionar una pieza de tu color", "Error", JOptionPane.ERROR_MESSAGE);
		} else if(c.getPieza().getNextMoves().size()==0) {
			JOptionPane.showMessageDialog(vista, "Esa pieza no la puedes mover", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			piezaSeleccionada = c.getPieza();
			selectPiece();
			posiblesDestinos();
			
		}
		
		

		
	}

	private void selectPiece() {
		
		vista.getPanelTurno().getLblSelectedPiece().setOpaque(true);
		vista.getPanelTurno().getLblSelectedPiece().setIcon(new ImageIcon(Celda.class.getResource("/media/" + piezaSeleccionada.getTipo().getForma())));
		
		
	}

	private void posiblesDestinos() {
		Set<Coordenada> posiblesMovimientos = piezaSeleccionada.getNextMoves();
		Tablero tablero = (Tablero)vista.getPanelTablero();
		for(Coordenada coor : posiblesMovimientos)  {
			Celda celda = tablero.getCelda(coor);
			if(!celda.contienePieza())
				celda.resaltar(Celda.colorBordeCelda, 2);
			else
				celda.resaltar(Celda.colorBordeCeldaComer, 2);
		}
	}

	private void cambiarColorCeldaBlanca() {
		
		java.awt.Color color = JColorChooser.showDialog(propiedades.getBtnColorCeldaBlanca(),"Selecciona un color",propiedades.getBtnColorCeldaBlanca().getBackground());
		
		if(color != null) {
			
			propiedades.getBtnColorCeldaBlanca().setBackground(color);
			MyConfig.getInstance().setWhiteCellColor(color);
			Celda.colorCeldaBlanca = color;
			((Tablero)vista.getPanelTablero()).repaintBoard();
			
		}
		
	}
	
	private void cambiarColorCeldaNegra() {
		
		java.awt.Color color = JColorChooser.showDialog(propiedades.getBtnColorCeldaNegra(), "Selecciona un color", propiedades.getBtnColorCeldaNegra().getBackground());
		
		if(color != null) {
			
			propiedades.getBtnColorCeldaNegra().setBackground(color);
			MyConfig.getInstance().setBlackCellColor(color);
			Celda.colorCeldaNegra = color;
			((Tablero)vista.getPanelTablero()).repaintBoard();
			
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
		propiedades.getBtnColorCeldaNegra().setActionCommand("Cambiar color celda negra");
		
		
	}
	
	private void terminarJuego() {
		
		Component[] components = vista.getPanelTablero().getComponents();
		
		for(Component component : components) {
			
			if(component instanceof Celda) {
				((Celda) component).setEnabled(false);
			}
			
		}
		
	}
	
	
	
	
	
	
	
	
	
}
