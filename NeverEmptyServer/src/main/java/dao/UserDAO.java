package dao;

import java.util.Arrays;

import data.User;

public class UserDAO {
	private User[] tmpUsers = {
			new User("A", "a", ""),
			new User("Enara", "enara.etxaniz@opendeusto.es", ""),
			new User("Jesus", "jesus.delapisa@opendeusto.es", ""),
			new User("Cristian", "cristian.perez@opendeusto.es", ""),
			new User("Alvaro", "arosa001@opendeusto.es", "")
			};
	
	public UserDAO() {
	
	}
	
	public boolean storeUser (User user) {
		return false;
	}
	public User getUser (User user) {
		for (User OneUser : tmpUsers) {
			if(OneUser.getUsername().equals(user.getUsername())) {
				user.setEmail(OneUser.getEmail());
				return user;
			}
		}
		return null;
	}
}
