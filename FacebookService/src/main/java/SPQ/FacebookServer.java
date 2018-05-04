package SPQ;

import java.io.IOException;
import java.net.ServerSocket;

public class FacebookServer {
	
private static int numClients = 0;
	
	public static void main(String args[]) {
		if (args.length < 1) {
			System.err.println(" # Usage: FacebookServer [PORT]");
			System.exit(1);
		}
		
		//args[1] = Server socket port
		int serverPort = Integer.parseInt(args[0]);
		
		try {
			ServerSocket tcpServerSocket = new ServerSocket(serverPort);
			System.out.println(" - FacebookServer: Waiting for connections '" + tcpServerSocket.getInetAddress().getHostAddress() + ":" + tcpServerSocket.getLocalPort() + "' ...");
			
			while (true) {
				//Ejecuta el googleservice
				new FacebookService(tcpServerSocket.accept());
				System.out.println(" - FacebookServer: New client connection accepted. Client number: " + ++numClients);
			}
		} catch (IOException e) {
			System.err.println("# FacebookServer: IO error:" + e.getMessage());
		}
	}
}