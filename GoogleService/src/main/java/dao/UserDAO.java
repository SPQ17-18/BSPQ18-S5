package dao;

import java.util.Arrays;

import data.User;

public class UserDAO implements IUserDAO{
	String[] tmpUsers = {
			"a",
			"enara.etxaniz@opendeusto.es",
			"jesus.delapisa@opendeusto.es",
			"cristian.perez@opendeusto.es",
			"arosa001@opendeusto.es"};

	String[] tmpPasswords = { 
			"123",
			"1234",
			"12345",
			"123456",
			"1234567"
			};
	
	public User getUser(String email) {
		int index = Arrays.asList(tmpUsers).indexOf(email);
		String password = tmpPasswords[index];
		
		User user = new User(email, password);
		return user;
	}
	
}
