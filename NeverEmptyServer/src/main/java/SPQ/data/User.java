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
	
	public User(String username, String email, String password, String registerMethod) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.registerMethod = registerMethod;
		}

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
    public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCardholder() {
		return cardholder;
	}

	public void setCardholder(String cardholder) {
		this.cardholder = cardholder;
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

	public long getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
	}

	
	
}
