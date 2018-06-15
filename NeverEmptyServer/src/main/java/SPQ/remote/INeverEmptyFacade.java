package SPQ.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

import SPQ.dto.PaymentDTO;
import SPQ.dto.ProductDTO;
import SPQ.dto.UserDTO;


public interface INeverEmptyFacade extends Remote{
	
	public boolean registerGoogle(UserDTO userDTO) throws RemoteException;
	public boolean registerFacebook(UserDTO userDTO) throws RemoteException;
	public boolean registerNeverEmpty(UserDTO userDTO) throws RemoteException;
	public boolean login(UserDTO user) throws RemoteException;
	
	public ProductDTO getProducts() throws RemoteException;
	public UserDTO getUser(UserDTO user) throws RemoteException;
	
	public boolean updateUser(UserDTO userDTO) throws RemoteException;
	
	public boolean payWithPaypal (PaymentDTO paymentDTO) throws RemoteException;
	public boolean payWithVisa (PaymentDTO paymentDTO) throws RemoteException;
}
