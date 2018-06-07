package SPQ;

import java.io.IOException;
import java.net.ServerSocket;

public class PaypalServer {

	
private static int numClients = 0;
	
	public static void main(String args[]) {
		if (args.length < 1) {
			System.err.println(" # Usage: PaypalServer [PORT]");
			System.exit(1);
		}
		
		//args[1] = Server socket port
		int serverPort = Integer.parseInt(args[0]);
		
		try {
			ServerSocket tcpServerSocket = new ServerSocket(serverPort);
			System.out.println(" - PaypalServer: Waiting for connections '" + tcpServerSocket.getInetAddress().getHostAddress() + ":" + tcpServerSocket.getLocalPort() + "' ...");
			
			while (true) {
				//Ejecuta el googleservice
				new PaypalService(tcpServerSocket.accept());
				System.out.println(" - PaypalServer: New client connection accepted. Client number: " + ++numClients);
			}
		} catch (IOException e) {
			System.err.println("# PaypalServer: IO error:" + e.getMessage());
		}
	}
	
}
