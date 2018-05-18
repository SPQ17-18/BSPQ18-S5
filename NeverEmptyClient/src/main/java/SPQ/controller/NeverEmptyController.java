package SPQ.controller;

import java.rmi.RemoteException;

import org.apache.log4j.Logger;
import SPQ.dto.UserDTO;
import SPQ.remote.RMIServiceLocator;


public class NeverEmptyController {
	private UserDTO userDTO;
	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}
	RMIServiceLocator rmi;
	
	static Logger logger = Logger.getLogger(NeverEmptyController.class.getName());
	
	public NeverEmptyController(RMIServiceLocator rmi) {
		this.rmi = rmi;
	}

	public boolean register(String username, String email, String password) throws RemoteException {
		return rmi.getNeverEmptyServer().registerGoogle(username, email, password);
	}

	public boolean login(String username, String password) throws RemoteException {
		logger.debug("Username: " +username + " , Password"+ password);
		if(rmi.getNeverEmptyServer().login(new UserDTO(username, password))) {
			logger.info("User " + username + " logged successfully.");
			UserDTO userFetched = this.getUser(new UserDTO(username, password));
			this.userDTO = userFetched;
			return true;
		}else {
			return false;
		}
	}
	public String getProducts() throws RemoteException{
		return rmi.getNeverEmptyServer().getProducts();
	}
	public UserDTO getUser(UserDTO userDTO) throws RemoteException {
		return rmi.getNeverEmptyServer().getUser(userDTO);
	}

}

