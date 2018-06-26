/**@package SPQ.data
 * This package contains the classes that define the object user and product, their parameters and their constructors
 */
package SPQ.data;
import java.io.Serializable;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;


@PersistenceCapable(detachable = "true")
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private double price;
	private int quantity;
	private double sale;

	/**Constructor of Product.class
	 * 
	 * @param name
	 * @param price
	 * @param quantity
	 * @param sale
	 */
	
	public Product (String name, double price, int quantity, double sale) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.sale = sale;
	}
	
	/**
	 * Return the name attribute of the class
	 * @return name instance
	 */
	
	public String getName() {
		return name;
	}
	
	/**
	 * Sets name attribute of the class.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Return the price attribute of the class
	 * @return price instance
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 * Sets price attribute of the class.
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	
	/**
	 * Return the quantity attribute of the class
	 * @return quantity instance
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Sets quantity attribute of the class.
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	/**
	 * Return the sale attribute of the class
	 * @return sale instance
	 */
	public double getSale() {
		return sale;
	}
	
	/**
	 * Sets sale attribute of the class.
	 */
	public void setSale(double sale) {
		this.sale = sale;
	}
}


