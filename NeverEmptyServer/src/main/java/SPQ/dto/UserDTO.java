/** @package SPQ.dto
 *  This package prepare some object to transfer to client
 */
package SPQ.dto;

import java.io.Serializable;

import SPQ.data.User;


/**This class prepare the object to be transferable
 *
 */
public class UserDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String username;
	private String email = null;
	private String password;
	private String registerMethod = null;
	private String address;
	
	private String payPalEmail = null;
	private String payPalPassword = null;
	
	private long cardNumber = -1;
	private String cardholder;

	
	/**
	 * Constructor of UserDTO
	 * @param user
	 */
	public UserDTO (User user) {
		super();
		this.username = user.getUsername();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.address = user.getAddress();
		this.registerMethod = user.getRegisterMethod();
		this.payPalEmail = user.getPayPalEmail();
		this.payPalPassword = user.getPayPalPassword();
		this.cardNumber = user.getCardNumber();
		this.cardholder = user.getCardholder();
	}
	
	/**
	 * Returns the address attribute of the class.
	 * @return address instance.
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
	 * Returns the cardholder attribute of the class.
	 * @return cardholder instance.
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

	
	/**Constructor of UserDTO
	 * 
	 * @param username
	 * @param password
	 */
	public UserDTO(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	/**Constructor of UserDTO
	 * 
	 * @param username
	 * @param password
	 * @param email
	 */
	public UserDTO(String username, String password, String email) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	/**Constructor of UserDTO 
	 * 
	 * @param username
	 * @param email
	 * @param password
	 * @param registerMethod
	 * @param address
	 * @param payPalEmail
	 * @param payPalPassword
	 * @param cardNumber
	 * @param cardholder
	 */
	public UserDTO(String username, String email, String password, String registerMethod, String address, String payPalEmail,
			String payPalPassword, long cardNumber, String cardholder) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.registerMethod = registerMethod;
		this.address = address;
		this.payPalEmail = payPalEmail;
		this.payPalPassword = payPalPassword;
		this.cardNumber = cardNumber;
		this.cardholder = cardholder;
	}

	/**
	 * Constructor of UserDTo
	 * @param username
	 * @param email
	 * @param password
	 * @param registerMethod
	 */
	public UserDTO(String username, String email, String password, String registerMethod) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.registerMethod = registerMethod;
	}

	/**
	 * Returns the username attribute of the class.
	 * @return username instance.
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
	 * Returns the email attribute of the class.
	 * @return email instance.
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
	 * Returns the password attribute of the class.
	 * @return password instance.
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
	 * Returns the registerMethod attribute of the class.
	 * @return registerMethod instance.
	 */
	public String getRegisterMethod() {
		return registerMethod;
	}

	/**
	 * Sets registerMethod attribute of the class.
	 */
	public void setRegisterMethod(String registerMethod) {
		this.registerMethod = registerMethod;
	}

	/**
	 * Returns the paypalEmail attribute of the class.
	 * @return cardnumber instance.
	 */
	public String getPayPalEmail() {
		return payPalEmail;
	}

	/**
	 * Sets paypalEmail attribute of the class.
	 */
	public void setPayPalEmail(String payPalEmail) {
		this.payPalEmail = payPalEmail;
	}

	/**
	 * Returns the getPayPal attribute of the class.
	 * @return GetPayPal instance.
	 */
	public String getPayPalPassword() {
		return payPalPassword;
	}

	/**
	 * Sets paypalPassword attribute of the class.
	 */
	public void setPayPalPassword(String payPalPassword) {
		this.payPalPassword = payPalPassword;
	}

	/**
	 * Returns the cardnumber attribute of the class.
	 * @return cardnumber instance.
	 */
	public long getCardNumber() {
		return cardNumber;
	}

	/**
	 * Sets cardNumber attribute of the class.
	 */
	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
	}
}
