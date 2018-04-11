package SPQ;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;



public class GoogleService extends Thread {
	private DataInputStream in;
	private DataOutputStream out;
	private Socket tcpSocket;
	private List<String> emails = new ArrayList<String>();
	public GoogleService(Socket socket) {
		try {
			this.emails.add("xsarri9@opendeusto.es");
			this.emails.add("mikel.fdez@opendeusto.es");
			this.emails.add("cristian.perez@opendeusto.es");
			this.emails.add("enara.etxaniz@opendeusto.es");
			this.emails.add("a");
			this.tcpSocket = socket;
		    this.in = new DataInputStream(socket.getInputStream());
			this.out = new DataOutputStream(socket.getOutputStream());
			this.start();
		} catch (IOException e) {
			System.err.println("# GoogleService - TCPConnection IO error:" + e.getMessage());
		}
	}

	public void run() {
		try {
			String data = this.in.readUTF();
			
			System.out.println("   - GoogleService - Received data from '" + tcpSocket.getInetAddress().getHostAddress() + ":" + tcpSocket.getPort() + "' -> '" + data + "'");					
			data = this.mail(data);
			this.out.writeUTF(data);					
			System.out.println("   - GoogleService - Sent data to '" + tcpSocket.getInetAddress().getHostAddress() + ":" + tcpSocket.getPort() + "' -> '" + data.toUpperCase() + "'");
		} catch (EOFException e) {
			System.err.println("   # GoogleService - TCPConnection EOF error" + e.getMessage());
		} catch (IOException e) {
			System.err.println("   # GoogleService - TCPConnection IO error:" + e.getMessage());
		} finally {
			try {
				tcpSocket.close();
			} catch (IOException e) {
				System.err.println("   # GoogleService - TCPConnection IO error:" + e.getMessage());
			}
		}
	}
	
	public String mail(String msg) {
		for (int i=0; i<emails.size(); i++){
			if (emails.get(i).equals(msg)){
				return "Correct";
			}
		}
		
		return "Incorrect";
	}
}
