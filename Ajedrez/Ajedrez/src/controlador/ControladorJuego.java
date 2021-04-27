package controlador;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

import configuracion.MyConfig;
import entrada.Coordenada;
import entrada.Herramientas;
import modelo.Celda;
import modelo.Color;
import modelo.GestionFichasEliminadas;
import modelo.JPTablero;
import modelo.Movement;

import modelo.Pawn;
import modelo.Pieza;
import modelo.Player;

import vista.JPTurno;
import vista.VistaChess;
import vista.VistaPropiedades;

public class ControladorJuego implements ActionListener, MouseListener{


	

	private VistaChess vista = new VistaChess();
	private VistaPropiedades propiedades;
	private Pieza piezaSeleccionada = null;
	private GestionFichasEliminadas gestionFichasEliminadas;
	private DefaultListModel<Movement> dlm;
	private Deque<Movement> stack;
	
	
	
	
	public ControladorJuego(VistaChess vista) {	
		this.vista = vista;
		
		stack = new ArrayDeque<Movement>();
		
		inicializar();
	}
	
	private void inicializar() {
		
		
		
		gestionFichasEliminadas = new ControladorFichasEliminadas(vista.getPanelEliminadas());
		
		iniciarTablero();
		
		//Añadir los MouseListener
		vista.getPanelMovements().getList().addMouseListener(this);
		
		//Añadir ActionListener
		vista.getMntmProperties().addActionListener(this);
		vista.getPanelMovements().getBtnDelante().addActionListener(this);
		vista.getPanelMovements().getButtonAtras().addActionListener(this);
		vista.getMntmGuardar().addActionListener(this);
		vista.getMntmCargar().addActionListener(this);
		
		//Añadir ActionCommand
		vista.getMntmProperties().setActionCommand("Abrir preferencias");
		vista.getPanelMovements().getBtnDelante().setActionCommand("Next Movement");
		vista.getPanelMovements().getButtonAtras().setActionCommand("Previous Movement");
		vista.getMntmGuardar().setActionCommand("Guardar");
		vista.getMntmCargar().setActionCommand("Cargar");
		
		dlm = new DefaultListModel<Movement>();
		vista.getPanelMovements().getList().setModel(dlm);
		
		
		
	}

