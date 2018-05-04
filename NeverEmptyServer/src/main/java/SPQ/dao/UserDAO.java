package SPQ.dao;

import java.util.Arrays;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import SPQ.data.User;

public class UserDAO {
	private PersistenceManagerFactory persistenceManagerFactory;

	public UserDAO() {
		this.persistenceManagerFactory = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
	}

	private User[] tmpUsers = {
			new User("A", "a", ""),
			new User("Enara", "enara.etxaniz@opendeusto.es", ""),
			new User("Jesus", "jesus.delapisa@opendeusto.es", ""),
			new User("Cristian", "cristian.perez@opendeusto.es", ""),
			new User("Alvaro", "arosa001@opendeusto.es", "")
	};

	public boolean storeUser(User user) {
		boolean stored = false;
		PersistenceManager pm = this.persistenceManagerFactory.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();
			System.out.println("   * Storing a user: " + user);
			pm.makePersistent(user);
			tx.commit();
			stored = true;
		} catch (Exception ex) {
			System.out.println("   $ Error storing a user: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}		
			pm.close();
		}
		return stored;
	}

	//Buscar usuario por nombre
	public User getUser(User user) {
		PersistenceManager pm = this.persistenceManagerFactory.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(3);

		Transaction tx = pm.currentTransaction();
		User newUser = null;

		try {
			System.out.println("   * Buscando user: " + user.getUsername());

			tx.begin();
			Query<?> query = pm.newQuery("SELECT FROM " + User.class.getName() + " WHERE username == '" + user.getUsername() +"'");
			query.setUnique(true);
			newUser = (User) query.execute();
			tx.commit();

		} catch (Exception ex) {
			System.out.println("   $ Error retreiving an user: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}

		return newUser;
	}
	public void setUsermail(String email, String email2) {
		PersistenceManager pm = this.persistenceManagerFactory.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(3);

		Transaction tx = pm.currentTransaction();
		User user = null;
		try {
			System.out.println("* modificando mail user" + email);
			tx.begin();
			
			//UPDATE alumnos SET curso='secundaria' WHERE curso='primaria'

			
			Query<?> query = pm.newQuery("UPDATE " + User.class.getName() +"SET email= '"+ email2 + "'" + " WHERE email == '" + email +"'");
			query.setUnique(true);
			user = (User) query.execute();
			tx.commit();
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
		
	}
	
	public void setUser(String user, String user2) {
		PersistenceManager pm = this.persistenceManagerFactory.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(3);

		Transaction tx = pm.currentTransaction();
		User usuario = null;
		try {
			System.out.println("* modificando user" + user);
			tx.begin();	
			//no estoy muy seguro de si en la tabla es user o User (con u mayuscula)
			Query<?> query = pm.newQuery("UPDATE " + User.class.getName() +"SET user= '"+ user2 + "'" + " WHERE user == '" + user +"'");
			query.setUnique(true);
			usuario = (User) query.execute();
			tx.commit();
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
	
	}
}
