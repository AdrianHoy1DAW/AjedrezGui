package vista;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import modelo.Movement;
import net.miginfocom.swing.MigLayout;
import javax.swing.JList;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;

public class JPMovements extends JPanel {
	private JButton buttonAtras;
	private JButton btnDelante;
	private JScrollPane scrollPane;
	private JList<Movement> list;

	/**
	 * Create the panel.
	 */
	public JPMovements() {
		setBorder(new TitledBorder(null, "Movements", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new MigLayout("", "[grow]", "[222.00,grow][grow]"));
		
		scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 0,grow");
		
		list = new JList();
		scrollPane.setViewportView(list);
		
		JPanel panel = new JPanel();
		add(panel, "cell 0 1,alignx center,growy");
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
