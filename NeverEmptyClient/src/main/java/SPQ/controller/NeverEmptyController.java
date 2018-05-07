package SPQ.controller;

import java.rmi.RemoteException;
import java.util.ArrayList;

import SPQ.Producto;
import SPQ.remote.RMIServiceLocator;

public class NeverEmptyController {
	public static String username = "";
	public static String email = "";
	public static String payPalEmail = "";
	public static String payPalPassword = "";
	public static String cardNumber = "";
	RMIServiceLocator rmi;
	public NeverEmptyController(RMIServiceLocator rmi) {
		this.rmi = rmi;
	}

	public boolean register(String username, String email, String password) throws RemoteException {
		return rmi.getNeverEmptyServer().registerGoogle(username, email, password);
	}

	public boolean login(String username, String password) throws RemoteException {
		if(rmi.getNeverEmptyServer().login(username, password)) {
			String user = this.getUser(username);
			String [] userArray = user.split(";");
			if(!userArray[1].equals("")) {
			
				this.username = userArray[0];
				System.out.println(this.username);
			}
			if(!userArray[1].equals("")) {
				email = userArray[1];
				
				System.out.println(email);
			}
			if(!userArray[2].equals("null")) {
				payPalEmail = userArray[2];
				System.out.println(payPalEmail);
			}
			if(!userArray[3].equals("null")) {
				payPalPassword = userArray[3];
				System.out.println(payPalPassword);
			}
			if(!userArray[3].equals("-1")) {
				cardNumber = userArray[4];
				System.out.println(cardNumber);
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

