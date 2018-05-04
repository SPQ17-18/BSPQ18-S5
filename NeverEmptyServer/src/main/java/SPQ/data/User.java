package SPQ.data;

import java.util.List;

import javax.jdo.annotations.Element;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

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
	
	@Persistent(table="USER_PRODUCTS")
    @Join(column="USER_ID_OID")
    @Element(column="PRODUCT_ID_EID")
	private List<Product> shoppingList = null;
	
	public User(String username, String email, String password, String registerMethod) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.registerMethod = registerMethod;
		}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRegisterMethod() {
		return registerMethod;
	}

	public void setRegisterMethod(String registerMethod) {
		this.registerMethod = registerMethod;
	}

	public String getPayPalEmail() {
		return payPalEmail;
	}

	public void setPayPalEmail(String payPalEmail) {
		this.payPalEmail = payPalEmail;
	}

	public String getPayPalPassword() {
		return payPalPassword;
	}

	public void setPayPalPassword(String payPalPassword) {
		this.payPalPassword = payPalPassword;
	}

	public int getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}

	public List<Product> getShoppingList() {
		return shoppingList;
	}

	public void setShoppingList(List<Product> shoppingList) {
		this.shoppingList = shoppingList;
	}
	
	
}
