package SPQ;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import SPQ.dto.ProductDTO;
import SPQ.dto.UserDTO;
import SPQ.dao.ProductDAO;
import SPQ.dao.UserDAO;
import SPQ.data.Product;
import SPQ.data.User;
import SPQ.gateway.Eroski;
import SPQ.gateway.Google;
import SPQ.gateway.Facebook;
import SPQ.gateway.PayPal;
import SPQ.remote.INeverEmptyFacade;


public class NeverEmptyServer extends UnicastRemoteObject implements INeverEmptyFacade{

	protected NeverEmptyServer() throws RemoteException {
		super();
	}

	private static final long serialVersionUID = 1L;

	public boolean registerGoogle(String username, String email, String password) {
		System.out.println("Registrar en servidor");
		Google google = new Google("0.0.0.0", "35600");
		String googleAnswer = google.register(email, password);
		if (googleAnswer.equals("correct")){
			User user = new User(username, email, password, "Google");
			UserDAO userDAO = new UserDAO();
			userDAO.storeUser(user);
			return true;
		}
		return false;

	}
	
	public boolean registerFacebook(String username, String email, String password) {
		System.out.println("Registrar en servidor");
		Facebook facebook = new Facebook("0.0.0.0", "35600");
		String facebookAnswer = facebook.register(email, password);
		if (facebookAnswer.equals("correct")){
			User user = new User(username, email, password, "Facebook");
			UserDAO userDAO = new UserDAO();
			userDAO.storeUser(user);
			return true;
		}
		return false;
	}

	public boolean login(String username, String password) {
		Google google = new Google("0.0.0.0", "35600");
		UserDAO userDAO = new UserDAO();
		User user = userDAO.getUser(new User(username, "", password, ""));
		String answer = "incorrect";
		if (user.getRegisterMethod().equals("Google")) {
			answer = google.login(user.getEmail(), user.getPassword());
		}else if (user.getRegisterMethod().equals("Facebook")) {
			answer = google.login(user.getEmail(), user.getPassword());
		}
		if (answer.equals("correct")){
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean registerUser(String username, String password) {
			
			System.out.println("Checking whether the user already exits or not: '" + username +"'");
			UserDAO userDAO = new UserDAO();
			User user = null;
			boolean registrar=false;
			try {
				user = userDAO.retrieveUser(username);
			} catch (Exception  e) {
				System.out.println("Exception launched: " + e.getMessage());
			}
			
			if (user != null) {
				System.out.println("The user exists. So, setting new password for User: " + username);
				user.setPassword(password);
				System.out.println("Password set for User: " + username);
				userDAO.updateUser(user);
				registrar=true;
			} else {
				String mail= "";
				String registerMethod= "";
				System.out.println("Creating user: " + username);
				user = new User(username, password, mail, registerMethod );
				userDAO.storeUser(user);				 
				System.out.println("User created: " + username);
				registrar=true;
			}
			
			return registrar;
		}

	
	public String getProducts() {
		String eroskiAnswer = "incorrect";
		try {
			Eroski eroski = new Eroski("0.0.0.0", "35700");
			eroskiAnswer = eroski.getProducts();
			return eroskiAnswer;
		}catch (Exception e) {
			System.out.println(e);
		}
		return eroskiAnswer;

	}

	public String payWithPaypal(String email, String password, String price) {
		String paypalAnswer = "incorrect";
		try {
			PayPal paypal = new PayPal("0.0.0.0", "35800");
			paypalAnswer = paypal.pay(email, password, price);
			return paypalAnswer;
		}catch (Exception e) {
			System.out.println(e);
		}
		return paypalAnswer;
	}

	public boolean updateShoppingList (String username, String productList){
		try {
		User user = new User(username, "", "", "");
		//nombre, precio, cantidad
		String[] productsString = productList.split(";");
		List<Product> products = new ArrayList<>();
		for (String product : productsString) {
			String[] productSplit = product.split(",");
			Product p = new Product(productSplit[0], Double.parseDouble(productSplit[1]), Integer.parseInt(productSplit[2]));
			products.add(p);
		}
		user.setShoppingList(products);
		UserDAO userDAO = new UserDAO();
		return userDAO.updateShoppingList(user);
		}catch (Exception ex) {
			System.out.println("Update shopping list, datos incorrectos: " + ex.getMessage());
			return false;
		}
	}
	
	public boolean updateUserPayPalEmail (String username, String payPalEmail) {
		User user = new User(username, "", "", "");
		user.setPayPalEmail(payPalEmail);
		
		UserDAO userDAO = new UserDAO();
		return userDAO.updateUserPayPalEmail(user);
	}
	public boolean updateUserPayPalPassword (String username, String payPalPassword) {
		User user = new User(username, "", "", "");
		user.setPayPalPassword(payPalPassword);
		
		UserDAO userDAO = new UserDAO();
		return userDAO.updateUserPayPalPassword(user);
	}

	@Override
	public boolean updateUserCardNumber(String username, String cardNumber) throws RemoteException {
		User user = new User(username, "", "", "");
		user.setCardNumber(Integer.parseInt(cardNumber));
		
		UserDAO userDAO = new UserDAO();
		return userDAO.updateUserCardNumber(user);
	}

	public boolean modifyEmail(String username, String password, String maila, String mailb) {
		// TODO Auto-generated method stub
		User user = new User(username,password,maila,"");
		user.setEmail(mailb);
		
		UserDAO userDAO = new UserDAO();
		return userDAO.modifyEmail(user);
	}



	@Override
	public String sayMessage(String login, String password, String message) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}


}