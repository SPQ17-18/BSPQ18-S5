/** @package SPQ.dto
 *  This package prepare some object to transfer to client
 */

package SPQ.dto;

import java.io.Serializable;
import java.util.ArrayList;

import SPQ.data.Product;

/** 
 * @class ProductDTO
 * @brief This class prepares a product to be transferred.
 */
public class ProductDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	ArrayList<Product> productList = new ArrayList<Product>();

	
	/**
	 * This method returns the complete list of products in the list
	 * @return productList
	 */
	public ArrayList<Product> getProductList() {
		return productList;
	}

	/**
	 * Sets productList attribute of the class.
	 */
	public void setProductList(ArrayList<Product> productList) {
		this.productList = productList;
	}

	
	/**
	 * Constructor of ProductDTO
	 * @param productList
	 */
	public ProductDTO(ArrayList<Product> productList) {
		super();
		this.productList = productList;
	}
	
	
}
