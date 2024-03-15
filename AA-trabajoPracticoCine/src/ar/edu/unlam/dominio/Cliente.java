package ar.edu.unlam.dominio;

public class Cliente {
	
	//atributos
	private String nombre;
	private String apellido;
	private final int DNI;//
	private Cupon cupon;
	private double saldo;
	
	//constructores
	public Cliente(String nombre, String apellido, int dni, double saldo) {
		this.setNombre(nombre);
		this.setApellido(apellido);
		this.DNI = dni;
		this.cupon = null;
		this.setSaldo(saldo);
	}
	
	public Cliente(String nombre, String apellido, int dni, Cupon cupon, double saldo) {
		this.setNombre(nombre);
		this.setApellido(apellido);
		this.DNI = dni;
		this.cupon = cupon;
		this.setSaldo(saldo);
	}
	
	//metodos
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getApellido() {
		return apellido;
	}
	
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public int getDni() {
		return DNI;
	}
	
	public Cupon getCupon() {
		return cupon;
	}
	
	public boolean tieneCupon() {
		boolean tieneCupon = true;
		if(this.cupon == null) {
			tieneCupon = false;
		}
		return tieneCupon;
	}
	
	public double getSaldo() {
		return saldo;
	}
	
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	public String quitarSaldo(double valor) {
		String cadena = "";
		if(valor>this.saldo) {
			cadena = "el saldo es menor al precio de la entrada, no se puede concretar la operacion \n";
		}else {
			this.saldo -= valor;
			cadena = "su saldo actual es de "+this.saldo+"\n";
		}
		return cadena;
	}
	
	@Override
	public String toString() {
		String cadena = "Nombre y Apellido del cliente: "+this.getNombre()+" "+this.getApellido()+"\n"
				+"DNI del cliente: "+this.getDni()+"\n"
				+"Saldo: "+this.getSaldo()+"\n";
		if(this.cupon!=null) {
			cadena += this.cupon.toString();
		}
		return cadena;
	}
	
}
