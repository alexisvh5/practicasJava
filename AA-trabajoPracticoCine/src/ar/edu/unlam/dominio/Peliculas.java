package ar.edu.unlam.dominio;

public class Peliculas {
	
	//atributos
	private String nombre;
	private double precio;
	private int stock;
	
	//constructores
	public Peliculas(String nombre, double precio, int stock) {
		this.setNombre(nombre);
		this.setPrecio(precio);
		this.setStock(stock);
	}
	
	//metodos
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public double getPrecio() {
		return precio;
	}
	
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	public int getStock() {
		return stock;
	}
	
	public String setStock(int stock) {
		String cadena = "";
		if(stock>0) {
			this.stock += stock;
			cadena = "el numero de entradas actuales disponibles de "+this.nombre+" es de "+this.stock+" \n";
		}else {
			cadena = "no se ha podido concretar la operacion ingresar una cantidad adecuada \n";
		}
		return cadena;
	}
	
	public boolean cambiarStock(int cantidad) {
		boolean hayStock = false;
		if((this.stock-cantidad)>=0 && cantidad>0) {
			hayStock = true;
			this.setStock(this.stock-cantidad);
		}
		return hayStock;
	}
	
	
	@Override
	public String toString() {
		return "Nombre de la pelicula : "+this.getNombre()+"\n"
				+"precio: "+this.getPrecio()+"\n"
				+"entradas disponibles: "+this.getStock();
	}

}
