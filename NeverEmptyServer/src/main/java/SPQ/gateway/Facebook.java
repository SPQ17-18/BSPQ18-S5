package SPQ.gateway;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Facebook implements IFacebookGateway{

	private String serverIP;
	private int serverPort;
	private String message;
	public Facebook(String serverIP, String serverPort) {
		super();
		this.serverIP = serverIP;
		this.serverPort = Integer.parseInt(serverPort);
	}

	public String register(String email, String password){
		try {
			Socket tcpSocket = new Socket(serverIP, serverPort);
			//Streams to send and receive information are created from the Socket
			DataInputStream in = new DataInputStream(tcpSocket.getInputStream());
			
			String dataToSend = email + "," + password;
			DataOutputStream out = new DataOutputStream(tcpSocket.getOutputStream());
					//Send request (a String) to the server
					out.writeUTF(dataToSend);
			System.out.println(" - TCPSocketClient: Sent data to '" + tcpSocket.getInetAddress().getHostAddress() + ":" + tcpSocket.getPort() + "' -> '" + dataToSend + "'");

			//Read response (a String) from the server
			String receivedData = in.readUTF();	
			System.out.println(" - TCPSocketClient: Received data from '" + tcpSocket.getInetAddress().getHostAddress() + ":" + tcpSocket.getPort() + "' -> '" + receivedData + "'");
			message = receivedData;
		} catch (UnknownHostException e) {
			System.err.println("# TCPSocketClient: Socket error: " + e.getMessage());
		} catch (EOFException e) {
			System.err.println("# TCPSocketClient: EOF error: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("# TCPSocketClient: IO error: " + e.getMessage());
		}
		return message;
	}
	
	public String login(String email, String password) {
		return this.register(email, password);
	}
}
