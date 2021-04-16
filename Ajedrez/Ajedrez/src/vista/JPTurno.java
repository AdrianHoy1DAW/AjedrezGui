package vista;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import modelo.Color;

import java.awt.GridLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;

public class JPTurno extends JPanel {
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel;
	private Color turno;


	public JPTurno() {
		setBorder(new TitledBorder(null, "TURN", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel panel_1 = new JPanel();
		add(panel_1);
		panel_1.setLayout(new MigLayout("", "[grow]", "[][][66.00,grow]"));
		
		JLabel lblMove = new JLabel("Move");
		panel_1.add(lblMove, "cell 0 0,alignx center");
		
		lblNewLabel = new JLabel("");
		panel_1.add(lblNewLabel, "cell 0 2,alignx center");
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new MigLayout("", "[grow]", "[][grow]"));
		
		JLabel lblSelected = new JLabel("Selected");
		panel.add(lblSelected, "cell 0 0,alignx center");
		
		lblNewLabel_1 = new JLabel("");
		panel.add(lblNewLabel_1, "cell 0 1,alignx center");

	}
	
	public Color getTurno() {
		
		return turno;
		
	}
	
	public void cambiarTurno() {
		
		
		
	}

}
