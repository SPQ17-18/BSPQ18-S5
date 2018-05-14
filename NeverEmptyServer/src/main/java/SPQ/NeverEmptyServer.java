package SPQ;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

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

	private UserDAO userDAO;
	private ProductDAO productDAO;
	static Logger logger= Logger.getLogger(NeverEmptyServer.class.getName());
	private static final long serialVersionUID = 1L;
	
	public NeverEmptyServer() throws RemoteException{
		super();
		
		List<User> listUsuarios = new ArrayList<>();
		List<Product> listProducts = new ArrayList<>();
		
		//Usuarios
		userDAO = new UserDAO();
		User jesus= new User("jesus", "jesus@gmail.com", "1234", "Google");
		User cristian = new User("cristian", "cristian@gmail.com", "2345", "Google");
		User alvaro = new User("alvaro", "alvaro@gmail.com", "3456", "Facebook");
		User enara = new User("enara", "enara@gmail.com", "4567", "Facebook");
		
		userDAO.storeUser(jesus);
		userDAO.storeUser(cristian);
		userDAO.storeUser(alvaro);
		userDAO.storeUser(enara);
		
		listUsuarios.add(jesus);
		listUsuarios.add(cristian);
		listUsuarios.add(alvaro);
		listUsuarios.add(enara);
		
		//Productos
		
		productDAO = new ProductDAO();
		Product pizza = new Product("pizza de barbacoa", 5, 1);
		Product queso = new Product("queso", 7, 1);
		Product manzana = new Product("manzana", 3, 7);
		
		listProducts.add(queso);
		listProducts.add(manzana);
		listProducts.add(pizza);
		
		productDAO.storeProduct(pizza);
		productDAO.storeProduct(manzana);
		productDAO.storeProduct(queso);
		
		
		
		
	}
	
	/*protected NeverEmptyServer() throws RemoteException {
		super();
	}
	*/
	
	

	public boolean registerGoogle(String username, String email, String password) {
		logger.info("Registrar en servidor");
		Google google = new Google("0.0.0.0", "35600");
		String googleAnswer = google.register(email, password);
		if (googleAnswer.equals("correct")){
			User user = new User(username, email, password, "Google");
			//UserDAO userDAO = new UserDAO(); Ya esta creado en el constructor
			userDAO.storeUser(user);
			return true;
		}
		return false;

	}
	
	public boolean registerFacebook(String username, String email, String password) {
		logger.info("Registrar en servidor");
		Facebook facebook = new Facebook("0.0.0.0", "35600");
		String facebookAnswer = facebook.register(email, password);
		if (facebookAnswer.equals("correct")){
			User user = new User(username, email, password, "Facebook");
			//UserDAO userDAO = new UserDAO(); Ya esta creado en el constructor
			userDAO.storeUser(user);
			return true;
		}
		return false;
	}

	
	
	
	public boolean login(String username, String password) {
		Google google = new Google("0.0.0.0", "35600");
		//UserDAO userDAO = new UserDAO();Creado en el constructor
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
			
			logger.info("Checking whether the user already exits or not: '" + username +"'");
			//UserDAO userDAO = new UserDAO(); Ya esta creado en el constructor
			User user = null;
			boolean registrar=false;
			try {
				user = userDAO.retrieveUser(username);
			} catch (Exception  e) {
				logger.error("Exception launched: " + e.getMessage());
			}
			
			if (user != null) {
				logger.error("The user exists. So, setting new password for User: " + username);
				user.setPassword(password);
				logger.info("Password set for User: " + username);
				userDAO.updateUser(user);
				registrar=true;
			} else {
				String mail= "";
				String registerMethod= "";
				logger.info("Creating user: " + username);
				user = new User(username, password, mail, registerMethod );
				userDAO.storeUser(user);				 
				logger.info("User created: " + username);
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
			logger.error(e);
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
			logger.error(e);
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
		//UserDAO userDAO = new UserDAO();
		return userDAO.updateShoppingList(user);
		}catch (Exception ex) {
			logger.error("Update shopping list, datos incorrectos: " + ex.getMessage());
			return false;
		}
	}
	
	public boolean updateUserPayPalEmail (String username, String payPalEmail) {
		User user = new User(username, "", "", "");
		user.setPayPalEmail(payPalEmail);
		
		//UserDAO userDAO = new UserDAO();
		return userDAO.updateUserPayPalEmail(user);
	}
	public boolean updateUserPayPalPassword (String username, String payPalPassword) {
		User user = new User(username, "", "", "");
		user.setPayPalPassword(payPalPassword);
		
		//UserDAO userDAO = new UserDAO();
		return userDAO.updateUserPayPalPassword(user);
	}

	@Override
	public boolean updateUserCardNumber(String username, String cardNumber) throws RemoteException {
		User user = new User(username, "", "", "");
		user.setCardNumber(Integer.parseInt(cardNumber));
		
		//UserDAO userDAO = new UserDAO();
		return userDAO.updateUserCardNumber(user);
	}

	public boolean modifyEmail(String username, String password, String maila, String mailb) {
		// TODO Auto-generated method stub
		User user = new User(username,password,maila,"");
		user.setEmail(mailb);
		
		//UserDAO userDAO = new UserDAO();
		return userDAO.modifyEmail(user);
	}



}