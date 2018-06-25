package SPQ;

import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import org.apache.log4j.Logger;

import SPQ.dao.UserDAO;
import SPQ.data.User;
import SPQ.dto.UserDTO;

public class FacebookService extends Thread {
	private ObjectInputStream in;
	private DataOutputStream out;
	private Socket tcpSocket;
	static Logger logger = Logger.getLogger(FacebookService.class.getName());
	// Se instancia el socket
	public FacebookService(Socket socket) {
		try {
			this.tcpSocket = socket;
		    this.in = new ObjectInputStream(socket.getInputStream());
			this.out = new DataOutputStream(socket.getOutputStream());
			this.start();	//start llama a run()
		} catch (IOException e) {
			logger.error("TCPConnection IO error:" + e.getMessage());
		}
	}

	public void run() {
		try {
			UserDTO userDTO = (UserDTO) this.in.readObject();
			logger.info("Received data from '" + tcpSocket.getInetAddress().getHostAddress() + ":" + tcpSocket.getPort() + "' -> '" + userDTO.toString() + "'");					
			String data = this.readData(userDTO);
			this.out.writeUTF(data);					
			logger.info("Sent data to '" + tcpSocket.getInetAddress().getHostAddress() + ":" + tcpSocket.getPort() + "' -> '" + data + "'");
		} catch (EOFException e) {
			logger.error("TCPConnection EOF error" + e.getMessage());
		} catch (IOException e) {
			logger.error("TCPConnection IO error:" + e.getMessage());
		}catch (ClassNotFoundException e) {
			logger.error("ClassNotFound error:" + e.getMessage());
		} finally {
			try {
				tcpSocket.close();
			} catch (IOException e) {
				logger.error("TCPConnection IO error:" + e.getMessage());
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
			logger.error(" Wrong data : '" + data +"'");
		}
		return data;
	}
}