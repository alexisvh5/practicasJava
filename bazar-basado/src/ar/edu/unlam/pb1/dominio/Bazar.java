package ar.edu.unlam.pb1.dominio;



import ar.edu.unlam.pb1.dominio.enums.TipoDeIva;

public class Bazar {

	// TODO: Completar getters, setters, constructor y metodos necesarios para
	// garantizar el correcto funcionamiento.

	private String nombre;
	private Producto[] productos;

	public Bazar(String nombre) {
		this.nombre = nombre;
		this.productos = new Producto[10000];
	}

	public Producto obtenerProductoPorCodigoDeBarras(String codigoDeBarras) {
		// TODO: Buscar y devolver el producto que cumpla con el codigo de barras
		// suministrado. En caso de no existir algun producto, devolver null.
		int i = 0;
		Producto prodBuscado = null;
		while (i < productos.length) {

			if (productos[i] != null && productos[i].getCodigoBarras().equalsIgnoreCase(codigoDeBarras)) {
				prodBuscado = productos[i];
				i = productos.length;
			} else {
				i++;
			}

		}

		return prodBuscado;
	}

	public String agregarProducto(String codigoDeBarras, char seccion, String nombre, TipoDeIva tipoDeIva, int stock,
			double porcentajeGanancia, double costo) {

		// TODO: Se debera agregar un producto al bazar. Antes de realizar esta accion,
		// se debera validar el producto (ver metodo productoValido()).
		// Si el mensaje obtenido esta vacio, se debe proceder a agregar el producto al
		// bazar.
		// Devolver el mensaje resultante de validar el producto (puede estar vacio o
		// tener indicaciones de validacion).

		String mensaje = productoValido(codigoDeBarras, stock);

		if (mensaje.equalsIgnoreCase("")) {
			Producto nuevoAgregado = new Producto(codigoDeBarras, seccion, nombre, tipoDeIva, stock, porcentajeGanancia,
					costo);
			int i = 0;

			while (i < productos.length) {
				if (productos[i] == null) {
					productos[i] = nuevoAgregado;
					i = productos.length;
				} else {
					i++;
				}
			}

		}
		return mensaje;

	}

	private String productoValido(String codigoDeBarras, int stock) {
		// TODO: Verificar si los datos para crear un producto son validos. En caso de
		// ser valido, se debera devolver un texto vacio ("").
		// Las siguientes validaciones deben considerarse:
		// - El codigo de barras debe ser valido (ver metodo codigoDeBarrasValido()). En
		// caso de no ser valido agregar al mensaje de respuesta: "\nCodigo de barras
		// invalido."
		// - No debe existir otro producto con el mismo codigo de barras. Si existe,
		// agregar al mensaje de respuesta: "\nCodigo de barras existente."
		// - El stock ingresado debe ser mayor que cero. Si no lo fuera, agregar al
		// mensaje de respuesta: "\nEl stock no puede ser negativo."
		// Ejemplo de texto de respuesta con las 3 validaciones:
		// "\nCodigo de barras invalido.\nCodigo de barras existente.\nEl stock no puede
		// ser negativo."
		String texto = "";

		if (!codigoDeBarrasValido(codigoDeBarras)) {
			texto += "\nCodigo de barras invalido.";
		}

		if (stock < 0) {
			texto += "\nEl stock no puede ser negativo";
		}
		if (obtenerProductoPorCodigoDeBarras(codigoDeBarras) != null) {
			texto += "\nCodigo de barras existente.";
		}

		return texto;
	}

	private boolean codigoDeBarrasValido(String codigoDeBarras) {
		// TODO: Se debera devolver verdadero en caso de que el codigo de barras este
		// conformado por 12 caracteres y todos ellos sean numeros.
		boolean cierto = false;

		for (int i = 0; i < codigoDeBarras.length(); i++) {
			if (codigoDeBarras.length() == 12 && Character.isDigit(codigoDeBarras.charAt(i))) {
				cierto = true;
			} else {
				return false;
			}
		}

		return cierto;
	}

	public boolean agregarStockAProducto(String codigoDeBarras, int stockParaAgregar) {
		// TODO: Agregar el stock indicado al producto que cumpla con el codigo de
		// barras, solo si el producto existe.

		Producto prodACambiar = obtenerProductoPorCodigoDeBarras(codigoDeBarras);
		boolean sePuedo = false;

		if (prodACambiar != null) {
			prodACambiar.setStock(prodACambiar.getStock() + stockParaAgregar);
			sePuedo = true;
		}

		return sePuedo;
	}

	public void incrementarCostoDeProductoConPorcentaje(String codigoDeBarras, double porcentajeIncremento) {
		// TODO: Incrementar el costo de un producto el cual se debera buscar por su
		// codigo de barras, en el porcentaje indicado.
		// Ejemplo: costo = 100, porcentaje 10% -> nuevo costo = 110

		Producto prodACambiar = obtenerProductoPorCodigoDeBarras(codigoDeBarras);
		prodACambiar.setPorcentajeGanancia(prodACambiar.getPorcentajeGanancia() + porcentajeIncremento);
	}

	public Producto[] obtenerProductosPorSeccion(char seccion) {
		// TODO: Se debera devolver un array de productos que contengan solo los
		// productos que se encuentren en la seccion indicada.
		Producto[] productosConSeccion = new Producto[productos.length];
		int contador = 0;

		for (int i = 0; i < productos.length; i++) {
			if (productos[i] != null && productos[i].getSeccion() == seccion) {
				productosConSeccion[contador] = productos[i];
				contador++;
			}
		}

		return productosConSeccion;
	}

	public double obtenerPromedioDePrecioDeVentaDeProductosPorSeccionConOSinIva(char seccion, boolean incluyeIva) {
		// TODO: Se debera calcular y devolver el promedio de precio de venta de
		// productos que se encuentren en la seccion indicada, considerando si se debe
		// incluir el IVA o no.
		// Si no hay productos en dicha seccion, se debera devolver cero. Si hay, el
		// promedio se debera mostrar redondeado segun si sus decimales son mayor o
		// igual a cinco.
		double promedio = 0.0;
		double preciosConIvaAcumulados = 0.0;
		double preciosSinIvaAcumulados = 0.0;
		int cantidadDeProductosConIva = 0;
		int cantidadDeProductosSinIva = 0;

		Producto[] prodBuscadosPorSeccion = obtenerProductosPorSeccion(seccion);

		if (prodBuscadosPorSeccion != null && incluyeIva) {
			for (int i = 0; i < prodBuscadosPorSeccion.length; i++) {
				if (prodBuscadosPorSeccion[i] != null) {
					cantidadDeProductosConIva++;
					preciosConIvaAcumulados += prodBuscadosPorSeccion[i].obtenerPrecioDeVenta(incluyeIva);
				}
			}
			promedio=preciosConIvaAcumulados/cantidadDeProductosConIva;

		} else if (prodBuscadosPorSeccion != null && !incluyeIva) {
			for (int i = 0; i < prodBuscadosPorSeccion.length; i++) {
				if (prodBuscadosPorSeccion[i] != null) {
					cantidadDeProductosSinIva++;
					preciosSinIvaAcumulados += prodBuscadosPorSeccion[i].obtenerPrecioDeVenta(incluyeIva);
				}
			}
			promedio=preciosSinIvaAcumulados/cantidadDeProductosSinIva;
		}
		

		return Math.round(promedio);
	}

	public String getNombre() {
		return nombre;
	}

	public Producto[] getProductos() {
		return productos;
	}

}
