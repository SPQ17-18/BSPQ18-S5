package SPQ.main;

import java.awt.EventQueue;

import SPQ.controller.NeverEmptyController;
import SPQ.gui.VentanaInicio;
import SPQ.remote.RMIServiceLocator;

public class Main {
	public static void main(String[] args) {
		if (args.length != 3) {
			System.out.println("- NeverEmptyCLient: Wrong number of arguments");
			System.exit(0);
		}
		
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
			}
		
		try {
			RMIServiceLocator rmi = new RMIServiceLocator();
			rmi.setService(args);
			NeverEmptyController neverEmptyController = new NeverEmptyController(rmi);
			//Inicializo la ventana inicio
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						VentanaInicio vInicio = new VentanaInicio(neverEmptyController);
						vInicio.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
