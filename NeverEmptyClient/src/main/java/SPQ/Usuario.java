package SPQ;

import java.util.ArrayList;

public class Usuario {

	//3 atributos
	private String username;
	private String password;
	private String mail;
	
	
	//Constructor
	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	//Constructor con campos
	public Usuario(String username, String pass) {
		super();
		this.username = username;
		this.password = pass;
		
	}


	//Constructor con campos
	public Usuario(String username, String pass,String mail) {
		super();
		this.username = username;
		this.password = pass;
		this.mail=mail;
	}
	
	public ArrayList<Usuario> rellenar (ArrayList<Usuario> usuario){
		
		Usuario u1= new Usuario("jesus","12");
		
		usuario.add(u1);
		
		return usuario;
	}

	//Getters y Setters
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
