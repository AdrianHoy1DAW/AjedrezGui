package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import configuracion.MyConfig;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VistaPropiedades extends JFrame {

	private JPanel contentPane;
	private JButton btnColorCeldaNegra;
	private JButton btnColorCeldaBlanca;
	private JButton btnBordeNormal;
	private JButton btnBordeComer;



	/**
	 * Create the frame.
	 */
	public VistaPropiedades() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 275);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][][][][]", "[][31.00][31.00][30.00][30.00][][][]"));
		
		JLabel lblColorCeldaBlanca = new JLabel("Color Celda Blanca");
		contentPane.add(lblColorCeldaBlanca, "cell 0 1");
		
		btnColorCeldaBlanca = new JButton("Personalizar");
		btnColorCeldaBlanca.setBackground(new java.awt.Color(MyConfig.getInstance().getWhiteCellColor()));
		contentPane.add(btnColorCeldaBlanca, "cell 1 1");
		
		JLabel lblColorCeldaNegra = new JLabel("Color Celda Negra");
		
		contentPane.add(lblColorCeldaNegra, "cell 0 2");
		
		btnColorCeldaNegra = new JButton("Personalizar");
		btnColorCeldaNegra.setBackground(new java.awt.Color(MyConfig.getInstance().getBlackCellColor()));
		contentPane.add(btnColorCeldaNegra, "cell 1 2");
		
		JLabel lblColorBordeCelda = new JLabel("Color Borde Celda");
		contentPane.add(lblColorBordeCelda, "cell 0 3");
		
		btnBordeNormal = new JButton("Personalizar");
		btnBordeNormal.setBackground(new java.awt.Color(MyConfig.getInstance().getBorderColorYellow()));
		contentPane.add(btnBordeNormal, "cell 1 3");
		
		JLabel lblColorBordeCelda_1 = new JLabel("Color Borde Celda Comer");
		contentPane.add(lblColorBordeCelda_1, "cell 0 4");
		
		btnBordeComer = new JButton("Personalizar");
		btnBordeComer.setBackground(new java.awt.Color(MyConfig.getInstance().getBorderColorRed()));
		contentPane.add(btnBordeComer, "cell 1 4");
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		contentPane.add(btnCerrar, "cell 5 7");
	}



	public JButton getBtnColorCeldaNegra() {
		return btnColorCeldaNegra;
	}



	public JButton getBtnColorCeldaBlanca() {
		return btnColorCeldaBlanca;
	}



	public JButton getBtnBordeNormal() {
		return btnBordeNormal;
	}



	public JButton getBtnBordeComer() {
		return btnBordeComer;
	}
	
	

}
