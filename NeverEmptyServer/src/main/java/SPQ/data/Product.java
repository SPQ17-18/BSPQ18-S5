package SPQ.data;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;


/**
 * Clase producto
 *
 */
@PersistenceCapable(detachable = "true")
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
public class Product {
	private String name;
	private double price;
	private int quantity;
	
	/**
	 * Constructor que crea la clase producto con las siguientes caracteristicas:
	 * @param name
	 * @param price
	 * @param quantity
	 */
	public Product (String name, double price, int quantity) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}
	
	/**
	 * @return nombre del producto
	 * devuelve el nombre del producto
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name
	 * modifica el nombre del producto
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return precio
	 * devuelve el precio del producto
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 * @param price
	 * modifica el precio del producto
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return cantidad
	 * devuelve la cantidad del producto
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity
	 * modifica la cantidad del producto
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}


