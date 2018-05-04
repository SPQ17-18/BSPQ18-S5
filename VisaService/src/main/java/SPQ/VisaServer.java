package SPQ;

import java.io.IOException;
import java.net.ServerSocket;

public class VisaServer {

	private static int numClients = 0;
		
		public static void main(String args[]) {
			if (args.length < 1) {
				System.err.println(" # Usage: VisaServer [PORT]");
				System.exit(1);
			}
			
			//args[1] = Server socket port
			int serverPort = Integer.parseInt(args[0]);
			
			try {
				ServerSocket tcpServerSocket = new ServerSocket(serverPort);
				System.out.println(" - VisaServer: Waiting for connections '" + tcpServerSocket.getInetAddress().getHostAddress() + ":" + tcpServerSocket.getLocalPort() + "' ...");
				
				while (true) {
					//Ejecuta el googleservice
					new VisaService(tcpServerSocket.accept());
					System.out.println(" - VisaServer: New client connection accepted. Client number: " + ++numClients);
				}
			} catch (IOException e) {
				System.err.println("# VisaServer: IO error:" + e.getMessage());
			}
		}
		
	
}
