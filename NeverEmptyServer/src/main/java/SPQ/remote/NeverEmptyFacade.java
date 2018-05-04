package SPQ.remote;

import SPQ.NeverEmptyServer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class NeverEmptyFacade extends UnicastRemoteObject implements INeverEmptyFacade{
	private static final long serialVersionUID = 1L;
	private NeverEmptyServer neverEmptyServer;
	
	public NeverEmptyFacade(NeverEmptyServer neverEmptyServer) throws RemoteException {
		super();
		this.neverEmptyServer = neverEmptyServer;
	}
	
	public boolean registerGoogle(String username, String email, String password) throws RemoteException {
		return neverEmptyServer.registerGoogle(username, email, password);
	}
	
	public boolean registerFacebook(String username, String email, String password) throws RemoteException {
		return neverEmptyServer.registerFacebook(username, email, password);
	}

	public boolean login(String username, String password) throws RemoteException {
		return neverEmptyServer.login(username, password);
	}
	
	public String getProducts() throws RemoteException {
		return neverEmptyServer.getProducts();
	}

}
