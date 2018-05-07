package SPQ.remote;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface INeverEmptyFacade extends Remote{
	
	public boolean registerGoogle(String username, String email, String password ) throws RemoteException;
	public boolean registerFacebook(String username, String email, String password ) throws RemoteException;
	public boolean login(String username, String password) throws RemoteException;
	
	public String getProducts() throws RemoteException;
	public String getUser(String username) throws RemoteException;
	
	public boolean updateShoppingList (String username, String productList) throws RemoteException;
	
	public boolean updateUserPayPalEmail (String username, String payPalEmail) throws RemoteException;
	public boolean updateUserPayPalPassword (String username, String payPalPassword) throws RemoteException;
	public boolean updateUserCardNumber (String username, String cardNumber) throws RemoteException;
}
