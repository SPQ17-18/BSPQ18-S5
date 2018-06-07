package SPQ.dto;

import java.io.Serializable;

import SPQ.data.User;

public class UserDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private String email = null;
	private String password;


	public UserDTO (User user) {
		super();
		this.email = user.getEmail();
		this.password = user.getPassword();

	}
	
	public UserDTO(String password, String email) {
		super();
		this.password = password;
		this.email = email;
	}
	
	public UserDTO(String email, String password, String registerMethod) {
		super();
		this.email = email;
		this.password = password;

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
}

