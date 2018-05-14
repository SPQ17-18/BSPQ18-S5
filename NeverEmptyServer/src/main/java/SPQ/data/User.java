package SPQ.data;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.Element;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import SPQ.data.Product;

/**
 * La clase usuario
 * 
 *
 */
@PersistenceCapable(detachable = "true")
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
public class User {
	private String username;
	private String email;
	private String password;
	private String registerMethod;
	
	private String payPalEmail = null;
	private String payPalPassword = null;
	
	private int cardNumber = -1;
	

    @Element(column="USER_ID")
	private List<Product> shoppingList = null;
    

	
	/**
	 * Constructor que crea un usuario dadas las siguientes caracteristicas
	 * @param username
	 * @param email
	 * @param password
	 * @param registerMethod
	 */
	public User(String username, String email, String password, String registerMethod) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.registerMethod = registerMethod;
		}
	
	
	/**
	 * @return username
	 * el nombre de usuario del usuario
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username 
	 * modifica el nombre de usuario
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return email
	 * Devuelve el email de un usuario
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 * modifica el email del usuario
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return password
	 * Devuelve la contrasenia de un usuario
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 * Modifica la contrasenia de un usuario
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return registerMethod
	 * Devuelve el metodo de registro del usuario: Google o Facebook
	 */
	public String getRegisterMethod() {
		return registerMethod;
	}

	/**
	 * @param registerMethod
	 * Modifica el metodo de registro de un usuario
	 */
	public void setRegisterMethod(String registerMethod) {
		this.registerMethod = registerMethod;
	}

	/**
	 * @return email de PayPal
	 * Devuelve el email de Paypal
	 */
	public String getPayPalEmail() {
		return payPalEmail;
	}

	/**
	 * @param email del Paypal
	 * Modifica el email de Paypal
	 */
	public void setPayPalEmail(String payPalEmail) {
		this.payPalEmail = payPalEmail;
	}

	/**
	 * @return contrasenia de Paypal
	 * Devuelve la contrasenia de Paypal
	 */
	public String getPayPalPassword() {
		return payPalPassword;
	}

	/**
	 * @param payPalPassword
	 * Modifica la contrasenia de Paypal
	 */
	public void setPayPalPassword(String payPalPassword) {
		this.payPalPassword = payPalPassword;
	}

	/**
	 * @return numero de credito
	 * Devuelve el numero de credito del usuario
	 */
	public int getCardNumber() {
		return cardNumber;
	}

	/**
	 * @param cardNumber
	 * Modifica el numero de credito del usuario
	 */
	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}

	/**
	 * @return lista de productos
	 * Devuelve la lista de productos del usuario
	 */
	public List<Product> getShoppingList() {
		return shoppingList;
	}

	/**
	 * @param shoppingList
	 * Modifica la lista de productos del usuario
	 */
	public void setShoppingList(List<Product> shoppingList) {
		this.shoppingList = shoppingList;
	}
	
	
}