	private void iniciarTablero() {
		Component[] components = vista.getPanelTablero().getComponents();
		
		for(Component component : components) {
			
			if(component instanceof Celda) {
				((Celda) component).addActionListener(this);
			}
			
		}
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
			
		} else if(comando.equals("Previous Movement")) {
			
			previousMovement();
			
		} else if(comando.equals("Next Movement")) {
			
			nextMovement();
			
		} else if(comando.equals("Cambiar color borde no comer")) {
			
			cambiarColorBordeNoKill();
			
		} else if(comando.equals("Cambiar color borde comer")) {
			
			cambiarColorBordeKill();
			
		} else if(comando.equals("Guardar")) {
			
			guardarPartida();
			
		} else if(comando.equals("Cargar")) {
			
			open();
			
		}
		
		
	}

	private void guardarPartida() {
		
		JFileChooser jfc = new JFileChooser();
		int opcion = jfc.showSaveDialog(vista);
		
		
		 
		
		if(opcion == JFileChooser.APPROVE_OPTION) {
			
			try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(jfc.getSelectedFile()))) {
				
				oos.writeObject(convertirEnStack());
				oos.writeObject(Movement.getNumero() -1);
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}	
	}
	
	private ArrayDeque<Movement> convertirEnStack() {
		
		int cuenta = dlm.size();
		ArrayDeque<Movement> stack2 = new ArrayDeque<>();
		
		
		for(int i = 0; i < cuenta ; i++) {
			
			stack2.add(dlm.get(i));
			
			
		}
		
		stack2.addAll(stack);
		
		
		return stack2;
		
	}

	private void open() {
		

		JFileChooser jfc = new JFileChooser();
		int opcion = jfc.showOpenDialog(vista);
		int cuenta = 0;
		ArrayDeque<Movement> movi = new ArrayDeque<>();


		
		if(opcion == JFileChooser.APPROVE_OPTION) {
			
			try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(jfc.getSelectedFile()))) {
				

				
				movi = (ArrayDeque<Movement>)ois.readObject();
				avanzar(movi);
				cuenta = (int)ois.readObject();
				retroceder(cuenta);
				
					
			
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		

		
	}
	


	private void retroceder(int cuenta) {
		
		int contador = dlm.size();
		
		
		while(contador > cuenta) {
			previousMovement();
			contador --;
		}
		
	}

	private void avanzar(ArrayDeque<Movement> movi) {
		
		int i = 0;
		int cuenta = movi.size();
				
		while(i < cuenta) {
			try {
				nextMovemente(movi.pop());
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			i ++;
		}
		
	}
		
	

	private void nextMovement() {
		
		try {
			
			Movement m = stack.pop();
			nextMovemente(m);
			
		
		} catch (NoSuchElementException ne) {
		
			JOptionPane.showMessageDialog(vista, "No hay movimientos para avanzar", "Error", JOptionPane.ERROR_MESSAGE);
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(vista, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}

	private void nextMovemente(Movement m) throws Exception {
		dlm.addElement(m);
		Coordenada origen,destino;
		JPTablero tablero = vista.getPanelTablero();

		origen = m.getOrigen();
		destino = m.getDestino();
		
		switch(m.getTipoAccion()) {
		case Movement.NOT_KILL : 
			

			
			
//				vista.getPanelTablero().getCelda(origen).getPieza().move(destino);
			tablero.getCelda(origen).getPieza().setPosicion(destino);
			tablero.getCelda(destino).setPieza(tablero.getCelda(origen).getPieza());
			tablero.getCelda(origen).setPieza(null);
			
			
			break;
		case Movement.KILL :
			
			gestionFichasEliminadas.addPiece(m.getFicha());
			
			tablero.getCelda(origen).getPieza().setPosicion(destino);
			tablero.getCelda(destino).setPieza(tablero.getCelda(origen).getPieza());
			tablero.getCelda(origen).setPieza(null);
			
			
			if(m.getFicha().getColor() == Color.WHITE) {
				tablero.saveRemovedPiece(m.getFicha());
			} else {
				tablero.saveRemovedPiece(m.getFicha());
			}
			
			
			
		break;
		case Movement.RISE :
		
			m.getFichaPeon().setPosicion(null);
			m.getFichaGenerada().setPosicion(destino);
			tablero.getCelda(origen).setPieza(null);
			tablero.getCelda(destino).setPieza(m.getFichaGenerada());
			
			
			if(m.getFichaPeon().getColor() == Color.WHITE) {
				tablero.saveRemovedPiece(m.getFichaPeon());
				tablero.getBlancas().add(m.getFichaGenerada());
			} else {
				tablero.saveRemovedPiece(m.getFichaPeon());
				tablero.getNegras().add(m.getFichaGenerada());
			}

			
			
			break;
			
		case Movement.RISE_AND_KILL :
			
			m.getFichaGenerada().setPosicion(destino);
			m.getFichaPeon().setPosicion(null);
			gestionFichasEliminadas.addPiece(m.getFicha());
			tablero.getCelda(origen).setPieza(null);
			tablero.getCelda(destino).setPieza(null);
			tablero.getCelda(destino).setPieza(m.getFichaGenerada());
			
			if(m.getFichaPeon().getColor() == Color.WHITE) {
				tablero.saveRemovedPiece(m.getFichaPeon());
				tablero.getBlancas().add(m.getFichaGenerada());
				tablero.saveRemovedPiece(m.getFicha());
			} else {
				tablero.saveRemovedPiece(m.getFichaPeon());
				tablero.getNegras().add(m.getFichaGenerada());
				tablero.saveRemovedPiece(m.getFicha());
			}
			
			break;
			
		default:throw new Exception("Tipo desconocido");
			
		}
		
		vista.getPanelTurno().cambiarTurno();
		Movement.increaseNumberOfMovements();
		if(piezaSeleccionada != null) {
			desmarcarPosiblesDestinos(tablero);
			piezaSeleccionada = null;
			borrarPiezaSeleccionada();
		}
	}

	private void previousMovement() {
		
		JPTablero tablero = vista.getPanelTablero();

		
		try {
			
			Movement m = dlm.remove(dlm.getSize() -1);
			stack.push(m);
			Coordenada origen,destino;
			
			destino = m.getDestino();
			origen = m.getOrigen();
			
			switch(m.getTipoAccion()) {
			case Movement.NOT_KILL :
				

				
				
//				vista.getPanelTablero().getCelda(destino).getPieza().move(origen);
			
				tablero.getCelda(destino).getPieza().setPosicion(origen);
				tablero.getCelda(origen).setPieza(tablero.getCelda(destino).getPieza());
				tablero.getCelda(destino).setPieza(null);
				
				
				
				
				break;
			case Movement.KILL :
				
		
				tablero.getCelda(destino).getPieza().setPosicion(origen);
				m.getFicha().setPosicion(destino);	
				tablero.getCelda(origen).setPieza(tablero.getCelda(destino).getPieza());
				tablero.getCelda(destino).setPieza(m.getFicha());
				
				
	
				
				gestionFichasEliminadas.removePiece(m.getFicha());
				
				if(m.getFicha().getColor() == Color.WHITE) {
					tablero.getBlancas().add(m.getFicha());
				} else {
					tablero.getNegras().add(m.getFicha());
				}
				
				break;
				
			case Movement.RISE :
				
				m.getFichaPeon().setPosicion(origen);
				m.getFichaGenerada().setPosicion(null);
				
				tablero.getCelda(origen).setPieza(m.getFichaPeon());
				tablero.getCelda(destino).setPieza(null);
				
				if(m.getFichaPeon().getColor() == Color.WHITE) {
					tablero.getBlancas().remove(m.getFichaGenerada());
					tablero.getBlancas().add(m.getFichaPeon());
				} else {
					tablero.getNegras().remove(m.getFichaGenerada());
					tablero.getNegras().add(m.getFichaPeon());
				}

				break;
			case Movement.RISE_AND_KILL :
				
				m.getFichaPeon().setPosicion(origen);
				m.getFicha().setPosicion(destino);
				m.getFichaGenerada().setPosicion(null);
				
				tablero.getCelda(origen).setPieza(m.getFichaPeon());
				tablero.getCelda(destino).setPieza(null);
				tablero.getCelda(destino).setPieza(m.getFicha());
				gestionFichasEliminadas.removePiece(m.getFicha());
				
				if(m.getFichaGenerada().getColor() == Color.WHITE) {
					tablero.getBlancas().remove(m.getFichaGenerada());
					tablero.getBlancas().add(m.getFichaPeon());
					tablero.getNegras().add(m.getFicha());
				} else {
					tablero.getNegras().remove(m.getFichaGenerada());
					tablero.getNegras().add(m.getFichaPeon());
					tablero.getBlancas().add(m.getFicha());
				}
				
				break;
				
			default:throw new Exception("Tipo desconocido");
			
			}
			
			vista.getPanelTurno().cambiarTurno();
			Movement.decreaseNumberOfMovements();
			if(piezaSeleccionada != null) {
				desmarcarPosiblesDestinos(tablero);
				piezaSeleccionada = null;
				borrarPiezaSeleccionada();
			}

			
			
		} catch (ArrayIndexOutOfBoundsException ae) {
			JOptionPane.showMessageDialog(vista, "No hay anteriores movimientos", "Error", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(vista, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
		JPTablero tablero = vista.getPanelTablero();
		Coordenada coor = tablero.getCoordenadaFromCell(c);
		
		if(piezaSeleccionada.getNextMoves().contains(coor) == false) {
			JOptionPane.showMessageDialog(vista, "No puedes mover ahi", "Error", JOptionPane.ERROR_MESSAGE);

		} else {
			Movement m = null;
			
			Coordenada origen = piezaSeleccionada.getPosicion();
			Coordenada destino = tablero.getCoordenadaFromCell(c);
			
			desmarcarPosiblesDestinos(tablero);
			
			// Comprobamos si matamos pieza
			if(c.contienePieza()) {
				// Comprobamos si un peon promociona
				if((tablero.getCoordenadaFromCell(c).getEjeY() == 8 || tablero.getCoordenadaFromCell(c).getEjeY() == 1) && piezaSeleccionada instanceof Pawn) {
					m = new Movement(origen,destino,Movement.RISE_AND_KILL,c.getPieza(),null,piezaSeleccionada);
					
				} else {
					m = new Movement(origen,destino,Movement.KILL,c.getPieza(),null,null);
				}
				gestionFichasEliminadas.addPiece(c.getPieza());
			}
			//Comprobamos si no se ha comido pieza (m != null) y comprobamos si el peon promociona
			if(m == null && (tablero.getCoordenadaFromCell(c).getEjeY() == 8 || tablero.getCoordenadaFromCell(c).getEjeY() == 1) && piezaSeleccionada instanceof Pawn) {
				
				m = new Movement(origen,destino,Movement.RISE,null,null,piezaSeleccionada);
				
				// Comprobamos si no se ha dado ninguna de las condiciones anteriores por lo tanto es un movimiento sin muerte ni promoción
			} else if(m == null) {
				
				m = new Movement(origen,destino,Movement.NOT_KILL,null,null,null);
			}
			//Agragamos el movimiento a la lista
			dlm.addElement(m);
			piezaSeleccionada.move(coor);
			//Comprobamos si ha habido promocion y si la ha habido indicamos en el movimiento el tipo de ficha en la que ha promocionado el peon
			if(m.getTipoAccion() == Movement.RISE || m.getTipoAccion() == Movement.RISE_AND_KILL) {
				m.setFichaGenerada(c.getPieza());
			}
			
			piezaSeleccionada = null;
			borrarPiezaSeleccionada();
			vista.getPanelTurno().cambiarTurno();
			comprobacionesFinales(tablero);
			stack.removeAll(stack);

			
		}
		
	}

	private void desmarcarPosiblesDestinos(JPTablero tablero) {
		for(Coordenada coordenada : piezaSeleccionada.getNextMoves()) {
			tablero.getCelda(coordenada).setBorder(new LineBorder(java.awt.Color.gray,1));
		}
	}

	private void borrarPiezaSeleccionada() {
		
		vista.getPanelTurno().getLblSelectedPiece().setOpaque(false);
		vista.getPanelTurno().getLblSelectedPiece().setIcon(null);
		
	}

	private void comprobacionesFinales(JPTablero tablero) {
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
		JPTablero tablero = vista.getPanelTablero();
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
			vista.getPanelTablero().repaintBoard();
			
		}
		
	}
	
	private void cambiarColorCeldaNegra() {
		
		java.awt.Color color = JColorChooser.showDialog(propiedades.getBtnColorCeldaNegra(), "Selecciona un color", propiedades.getBtnColorCeldaNegra().getBackground());
		
		if(color != null) {
			
			propiedades.getBtnColorCeldaNegra().setBackground(color);
			MyConfig.getInstance().setBlackCellColor(color);
			Celda.colorCeldaNegra = color;
			vista.getPanelTablero().repaintBoard();
			
		}
	}
	
	private void cambiarColorBordeNoKill() {
		
		java.awt.Color color = JColorChooser.showDialog(propiedades.getBtnColorCeldaNegra(), "Selecciona un color", propiedades.getBtnColorCeldaNegra().getBackground());

		if(color != null) {
			
			propiedades.getBtnBordeNormal().setBackground(color);
			MyConfig.getInstance().setBorderNotKill(color);
			Celda.colorBordeCelda = color;
			vista.getPanelTablero().repaintBoard();
			
		}
		
	}
	
	private void cambiarColorBordeKill() {
		
		java.awt.Color color = JColorChooser.showDialog(propiedades.getBtnColorCeldaNegra(), "Selecciona un color", propiedades.getBtnColorCeldaNegra().getBackground());

		if(color != null) {
			
			propiedades.getBtnBordeComer().setBackground(color);
			MyConfig.getInstance().setBorderKill(color);
			Celda.colorBordeCeldaComer = color;
			vista.getPanelTablero().repaintBoard();
			
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
		propiedades.getBtnBordeNormal().setActionCommand("Cambiar color borde no comer");
		propiedades.getBtnBordeComer().setActionCommand("Cambiar color borde comer");
		
		
	}
	
	private void terminarJuego() {
		
		Component[] components = vista.getPanelTablero().getComponents();
		
		for(Component component : components) {
			
			if(component instanceof Celda) {
				((Celda) component).setEnabled(false);
			}
			
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent me) {
		
		Component c = me.getComponent();
		
		if(c == vista.getPanelMovements().getList()) {
			int index = vista.getPanelMovements().getList().getSelectedIndex();
			while(dlm.getSize() > index) {
				previousMovement();
			
			}
		}
		
		
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	
	
	
	
}
