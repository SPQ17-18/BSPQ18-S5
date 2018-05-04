package SPQ.remote;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import SPQ.Producto;

public interface INeverEmptyFacade extends Remote{
	public boolean register(String username, String email, String password ) throws RemoteException;
	public boolean login(String username, String password) throws RemoteException;
	public String getProducts() throws RemoteException;
	
	//Metodo que devuelve la lista de la compra con el producto borrado
	public ArrayList<Producto> deleteProduct() throws RemoteException;
}
