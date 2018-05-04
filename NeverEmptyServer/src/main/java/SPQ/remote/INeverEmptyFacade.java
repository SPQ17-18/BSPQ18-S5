package SPQ.remote;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface INeverEmptyFacade extends Remote{
	public boolean registerGoogle(String username, String email, String password ) throws RemoteException;
	public boolean registerFacebook(String username, String email, String password ) throws RemoteException;
	public boolean login(String username, String password) throws RemoteException;
	public String getProducts() throws RemoteException;
}
