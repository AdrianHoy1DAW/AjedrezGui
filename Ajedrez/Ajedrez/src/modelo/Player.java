package modelo;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;

public class Player implements Serializable{
	
	private String name;


	public Player(String name) {
		
		this.name = name;
	
	}

	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	@Override
	public String toString() {
		return "Player [name=" + name + "]";
	}
}