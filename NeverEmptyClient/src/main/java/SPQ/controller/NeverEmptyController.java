/** @package SPQ.controller
 * @brief This package contains the classes that act as intermediaries between the graphic user interface and the classes that speak with the server.
 */
package SPQ.controller;
import java.rmi.RemoteException;

import org.apache.log4j.Logger;

import SPQ.dto.PaymentDTO;
import SPQ.dto.ProductDTO;
import SPQ.dto.UserDTO;
import SPQ.remote.RMIServiceLocator;

/** @class NeverEmptyController
 * @brief This class acts as an intermediary between the graphic user interface and the classes that call the server.
 * It mostly contains registration, login and payment methods.
 */
public class NeverEmptyController {
	RMIServiceLocator rmi;
	private UserDTO userDTO;
	static Logger logger = Logger.getLogger(NeverEmptyController.class.getName());

	/**
	 * Constructor of NeverEmptyController class. 
	 * @param rmi. Receives RMIServiceLocator instance.
	 */
	public NeverEmptyController(RMIServiceLocator rmi) {
		this.rmi = rmi;
	}
	/**
	 * Returns the UserDTO attribute of the class.
	 * @return UserDTO instance.
	 */
	public UserDTO getUserDTO() {
		return userDTO;
	}
	/**
	 * Sets UserDTO attribute of the class.
	 */
	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}
	/**
	 * This method allows to sign up on NeverEmpty with an existing Google account.
	 * It calls the registerGoogle(UserDTO) function of NeverEmptyFacade.
	 * @param username. String, NeverEmpty username.
	 * @param email. String, existing Google email.
	 * @param password. String, Google password.
	 * @return true if the credentials are correct. False if not.
	 * @throws RemoteException
	 */
	public boolean registerGoogle(String username, String email, String password) throws RemoteException { 
		return rmi.getNeverEmptyServer().registerGoogle(new UserDTO(username, password, email));
	}
	/**
	 * This method allows to sign up on NeverEmpty with an existing Facebook account.
	 * It calls the registerFacebook(UserDTO) function of NeverEmptyFacade.
	 * @param username. String, new NeverEmpty username.
	 * @param email. String, existing Facebook email.
	 * @param password. String, Facebook password.
	 * @return true if the credentials are correct. False if not.
	 * @throws RemoteException
	 */
	public boolean registerFacebook(String username, String email, String password) throws RemoteException { 
		return rmi.getNeverEmptyServer().registerFacebook(new UserDTO(username, password, email));
	}
	/**
	 * Sign up on NeverEmpty.
	 * It calls registerNeverEmpty(UserDTO) function of NeverEmptyFacade.
	 * @param username. String, new NeverEmpty username.
	 * @param email. String, existing email.
	 * @param password. String, new password for NeverEmpty.
	 * @throws RemoteException
	 */
	public boolean registerNeverEmpty(String username, String email, String password) throws RemoteException { 
		return rmi.getNeverEmptyServer().registerNeverEmpty(new UserDTO(username, password, email));
	}
	/**
	 * Tries to login in NeverEmpty.
	 * It calls the login(UserDTO) function on NeverEmptyFacade and sets the NeverEmptyController UserDTO attribute with the received user.
	 * @param username
	 * @param password
	 * @return true if credentials are correct, false if not.
	 * @throws RemoteException
	 */
	public boolean login(String username, String password) throws RemoteException {
		logger.debug("Login user: " +username + " , password: "+ password);
		if(rmi.getNeverEmptyServer().login(new UserDTO(username, password))) {
			logger.info("User " + username + " logged successfully.");
			UserDTO userFetched = this.getUser(new UserDTO(username, password));
			this.userDTO = userFetched;
			return true;
		}else {
			return false;
		}
	}
	/**
	 * Asks to the NeverEmptyServer for the complete list of products.
	 * @return A ProductDTO that contains the full list of products.
	 * @throws RemoteException
	 */
	public ProductDTO getProducts() throws RemoteException{
		return rmi.getNeverEmptyServer().getProducts();
	}
	/**
	 * Asks to the NeverEmpty server for all the data from the user specified in the given userDTO.
	 * @param userDTO
	 * @return All the information of the user.
	 * @throws RemoteException
	 */
	public UserDTO getUser(UserDTO userDTO) throws RemoteException {
		return rmi.getNeverEmptyServer().getUser(userDTO);
	}
	/**
	 * Sends the payment information to the NeverEmpty server, who will manage the payment via PayPal.
	 * @param paymentDTO. At least must be filled the paypalUsername, paypalPassword and total amount fields.
	 * @return true if the payment has been successful, false if not.
	 * @throws RemoteException
	 */
	public boolean payWithPayPal (PaymentDTO paymentDTO) throws RemoteException {
		return rmi.getNeverEmptyServer().payWithPaypal(paymentDTO);
	}
	/**
	 * Sends the payment information to the NeverEmpty server, who will manage the payment via VISA.
	 * @param paymentDTO. At least must be filled a cardnumber, cardholder, CVV, expDate and the amount to pay.
	 * @return true if the payment has been successful, false if not.
	 * @throws RemoteException
	 */
	public boolean payWithVisa (PaymentDTO paymentDTO) throws RemoteException {
		return rmi.getNeverEmptyServer().payWithVisa(paymentDTO);
	}
	/**
	 * Sends user data to update the database. The email is the identifier of the user, so it is immutable.
	 * @param userDTO
	 * @return
	 * @throws RemoteException
	 */
	public boolean updateUser (UserDTO userDTO) throws RemoteException {
		return rmi.getNeverEmptyServer().updateUser(userDTO);
	}

}

