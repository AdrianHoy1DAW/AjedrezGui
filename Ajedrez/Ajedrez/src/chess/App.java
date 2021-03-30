package chess;

import java.awt.EventQueue;
import java.util.Scanner;



import controlador.ControladorJuego;

import modelo.Player;
import vista.VistaChess;

public class App {

		


		/**
		 * Launch the application.
		 */
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						VistaChess frame = new VistaChess();
						ControladorJuego c = new ControladorJuego(frame);
						c.go();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		
	
		
		
		
		
	}

