package SPQ.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import SPQ.data.Product;
import SPQ.data.User;

public class UserDAO {
	
	public static void main(String[] args) {
		UserDAO udao = new UserDAO();
		User user = new User("Enara", "enara96etxaniz@gmail.com", "123", "Google");

		Product p1 = new Product("Culo", 1, 5);
		Product p2 = new Product("Pera", 0.5, 10);
		List<Product> products = new ArrayList<>();
		products.add(p1);
		products.add(p2);
		
		user.setShoppingList(products);
		
		udao.updateShoppingList(user);
	}
	
	private PersistenceManagerFactory persistenceManagerFactory;

	public UserDAO() {
		this.persistenceManagerFactory = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
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
}
