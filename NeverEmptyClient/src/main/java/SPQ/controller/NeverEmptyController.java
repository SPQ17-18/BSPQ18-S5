package SPQ.controller;

import java.rmi.RemoteException;
import SPQ.remote.RMIServiceLocator;

public class NeverEmptyController {
	public static String username;
	RMIServiceLocator rmi;
	public NeverEmptyController(RMIServiceLocator rmi) {
		this.rmi = rmi;
	}

	public boolean register(String username, String email, String password) throws RemoteException {
		return rmi.getNeverEmptyServer().register(username, email, password);
	}

	public boolean login(String usuario, String password) throws RemoteException {
		if(rmi.getNeverEmptyServer().login(usuario, password)) {
			this.username = usuario;
			return true;
		}else {
			return false;
		}
	}
	public String getProducts() throws RemoteException{
		return rmi.getNeverEmptyServer().getProducts();
	}
}

