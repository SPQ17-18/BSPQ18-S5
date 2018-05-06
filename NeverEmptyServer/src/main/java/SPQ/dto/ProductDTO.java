package SPQ.dto;

import java.io.Serializable;

public class ProductDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String nombreProduct;
	private double precioProduct;
	private int quantity;
	
	public ProductDTO (String nombreProduct,double precioProduct, int quantity) {
		this.nombreProduct = nombreProduct;
		this.precioProduct = precioProduct;
		this.quantity= quantity;
	}

	public String getNombreProduct() {
		return nombreProduct;
	}

	public void setNombreProduct(String nombreProduct) {
		this.nombreProduct = nombreProduct;
	}

	public double getPrecioProduct() {
		return precioProduct;
	}

	public void setPrecioProduct(double precioProduct) {
		this.precioProduct = precioProduct;
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	
	
	
}
