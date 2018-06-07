package SPQ;

import java.io.IOException;
import java.net.ServerSocket;

public class GoogleServer {
	
private static int numClients = 0;
	
	public static void main(String args[]) {
		if (args.length < 1) {
			System.err.println(" # Usage: GoogleServer [PORT]");
			System.exit(1);
		}
		
		//args[1] = Server socket port
		int serverPort = Integer.parseInt(args[0]);
		
		try {
			ServerSocket tcpServerSocket = new ServerSocket(serverPort);
			System.out.println(" - GoogleServer: Waiting for connections '" + tcpServerSocket.getInetAddress().getHostAddress() + ":" + tcpServerSocket.getLocalPort() + "' ...");
			
			while (true) {
				//Ejecuta el googleservice
				new GoogleService(tcpServerSocket.accept());
				System.out.println(" - GoogleServer: New client connection accepted. Client number: " + ++numClients);
			}
		} catch (IOException e) {
			System.err.println("# GoogleServer: IO error:" + e.getMessage());
		}
	}
}