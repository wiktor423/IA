package practica.ia;

public class Nodo {
	
	private String nombre;
	private int x;//coordenada en interfaz
	private int y;
	
	public Nodo() {
		
	}
	
	public Nodo(String nombre, int x, int y) {
		this.nombre=nombre;
		this.x=x;
		this.y=y;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	

}
