package vista;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.FlowLayout;

public class JPFichasEliminadas extends JPanel {
	private JPanel panelBlancas;
	private JPanel panelNegras;

	/**
	 * Create the panel.
	 */
	public JPFichasEliminadas() {
		setLayout(new GridLayout(2, 0, 0, 0));
		
		panelBlancas = new JPFichas("White");
		add(panelBlancas);
		panelBlancas.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		panelNegras = new JPFichas("Black");
		add(panelNegras);
		panelNegras.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

	}

	public JPanel getPanelBlancas() {
		return panelBlancas;
	}

	public JPanel getPanelNegras() {
		return panelNegras;
	}
	
	

}
