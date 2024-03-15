package ar.edu.unlam.interfaz;
import java.util.Scanner;
import ar.edu.unlam.dominio.Cine;
import ar.edu.unlam.dominio.Peliculas;
import ar.edu.unlam.dominio.Cliente;
import ar.edu.unlam.dominio.Cupon;

import java.util.Random;
public class Menu {
	
	static Random random = new Random();
	static Scanner teclado = new Scanner(System.in);

	public static void main(String[] args) {
		

		
		//creando cine
		Cine cine = new Cine("Cinemark", 10, 15);
		mensajePorPantalla("Cine "+cine.getNombre()+"\n");
		
		//ingresando pelis a la cartelera
		Peliculas Batman = new Peliculas("Batman", precioRandom(), stockRandom());
		Peliculas Dracula = new Peliculas("Dracula", precioRandom(), stockRandom());
		Peliculas miPobreAngelito = new Peliculas("mi pobre angelito", precioRandom(), stockRandom());
		Peliculas elSeniorDeLosAnillos = new Peliculas("El senior de los anillos", precioRandom(), stockRandom());
		Peliculas river = new Peliculas("River 9/12", precioRandom(), stockRandom());
		Peliculas crepusculo = new Peliculas("Crepusculo 2", precioRandom(), stockRandom());
		Peliculas robinHood = new Peliculas("Robin hood", precioRandom(), stockRandom());
		Peliculas juegos = new Peliculas("Los juegos del hambre", precioRandom(), stockRandom());
		Peliculas mujerBonita = new Peliculas("Mujer bonita", precioRandom(), stockRandom());
		Peliculas pecadosCapitales = new Peliculas("Pecados capitales", precioRandom(), stockRandom());
		Peliculas [] arrayPeliculas = {Batman, Dracula, miPobreAngelito, elSeniorDeLosAnillos, river, crepusculo, robinHood, juegos, mujerBonita, pecadosCapitales};
		for(int i=0; i<arrayPeliculas.length; i++) {
			cine.setPeliculas(arrayPeliculas[i]);
		}
		mostrarLineasParaSeparar();
		
		//Menu de gestion de la Cine
		int eleccion = 0;
		boolean cortar = false;
		do {
			mensajePorPantalla("Opciones disponibles: ");
			mensajePorPantalla(
					"01. Ver el cartelera \n"
		
							+"02. Buscar alguna pelicula en particular \n"
					+"03. Sacar pelicula de cartelera \n"
					+"04. Ingresar cliente \n"
					+"05. Comprar entradas \n"
					+"06. Ver datos de un cliente \n"
					+"07. Ordenar peliculas por nombre \n"
					+"08. Pelicula mas barata \n"
					+"09. Pelicula mas cara \n"
					+"10. Salir");
			mostrarLineasParaSeparar();
			mensajePorPantalla("Ingresar opcion: ");
			eleccion = teclado.nextInt();
			
			switch(eleccion) {
			case 1:
				mostrarLineasParaSeparar();
				mensajePorPantalla("Catalogo de peliculas: ");
				mensajePorPantalla(cine.getPeliculas());
				mostrarLineasParaSeparar();
				break;
			case 2:
				mostrarLineasParaSeparar();
				mensajePorPantalla("Ingresar codigo de la pelicula: ");
				int codigoDeUnaPelicula = teclado.nextInt();
				mostrarLineasParaSeparar();
				mensajePorPantalla(cine.getPeliculaConCodigo(codigoDeUnaPelicula));
				mostrarLineasParaSeparar();
				break;
			case 3:
				mostrarLineasParaSeparar();
				mensajePorPantalla("Ingresar codigo de la pelicula: ");
				int codigoQuitar = teclado.nextInt();
				mostrarLineasParaSeparar();
				mensajePorPantalla(cine.sacarPelicula(codigoQuitar));
				mostrarLineasParaSeparar();
				break;
			case 4:
				mostrarLineasParaSeparar();
				mensajePorPantalla("Ingresar nombre:");
				String nombre = teclado.next();
				mostrarLineasParaSeparar();
				mensajePorPantalla("Ingresar apellido: ");
				String apellido = teclado.next();
				mostrarLineasParaSeparar();
				mensajePorPantalla("Ingresar numero de DNI: ");
				int dni = teclado.nextInt();
				mostrarLineasParaSeparar();
				mensajePorPantalla("Ingresar saldo: ");
				double saldo = teclado.nextDouble();
				mostrarLineasParaSeparar();
				mensajePorPantalla("Tiene cupon de descuento?(responder con SI o NO): ");
				String tieneCupon = teclado.next();
				mostrarLineasParaSeparar();
				
				if(tieneCupon.equals("SI") || tieneCupon.equals("si")) {
					mensajePorPantalla("Ingresar nombre de la pelicula para la cual tiene el cupon de descuento: ");
					String pelicula = teclado.next();
					mostrarLineasParaSeparar();
					Cupon cupon = new Cupon(pelicula);
					Cliente clienteConCupon = new Cliente(nombre, apellido, dni, cupon, saldo);
					mensajePorPantalla(cine.setClientes(clienteConCupon));
				}else {
					Cliente clienteSinCupon = new Cliente(nombre, apellido, dni, saldo);
					mensajePorPantalla(cine.setClientes(clienteSinCupon));
				}
				mostrarLineasParaSeparar();
				break;
			case 5:
				mostrarLineasParaSeparar();

				mensajePorPantalla("Si compras entradas para una pelicula con cupon, tenes un 40% de descuento");
				mostrarLineasParaSeparar();
				mensajePorPantalla("Ingresar numero del cliente: ");
				int numeroCliente = teclado.nextInt();
				mostrarLineasParaSeparar();
				boolean cortarCompra = false;				
				do {
				mensajePorPantalla("Ingresar nombre de la pelicula: ");
				String nombrePeli = teclado.next();
				mostrarLineasParaSeparar();
				mensajePorPantalla("Ingresar cantidad de entradas a comprar: ");
				int cantidad = teclado.nextInt();
				mostrarLineasParaSeparar();					
				mensajePorPantalla(cine.comprarPelicula(numeroCliente, nombrePeli, cantidad));
				mostrarLineasParaSeparar();
				mensajePorPantalla("Desea comprar algo mas (SI o NO): ");
				String terminarCompra = teclado.next();
				mostrarLineasParaSeparar();
				if(terminarCompra.equals("SI") || terminarCompra.equals("si")) {
					mensajePorPantalla("se volveran a pedir los datos para la nueva compra");
					mostrarLineasParaSeparar();
				}else {
					cortarCompra = true;
					mensajePorPantalla("se volvera al menu");
					mostrarLineasParaSeparar();
				}
				}while(!cortarCompra);
				
				break;
			case 6:
				mostrarLineasParaSeparar();
				mensajePorPantalla("Ingresar numero del cliente que desea conocer sus datos: ");
				int numero = teclado.nextInt();
				mostrarLineasParaSeparar();
				mensajePorPantalla(cine.getClienteConNumero(numero));
				mostrarLineasParaSeparar();
				break;
			case 7:
				mostrarLineasParaSeparar();
				mensajePorPantalla("Cartelera: \n");
				mensajePorPantalla(cine.ordenarPeliculasPorNombre());
				mostrarLineasParaSeparar();
				break;
			case 8:
				mostrarLineasParaSeparar();
				mensajePorPantalla(cine.peliculaMasBarata());
				mostrarLineasParaSeparar();
				break;
			case 9:
				mostrarLineasParaSeparar();
				mensajePorPantalla(cine.peliculaMasCara());
				mostrarLineasParaSeparar();
				break;
			case 10:
				mostrarLineasParaSeparar();
				cortar = true;
				mensajePorPantalla("Esta saliendo del menu");
				mostrarLineasParaSeparar();
				break;
			default:
				mostrarLineasParaSeparar();
				mensajePorPantalla("Ha marcado una opcion que no se haya dentro de las disponibles");
				mostrarLineasParaSeparar();
				break;
			}
		} while(!cortar);


	}
	
	private static void mensajePorPantalla(String mensaje) {
		System.out.println(mensaje);
	}
	
	private static void mostrarLineasParaSeparar() {
		System.out.println("-------------------------------------------------------------------------------------------------------------------\n");
	}
	
	private static double precioRandom() {
		double precio = random.nextInt(100)+1;
		return precio;
	}
	
	private static int stockRandom() {
		int stock = random.nextInt(10)+1;
		return stock;
	}

}
