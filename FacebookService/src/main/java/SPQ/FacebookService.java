package SPQ;

import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import SPQ.dao.UserDAO;
import SPQ.data.User;
import SPQ.dto.UserDTO;

public class FacebookService extends Thread {
	private ObjectInputStream in;
	private DataOutputStream out;
	private Socket tcpSocket;
	// Se instancia el socket
	public FacebookService(Socket socket) {
		try {
			this.tcpSocket = socket;
		    this.in = new ObjectInputStream(socket.getInputStream());
			this.out = new DataOutputStream(socket.getOutputStream());
			this.start();	//start llama a run()
		} catch (IOException e) {
			System.err.println("# FacebookService - TCPConnection IO error:" + e.getMessage());
		}
	}

	public void run() {
		try {
			UserDTO userDTO = (UserDTO) this.in.readObject();
			System.out.println("   - FacebookService - Received data from '" + tcpSocket.getInetAddress().getHostAddress() + ":" + tcpSocket.getPort() + "' -> '" + userDTO.toString() + "'");					
			String data = this.readData(userDTO);
			this.out.writeUTF(data);					
			System.out.println("   - FacebookService - Sent data to '" + tcpSocket.getInetAddress().getHostAddress() + ":" + tcpSocket.getPort() + "' -> '" + data + "'");
		} catch (EOFException e) {
			System.err.println("   # FacebookService - TCPConnection EOF error" + e.getMessage());
		} catch (IOException e) {
			System.err.println("   # FacebookService - TCPConnection IO error:" + e.getMessage());
		}catch (ClassNotFoundException e) {
			System.err.println("   # FacebookService - ClassNotFound error:" + e.getMessage());
		} finally {
			try {
				tcpSocket.close();
			} catch (IOException e) {
				System.err.println("   # FacebookService - TCPConnection IO error:" + e.getMessage());
			}
		}
	}
	
	public String readData(UserDTO userDTO) {
		String data = "incorrect";
		try {
			String email = userDTO.getEmail();
			String password = userDTO.getPassword();
			
			UserDAO userDAO = new UserDAO();
			User user = userDAO.getUser(email);
			
			if (user.getPassword().equals(password)) {
				data = "correct";
			}else {
				data = "incorrect";
			}
		}catch (RuntimeException e) {
			System.err.println(" # FacebookService - Wrong data : '" + data +"'");
		}
		return data;
	}
}