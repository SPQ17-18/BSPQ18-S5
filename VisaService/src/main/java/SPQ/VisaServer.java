package SPQ;

import java.io.IOException;
import java.net.ServerSocket;

import org.apache.log4j.Logger;

public class VisaServer {
	static Logger logger = Logger.getLogger(VisaServer.class.getName());
	private static int numClients = 0;
		
		public static void main(String args[]) {
			if (args.length < 1) {
				logger.error("Invalid argument number.");
				System.exit(1);
			}
			
			//args[1] = Server socket port
			int serverPort = Integer.parseInt(args[0]);
			
			try {
				ServerSocket tcpServerSocket = new ServerSocket(serverPort);
				logger.info("Waiting for connections '" + tcpServerSocket.getInetAddress().getHostAddress() + ":" + tcpServerSocket.getLocalPort() + "' ...");
				
				while (true) {
					//Ejecuta el visaService
					new VisaService(tcpServerSocket.accept());
					logger.info("New client connection accepted. Client number: " + ++numClients);
				}
			} catch (IOException e) {
				logger.error("IO error:" + e.getMessage());
			}
		}
		
	
}
