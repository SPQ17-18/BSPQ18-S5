package SPQ;

import dao.UserDAO;
import data.User;
import gateway.Google;

public class NeverEmptyServer {

	public NeverEmptyServer() {

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