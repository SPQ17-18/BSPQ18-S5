package SPQ.dao;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import org.apache.log4j.Logger;

import javax.jdo.Query;
import SPQ.data.Account;

public class AccountDAO implements IAccountDAO{
	private PersistenceManagerFactory persistenceManagerFactory;
	static Logger logger = Logger.getLogger(AccountDAO.class.getName());
	public AccountDAO() {
		this.persistenceManagerFactory = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
	}

	public boolean pay (Account account, double total) {

		PersistenceManager pm = this.persistenceManagerFactory.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(3);

		Transaction tx = pm.currentTransaction();

		try {
			logger.info("Actualizando cuenta: " + account.getCardnumber());

			tx.begin();
			Query<?> query = pm.newQuery("SELECT FROM "+ Account.class.getName() + " WHERE cardnumber == " + account.getCardnumber() +
					" && cvv == " + account.getCvv() + 
					" && cardholder == '" + account.getCardholder() + "'" +
					" && expDate == '" + account.getExpDate() + "'");

			query.setUnique(true);
			account = (Account) query.execute();
			if (account == null) throw new Exception("Pago rechazado, datos de pago incorrectos.");

			double balance = account.getBalance();
			if (account.getBalance() >= total) {
				balance = account.getBalance() - total;
			}else {
				throw new Exception("Pago rechazado, la cuenta no tiene suficiente credito.");
			}

			account.setBalance(balance);
			tx.commit();

		} catch (Exception ex) {
			logger.error("Error updating an account: " + ex.getMessage());
			return false;
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
				return false;
			}

			pm.close();

		}
		return true;
	}

	public void storeAccount (Account account) {
		PersistenceManager pm = this.persistenceManagerFactory.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();
			logger.info("Storing a user: " + account);
			pm.makePersistent(account);
			tx.commit();
		} catch (Exception ex) {
			logger.error("Error storing a user: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}		
			pm.close();
		}
	}

}