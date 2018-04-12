package SPQ;

import java.util.ArrayList;

public class Producto {
	public static ArrayList<Producto> obtenerListaProductos;
	private String nombreProducto;
	private double precioProducto;
	
	public Producto(String nombreProducto, double precioProducto) {
		super();
		this.nombreProducto = nombreProducto;
		this.precioProducto = precioProducto;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public double getPrecioProducto() {
		return precioProducto;
	}

	public void setPrecioProducto(double precioProducto) {
		this.precioProducto = precioProducto;
	}

	public static ArrayList<Producto> getObtenerListaProductos() {
		
		Producto zanahoria = new Producto("Zanahoria", 12.99);
		Producto platano = new Producto("Platano", 12.99);
		Producto lechuga = new Producto("Lechuga", 12.99);
		Producto tomate = new Producto("Tomate", 12.99);
		
		obtenerListaProductos.add(zanahoria);
		obtenerListaProductos.add(platano);
		obtenerListaProductos.add(lechuga);
		obtenerListaProductos.add(tomate);
		
		return obtenerListaProductos;
	}

	public static void setObtenerListaProductos(ArrayList<Producto> obtenerListaProductos) {
		Producto.obtenerListaProductos = obtenerListaProductos;
	}
}
