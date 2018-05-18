package SPQ.dto;

import java.io.Serializable;
import java.util.ArrayList;

import SPQ.data.Product;

public class ProductDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	ArrayList<Product> productList = new ArrayList<Product>();

	public ArrayList<Product> getProductList() {
		return productList;
	}

	public void setProductList(ArrayList<Product> productList) {
		this.productList = productList;
	}

	public ProductDTO(ArrayList<Product> productList) {
		super();
		this.productList = productList;
	}
	
	
}
