package ar.edu.unlam.pb1.dominio;

import ar.edu.unlam.pb1.dominio.enums.TipoDeIva;

public class Producto {

	// TODO: Completar getters, setters, constructor y metodos necesarios para
	// garantizar el correcto funcionamiento. No olvidar incluir el precio de venta
	// al mostrar un producto (mostrando el precio de venta con IVA).
	private String codigoBarras;
	private char seccion;
	private String nombre;
	private TipoDeIva tipoDeIva;
	private int stock;
	private double porcentajeGanancia;
	private double costo;
	
	
	private final double BASE_PORCENTUAL=100;
	private double precioDeVenta;

	public Producto(String codigoBarras, char seccion, String nombre, TipoDeIva tipoDeIva, int stock,
			double porcentajeGanancia, double costo) {
		this.codigoBarras=codigoBarras;
		this.seccion=seccion;
		this.nombre=nombre;
		this.tipoDeIva=tipoDeIva;
		this.stock=stock;
		this.porcentajeGanancia=porcentajeGanancia;
		this.costo=costo;
		this.precioDeVenta=0;
	}

	public double obtenerPrecioDeVenta(boolean incluyeIva) {
		// TODO: Calcular y devolver el precio de venta de un producto considerando la
		// siguiente ecuación:
		
		
		// Precio venta = (costo / (Base porcentual - porcentaje de ganancia)) * 100.
		// Para la base porcentual, utilizaremos 100%.
		// Ejemplo: ($100 / (100% - 20%)) * 100 (este valor es constante).
		
		
		// En caso de que el precio de venta deba incluir el porcentaje de IVA, se deberá
		// agregar al precio de venta segun su tipo. Siempre devolver el precio de venta
		// redondeado (el valor decimal sea mayor o igual a 5).
		
		this.precioDeVenta = (this.costo/(this.BASE_PORCENTUAL-this.porcentajeGanancia))*100;
		
		double iva=0;
		
		if(incluyeIva &&this.tipoDeIva == TipoDeIva.GENERAL) {
			iva=(27*this.precioDeVenta)/100;
			this.precioDeVenta +=iva; 
		}
		if(incluyeIva&&this.tipoDeIva == TipoDeIva.REDUCIDO_I) {
			iva=(21*this.precioDeVenta)/100;
			this.precioDeVenta +=iva; 
		}
		if(incluyeIva&&this.tipoDeIva == TipoDeIva.REDUCIDO_II) {
			iva=(10.5*this.precioDeVenta)/100;
			this.precioDeVenta +=iva; 
		}
		if(incluyeIva&&this.tipoDeIva == TipoDeIva.SUPER_REDUCIDO) {
			iva=(2.5*this.precioDeVenta)/100;
			this.precioDeVenta +=iva; 
		}
	
		
		return this.precioDeVenta;
	}
	
	////////////////////// geters y sester

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public char getSeccion() {
		return seccion;
	}

	public void setSeccion(char seccion) {
		this.seccion = seccion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public TipoDeIva getTipoDeIva() {
		return tipoDeIva;
	}

	public void setTipoDeIva(TipoDeIva tipoDeIva) {
		this.tipoDeIva = tipoDeIva;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public double getPorcentajeGanancia() {
		return porcentajeGanancia;
	}

	public void setPorcentajeGanancia(double porcentajeGanancia) {
		this.porcentajeGanancia = porcentajeGanancia;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	@Override
	public String toString() {
		return "Producto [codigoBarras=" + codigoBarras + ", seccion=" + seccion + ", nombre=" + nombre + ", tipoDeIva="
				+ tipoDeIva + ", stock=" + stock + ", porcentajeGanancia=" + porcentajeGanancia + ", costo=" + costo
				 + ", precioVenta=" + precioDeVenta + "]";
	}
	
	
}
