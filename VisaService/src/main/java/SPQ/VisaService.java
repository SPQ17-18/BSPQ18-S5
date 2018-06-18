package SPQ;

import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import SPQ.dao.AccountDAO;
import SPQ.data.Account;
import SPQ.dto.PaymentDTO;

public class VisaService extends Thread{
	private ObjectInputStream in;
	private DataOutputStream out;
	private Socket tcpSocket;
	// Se instancia el socket
	public VisaService(Socket socket) {
		try {
			this.tcpSocket = socket;
		    this.in = new ObjectInputStream(socket.getInputStream());
			this.out = new DataOutputStream(socket.getOutputStream());
			this.start();	//start llama a run()
		} catch (IOException e) {
			System.err.println("# VisaService - TCPConnection IO error:" + e.getMessage());
		}
	}

	public void run() {
		try {
			PaymentDTO paymentDTO = (PaymentDTO) this.in.readObject();
			System.out.println("   - VisaService - Received data from '" + tcpSocket.getInetAddress().getHostAddress() + ":" + tcpSocket.getPort() + "' -> '" + paymentDTO.toString() + "'");					
			String data = this.readData(paymentDTO);
			this.out.writeUTF(data);					
			System.out.println("   - VisaService - Sent data to '" + tcpSocket.getInetAddress().getHostAddress() + ":" + tcpSocket.getPort() + "' -> '" + data + "'");
		} catch (EOFException e) {
			System.err.println("   # VisaService - TCPConnection EOF error" + e.getMessage());
		} catch (IOException e) {
			System.err.println("   # VisaService - TCPConnection IO error:" + e.getMessage());
		}catch (ClassNotFoundException e) {
			System.err.println("   # VisaService - ClassNotFound error:" + e.getMessage());
		} finally {
			try {
				tcpSocket.close();
			} catch (IOException e) {
				System.err.println("   # VisaService - TCPConnection IO error:" + e.getMessage());
			}
		}
	}
	
	public String readData(PaymentDTO paymentDTO) {
		String data = "false";
		try {
			
			AccountDAO accountDAO = new AccountDAO();
			Account account = new Account(paymentDTO);
			if (accountDAO.pay(account, paymentDTO.getTotal())){
				return "true";
			}
			
		}catch (RuntimeException e) {
			System.err.println(" # VisaService - Wrong data");
		}
		return data;
	}
}
