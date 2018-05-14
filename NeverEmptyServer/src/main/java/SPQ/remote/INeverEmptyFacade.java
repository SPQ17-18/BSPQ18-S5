package SPQ.remote;
import java.rmi.Remote;
import java.rmi.RemoteException;

import SPQ.data.User;

/**
 * Interfaz fachada de neverEmpty
 *
 */
public interface INeverEmptyFacade extends Remote{
	
	/**
	 * 
	 * Metodo que registra un usuario por el metodo de registro de google con las siguientes caracteristicas:
	 * 
	 * @param username
	 * @param email
	 * @param password
	 * @return
	 * @throws RemoteException
	 */
	public boolean registerGoogle(String username, String email, String password ) throws RemoteException;
	
	/**
	 * Metodo que registra un usuario por el metodo de registro de Facebook con las siguientes caracteristicas:
	 * 
	 * @param username
	 * @param email
	 * @param password
	 * @return
	 * @throws RemoteException
	 */
	public boolean registerFacebook(String username, String email, String password ) throws RemoteException;
	
	/**
	 * Metodo que sirve para iniciar sesion con un usuario con las siguientes caracteristicas:
	 * @param username
	 * @param password
	 * @return
	 * @throws RemoteException
	 */
	public boolean login(String username, String password) throws RemoteException;
	
	/**
	 * Metodo que devuelve los productos de un usuario
	 * @return products
	 * @throws RemoteException
	 */
	public String getProducts() throws RemoteException;
	
	/**
	 * Metodo que actualiza la lista de la compra de un usuario 
	 * @param username
	 * @param productList
	 * @return
	 * @throws RemoteException
	 */
	public boolean updateShoppingList (String username, String productList) throws RemoteException;
	
	/**
	 * Metodo que actualiza el email de Paypal
	 * @param username
	 * @param payPalEmail
	 * @return
	 * @throws RemoteException
	 */
	public boolean updateUserPayPalEmail (String username, String payPalEmail) throws RemoteException;
	
	/**
	 * Metodo que actualiza la contrasenia de Paypal
	 * @param username
	 * @param payPalPassword
	 * @return
	 * @throws RemoteException
	 */
	public boolean updateUserPayPalPassword (String username, String payPalPassword) throws RemoteException;
	
	/**
	 * Metodo que actualiza el numero de credito del usuario
	 * @param username
	 * @param cardNumber
	 * @return
	 * @throws RemoteException
	 */
	public boolean updateUserCardNumber (String username, String cardNumber) throws RemoteException;
	
	/**
	 * Metodo para registrar el usuario dado un nombre de usuario y una contrasenia
	 * @param username
	 * @param password
	 * @return
	 * @throws RemoteException
	 */
	public boolean registerUser(String username, String password) throws RemoteException;
	//public String sayMessage(String login, String password, String message) throws RemoteException;
	//public User getUserProducts(String login) throws RemoteException;
}
