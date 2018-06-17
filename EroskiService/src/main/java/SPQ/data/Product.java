package SPQ.data;

import java.io.Serializable;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable(detachable = "true")
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
public class Product implements Serializable{

	private static final long serialVersionUID = 1L;
	String name;
	double price;
	float sale = 0;
	
	public Product (String name, double price) {
		this.name = name;
		this.price = price;
	}
	
	public Product(String name, double price, float sale) {
		super();
		this.name = name;
		this.price = price;
		this.sale = sale;
	}

	public float getSale() {
		return sale;
	}

	public void setSale(float sale) {
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
	
}
