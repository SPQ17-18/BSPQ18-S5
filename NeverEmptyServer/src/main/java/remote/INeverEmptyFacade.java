package remote;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface INeverEmptyFacade {
	public boolean register(String username, String email, String password ) throws RemoteException;
	public boolean login(String username, String password) throws RemoteException;
}
