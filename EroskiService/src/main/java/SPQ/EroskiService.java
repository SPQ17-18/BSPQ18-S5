package SPQ;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

import SPQ.dao.ProductDAO;
import SPQ.data.Product;

public class EroskiService extends Thread {
	private DataInputStream in;
	private DataOutputStream out;
	private Socket tcpSocket;
	// Se instancia el socket
	public EroskiService(Socket socket) {
		try {
			this.tcpSocket = socket;
		    this.in = new DataInputStream(socket.getInputStream());
			this.out = new DataOutputStream(socket.getOutputStream());
			this.start();	//start llama a run()
		} catch (IOException e) {
			System.err.println("# EroskiService - TCPConnection IO error:" + e.getMessage());
		}
	}

	public void run() {
		try {
			String data = this.in.readUTF();
			System.out.println("   - EroskiService - Received data from '" + tcpSocket.getInetAddress().getHostAddress() + ":" + tcpSocket.getPort() + "' -> '" + data + "'");					
			data = this.readData(data);
			this.out.writeUTF(data);					
			System.out.println("   - EroskiService - Sent data to '" + tcpSocket.getInetAddress().getHostAddress() + ":" + tcpSocket.getPort() + "' -> '" + data.toUpperCase() + "'");
		} catch (EOFException e) {
			System.err.println("   # EroskiService - TCPConnection EOF error" + e.getMessage());
		} catch (IOException e) {
			System.err.println("   # EroskiService - TCPConnection IO error:" + e.getMessage());
		} finally {
			try {
				tcpSocket.close();
			} catch (IOException e) {
				System.err.println("   # EroskiService - TCPConnection IO error:" + e.getMessage());
			}
		}
	}
	
	//DEVOLVER TODOS LOS PRODUCTOS
	
	public String readData(String data) {
		String[] arrayData = data.split(",");
		try {
			String email = arrayData[0];
			String password = arrayData[1];
			
			ProductDAO productDAO = new ProductDAO();
			List<Product> products = productDAO.getProducts();
			
			if (products != null) {
				
				for (Product product : products)
				{
				    data += product.getName() + "," + product.getPrice() + ";";
				}
				
				
			}else {
				data = "incorrect";
			}
		}catch (RuntimeException e) {
			System.err.println(" # EroskiService - Wrong data");
		}
		return data;
	}
}
