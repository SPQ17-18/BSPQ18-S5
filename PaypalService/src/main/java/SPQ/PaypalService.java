package SPQ;

import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import SPQ.dao.UserDAO;
import SPQ.data.User;
import SPQ.dto.PaymentDTO;

public class PaypalService extends Thread{
	private ObjectInputStream in;
	private DataOutputStream out;
	private Socket tcpSocket;
	// Se instancia el socket
	public PaypalService(Socket socket) {
		try {
			this.tcpSocket = socket;
		    this.in = new ObjectInputStream(socket.getInputStream());
			this.out = new DataOutputStream(socket.getOutputStream());
			this.start();	//start llama a run()
		} catch (IOException e) {
			System.err.println("# PaypalService - TCPConnection IO error:" + e.getMessage());
		}
	}

	public void run() {
		try {
			PaymentDTO paymentDTO = (PaymentDTO) this.in.readObject();
			System.out.println("   - PaypalService - Received data from '" + tcpSocket.getInetAddress().getHostAddress() + ":" + tcpSocket.getPort() + "' -> '" + paymentDTO.toString() + "'");					
			String data = this.readData(paymentDTO);
			this.out.writeUTF(data);					
			System.out.println("   - PaypalService - Sent data to '" + tcpSocket.getInetAddress().getHostAddress() + ":" + tcpSocket.getPort() + "' -> '" + data + "'");
		} catch (EOFException e) {
			System.err.println("   # PaypalService - TCPConnection EOF error" + e.getMessage());
		} catch (IOException e) {
			System.err.println("   # PaypalService - TCPConnection IO error:" + e.getMessage());
		}catch (ClassNotFoundException e) {
			System.err.println("   # PaypalService - ClassNotFound error:" + e.getMessage());
		} finally {
			try {
				tcpSocket.close();
			} catch (IOException e) {
				System.err.println("   # PaypalService - TCPConnection IO error:" + e.getMessage());
			}
		}
	}
	
	public String readData(PaymentDTO paymentDTO) {
		String data = "false";
		try {
			
			UserDAO userDAO = new UserDAO();
			User user = new User(paymentDTO.getUsername(), paymentDTO.getPassword(), -1);
			if(userDAO.pay(user, paymentDTO.getTotal())) data = "true";
			
		}catch (RuntimeException e) {
			System.err.println(" # PaypalService - Wrong data");
		}
		return data;
	}
}
