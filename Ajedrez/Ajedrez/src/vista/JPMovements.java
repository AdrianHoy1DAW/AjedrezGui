package vista;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import modelo.Movement;
import net.miginfocom.swing.MigLayout;
import javax.swing.JList;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class JPMovements extends JPanel {
	private JList<Movement> list;
	private JButton buttonAtras;
	private JButton btnDelante;

	/**
	 * Create the panel.
	 */
	public JPMovements() {
		setBorder(new TitledBorder(null, "Movements", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new MigLayout("", "[grow]", "[222.00][grow]"));
		
		list = new JList<>();
		add(list, "cell 0 0,grow");
		
		JPanel panel = new JPanel();
		add(panel, "cell 0 1,grow");
		panel.setLayout(new MigLayout("", "[][][][][][][]", "[]"));
		
		buttonAtras = new JButton("<");
		panel.add(buttonAtras, "cell 0 0");
		
		btnDelante = new JButton(">");
		panel.add(btnDelante, "cell 6 0");

	}

	public JList<Movement> getList() {
		return list;
	}

	public JButton getButtonAtras() {
		return buttonAtras;
	}

	public JButton getBtnDelante() {
		return btnDelante;
	}
	
	

}
