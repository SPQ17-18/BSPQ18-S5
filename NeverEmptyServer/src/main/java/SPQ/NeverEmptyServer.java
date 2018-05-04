package SPQ;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import SPQ.dao.UserDAO;
import SPQ.data.User;
import SPQ.gateway.Eroski;
import SPQ.gateway.Google;
import SPQ.remote.INeverEmptyFacade;


public class NeverEmptyServer extends UnicastRemoteObject implements INeverEmptyFacade{

	protected NeverEmptyServer() throws RemoteException {
		super();
	}

	private static final long serialVersionUID = 1L;

	public boolean register(String username, String email, String password) {
		System.out.println("Registrar en servidor");
		Google google = new Google("0.0.0.0", "35600");
		String googleAnswer = google.register(email, password);
		if (googleAnswer.equals("correct")){
			User user = new User(username, email, password);
			UserDAO userDAO = new UserDAO();
			userDAO.storeUser(user);
			return true;
		}
		return false;
	}

	public boolean login(String username, String password) {
		Google google = new Google("0.0.0.0", "35600");
		UserDAO userDAO = new UserDAO();
		User user = userDAO.getUser(new User(username, "", password));
		String googleAnswer = google.login(user.getEmail(), user.getPassword());
		if (googleAnswer.equals("correct")){
			return true;
		} else {
			return false;
		}
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
	
	public boolean modifyEmail (String user,String password, String maila, String mailb) {
		boolean hecho = false;
		System.out.println("modificando correo");
		Google google = new Google("0.0.0.0", "35600");
		String googleAnswer = google.register(maila, password);
		if (googleAnswer.equals("correct")) {
			UserDAO userDAO = new UserDAO();
			userDAO.setUsermail(maila, mailb);
			hecho=true;
			return hecho;
		}else {
			return hecho;
		}
		
	}


}