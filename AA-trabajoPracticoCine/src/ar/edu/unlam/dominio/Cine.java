package ar.edu.unlam.dominio;

public class Cine {

	// atributos
	private String nombre;
	private Peliculas[] peliculas;
	private Cliente[] clientes;

	// constructores
	public Cine(String nombre, int cantidadPeliculas, int cantidadClientes) {
		this.setNombre(nombre);
		this.peliculas = new Peliculas[cantidadPeliculas];
		this.clientes = new Cliente[cantidadClientes];
	}

	// metodos
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPeliculaConCodigo(int codigo) {
		String cadena = "";
		if (codigo >= 0 && codigo < this.peliculas.length && this.peliculas[codigo] != null) {
			cadena = this.peliculas[codigo].toString() + "\n";
		} else {
			cadena = "el codigo ingresado no se haya en la cartelera \n";
		}
		return cadena;
	}

	public String getPeliculas() {
		String cadena = "";

		for (int i = 0; i < this.peliculas.length; i++) {
			if (this.peliculas[i] != null) {
				cadena += "Codigo: " + i + "\n" + this.peliculas[i].toString() + "\n" + "\n";
			}
		}
		return cadena;
	}

	public void setPeliculas(Peliculas peliculas) {

		boolean verdadero = false;
		int i = 0;
		while (!verdadero && i < this.peliculas.length) {
			if (this.peliculas[i] == null) {
				this.peliculas[i] = peliculas;
				verdadero = true;
			}
			i++;
		}

	}

	public String getClienteConNumero(int numero) {
		String cliente = "";
		if (this.clientes[numero] == null) {
			cliente = "el numero ingresado no esta asignado a ningun cliente \n";
		} else {
			cliente = clientes[numero].toString() + "\n";
		}
		return cliente;
	}

	public String getClientes() {
		String cadena = "";
		for (int i = 0; i < this.clientes.length; i++) {
			if (this.clientes[i] != null) {
				cadena += "Numero: " + i + "\n" + this.clientes[i].toString() + "\n" + "\n";
			}
		}
		return cadena;
	}

	public String setClientes(Cliente cliente) {
		String cadena = "";
		int i = 0;

		while (i < this.clientes.length) {
			if (this.clientes[i] == null) {
				this.clientes[i] = cliente;
				cadena = "el numero del cliente es: " + i + "\n";
			}
			i++;
		}
		return cadena;
	}

	public String sacarPelicula(int codigo) {
		String nombre = "";
		String cadena = "";
		if (this.peliculas[codigo] != null) {
			nombre = this.peliculas[codigo].getNombre();
			this.peliculas[codigo] = null;
			cadena = "se ha quitado " + nombre + " de la cartelera \n";
		} else {
			cadena = "no se encontro pelicula con ese codigo \n";
		}
		return cadena;
	}

	public String comprarPelicula(int numero, String pelicula, int cantidad) {
		String cadena = "";
		if (this.existePelicula(pelicula)) {
			if (this.clientes[numero] == null) {
				cadena = "el numero ingresado no esta asignado a ningun cliente \n";
			} else {
				if (this.peliculas[this.getCodigoPeliculaConNombre(pelicula)].cambiarStock(cantidad)) {
					if (this.clientes[numero].tieneCupon()
							&& this.clientes[numero].getCupon().getNombrePelicula().equals(pelicula)) {
						this.clientes[numero].quitarSaldo(
								(this.peliculas[this.getCodigoPeliculaConNombre(pelicula)].getPrecio() * cantidad)
										* 0.40);
						this.peliculas[this.getCodigoPeliculaConNombre(pelicula)].cambiarStock(cantidad);
						cadena = "el precio de " + pelicula
								+ " tiene un descuento del 40% al tener cupon, se ha concretado la transaccion con exito, su saldo actual es de "
								+ this.clientes[numero].getSaldo() + " \n";
					} else {
						this.clientes[numero].quitarSaldo(
								this.peliculas[this.getCodigoPeliculaConNombre(pelicula)].getPrecio() * cantidad);
						this.peliculas[this.getCodigoPeliculaConNombre(pelicula)].cambiarStock(cantidad);
						cadena = "se ha concretado la transaccion con exito, su saldo actual es de "
								+ this.clientes[numero].getSaldo() + " \n";
					}
				} else {
					cadena = "no estan disponibles esa cantidad de lugares para : " + pelicula + "\n";
				}

			}
		} else {
			cadena = "la pelicula ingersada no se haya dentro del catalogo \n";
		}
		return cadena;
	}

	public String ordenarPeliculasPorNombre() {
		Peliculas aux = null;
		for (int i = 1; i < this.peliculas.length; i++) {
			for (int j = 0; j < this.peliculas.length - 1; j++) {
				if (this.peliculas[j] != null && this.peliculas[j + 1] != null
						&& this.peliculas[j].getNombre().compareTo(this.peliculas[j + 1].getNombre()) > 0) {
					aux = this.peliculas[j + 1];
					this.peliculas[j + 1] = this.peliculas[j];
					this.peliculas[j] = aux;
				}
			}

		}
		return this.getPeliculas();
	}

	public String peliculaMasBarata() {
		Peliculas min = this.asigarValorAuxiliar();
		for (int i = 0; i < this.peliculas.length; i++) {
			if (this.peliculas[i] != null && min.getPrecio() > this.peliculas[i].getPrecio()) {
				min = this.peliculas[i];
			}
		}
		return min.toString() + "\n";
	}

	public String peliculaMasCara() {
		Peliculas max = this.asigarValorAuxiliar();
		for (int i = 0; i < this.peliculas.length; i++) {
			if (this.peliculas[i] != null && max.getPrecio() < this.peliculas[i].getPrecio()) {
				max = this.peliculas[i];
			}
		}
		return max.toString() + "\n";
	}

	private boolean existePelicula(String nombre) {
		boolean existe = false;
		int i = 0;
		while (!existe && i < this.peliculas.length) {
			if (this.peliculas[i].getNombre().equals(nombre)) {
				existe = true;
			}
			i++;
		}
		return existe;
	}

	private int getCodigoPeliculaConNombre(String pelicula) {
		int codigo = 0;
		boolean encontrado = false;
		int i = 0;
		while (!encontrado && i < this.peliculas.length) {
			if (this.peliculas[i].getNombre().equals(pelicula)) {
				codigo = i;
				encontrado = true;
			}
			i++;
		}
		return codigo;
	}

	private Peliculas asigarValorAuxiliar() {
		Peliculas aux = null;
		int i = 0;
		while (aux == null && i < this.peliculas.length) {
			if (this.peliculas[i] != null) {
				aux = this.peliculas[i];
			}
			i++;
		}
		return aux;
	}

}
