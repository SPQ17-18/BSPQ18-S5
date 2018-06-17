package SPQ.dao;

import SPQ.data.User;

public interface IUserDAO {
	public boolean pay (User user, double total);
	public void storeUser(User user);
}
