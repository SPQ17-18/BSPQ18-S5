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


public class NeverEmptyServer extends UnicastRemoteObject implements INeverEmptyFacade{

	protected NeverEmptyServer() throws RemoteException {
		super();
	}
	
	static Logger logger = Logger.getLogger(NeverEmptyServer.class.getName());

	private static final long serialVersionUID = 1L;

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
	
	public boolean login(UserDTO user) {

		UserDAO userDAO = new UserDAO();
		User userFetched = userDAO.getUser(user.getUsername(), user.getPassword());
		if(userFetched != null) {
			return true;
		}else {
			return false;
		}
		
	}
	
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
	
	public boolean updateUser (UserDTO userDTO) {
		User user = new User(userDTO);
		UserDAO udao = new UserDAO();
		return udao.updateUser(user);
	}


	

}