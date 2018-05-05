package SPQ.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import SPQ.data.Product;
import SPQ.data.User;

public class ProductDAO {

	private PersistenceManagerFactory persistenceManagerFactory;
	static Logger logger = Logger.getLogger(ProductDAO.class.getName());
	
	public ProductDAO() {
		this.persistenceManagerFactory = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		
	}
	
	public boolean storeProduct(Product p) {
		boolean stored = false;
		PersistenceManager pm = this.persistenceManagerFactory.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();
			System.out.println("   * Storing a product: " + p);
			pm.makePersistent(p);
			tx.commit();
			stored = true;
		} catch (Exception ex) {
			System.out.println("   $ Error storing a product: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}		
			pm.close();
		}
		return stored;
	}
	
	//Obtener el producto por nombre
	public Product getProduct(Product p) {
		PersistenceManager pm = this.persistenceManagerFactory.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(3);

		Transaction tx = pm.currentTransaction();
		Product newProduct = null;

		try {
			System.out.println("   * Buscando producto: " + p.getName());

			tx.begin();
			Query<?> query = pm.newQuery("SELECT FROM " + Product.class.getName() + " WHERE productname == '" + p.getName() +"'");
			query.setUnique(true);
			newProduct = (Product) query.execute();
			tx.commit();

		} catch (Exception ex) {
			System.out.println("   $ Error retreiving a product: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}

		return newProduct;
	}
	
	//Eliminar el producto por nombre
	public Product deleteProduct(Product p) {
		PersistenceManager pm = this.persistenceManagerFactory.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(3);

		Transaction tx = pm.currentTransaction();
		Product productDelete = null;

		try {
			System.out.println("   * Eliminando producto: " + p.getName());

			tx.begin();
			Query<?> query = pm.newQuery("DELETE FROM " + Product.class.getName() + " WHERE productname == '" + p.getName() +"'");
			query.setUnique(true);
			productDelete = (Product) query.execute();
			tx.commit();

		} catch (Exception ex) {
			System.out.println("   $ Error deleting a product: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}

		return productDelete;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<Product> getProducts(String nombre,double precio,int cantidad){
		
		PersistenceManager pm = persistenceManagerFactory.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(3);
		pm.setDetachAllOnCommit(true);
		Transaction tx = pm.currentTransaction();
		List<Product> Products = new ArrayList<Product>();
	    
		try {
			logger.info ("   * Querying a Product: " + nombre+precio+cantidad);
	    	tx.begin();
	    	Query<Product> query=null;
    		query = pm.newQuery("SELECT FROM " + Product.class.getName());
	    	Products= (List<Product>)query.execute();
	        tx.commit();
   	    
	     } catch (Exception ex) {
	    	 //logger.error("   $ Error retreiving an extent: " + ex.getMessage());
	    	 System.out.println("   $ Error retreiving an extent: " + ex.getMessage());
	    	 
	     } finally {
		   	if (tx != null && tx.isActive()) {
		   		tx.rollback();
		 }
				
	   		pm.close();
	     }

	    return Products;
	}
	
	public void updateProduct(Product product) {
		PersistenceManager pm = persistenceManagerFactory.getPersistenceManager();
	    Transaction tx = pm.currentTransaction();
	    
	    try {
	    	tx.begin();
	    	pm.makePersistent(product);
	    	tx.commit();
	     } catch (Exception ex) {
	    	 //logger.error("   $ Error retreiving an extent: " + ex.getMessage());
	    	 System.out.println("   $ Error retreiving an extent: " + ex.getMessage());
	     } finally {
		   	if (tx != null && tx.isActive()) {
		   		tx.rollback();
		   	}
				
	   		pm.close();
	     }
	}
	
	
}
