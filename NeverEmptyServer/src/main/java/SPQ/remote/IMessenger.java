package SPQ.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

import SPQ.data.User;

public interface IMessenger extends Remote {

	String sayMessage(String login, String password, String message) throws RemoteException;
	void registerUser(String login, String password) throws RemoteException;
	User getUserMessages(String login) throws RemoteException;
}
