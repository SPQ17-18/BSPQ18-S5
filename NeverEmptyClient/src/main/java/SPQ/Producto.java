package SPQ;

import java.util.ArrayList;

public class Producto {
	private String nombreProducto;
	private double precioProducto;
	private int quantity;
	
	public Producto(String nombreProducto, double precioProducto, int quanity) {
		super();
		this.nombreProducto = nombreProducto;
		this.precioProducto = precioProducto;
		this.quantity = quantity;
	}
	
	public Producto() {
	}
	
//	public ArrayList<Producto> rellenarArrayProduto (ArrayList<Producto> producto){
//		
//		Producto zanahoria = new Producto("Zanahoria", 3.99);
//		Producto platano = new Producto("Platano", 2.99);
//		Producto lechuga = new Producto("Lechuga", 0.99);
//		Producto tomate = new Producto("Tomate", 2.99);
//		
//		producto.add(zanahoria);
//		producto.add(platano);
//		producto.add(lechuga);
//		producto.add(tomate);
//		
//		return producto;
//		
//	}

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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
