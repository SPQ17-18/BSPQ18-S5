package SPQ.gateway;

import java.net.Socket;

import java.io.DataInputStream;
import java.io.ObjectOutputStream;

import java.io.EOFException;
import java.io.IOException;
import java.net.UnknownHostException;

import SPQ.dto.UserDTO;

public class Facebook implements IFacebookGateway{

	private String message;
	private ObjectOutputStream out;
	private Socket tcpSocket;
	
	public Facebook(String serverIP, String serverPort) {
		super();
		try {
			System.out.println("PUERTO " + serverPort);
			this.tcpSocket = new Socket(serverIP, Integer.parseInt(serverPort));
			this.out = new ObjectOutputStream(this.tcpSocket.getOutputStream());
		} catch (IOException e) {
			System.err.println("# FacebookService - TCPConnection IO error:" + e.getMessage());
		}
	}

	public String register(UserDTO userDTO){
		try {

			//Streams to send and receive information are created from the Socket
			DataInputStream in = new DataInputStream(tcpSocket.getInputStream());

			//Send request (a UserDTO) to the server
			out.writeObject(userDTO);
			System.out.println(" - TCPSocketClient: Sent data to '" + tcpSocket.getInetAddress().getHostAddress() + ":" + tcpSocket.getPort() + "' -> '" + userDTO.toString()+ "'");

			//Read response (a String) from the server
			String receivedData = in.readUTF();	
			System.out.println(" - TCPSocketClient: Received data from '" + tcpSocket.getInetAddress().getHostAddress() + ":" + tcpSocket.getPort() + "' -> '" + receivedData + "'");
			
			message = receivedData;
			this.tcpSocket.close();
			
		} catch (UnknownHostException e) {
			System.err.println("# TCPSocketClient: Socket error: " + e.getMessage());
		} catch (EOFException e) {
			System.err.println("# TCPSocketClient: EOF error: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("# TCPSocketClient: IO error: " + e.getMessage());
		}
		
		System.out.println(message);
		return message;
	}

}
