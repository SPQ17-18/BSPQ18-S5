package SPQ.controller;

import java.rmi.RemoteException;
import SPQ.remote.RMIServiceLocator;

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
}

