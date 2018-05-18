package SPQ.dao;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import SPQ.data.Product;
import javax.jdo.Extent;

import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

//	public static void main(String[] args) {
//		ProductDAO pdao = new ProductDAO();
//		List<Product> productos = pdao.getProducts();
//		for (Product m : productos) {
//			System.out.println(m.getName());
//			System.out.println(m.getPrice());
//		}
//	}
	
	public static void main(String[] args) {
		Product p = new Product("Pera", 2);
		ProductDAO pd = new ProductDAO();
		pd.setProduct(p);
	}
	private PersistenceManagerFactory persistenceManagerFactory;

	public ProductDAO() {
		this.persistenceManagerFactory = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
	}

	public boolean setProduct (Product product) {
		boolean stored = false;
		PersistenceManager pm = this.persistenceManagerFactory.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();
			System.out.println("   * Storing a product: " + product);
			pm.makePersistent(product);
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
	
	public List<Product> getProducts() {
		PersistenceManager pm = this.persistenceManagerFactory.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(3);

		Transaction tx = pm.currentTransaction();
		
		List<Product> products = new ArrayList<Product>();

		try {

			tx.begin();			
			Extent<Product> extent = pm.getExtent(Product.class, true);

			for (Product product : extent) {
				products.add(product);
			}

			tx.commit();			
		} catch (Exception ex) {
			System.out.println("   $ Error retrieving an extent: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();    		
		}

		return products;
	}
	
	public List<Product> deleteProduct() {
		PersistenceManager pm = this.persistenceManagerFactory.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(3);

		Transaction tx = pm.currentTransaction();
		List<Product> products = new ArrayList<Product>();

		try {

			tx.begin();			
			Extent<Product> extent = pm.getExtent(Product.class, true);

			for (Product product : extent) {
				products.add(product);
			}

			tx.commit();			
		} catch (Exception ex) {
			System.out.println("   $ Error retrieving an extent: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();    		
		}

		return products;
	}
}