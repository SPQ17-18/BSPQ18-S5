package SPQ;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import SPQ.dao.ProductDAO;
import SPQ.data.Product;
import SPQ.dto.ProductDTO;

public class EroskiService extends Thread {
	private DataInputStream in;
	private ObjectOutputStream out;
	private Socket tcpSocket;
	static Logger logger = Logger.getLogger(EroskiServer.class.getName());
	// Se instancia el socket
	public EroskiService(Socket socket) {
		try {
			this.tcpSocket = socket;
		    this.in = new DataInputStream(socket.getInputStream());
			this.out = new ObjectOutputStream(socket.getOutputStream());
			this.start();	//start llama a run()
		} catch (IOException e) {
			logger.error("TCPConnection IO error:" + e.getMessage());
		}
	}

	public void run() {
		try {
			String data = this.in.readUTF();
			ProductDTO productDTO = null;
			logger.info("Received data from '" + tcpSocket.getInetAddress().getHostAddress() + ":" + tcpSocket.getPort() + "' -> '" + data + "'");					
			productDTO = this.readData(data);
			this.out.writeObject(productDTO);					
			logger.info("Sent data to '" + tcpSocket.getInetAddress().getHostAddress() + ":" + tcpSocket.getPort() + "' -> '" + productDTO.getProductList().toString() + "'");
		} catch (EOFException e) {
			logger.error("TCPConnection EOF error" + e.getMessage());
		} catch (IOException e) {
			logger.error("TCPConnection IO error:" + e.getMessage());
		} finally {
			try {
				tcpSocket.close();
			} catch (IOException e) {
				logger.error("TCPConnection IO error:" + e.getMessage());
			}
		}
	}
	
	//Devolver todos los productos
	
	public ProductDTO readData(String data) {
		ProductDTO productDTO = null;
		try {
			
			ProductDAO productDAO = new ProductDAO();
			ArrayList<Product> products = productDAO.getProducts();
			if ( products.isEmpty()) {
				logger.error("No product fetched.");
			}else {
				productDTO = new ProductDTO(products);
			}
			
		}catch (RuntimeException e) {
			logger.error("Wrong data.");
		}
		return productDTO;
	}
}
