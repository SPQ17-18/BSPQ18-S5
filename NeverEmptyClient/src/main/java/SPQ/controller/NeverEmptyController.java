package SPQ.controller;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
//import SPQ.NeverEmptyServer;

import SPQ.data.Product;
import SPQ.remote.RMIServiceLocator;


public class NeverEmptyController {
	public static String username = "";
	public static String email = "";
	public static String payPalEmail = "";
	public static String payPalPassword = "";
	public static String cardNumber = "";
	RMIServiceLocator rmi;
	
	static Logger logger = Logger.getLogger(NeverEmptyController.class.getName());
	
	public NeverEmptyController(RMIServiceLocator rmi) {
		this.rmi = rmi;
	}

	public boolean register(String username, String email, String password) throws RemoteException {
		return rmi.getNeverEmptyServer().registerGoogle(username, email, password);
	}

	public boolean login(String username, String password) throws RemoteException {
		logger.debug("ANTES DEL LOGIN");
		logger.debug("USERNAME: " +username + " PASSWORD"+ password);
		if(rmi.getNeverEmptyServer().login(username, password)) {
			logger.info("HACE EL LOGIN");
			String user = this.getUser(username);
			String [] userArray = user.split(";");
			if(!userArray[1].equals("")) {
			
				this.username = userArray[0];
				logger.info(this.username);
			}
			if(!userArray[1].equals("")) {
				email = userArray[1];
				
				logger.info(email);
			}
			if(!userArray[2].equals("null")) {
				payPalEmail = userArray[2];
				logger.info(payPalEmail);
			}
			if(!userArray[3].equals("null")) {
				payPalPassword = userArray[3];
				logger.info(payPalPassword);
			}
			if(!userArray[3].equals("-1")) {
				cardNumber = userArray[4];
				logger.info(cardNumber);
			}
			return true;
		}else {
			return false;
		}
	}
	public String getProducts() throws RemoteException{
		return rmi.getNeverEmptyServer().getProducts();
	}
	public String getUser(String username) throws RemoteException {
		return rmi.getNeverEmptyServer().getUser(username);
	}

}

