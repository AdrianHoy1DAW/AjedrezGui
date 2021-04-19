package vista;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import modelo.Celda;
import modelo.Color;

import java.awt.GridLayout;
import net.miginfocom.swing.MigLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class JPTurno extends JPanel {
	private JLabel lblSelectedPiece;
	private JLabel lblTurno;
	private Color turno = Color.WHITE;


	public JPTurno() {
		setBorder(new TitledBorder(null, "TURN", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel panel_1 = new JPanel();
		add(panel_1);
		panel_1.setLayout(new MigLayout("", "[grow]", "[][][66.00,grow]"));
		
		JLabel lblMove = new JLabel("Move");
		panel_1.add(lblMove, "cell 0 0,alignx center");
		
		lblTurno = new JLabel("");
		panel_1.add(lblTurno, "cell 0 2,alignx center");
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new MigLayout("", "[grow]", "[][grow]"));
		
		JLabel lblSelected = new JLabel("Selected");
		panel.add(lblSelected, "cell 0 0,alignx center");
		
		lblSelectedPiece = new JLabel("");
		panel.add(lblSelectedPiece, "cell 0 1,alignx center");
		lblTurno.setIcon(new ImageIcon(Celda.class.getResource("/media/b_peon.gif")));
		
		

	}
	
	public Color getTurno() {
		
		return turno;
		
	}
	
	public void cambiarTurno() {
		
		turno = Color.values()[(turno.ordinal() +1) % Color.values().length];
		if(turno == Color.WHITE) {
			lblTurno.setIcon(new ImageIcon(Celda.class.getResource("/media/b_peon.gif")));
		} else {
			lblTurno.setIcon(new ImageIcon(Celda.class.getResource("/media/n_peon.gif")));
		}
		
	}

	public JLabel getLblSelectedPiece() {
		return lblSelectedPiece;
	}

	public JLabel getLblTurno() {
		return lblTurno;
	}
	
	
	

}
