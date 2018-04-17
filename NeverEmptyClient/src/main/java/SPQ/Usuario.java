package SPQ;

import java.util.ArrayList;

public class Usuario {

	//3 atributos
	private String username;
	private String password;
	private String mail;
	
	
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
	
	public ArrayList<Usuario> rellenar (ArrayList<Usuario> UsuarioRegistrados){
		
		Usuario u1= new Usuario("jesus","12");
		
		UsuarioRegistrados.add(u1);
		
		return UsuarioRegistrados;
	}

	@Override
	public String toString() {
		return "Usuario [username=" + username + ", password=" + password + ", mail=" + mail + "]";
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
