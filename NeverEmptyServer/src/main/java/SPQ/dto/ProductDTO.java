package SPQ.dto;

import java.io.Serializable;

public class ProductDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String nombreProduct;
	private double precioProduct;
	
	public ProductDTO (String nombreProduct,double precioProduct) {
		this.nombreProduct = nombreProduct;
		this.precioProduct = precioProduct;
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
	
	
	
	
	
}
