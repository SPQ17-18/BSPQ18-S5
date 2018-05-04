package SPQ.dao;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import SPQ.data.Product;
import SPQ.data.User;

public class ProductDAO {

	private PersistenceManagerFactory persistenceManagerFactory;
	
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
			System.out.println("   * Buscando producto: " + p.getNombreProduct());

			tx.begin();
			Query<?> query = pm.newQuery("SELECT FROM " + Product.class.getName() + " WHERE productname == '" + p.getNombreProduct() +"'");
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
			System.out.println("   * Eliminando producto: " + p.getNombreProduct());

			tx.begin();
			Query<?> query = pm.newQuery("DELETE FROM " + Product.class.getName() + " WHERE productname == '" + p.getNombreProduct() +"'");
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
	
	
}
