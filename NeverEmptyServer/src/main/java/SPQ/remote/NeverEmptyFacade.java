package SPQ.remote;

import SPQ.NeverEmptyServer;
import SPQ.dao.IUserDAO;
import SPQ.dao.UserDAO;
import SPQ.data.User;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Fachada NeverEmptyFacade que implementa la interfaz de la fachada de neveremtpy
 *
 */
public class NeverEmptyFacade extends UnicastRemoteObject implements INeverEmptyFacade{

	private NeverEmptyServer neverEmptyServer;
	
	private static final long serialVersionUID = 1L;
	
	//IUserDAO dao;
	
	
	/*
	public NeverEmptyFacade() throws RemoteException {
		super();
		dao = new UserDAO();
	
	}
	
	public NeverEmptyFacade(IUserDAO udao) throws RemoteException {
		super();
		//dao = udao;
	
	}
	*/
	
	/**
	 * Constructor que define el servidor de neverEmpty del cual vamos a llamar a los metodos 
	 * @param neverEmptyServer
	 * @throws RemoteException
	 */
	public NeverEmptyFacade(NeverEmptyServer neverEmptyServer) throws RemoteException {
		super();
		this.neverEmptyServer = neverEmptyServer;
	}
	
	/**
	 * Metodo que llama desde el servidor de neverEmpty al metodo registrar por Google
	 */
	public boolean registerGoogle(String username, String email, String password) throws RemoteException {
		return neverEmptyServer.registerGoogle(username, email, password);
	}
	
	/**
	 * Metodo que llama desde el servidor de neverEmty al metodo registrar Facebook
	 */
	public boolean registerFacebook(String username, String email, String password) throws RemoteException {
		return neverEmptyServer.registerFacebook(username, email, password);
	}

	/**
	 * Metodo que llama desde el servidor de neverEmpty al metodo de iniciar sesion
	 */
	public boolean login(String username, String password) throws RemoteException {
		return neverEmptyServer.login(username, password);
	}
	
	/**
	 * Metodo que llama desde el servidor de neverEmpty al metodo de conseguir productos
	 */
	public String getProducts() throws RemoteException {
		return neverEmptyServer.getProducts();
	}
	
	
/*	//como no devuelve nada no pongo un return no??
	public boolean modifyEmail (String user,String password, String maila, String mailb)throws RemoteException {
		return neverEmptyServer.modifyEmail(user, password, maila, mailb);
	}
*/
	
	/**
	 * Metodo que llama 
	 */
	@Override
	public boolean updateShoppingList(String username, String productList) throws RemoteException {
		return neverEmptyServer.updateShoppingList(username, productList);
	}

	@Override
	public boolean updateUserPayPalEmail(String username, String payPalEmail) throws RemoteException {
		return neverEmptyServer.updateUserPayPalEmail(username, payPalEmail);
	}

	@Override
	public boolean updateUserPayPalPassword(String username, String payPalPassword) throws RemoteException {
		return neverEmptyServer.updateUserPayPalPassword(username, payPalPassword);
	}

	@Override
	public boolean updateUserCardNumber(String username, String cardNumber) throws RemoteException {
		return neverEmptyServer.updateUserCardNumber(username, cardNumber);
	}

	@Override
	public boolean registerUser(String username, String password) throws RemoteException {
		return neverEmptyServer.registerUser(username, password);
	}
	


}
