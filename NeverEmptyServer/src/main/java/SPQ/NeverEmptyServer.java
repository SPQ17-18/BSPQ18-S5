/**@package SPQ
 * @brief This package contains the classes with the main functionality
 */
package SPQ;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.apache.log4j.Logger;

import SPQ.dao.UserDAO;
import SPQ.data.User;
import SPQ.dto.PaymentDTO;
import SPQ.dto.ProductDTO;
import SPQ.dto.UserDTO;
import SPQ.gateway.Eroski;
import SPQ.gateway.Google;
import SPQ.gateway.Facebook;
import SPQ.gateway.PayPal;
import SPQ.gateway.Visa;
import SPQ.remote.INeverEmptyFacade;

/**class NeverEmptySwrver
 * @brief This class contains the methods that register and login a user, and recover information about them
 */

public class NeverEmptyServer extends UnicastRemoteObject implements INeverEmptyFacade{

	protected NeverEmptyServer() throws RemoteException {
		super();
	}
	
	static Logger logger = Logger.getLogger(NeverEmptyServer.class.getName());

	private static final long serialVersionUID = 1L;

	/**
	 * This method allows to register an user with his google account
	 * @param UserDTO
	 * @return a user with username, email, password and the string "google"
	 */
	public boolean registerGoogle(UserDTO userDTO) {
		logger.info("Registrar en servidor");
		Google google = new Google("127.0.0.1", "35600");
		String googleAnswer = google.register(userDTO);
		if (googleAnswer.equals("correct")){
			User user = new User(userDTO.getUsername(), userDTO.getEmail(), userDTO.getPassword(), "Google");
			UserDAO userDAO = new UserDAO();
			userDAO.storeUser(user);
			return true;
		}
		return false;
	}
	/**
	 * This methods allows register an user with his facebook account 
	 * @param UsertDTO
	 * @return a user with username, email, password and the string "facebook"
	 */
	
	public boolean registerFacebook(UserDTO userDTO) {
		logger.info("Registrar en servidor");
		Facebook facebook = new Facebook("127.0.0.1", "35900");
		String facebookAnswer = facebook.register(userDTO);
		if (facebookAnswer.equals("correct")){
			User user = new User(userDTO.getUsername(), userDTO.getEmail(), userDTO.getPassword(), "Facebook");
			UserDAO userDAO = new UserDAO();
			userDAO.storeUser(user);
			return true;
		}
		return false;
	}
	
	/**
	 * This method pick up all information about a user and register it into NeverEmptyServer
	 * moves the information to UserDAO to send it to server
	 * @param UserDTO
	 * @return if the information is send to server return true, if not return false
	 * @throws remoteException
	 */
	
	public boolean registerNeverEmpty(UserDTO userDTO) throws RemoteException {
		try {
			User user = new User(userDTO.getUsername(), userDTO.getEmail(), userDTO.getPassword(), "NeverEmpty");
			UserDAO userDAO = new UserDAO();
			userDAO.storeUser(user);
			return true;
		}catch(Exception e) {
			System.err.println("NeverEmptyServer - Error: "+ e.getMessage());
		}
		return false;
	}

	public UserDTO getUser(UserDTO user) {
		UserDAO userDAO = new UserDAO();
		User userFetched = userDAO.getUser(user.getUsername(), user.getPassword());
		UserDTO userDTO = new UserDTO(userFetched);
		return userDTO;
	}
	
	/**
	 * This method login a user into server
	 * @param UserDTO
	 * @return true if name and password coincide, false if not
	 */
	
	public boolean login(UserDTO user) {

		UserDAO userDAO = new UserDAO();
		User userFetched = userDAO.getUser(user.getUsername(), user.getPassword());
		if(userFetched != null) {
			return true;
		}else {
			return false;
		}
		
	}
	
	/**
	 * This method ask to the neverEmpty server for the complete list of products
	 * @return the complete list of products that the server contains
	 */
	
	public ProductDTO getProducts() {
		ProductDTO eroskiAnswer = null;
		try {
			Eroski eroski = new Eroski("127.0.0.1", "35700");
			eroskiAnswer = eroski.getProducts();
			return eroskiAnswer;
		}catch (Exception e) {
			logger.error(e);
		}
		return eroskiAnswer;

	}
	
	/**
	 * This method allows a user to pay with his paypal account
	 * @param paymentDTO
	 * @return true if the payment connect correctly, false if not
	 */
	
	public boolean payWithPaypal(PaymentDTO paymentDTO) {
		boolean paypalAnswer = false;
		try {
			PayPal paypal = new PayPal("127.0.0.1", "35800");
			paypalAnswer = paypal.pay(paymentDTO);
			return paypalAnswer;
		}catch (Exception e) {
			logger.error(e);
		}
		return paypalAnswer;
	}
	
	/**
	 * This method allows a user to pay with his visa account
	 * @param paymentDTO
	 * @return true if the payment connect correctly, false if not
	 */
	
	public boolean payWithVisa(PaymentDTO paymentDTO) {
		boolean visaAnswer = false;
		try {
			Visa visa = new Visa("127.0.0.1", "36000");
			visaAnswer = visa.pay(paymentDTO);
			return visaAnswer;
		}catch (Exception e) {
			logger.error(e);
		}
		return visaAnswer;
	}
	
	/**
	 * This method allows a user to change their credentials except mail
	 * @param UserDTO
	 * @param username
	 * @param password
	 * @return new user with the new credentials
	 */
	public boolean updateUser (UserDTO userDTO) {
		User user = new User(userDTO);
		UserDAO udao = new UserDAO();
		return udao.updateUser(user);
	}


	

}