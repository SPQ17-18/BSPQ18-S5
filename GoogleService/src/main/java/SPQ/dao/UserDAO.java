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
//	public void setUsermail(String email, String email2) {
//		PersistenceManager pm = this.persistenceManagerFactory.getPersistenceManager();
//		pm.getFetchPlan().setMaxFetchDepth(3);
//
//		Transaction tx = pm.currentTransaction();
//		User user = null;
//		try {
//			System.out.println("* modificando mail user" + email);
//			tx.begin();
//			
//			//UPDATE alumnos SET curso='secundaria' WHERE curso='primaria'
//
//			
//			Query<?> query = pm.newQuery("UPDATE " + User.class.getName() +"SET email= '"+ email2 + "'" + " WHERE email == '" + email +"'");
//			query.setUnique(true);
//			user = (User) query.execute();
//			tx.commit();
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//		}finally {
//			if (tx != null && tx.isActive()) {
//				tx.rollback();
//			}
//
//			pm.close();
//		}
//		
//	}
//	
//	public void setUser(String user, String user2) {
//		PersistenceManager pm = this.persistenceManagerFactory.getPersistenceManager();
//		pm.getFetchPlan().setMaxFetchDepth(3);
//
//		Transaction tx = pm.currentTransaction();
//		User usuario = null;
//		try {
//			System.out.println("* modificando user" + user);
//			tx.begin();	
//			//no estoy muy seguro de si en la tabla es user o User (con u mayuscula)
//			Query<?> query = pm.newQuery("UPDATE " + User.class.getName() +"SET user= '"+ user2 + "'" + " WHERE user == '" + user +"'");
//			query.setUnique(true);
//			usuario = (User) query.execute();
//			tx.commit();
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//		}finally {
//			if (tx != null && tx.isActive()) {
//				tx.rollback();
//			}
//
//			pm.close();
//		}
//	
//	}
	
	
//}
