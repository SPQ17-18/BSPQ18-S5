package SPQ.remote;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import SPQ.Producto;
import SPQ.controller.ProductDTO;
import SPQ.controller.UserDTO;

public interface INeverEmptyFacade extends Remote{
	public boolean register(String username, String email, String password ) throws RemoteException;
	public boolean login(String username, String password) throws RemoteException;
	public String getProducts() throws RemoteException;
	public boolean modifyEmail (String user,String password, String maila, String mailb)throws RemoteException;
	
	public boolean registerUser(UserDTO userDTO) throws RemoteException;
	public boolean insertProduct(ProductDTO productDTO) throws RemoteException;
	public boolean identifyUser(String username, String password) throws RemoteException;
	public boolean updateUser(UserDTO userDTO) throws RemoteException;
}
