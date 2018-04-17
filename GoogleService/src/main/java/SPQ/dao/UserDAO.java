package SPQ.dao;

import java.util.Arrays;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;
import javax.jdo.Query;
import SPQ.data.User;

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
	private PersistenceManagerFactory persistenceManagerFactory;

	public UserDAO() {
		this.persistenceManagerFactory = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
	}

	/*
	public User getUser(String email) {
		int index = Arrays.asList(tmpUsers).indexOf(email);
		String password = tmpPasswords[index];

		User user = new User(email, password);
		return user;
	}
	 */

	//Buscar usuario por email
	public User getUser(String email) {
		PersistenceManager pm = this.persistenceManagerFactory.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(3);

		Transaction tx = pm.currentTransaction();
		User user = null;

		try {
			System.out.println("   * Buscando user: " + email);

			tx.begin();
			Query<?> query = pm.newQuery("SELECT FROM " + User.class.getName() + " WHERE email == '" + email +"'");
			query.setUnique(true);
			user = (User) query.execute();
			tx.commit();

		} catch (Exception ex) {
			System.out.println("   $ Error retreiving an user: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}

		return user;



	}
}
