package SPQ;

import java.io.IOException;
import java.net.ServerSocket;

public class EroskiServer {
	
private static int numClients = 0;
	
	public static void main(String args[]) {
		if (args.length < 1) {
			System.err.println(" # Usage: EroskiServer [PORT]");
			System.exit(1);
		}
		
		int serverPort = Integer.parseInt(args[0]);
		
		try {
			ServerSocket tcpServerSocket = new ServerSocket(serverPort);
			System.out.println(" - EroskiServer: Waiting for connections '" + tcpServerSocket.getInetAddress().getHostAddress() + ":" + tcpServerSocket.getLocalPort() + "' ...");
			
			while (true) {
				//Ejecuta el eroskiservice
				new EroskiService(tcpServerSocket.accept());
				System.out.println(" - EroskiServer: New client connection accepted. Client number: " + ++numClients);
			}
		} catch (IOException e) {
			System.err.println("# EroskiServer: IO error:" + e.getMessage());
		}
	}
}
