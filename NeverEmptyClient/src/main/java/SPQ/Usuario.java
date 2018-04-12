package SPQ;

public class Usuario {

	//3 atributos
	private String Username;
	private String password;
	private String mail;
	
	
	//Constructor
	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//Getters y Setters
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
}
