package SPQ.controller;

import java.rmi.RemoteException;
import java.util.ArrayList;

import SPQ.Producto;
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
	public String getProducts() throws RemoteException{
		return rmi.getNeverEmptyServer().getProducts();
	}
	
	//Metodo para eliminar producto de la lista de la compra
	public ArrayList<Producto> deleteProduct() throws RemoteException{
		return rmi.getNeverEmptyServer().deleteProduct();
	}
}

