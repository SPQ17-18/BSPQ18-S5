package SPQ.data;


import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
public class Product {
	
	private String nombreProduct;
	private double precioProduct;
	
	public Product(String nombreProduct, double precioProduct) {
		super();
		this.nombreProduct= nombreProduct;
		this.precioProduct= precioProduct;
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
