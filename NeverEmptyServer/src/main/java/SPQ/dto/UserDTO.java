package SPQ.dto;

import java.io.Serializable;

import SPQ.data.User;

public class UserDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String username;
	private String email = null;
	private String password;
	private String registerMethod = null;
	
	private String payPalEmail = null;
	private String payPalPassword = null;
	
	private int cardNumber = -1;

	public UserDTO (User user) {
		super();
		this.username = user.getUsername();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.registerMethod = user.getRegisterMethod();
		this.payPalEmail = user.getPayPalEmail();
		this.payPalPassword = user.getPayPalPassword();
		this.cardNumber = user.getCardNumber();
	}
	
	public UserDTO(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public UserDTO(String username, String password, String email) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	public UserDTO(String username, String email, String password, String registerMethod, String payPalEmail,
			String payPalPassword, int cardNumber) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.registerMethod = registerMethod;
		this.payPalEmail = payPalEmail;
		this.payPalPassword = payPalPassword;
		this.cardNumber = cardNumber;
	}

	public UserDTO(String username, String email, String password, String registerMethod) {
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
}
