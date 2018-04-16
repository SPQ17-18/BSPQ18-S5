package SPQ;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import SPQ.dao.UserDAO;
import SPQ.data.User;
import SPQ.gateway.Google;
import SPQ.remote.INeverEmptyFacade;


public class NeverEmptyServer extends UnicastRemoteObject implements INeverEmptyFacade{

	protected NeverEmptyServer() throws RemoteException {
		super();
	}

	private static final long serialVersionUID = 1L;

	public boolean register(String username, String email, String password) {
		Google google = new Google("127.0.0.1", "35600");
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
		Google google = new Google("127.0.0.1", "35600");
		UserDAO userDAO = new UserDAO();
		User user = userDAO.getUser(new User(username, "", password));
		String googleAnswer = google.login(user.getEmail(), user.getPassword());
		if (googleAnswer.equals("correct")){
			return true;
		} else {
			return false;
		}
	}




}