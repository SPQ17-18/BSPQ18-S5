package SPQ.gateway;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

import SPQ.data.Product;
import SPQ.dto.ProductDTO;
public class Eroski implements IEroskiGateway{

	
	private String serverIP;
	private int serverPort;

	public Eroski(String serverIP, String serverPort) {
		super();
		this.serverIP = serverIP;
		this.serverPort = Integer.parseInt(serverPort);
	}

	public ProductDTO getProducts(){
		ProductDTO products = null;
		try {
			Socket tcpSocket = new Socket(serverIP, serverPort);
			//Streams to send and receive information are created from the Socket
			ObjectInputStream in = new ObjectInputStream(tcpSocket.getInputStream());

			DataOutputStream out = new DataOutputStream(tcpSocket.getOutputStream());
					//Send request (a String) to the server
					out.writeUTF("");
			System.out.println(" - TCPSocketClient: Sent data to '" + tcpSocket.getInetAddress().getHostAddress() + ":" + tcpSocket.getPort());

			//Read response (a Object) from the server
			products = (ProductDTO) in.readObject();
			System.out.println(" - TCPSocketClient: Received data from '" + tcpSocket.getInetAddress().getHostAddress() + ":" + tcpSocket.getPort() + "' -> '" + products.getProductList().toString() + "'");

		} catch (UnknownHostException e) {
			System.err.println("# TCPSocketClient: Socket error: " + e.getMessage());
		} catch (EOFException e) {
			System.err.println("# TCPSocketClient: EOF error: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("# TCPSocketClient: IO error: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.err.println("# TCPSocketClient: ClassNotFound error: " + e.getMessage());
		}
		return products;
	}
	

}
