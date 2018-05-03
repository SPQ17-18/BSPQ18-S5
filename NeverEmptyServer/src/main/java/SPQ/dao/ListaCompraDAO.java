package SPQ.dao;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import SPQ.data.Product;

public class ListaCompraDAO {
	
	private PersistenceManagerFactory pmf;
	
	/**
	 * Constructor de mfdao crea un persistence manager factory
	 */
	public ListaCompraDAO() {
		pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
	}
	
	/**
	 * Metodo para guardar un objeto en la bases de datos
	 * 
	 * @param object
	 *            objeto que le pasamos para guardar en la bds
	 */
	private boolean storeObject(Object object) {
		boolean b = false;
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();
			System.out.println("   * Storing an object: " + object);
			pm.makePersistent(object);
			tx.commit();
			b = true;
		} catch (Exception ex) {
			System.out.println("   $ Error storing an object: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
		return b;
	}
	
	//Guarda en la base de datos un producto
	public void storeProduct(Product p) {
		// TODO Auto-generated method stub
		this.storeObject(p);
	}
	
	//Guarda los productos en la listaCompra
	public List<String> loadProducts() {
		// TODO Auto-generated method stub
		List<String> listaCompra = new ArrayList<String>();
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(3);
		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();
			Extent<Product> extent = pm.getExtent(Product.class, true);
			for (Product p : extent) {
				String product = p.getNombreProduct() + "#" + p.getPrecioProduct();
				listaCompra.add(product);
			}
			tx.commit();
		} catch (Exception ex) {
			System.out.println(" $ Error retrieving products: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
		return listaCompra;
	}
	
	//Borra un producto de la listaCompra y devuelve a la lista de la compra
	public List<String> deleteProduct() {
		// TODO Auto-generated method stub
		List<String> listaCompraEliminados = new ArrayList<String>();
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(3);
		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();
			Extent<Product> extent = pm.getExtent(Product.class, true);
			for (Product p : extent) {
				String product = p.getNombreProduct() + "#" + p.getPrecioProduct();
				listaCompraEliminados.remove(p);
			}
			tx.commit();
		} catch (Exception ex) {
			System.out.println(" $ Error deleting products: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
		return listaCompraEliminados;
	}
	
	
	
	
	
	
}
