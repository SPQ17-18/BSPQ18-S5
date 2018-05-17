package SPQ.dao;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import org.apache.log4j.Logger;

import SPQ.data.User;

public class UserDAO {
	
//	public static void main(String[] args) {
//		UserDAO udao = new UserDAO();
//		User user = new User("Enara", "enara96etxaniz@gmail.com", "123", "Google");
//
//		Product p1 = new Product("Culo", 1, 5);
//		Product p2 = new Product("Pera", 0.5, 10);
//		List<Product> products = new ArrayList<>();
//		products.add(p1);
//		products.add(p2);
//		
//		user.setShoppingList(products);
//		
//		udao.updateShoppingList(user);
//	}
	
	static Logger logger = Logger.getLogger(UserDAO.class.getName());
	
	private PersistenceManagerFactory persistenceManagerFactory;

	public UserDAO() {
		this.persistenceManagerFactory = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
	}

	public boolean updateUserPayPalPassword (User user) {
		boolean updated = false;
		PersistenceManager pm = this.persistenceManagerFactory.getPersistenceManager();
		try {
			Query<?> query =  pm.newQuery("SELECT FROM " + User.class.getName() + " WHERE username == '" + user.getUsername() +"'");
			query.setUnique(true);
			User userFromDb = (User) query.execute();
			userFromDb.setPayPalPassword(user.getPayPalPassword());
		    updated = true;
		    logger.info("   $ Updated paypal password! ");
		}catch (Exception ex) {
			logger.error("   $ Error updating paypal password: " + ex.getMessage());
			
		}
		finally {
			
		    pm.close();
		}
		return updated;
	}
	
	public boolean updateUserCardNumber (User user) {
		boolean updated = false;
		PersistenceManager pm = this.persistenceManagerFactory.getPersistenceManager();
		try {
			Query<?> query =  pm.newQuery("SELECT FROM " + User.class.getName() + " WHERE username == '" + user.getUsername() +"'");
			query.setUnique(true);
			User userFromDb = (User) query.execute();
			userFromDb.setCardNumber(user.getCardNumber());
		    updated = true;
		    logger.info("   $ Updated card number! ");
		}catch (Exception ex) {
			logger.error("   $ Error updating card number: " + ex.getMessage());
			
		}
		finally {
			
		    pm.close();
		}
		return updated;
	}
	
	public boolean updateUserPayPalEmail (User user) {
		boolean updated = false;
		PersistenceManager pm = this.persistenceManagerFactory.getPersistenceManager();
		try {
			Query<?> query =  pm.newQuery("SELECT FROM " + User.class.getName() + " WHERE username == '" + user.getUsername() +"'");
			query.setUnique(true);
			User userFromDb = (User) query.execute();
			userFromDb.setPayPalEmail(user.getPayPalEmail());
		    updated = true;
		    logger.info("   $ Updated paypal email! ");
		}catch (Exception ex) {
			logger.error("   $ Error updating paypal email: " + ex.getMessage());
			
		}
		finally {
			
		    pm.close();
		}
		return updated;
	}
	
	public boolean updateShoppingList (User user) {
		boolean updated = false;
		PersistenceManager pm = this.persistenceManagerFactory.getPersistenceManager();
		try {
			Query<?> query =  pm.newQuery("SELECT FROM " + User.class.getName() + " WHERE username == '" + user.getUsername() +"'");
			query.setUnique(true);
			User userFromDb = (User) query.execute();
			userFromDb.setShoppingList(user.getShoppingList());
		    updated = true;
		    logger.info("   $ Updated shopping list! ");
		}catch (Exception ex) {
			logger.error("   $ Error updating shopping list: " + ex.getMessage());
			
		}
		finally {
			
		    pm.close();
		}
		return updated;
	}

	public boolean storeUser(User user) {
		boolean stored = false;
		PersistenceManager pm = this.persistenceManagerFactory.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();
			logger.info("   * Storing a user: " + user);
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
	public User getUser(String username) {
		PersistenceManager pm = this.persistenceManagerFactory.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(3);

		Transaction tx = pm.currentTransaction();
		User user = null;

		try {
			logger.info("   * Buscando user: " + username);

			tx.begin();
			Query<?> query = pm.newQuery("SELECT FROM " + User.class.getName() + " WHERE username == '" + username +"'");
			query.setUnique(true);
			user = (User) query.execute();
			tx.commit();

		} catch (Exception ex) {
			logger.error("   $ Error retreiving an user: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}

		return user;
	}
	
	public void setUsermail(String email, String email2) {
		PersistenceManager pm = this.persistenceManagerFactory.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(3);

		Transaction tx = pm.currentTransaction();
		User user = null;
		try {
			logger.info("* modificando mail user" + email);
			tx.begin();
			
			//UPDATE alumnos SET curso='secundaria' WHERE curso='primaria'

			
			Query<?> query = pm.newQuery("UPDATE " + User.class.getName() +"SET email= '"+ email2 + "'" + " WHERE email == '" + email +"'");
			query.setUnique(true);
			user = (User) query.execute();
			tx.commit();
			
		} catch (Exception e) {
			
			logger.error("   $ Error retreiving an user: " + e.getMessage());
		}finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
		
	}
	

}
