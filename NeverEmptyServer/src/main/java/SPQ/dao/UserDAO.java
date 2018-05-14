package SPQ.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import org.apache.log4j.Logger;

import SPQ.data.User;

/**
 * Clase UserDAO. Permite la conexion con la bd.
 *
 */
public class UserDAO implements IUserDAO {
	
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
	
	private PersistenceManagerFactory persistenceManagerFactory;
	private String usermail;
	
	static Logger logger = Logger.getLogger(UserDAO.class.getName());

	/**
	 * Crea un objeto UserDAO
	 */
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
		    System.out.println("   $ Updated paypal password! ");
		}catch (Exception ex) {
			System.out.println("   $ Error updating paypal password: " + ex.getMessage());
			
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
		    System.out.println("   $ Updated card number! ");
		}catch (Exception ex) {
			System.out.println("   $ Error updating card number: " + ex.getMessage());
			
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
		    System.out.println("   $ Updated paypal email! ");
		}catch (Exception ex) {
			System.out.println("   $ Error updating paypal email: " + ex.getMessage());
			
		}
		finally {
			
		    pm.close();
		}
		return updated;
	}
	
	public boolean modifyEmail (User user) {
		boolean updated = false;
		PersistenceManager pm = this.persistenceManagerFactory.getPersistenceManager();
		try {
			Query<?> query =  pm.newQuery("SELECT FROM " + User.class.getName() + " WHERE username == '" + user.getUsername() +"'");
			query.setUnique(true);
			User userFromDb = (User) query.execute();
			userFromDb.setPayPalEmail(user.getEmail());
		    updated = true;
		    System.out.println("   $ Updated email! ");
		}catch (Exception ex) {
			System.out.println("   $ Error updating email: " + ex.getMessage());
			
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
		    System.out.println("   $ Updated shopping list! ");
		}catch (Exception ex) {
			System.out.println("   $ Error updating shopping list: " + ex.getMessage());
			
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
	
	public boolean loginUser(String username, String password) {
		boolean resul = false;
		PersistenceManager pm = persistenceManagerFactory.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();
			Extent<User> ex = pm.getExtent(User.class, true);
			for (User u : ex) {
				if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
					resul = true;
					this.usermail = u.getEmail();
				}
			}
			tx.commit();
		} catch (Exception ex) {
			logger.error("   $ Error login for user: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		return resul;
	}
	
	public void deleteUser(User usuario) {
		PersistenceManager pm = persistenceManagerFactory.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();
			Query<User> query = pm.newQuery(User.class, "username =='" + usuario.getUsername() + "'");
			Collection<?> result = (Collection<?>) query.execute();
			User user = (User) result.iterator().next();
			query.close(result);
			pm.deletePersistent(user);
			tx.commit();
		} catch (Exception ex) {
			logger.error( "Error cleaning a film: " + ex.getMessage());
		
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			if (pm != null && !pm.isClosed()) {
				pm.close();
			}
		}
	}
	
	public List<User> getUsuarios() {
		PersistenceManager pm = persistenceManagerFactory.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(3);
		
		Transaction tx = pm.currentTransaction();
		List<User> Usuarios = new ArrayList<User>();
		
		try {
			logger.info("   * Sacando un Extent para Usuarios.");
			
			tx.begin();			
			Extent<User> extent = pm.getExtent(User.class, true);
			
			for (User Usuario : extent) {
				Usuarios.add(Usuario);
			}

			tx.commit();			
		} catch (Exception ex) {
			logger.error("   $ Error retrieving an extent: " + ex.getMessage());
	    } finally {
	    	if (tx != null && tx.isActive()) {
	    		tx.rollback();
	    	}

    		pm.close();    		
	    }
	    			
		return Usuarios;
	}

	
	public boolean checkUser(User usuario) {
		boolean resul = false;
		PersistenceManager pm = persistenceManagerFactory.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();
			logger.info("   * Checking if " + usuario + " exists in the database");
			Extent<User> ex = pm.getExtent(User.class, true);
			for (User u : ex) {
				if (u.getUsername().equals(usuario.getUsername())) {
					resul = true;
				}
			}
			tx.commit();
		} catch (Exception ex) {
			logger.error("   $ Error during the checking of user: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
		return resul;
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


	@Override
	public User retrieveUser(String login) {
		User user = null;
		PersistenceManager pm = persistenceManagerFactory.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(2);
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			user = pm.getObjectById(User.class, login);
			tx.commit();
		} catch (javax.jdo.JDOObjectNotFoundException jonfe)
		{
			System.out.println("User does not exist: " + jonfe.getMessage());
		}
		
		finally {
	    	if (tx != null && tx.isActive()) {
	    		tx.rollback();
	    	}
				
    		pm.close();
	    }

		return user;
	}
	
	@Override
	public void updateUser(User u) {
		PersistenceManager pm = persistenceManagerFactory.getPersistenceManager();
	    Transaction tx = pm.currentTransaction();
	    
	    try {
	    	tx.begin();
	    	pm.makePersistent(u);
	    	tx.commit();
	     } catch (Exception ex) {
		   	System.out.println("Error updating a user: " + ex.getMessage());
	     } finally {
		   	if (tx != null && tx.isActive()) {
		   		tx.rollback();
		   	}
				
	   		pm.close();
	     }

	}


}
