package modelo;



public class Lista<T> {
	private Nodo<T> cabeza;
	private int size;
	private Nodo<T> cola;
	
	
	public Lista() {
		super();
	}
	
	public void addHead(T numero) {
		
		Nodo<T> nodo = new Nodo<T>(numero);
		
		if(cabeza == null) {
			cabeza = nodo;
			cola = nodo;
		} else {
			nodo.setSiguiente(cabeza);
			cabeza.setAnterior(nodo);
			cabeza = nodo;
			
		}
		
		size ++;
	}
	
	
	public void addTail(T numero) {
		
		Nodo<T> nodo = new Nodo<T>(numero);
		
		if(cabeza == null) {
			cabeza = nodo;
			cola = nodo;
		} else {
			nodo.setAnterior(cola);
			cola.setSiguiente(nodo);
			cola = nodo;
		}
		
		size ++;
		
	}
	
	public T getHead() {
		T valor = null;
		
		if(cabeza == null) {
			return null;
		} else if (cabeza == cola) {
			valor = cabeza.getInfo();
			cabeza = null;
			cola = null;
			size --;
		} else {
			valor = cabeza.getInfo();
			cabeza.getSiguiente().setAnterior(null);
			cabeza = cabeza.getSiguiente();
			size --;
		}
		
		return valor;
	}
	
	public T getTail() {
		T valor = null;
		
		if(cabeza == null) {
			return null;
		} else if (cabeza == cola) {
			valor = cabeza.getInfo();
			cabeza = null;
			cola = null;
			size --;
		} else {
			valor = cola.getInfo();
			cola.getAnterior().setSiguiente(null);
			cola = cola.getAnterior();
			size --;
		}
		
		return valor;
	}
	
	public int search(T persona) {
		
		int posicion = -1;
		
		Nodo<T> aux = cabeza;
		int i = 0;
		
		while(aux != null && posicion == -1) {
			if(persona.equals(aux.getInfo())) 
				posicion = i;
			else 
				aux = aux.getSiguiente();
			
			i ++;
		}
		
		
		return posicion;
		
	}
	
	public boolean contains(T valor) {
		
		boolean encontrado = false;
		
		Nodo<T> aux = cabeza;
		
		
		while(aux != null && !encontrado ) {
			if(valor.equals(aux.getInfo())) 
				encontrado = true;
			else 
				aux = aux.getSiguiente();
			
		}
		
		
		return encontrado;
		
	}
	
	
	
	public T get(int indice) {
		T valor = null;
		int i = 0;
		Nodo<T> aux = cabeza;
		
		if(indice > size -1 || indice < 0) {
			return null;
		} else if (indice == 0) {
			return this.getHead();
		} else if (indice == size -1) {
			return this.getTail();
		} else {
			while(i != indice) {
				aux = aux.getSiguiente();
				i ++;
			}
			valor = aux.getInfo();

		}
		return valor;
		
	}
	
	public T getAndRemove(T element) {
		T valor = null;
	
		
	if(cabeza == null)
		return null;
		if (element.equals(cabeza.getInfo())) {
			return this.getHead();
		} else if (element.equals(cola.getInfo())) {
			return this.getTail();
		} else {
			Nodo<T> aux = cabeza;
			
			while(aux != null && valor == null) {
				if(aux.getInfo().equals(element))
					valor = aux.getInfo();
				else 	
					aux = aux.getSiguiente();
			}
			if(valor != null) {
				aux.getSiguiente().setAnterior(aux.getAnterior());
				aux.getAnterior().setSiguiente(aux.getSiguiente());
				size --;
			}
			
		}
		return valor;
		
	}
	
	public T getSinBorrar(int indice) {
		
		T valor = null;
		int i = 0;
		Nodo<T> aux = cabeza;
		
		if(indice > size -1 || indice < 0) {
			return null;
		} else if (indice == 0) {
			return cabeza.getInfo();
		} else if (indice == size -1) {
			return cola.getInfo();
		} else {
			while(i != indice) {
				aux = aux.getSiguiente();
				i ++;
			}
			valor = aux.getInfo();
		}
		return valor;
		
		
	}
	
	public int getSize() {
		return size;
	}
	
	public boolean isEmpty() {
		return size==0;
	}
	
	public Lista<T> juntarListas(Lista<T> lista) {
		
	
		Nodo<T> aux;
		
		aux = lista.cabeza;
	
		
		while(aux!=null) {
			addHead(aux.getInfo());
			aux = aux.getSiguiente();
		}
		
		return this;
		
	}
	
	public int contar(T element) {
		int contar = 0;
		Nodo<T> auxiliar = cabeza;
		while(auxiliar != null) {
			if(auxiliar.getInfo().equals(element)) {
				contar ++;
			}
			auxiliar = auxiliar.getSiguiente();
		}
		
		
		return contar;
	}

	@Override
	public String toString() {
		
		String salida = "Size: " + size + "\n";
		salida += "Valores: ";
		Nodo<T> auxiliar = cabeza;
		
		
		while(auxiliar != null) {
			salida += auxiliar.toString() + " ";
			auxiliar = auxiliar.getSiguiente();
		}
		
		return salida;
		
	}
}
