/** @package SPQ.controller

 	@brief This is the brief documentation for the java package SPQ.controller
 */

/** @class NeverEmptyCotroller class.h "inc/class.h" 
* @brief This is a NeverEmptyController class.
* Some details about the NeverEMprtyController class 
*/
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
	static Logger logger = Logger.getLogger(NeverEmptyController.class.getName());
	
	
	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}
	
	
	
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
	
	public boolean updateUser (UserDTO userDTO) throws RemoteException {
		return rmi.getNeverEmptyServer().updateUser(userDTO);
	}

}

