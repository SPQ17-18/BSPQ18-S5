package SPQ.controller;

import java.rmi.RemoteException;
import SPQ.remote.RMIServiceLocator;
// HOLA
public class NeverEmptyController {
	RMIServiceLocator rmi;
	public NeverEmptyController(RMIServiceLocator rmi) {
		this.rmi = rmi;
	}

	public boolean register(String username, String email, String password) throws RemoteException {
		return rmi.getNeverEmptyServer().register(username, email, password);
	}

	public boolean login(String usuario, String password) throws RemoteException {
		return rmi.getNeverEmptyServer().login(usuario, password);
	}
	public String getProducts() throws RemoteException{
		return rmi.getNeverEmptyServer().getProducts();
	}
	public boolean modifyEmail(String user,String password, String maila, String mailb) throws RemoteException{
		return rmi.getNeverEmptyServer().modifyEmail(user, password, maila, mailb);
	}
}

