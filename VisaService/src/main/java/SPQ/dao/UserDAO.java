package SPQ.dao;

import java.util.Arrays;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;
import javax.jdo.Query;
import SPQ.data.User;

public class UserDAO implements IUserDAO{
	private PersistenceManagerFactory persistenceManagerFactory;

	public UserDAO() {
		this.persistenceManagerFactory = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
	}

	public boolean updateUser (User user, double price) {
		//Restamos el precio de los productos al saldo del usuario
		user.setSaldo(user.getSaldo() - price);
		
		PersistenceManager pm = this.persistenceManagerFactory.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(3);

		Transaction tx = pm.currentTransaction();

		try {
			System.out.println("   * Actualizando user: " + user.getUsername());

			tx.begin();
			Query<?> query = pm.newQuery(
					"UPDATE " + User.class.getName() + 
					" SET SALDO = '" + String.valueOf(user.getSaldo()) +
					"' WHERE USERNAME == '" + user.getUsername() +"'");
			query.setUnique(true);
			user = (User) query.execute();
			tx.commit();

		} catch (Exception ex) {
			System.out.println("   $ Error updating an user: " + ex.getMessage());
			return (false);
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
				return (false);
			}

			pm.close();
			
		}
		return (true);

	}
	
	//Buscar usuario por nombre
	public User getUser(String username) {
		PersistenceManager pm = this.persistenceManagerFactory.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(3);

		Transaction tx = pm.currentTransaction();
		User user = null;

		try {
			System.out.println("   * Buscando user: " + username);

			tx.begin();
			Query<?> query = pm.newQuery("SELECT FROM " + User.class.getName() + " WHERE email == '" + username +"'");
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