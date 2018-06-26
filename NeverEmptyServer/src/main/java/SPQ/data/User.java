/**@package SPQ.data
 * This package contains the classes that define the object user and product, their parameters and their constructors
 */
package SPQ.data;

import java.util.List;

import javax.jdo.annotations.Element;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;

import SPQ.dto.UserDTO;

@PersistenceCapable(detachable = "true")
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
public class User {

	private String username;
	private String email;
	private String password;
	private String registerMethod;
	
	private String address;
	
	private String payPalEmail = null;
	private String payPalPassword = null;
	
	private long cardNumber = -1;
	private String cardholder;
	
	/**Constructor of User.class
	 * 
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
	 * This method override the attributes of UserDTO
	 * @param userDTO
	 */
	public User(UserDTO userDTO) {
		super();
		this.username = userDTO.getUsername();
		this.email = userDTO.getEmail();
		this.password = userDTO.getPassword();
		this.registerMethod = userDTO.getRegisterMethod();
		
		this.address = userDTO.getAddress();
		this.payPalEmail = userDTO.getPayPalEmail();
		this.payPalPassword = userDTO.getPayPalPassword();
		
		this.cardNumber = userDTO.getCardNumber();
		this.cardholder = userDTO.getCardholder();
		
	}

	
	public void updateUser(User user) {
		this.username = user.getUsername();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.registerMethod = user.getRegisterMethod();
		
		this.address = user.getAddress();
		this.payPalEmail = user.getPayPalEmail();
		this.payPalPassword = user.getPayPalPassword();
		
		this.cardNumber = user.getCardNumber();
		this.cardholder = user.getCardholder();

	}
	
	/**
	 * Return the address attribute of the class
	 * @return name instance
	 */
    public String getAddress() {
		return address;
	}

    /**
	 * Sets address attribute of the class.
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Return the cardholder attribute of the class
	 * @return cardholder instance
	 */
	public String getCardholder() {
		return cardholder;
	}

	/**
	 * Sets cardholder attribute of the class.
	 */
	public void setCardholder(String cardholder) {
		this.cardholder = cardholder;
	}

	/**
	 * Return the username attribute of the class
	 * @return username instance
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets username attribute of the class.
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Return the email attribute of the class
	 * @return email instance
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets email attribute of the class.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Return the password attribute of the class
	 * @return password instance
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets password attribute of the class.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Return the registermethod attribute of the class
	 * @return registermethod instance
	 */
	public String getRegisterMethod() {
		return registerMethod;
	}

	/**
	 * Sets registermethod attribute of the class.
	 */
	public void setRegisterMethod(String registerMethod) {
		this.registerMethod = registerMethod;
	}

	/**
	 * Return the paypalemail attribute of the class
	 * @return paypalemail instance
	 */
	public String getPayPalEmail() {
		return payPalEmail;
	}

	/**
	 * Sets paypalemail attribute of the class.
	 */
	public void setPayPalEmail(String payPalEmail) {
		this.payPalEmail = payPalEmail;
	}

	/**
	 * Return the paypalpassword attribute of the class
	 * @return paypalpassword instance
	 */
	public String getPayPalPassword() {
		return payPalPassword;
	}

	/**
	 * Sets paypalpassword attribute of the class.
	 */
	public void setPayPalPassword(String payPalPassword) {
		this.payPalPassword = payPalPassword;
	}

	/**
	 * Return the cardnumber attribute of the class
	 * @return cardnumber instance
	 */
	public long getCardNumber() {
		return cardNumber;
	}

	/**
	 * Sets cardnumber attribute of the class.
	 */
	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
	}

	
	
}
