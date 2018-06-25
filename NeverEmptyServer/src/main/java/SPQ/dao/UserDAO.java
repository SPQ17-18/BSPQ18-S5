package SPQ.dao;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import org.apache.log4j.Logger;


import SPQ.data.User;

public class UserDAO {
	
	static Logger logger = Logger.getLogger(UserDAO.class.getName());
	
	private PersistenceManagerFactory persistenceManagerFactory;

	public UserDAO() {
		this.persistenceManagerFactory = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
	}

	public boolean updateUser(User user) {
		PersistenceManager pm = this.persistenceManagerFactory.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			logger.info("    * Updating a user: " + user.getUsername());
			Query<?> query = pm.newQuery(
					"SELECT FROM " + User.class.getName() + 
					" WHERE email == '" + user.getEmail() + "'");
			query.setUnique(true);
			User retrievedUser = (User) query.execute();
			if(retrievedUser == null) {
				throw new Exception("Error, el usuario que se pretende actualizar no está registrado.");
			}
			retrievedUser.updateUser(user);
			tx.commit();
		}catch (Exception e) {
			logger.error(UserDAO.class.getName() + ": " + e.getMessage());
			return false;
		}finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
				return (false);
			}

			pm.close();
			
		}
		return true;
	}
	
	public boolean storeUser(User user) {
		boolean stored = false;
		this.persistenceManagerFactory = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		PersistenceManager pm = this.persistenceManagerFactory.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();
			logger.info("   * Storing a user: " + user.getUsername());
			pm.makePersistent(user);
			tx.commit();
			stored = true;
		} catch (Exception ex) {
			logger.error("   $ Error storing a user: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}		
			pm.close();
		}
		return stored;
	}
	
	//Buscar usuario por nombre
	public User getUser(String username, String password) {
		PersistenceManager pm = this.persistenceManagerFactory.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(3);

		Transaction tx = pm.currentTransaction();
		User userFetched = null;

		try {
			logger.info("   * Buscando user: " + username);
			tx.begin();
			Query<?> query = pm.newQuery("SELECT FROM " + User.class.getName() + " WHERE username == '" + username +"' && password == '" + password + "'");
			query.setUnique(true);
			userFetched = (User) query.execute();
			tx.commit();

		} catch (Exception ex) {
			logger.error("   $ Error retreiving an user: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}

		return userFetched;
	}
	

}
