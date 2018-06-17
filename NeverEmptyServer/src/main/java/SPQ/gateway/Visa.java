package SPQ.gateway;

import java.io.DataInputStream;
import java.io.ObjectOutputStream;

import java.io.EOFException;
import java.io.IOException;

import java.net.Socket;
import java.net.UnknownHostException;

import SPQ.dto.PaymentDTO;

public class Visa implements IVisaGateway{

	private boolean answer;
	private ObjectOutputStream out;
	private Socket tcpSocket;
	
	public Visa(String serverIP, String serverPort) {
		super();
		try {
			System.out.println("PUERTO " + serverPort);
			this.tcpSocket = new Socket(serverIP, Integer.parseInt(serverPort));
			this.out = new ObjectOutputStream(this.tcpSocket.getOutputStream());
		} catch (IOException e) {
			System.err.println("# VisaService - TCPConnection IO error:" + e.getMessage());
		}
	}

	public boolean pay(PaymentDTO paymentDTO){
		try {
			//Streams to send and receive information are created from the Socket
			DataInputStream in = new DataInputStream(tcpSocket.getInputStream());

			//Send request (a UserDTO) to the server
			out.writeObject(paymentDTO);
			System.out.println(" - TCPSocketClient: Sent data to '" + tcpSocket.getInetAddress().getHostAddress() + ":" + tcpSocket.getPort() + "' -> '" + paymentDTO.toString()+ "'");

			//Read response (a String) from the server
			String receivedData = in.readUTF();	
			System.out.println(" - TCPSocketClient: Received data from '" + tcpSocket.getInetAddress().getHostAddress() + ":" + tcpSocket.getPort() + "' -> '" + receivedData + "'");
			answer = (receivedData.equals("true")) ? true : false;
			tcpSocket.close();
		
			
		} catch (UnknownHostException e) {
			System.err.println("# TCPSocketClient: Socket error: " + e.getMessage());
		} catch (EOFException e) {
			System.err.println("# TCPSocketClient: EOF error: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("# TCPSocketClient: IO error: " + e.getMessage());
		}
		
		return answer;
	}
	
}