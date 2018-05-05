package SPQ.dao;

import SPQ.data.User;

public interface IUserDAO {
	
	boolean updateUserPayPalPassword (User user);
	boolean updateUserCardNumber (User user);
	boolean updateUserPayPalEmail (User user);
	boolean updateShoppingList (User user);
	boolean storeUser(User user);
	User retrieveUser(String username);
	void updateUser(User user);
	

}
