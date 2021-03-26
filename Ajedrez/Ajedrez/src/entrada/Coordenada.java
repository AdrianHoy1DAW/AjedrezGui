package entrada;

import java.io.Serializable;

public class Coordenada implements Serializable {

	private char ejeX;
	private int ejeY;
	
	public Coordenada(char ejeX , int ejeY) {
		
//		if(ejeX < 0 || ejeX > 8) {
//			this.ejeX = 0;
//		} else  {
			this.ejeX  = ejeX;
//		}
		
//		String letra = String.valueOf(ejeY);
//		char upperLetra = letra.toUpperCase().charAt(0);
//		
//		if(upperLetra < 'A' || upperLetra > 'H') {
//			this.ejeY = 'A';
//		} else {
			this.ejeY = ejeY;
//		}
		
	}

	
	
	
	
	public char getEjeX() {
		return ejeX;
	}



	public int getEjeY() {
		return ejeY;
	}
	

	public Coordenada up() {
		return new Coordenada(this.ejeX , this.ejeY +1);
	}
	
	public Coordenada down() {
		return new Coordenada(this.ejeX, this.ejeY -1);
	}
	
	public Coordenada right() {
		return new Coordenada((char)(this.ejeX + 1),this.ejeY);
	}
	
	public Coordenada left() {
		return new Coordenada((char)(this.ejeX - 1),this.ejeY);
	}
	
	public Coordenada rightUp() {
		return up().right();
	}
	
	public Coordenada rightDown() {
		return down().right();
	}
	
	public Coordenada leftUp() {
		return up().left();
	}
	
	public Coordenada leftDown() {
		return down().left();
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Coordenada) {
			Coordenada a = (Coordenada) o;
			if(a.getEjeX() == this.getEjeX() && a.getEjeY() == this.getEjeY()) {
				return true;
			} else {
				return false;
			} 
		} else {
				return false;
			}
		}
			
	
	
	@Override
	public String toString() {
		return "\n" + String.valueOf(ejeX) + ejeY;
	}
	
}
