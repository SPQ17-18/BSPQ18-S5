package SPQ.data;

import java.io.Serializable;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable(detachable = "true")
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
public class User implements Serializable{
	private String username;
	private String email;
	private String password;
	private double balance;
	
	public User(String email, String password, double saldo) {
		super();
		this.email = email;
		this.password = password;
		this.balance = balance;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public double getBalance () {
		return balance;
	}
	public void setBalance (double balance) {
		this.balance = balance;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUsername () {
		return username;
	}
	public void setUsername (String username) {
		this.username = username;
	}
}
