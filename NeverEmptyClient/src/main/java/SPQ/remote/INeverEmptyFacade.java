package SPQ.remote;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface INeverEmptyFacade extends Remote{
	public boolean register(String username, String email, String password ) throws RemoteException;
	public boolean login(String username, String password) throws RemoteException;
	public String getProducts() throws RemoteException;
	public boolean modifyEmail (String user,String password, String maila, String mailb)throws RemoteException;
}
