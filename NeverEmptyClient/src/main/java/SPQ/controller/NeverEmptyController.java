package SPQ.controller;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import SPQ.Producto;
import SPQ.remote.RMIServiceLocator;
// HOLA
public class NeverEmptyController {
	public static String username;
	private RMIServiceLocator rmi;
	
	public NeverEmptyController(String[] args) throws RemoteException {
		rmi = new RMIServiceLocator();
		//cmsl.setServices(args[0], args[1], args[2]);
		
		UserDTO userDTO= new UserDTO();
	}
	
	public NeverEmptyController(RMIServiceLocator rmi) {
		this.rmi = rmi;
	}

	public boolean register(String username, String email, String password) throws RemoteException {
		return rmi.getNeverEmptyServer().register(username, email, password);
	}

	public boolean login(String usuario, String password) throws RemoteException {
		if(rmi.getNeverEmptyServer().login(usuario, password)) {
			this.username = usuario;
			return true;
		}else {
			return false;
		}
	}
	public String getProducts() throws RemoteException{
		return rmi.getNeverEmptyServer().getProducts();
	}
	
	//Registrar un usuario pasando un usuario 
	public boolean registerUser(UserDTO userDTO) {
		boolean registered = false;
		try {
			//registerede = cmsl.getService().registerMember(memberDTO);
			registered= rmi.getService().registerUser(userDTO);
		} catch (RemoteException e) {
			//logger.error("Error registering a member.");
			System.err.println(" # Messenger RemoteException: " + e.getMessage());
		}
		return registered;
	}
	
	//Insertar un producto pasando un producto
	public boolean insertProduct(ProductDTO productDTO) {
		boolean product = false;
		try {
			product= rmi.getService().insertProduct(productDTO);
		} catch (Exception e) {
			//logger.error("Error inserting a film.");
			System.err.println(" # Messenger RemoteException: " + e.getMessage());
		}
		return product;
	}
	
	
	public boolean identifyUser(String username, String password) {
		boolean login = false;
		try {
			login = rmi.getService().login(username, password);
		} catch (RemoteException e) {
			//logger.error("Error identifying a member.");
			System.err.println(" # Messenger RemoteException: " + e.getMessage());
		}
		return login;
	}
	
	public boolean updateProduct(ProductDTO productDTO) {
		boolean updated = false;
		try {
			updated = rmi.getService().updateUser(productDTO);
		} catch (RemoteException e) {
			//logger.error("Error updating a member.");
			System.err.println(" # Messenger RemoteException: " + e.getMessage());
		}
		return updated;
	}

	public List<ProductDTO> getAllProducts() {
		List<ProductDTO> products = null;
		try {
			products = rmi.getService().getProducts();
		} catch (RemoteException e) {
			//logger.error("Error getting members from server.");
			System.err.println(" # Messenger RemoteException: " + e.getMessage());
		}
		return products;
	}
	
	

}

