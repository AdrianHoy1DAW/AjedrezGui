package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.JPTablero;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridLayout;
import modelo.JPTablero;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.LayoutStyle.ComponentPlacement;

public class VistaChess extends JFrame {

	private JPanel contentPane;
	private JPTablero panelTablero;
	private JMenuItem mntmProperties;
	private JPFichasEliminadas panelEliminadas;
	private JPTurno panelTurno;
	private JPMovements panelMovements;
	



	/**
	 * Create the frame.
	 */
	public VistaChess() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1079, 653);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		mntmProperties = new JMenuItem("Properties");
		mnEdit.add(mntmProperties);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		panelTablero = new JPTablero();
		
		panelEliminadas = new JPFichasEliminadas();
		
		panelTurno = new JPTurno();
		
		panelMovements = new JPMovements();
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panelTablero, GroupLayout.PREFERRED_SIZE, 577, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panelTurno, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(panelMovements, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE))
						.addComponent(panelEliminadas, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap(52, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(panelMovements, 0, 0, Short.MAX_VALUE)
								.addComponent(panelTurno, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE))
							.addGap(21)
							.addComponent(panelEliminadas, GroupLayout.PREFERRED_SIZE, 315, GroupLayout.PREFERRED_SIZE))
						.addComponent(panelTablero, GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}




	public JPTablero getPanelTablero() {
		return panelTablero;
	}




	public JMenuItem getMntmProperties() {
		return mntmProperties;
	}




	public JPFichasEliminadas getPanelEliminadas() {
		return panelEliminadas;
	}




	public JPTurno getPanelTurno() {
		return panelTurno;
	}




	public JPMovements getPanelMovements() {
		return panelMovements;
	}
	
	
	
}
