package SPQ;

import dao.UserDAO;
import data.User;
import gateway.Google;
import remote.INeverEmptyFacade;
import remote.NeverEmptyFacade;
import java.rmi.Naming;

public class NeverEmptyServer {

	public static void main(String[] args) {
		if (args.length != 4) {
			System.exit(0);
		}

		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		String nameNeverEmpty = "//" + args[0] + ":" + args[1] + "/" + args[3];


		try {
			NeverEmptyServer neverEmptyServer = new NeverEmptyServer();
			
			INeverEmptyFacade neverEmptyFacade = new NeverEmptyFacade(neverEmptyServer);			
			Naming.rebind(nameNeverEmpty, neverEmptyFacade);
			System.out.println("* NeverEmpty Service '" + nameNeverEmpty + "' active and waiting...");
			
		
		} catch (Exception e) {
			System.err.println("$ NeverEmptyServer exception: " + e.getMessage());
			e.printStackTrace();
		}
	}

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