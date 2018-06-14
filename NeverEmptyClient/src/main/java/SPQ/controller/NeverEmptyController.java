package SPQ.controller;

import java.rmi.RemoteException;

import org.apache.log4j.Logger;

import SPQ.dto.PaymentDTO;
import SPQ.dto.ProductDTO;
import SPQ.dto.UserDTO;
import SPQ.remote.RMIServiceLocator;


public class NeverEmptyController {
	RMIServiceLocator rmi;
	private UserDTO userDTO;
	
	
	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}
	
	static Logger logger = Logger.getLogger(NeverEmptyController.class.getName());
	
	public NeverEmptyController(RMIServiceLocator rmi) {
		this.rmi = rmi;
	}

	public boolean registerGoogle(String username, String email, String password) throws RemoteException { 
		return rmi.getNeverEmptyServer().registerGoogle(new UserDTO(username, password, email));
	}
	
	public boolean registerFacebook(String username, String email, String password) throws RemoteException { 
		return rmi.getNeverEmptyServer().registerFacebook(new UserDTO(username, password, email));
	}
	
	public boolean registerNeverEmpty(String username, String email, String password) throws RemoteException { 
		return rmi.getNeverEmptyServer().registerNeverEmpty(new UserDTO(username, password, email));
	}

	public boolean login(String username, String password) throws RemoteException {
		logger.debug("Username: " +username + " , Password"+ password);
		if(rmi.getNeverEmptyServer().login(new UserDTO(username, password))) {
			logger.info("User " + username + " logged successfully.");
			UserDTO userFetched = this.getUser(new UserDTO(username, password));
			this.userDTO = userFetched;
			return true;
		}else {
			return false;
		}
	}
	public ProductDTO getProducts() throws RemoteException{
		return rmi.getNeverEmptyServer().getProducts();
	}
	public UserDTO getUser(UserDTO userDTO) throws RemoteException {
		return rmi.getNeverEmptyServer().getUser(userDTO);
	}
	
	public boolean payWithPayPal (PaymentDTO paymentDTO) throws RemoteException {
		return rmi.getNeverEmptyServer().payWithPaypal(paymentDTO);
	}
	
	public boolean payWithVisa (PaymentDTO paymentDTO) throws RemoteException {
		return rmi.getNeverEmptyServer().payWithVisa(paymentDTO);
	}

}

