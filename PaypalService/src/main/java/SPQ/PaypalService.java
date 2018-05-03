package SPQ;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

import SPQ.dao.UserDAO;
import SPQ.data.User;

public class PaypalService extends Thread{
	private DataInputStream in;
	private DataOutputStream out;
	private Socket tcpSocket;
	// Se instancia el socket
	public PaypalService(Socket socket) {
		try {
			this.tcpSocket = socket;
		    this.in = new DataInputStream(socket.getInputStream());
			this.out = new DataOutputStream(socket.getOutputStream());
			this.start();	//start llama a run()
		} catch (IOException e) {
			System.err.println("# PaypalService - TCPConnection IO error:" + e.getMessage());
		}
	}

	public void run() {
		try {
			String data = this.in.readUTF();
			System.out.println("   - PaypalService - Received data from '" + tcpSocket.getInetAddress().getHostAddress() + ":" + tcpSocket.getPort() + "' -> '" + data + "'");					
			data = this.readData(data);
			this.out.writeUTF(data);					
			System.out.println("   - PaypalService - Sent data to '" + tcpSocket.getInetAddress().getHostAddress() + ":" + tcpSocket.getPort() + "' -> '" + data.toUpperCase() + "'");
		} catch (EOFException e) {
			System.err.println("   # PaypalService - TCPConnection EOF error" + e.getMessage());
		} catch (IOException e) {
			System.err.println("   # PaypalService - TCPConnection IO error:" + e.getMessage());
		} finally {
			try {
				tcpSocket.close();
			} catch (IOException e) {
				System.err.println("   # PaypalService - TCPConnection IO error:" + e.getMessage());
			}
		}
	}
	
	public String readData(String data) {
		String[] arrayData = data.split(",");
		data = "incorrect";
		try {
			String email = arrayData[0];
			String password = arrayData[1];
			
			double price = Double.parseDouble(arrayData[2]);
			
			UserDAO userDAO = new UserDAO();
			User user = userDAO.getUser(email);
			
			if (user.getPassword().equals(password) &&
					user.getBalance() > price &&
					userDAO.updateUser(user, price)) {
					data = "correct";		
			}
			
		}catch (RuntimeException e) {
			System.err.println(" # PaypalService - Wrong data");
		}
		return data;
	}
}
