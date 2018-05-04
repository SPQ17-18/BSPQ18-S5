package SPQ;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

import SPQ.dao.UserDAO;
import SPQ.data.User;

public class VisaService extends Thread{
	private DataInputStream in;
	private DataOutputStream out;
	private Socket tcpSocket;
	// Se instancia el socket
	public VisaService(Socket socket) {
		try {
			this.tcpSocket = socket;
		    this.in = new DataInputStream(socket.getInputStream());
			this.out = new DataOutputStream(socket.getOutputStream());
			this.start();	//start llama a run()
		} catch (IOException e) {
			System.err.println("# VisaService - TCPConnection IO error:" + e.getMessage());
		}
	}

	public void run() {
		try {
			String data = this.in.readUTF();
			System.out.println("   - VisaService - Received data from '" + tcpSocket.getInetAddress().getHostAddress() + ":" + tcpSocket.getPort() + "' -> '" + data + "'");					
			data = this.readData(data);
			this.out.writeUTF(data);					
			System.out.println("   - VisaService - Sent data to '" + tcpSocket.getInetAddress().getHostAddress() + ":" + tcpSocket.getPort() + "' -> '" + data.toUpperCase() + "'");
		} catch (EOFException e) {
			System.err.println("   # VisaService - TCPConnection EOF error" + e.getMessage());
		} catch (IOException e) {
			System.err.println("   # VisaService - TCPConnection IO error:" + e.getMessage());
		} finally {
			try {
				tcpSocket.close();
			} catch (IOException e) {
				System.err.println("   # VisaService - TCPConnection IO error:" + e.getMessage());
			}
		}
	}
	
	public String readData(String data) {
		String[] arrayData = data.split(",");
		data = "incorrect";
		try {
			String username = arrayData[0];
			int cardNumber = Integer.parseInt(arrayData[1]);
			int ccv = Integer.parseInt(arrayData[2]);
			double price = Double.parseDouble(arrayData[2]);
			
			UserDAO userDAO = new UserDAO();
			User user = userDAO.getUser(username);
			
			
			if (user.getUsername().equals(username) && user.getCcv()==ccv &&user.getSaldo() > price && userDAO.updateUser(user, price)) {
					data = "correct";		
			}
			
		}catch (RuntimeException e) {
			System.err.println(" # VisaService - Wrong data");
		}
		return data;
	}
}
