package SPQ.server;

import java.rmi.Naming;

import SPQ.remote.IMessenger;
import SPQ.remote.Messenger;

@SuppressWarnings("deprecation")
public class Server {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		if (args.length != 3) {
			System.out.println("How to invoke: java [policy] [codebase] Server.Server [host] [port] [server]");
			System.exit(0);
		}

		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		String name = "//" + args[0] + ":" + args[1] + "/" + args[2];

		try {
			
			IMessenger objServer = new Messenger();
			Naming.rebind(name, objServer);
			System.out.println("Messaging Server '" + name + "' active and waiting...");
			java.io.InputStreamReader inputStreamReader = new java.io.InputStreamReader ( System.in );
			java.io.BufferedReader stdin = new java.io.BufferedReader ( inputStreamReader );
			String line  = stdin.readLine();
		} catch (Exception e) {
			System.err.println("Messager exception: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
