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

	private PersistenceManagerFactory persistenceManagerFactory;

	public ProductDAO() {
		this.persistenceManagerFactory = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
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
}