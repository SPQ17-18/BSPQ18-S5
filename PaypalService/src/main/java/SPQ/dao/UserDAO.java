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

	@Override
	public boolean pay (User user, double total) {
		
		PersistenceManager pm = this.persistenceManagerFactory.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(3);

		Transaction tx = pm.currentTransaction();

		try {
			System.out.println("   * Actualizando user: " + user.getEmail());

			tx.begin();
			Query<?> query = pm.newQuery(
					"SELECT FROM " + User.class.getName() + 
					" WHERE email == '" + user.getEmail() + "'");
			query.setUnique(true);
			user = (User) query.execute();
			if(user == null) {
				throw new Exception("Usuario no registrado. Pago rechazado.");
			}
			if(user.getBalance() >= total) {
				user.setBalance(user.getBalance() - total);
			}else {
				throw new Exception("Pago rechazado, la cuenta no tiene suficiente credito.");
			}
			
			tx.commit();

		} catch (Exception ex) {
			logger.error("Error updating an user: " + ex.getMessage());
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

	@Override
	public void storeUser(User user) {
		PersistenceManager pm = this.persistenceManagerFactory.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();
			logger.info("   * Storing a user: " + user);
			pm.makePersistent(user);
			tx.commit();
		} catch (Exception ex) {
			logger.error("   $ Error storing a user: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}		
			pm.close();
		}
		
	}
	
	public static void main(String[] args) {
		User user = new User("enara@paypal.es", "1234", 75);
		UserDAO udao = new UserDAO();
		udao.storeUser(user);
	}
	
	
}
