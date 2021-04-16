package controlador;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import modelo.Celda;
import modelo.Color;
import modelo.GestionFichasEliminadas;
import modelo.Pieza;
import vista.JPFichasEliminadas;
import vista.VistaChess;

public class ControladorFichasEliminadas implements GestionFichasEliminadas {

	private JPFichasEliminadas vista;
	private HashMap<Pieza,JLabel> fichasEliminadas;
	
	public ControladorFichasEliminadas(JPFichasEliminadas panel) {
		
		vista = panel;
		
		fichasEliminadas = new HashMap<>();
		
	}
	
	
	
	@Override
	public void addPiece(Pieza ficha) {
		
		if(ficha.getColor() == Color.WHITE) {
			
			add(ficha,vista.getPanelBlancas());
			
		} else {
			
			add(ficha,vista.getPanelNegras());
			
		}
		
		
	}

	@Override
	public void removePiece(Pieza ficha) {
		
		
		
	}
	
	private void add(Pieza ficha, JPanel panel) {
		
		JLabel label = new JLabel();
		label.setOpaque(true);
		
		Image image = (new ImageIcon(Celda.class.getResource("/media/" + ficha.getTipo().getForma())).getImage());
		ImageIcon imageIconResized = new ImageIcon(getScaledImage(image,25));
		label.setIcon(imageIconResized);
		
		panel.add(label);

		fichasEliminadas.put(ficha, label);

		
	}
	
	private Image getScaledImage(Image srcImg, int size){
		int h = size, w = size;
		
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = resizedImg.createGraphics();

	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(srcImg, 0, 0, w, h, null);
	    g2.dispose();

	    return resizedImg;
	}

}
