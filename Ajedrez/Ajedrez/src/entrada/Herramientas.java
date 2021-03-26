package entrada;

import java.util.Scanner;

public class Herramientas {

	public static void main(String[] args) {
		// TODO Auto-generated method stub


	}
	
	public static Coordenada obtenerCoordenada() {
		String coordenada = obtenerCadena();
		while(!esCoordenada(coordenada)) {
			coordenada = obtenerCadena();
		}
		
		
		coordenada = coordenada.toUpperCase();
		return new Coordenada(coordenada.charAt(0), Integer.parseInt(String.valueOf(coordenada.charAt(1))));
		
		
		
	}
	

	
	
	private static String obtenerCadena() {
		String coordenada = pedirString("Introduce una coordenada primero poniendo la letra y después el número ");
		
		if(coordenada.length() == 2) {
			return coordenada;
		} else {
			while(coordenada.length() != 2) {
				coordenada = pedirString("Introduce la coordenada correctamente");
		
			}	
		}	
			return coordenada;
		}
	
	
	private static boolean esCoordenada(String coordenada) {
		coordenada = coordenada.toUpperCase();
		if(coordenada.length() != 2)
			return false;
		if(!(coordenada.charAt(0) >= 'A' && coordenada.charAt(0) <= 'H'))
			return false;
		if(!(coordenada.charAt(1)>='1' && coordenada.charAt(1) <= '8' ))
			return false;
		return true;
	}
	
	
	
	public static String pedirString(String msg) {
		String pedido = "";
		Scanner sc = new Scanner(System.in);
		Mensaje(msg);
		pedido = sc.nextLine();
		return pedido;
		
		
	}
	
	public static int pedirInt(String msg) {
		int pedido = 0;
		boolean error = false;
		while(error == false) {
			try {
				pedido = Integer.parseInt(pedirString(msg));
				error = true;
			} catch (Exception e) {
				Mensaje("Debes introducir un número");
			}
		}
		
		return pedido;

		
	}
	
	public static void clear() {  
		System.out.print("\033[H\033[2J");  
		System.out.flush();  
	} 
	
	public static  void Mensaje(String mensaje) {
		System.out.println(mensaje);
	}

}
