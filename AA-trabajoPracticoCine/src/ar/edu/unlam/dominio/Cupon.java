package ar.edu.unlam.dominio;

public class Cupon {
	
	//atributos
	private String nombrePelicula;
	
	//constructores
	public Cupon(String nombrePelicula) {
		this.setNombrePelicula(nombrePelicula);
	}
	
	//metodos
	public String getNombrePelicula() {
		return nombrePelicula;
	}
	
	public void setNombrePelicula(String nombrePelicula) {
		this.nombrePelicula = nombrePelicula;
	}
	
	@Override
	public String toString() {
		return "Pelicula con descuento :"+this.nombrePelicula;
	}

}
