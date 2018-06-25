package SPQ.dao;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import org.apache.log4j.Logger;

import javax.jdo.Query;
import SPQ.data.User;

public class UserDAO implements IUserDAO{
	private PersistenceManagerFactory persistenceManagerFactory;
	static Logger logger = Logger.getLogger(UserDAO.class.getName());
	public UserDAO() {
		this.persistenceManagerFactory = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
	}


	//Buscar usuario por email
	public User getUser(String email) {
		PersistenceManager pm = this.persistenceManagerFactory.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(3);

		Transaction tx = pm.currentTransaction();
		User user = null;

		try {
			logger.info("Buscando user: " + email);
			tx.begin();
			Query<?> query = pm.newQuery("SELECT FROM " + User.class.getName() + " WHERE email == '" + email +"'");
			query.setUnique(true);
			user = (User) query.execute();
			tx.commit();

		} catch (Exception ex) {
			logger.error("Error retreiving an user: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}

		return user;
	}
}

