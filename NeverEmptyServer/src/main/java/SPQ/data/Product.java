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

	public Product (String name, double price, int quantity, double sale) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.sale = sale;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public double getSale() {
		return sale;
	}

	public void setSale(double sale) {
		this.sale = sale;
	}
}


