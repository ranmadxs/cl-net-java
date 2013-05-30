package cl.net.servlet;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PingServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7798926377217987361L;

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException { 
		Socket socket = new Socket();
		String serverAddr = "ranmadxs.dyndns.org";
		Integer portNumber = 21;
		String lineServer = null;
		
		try{
			
		
		// Configure socket here
		socket.connect(new InetSocketAddress(serverAddr, portNumber), 3000);
		if (! socket.isConnected()) {
			lineServer = "OFFLINE";
		} else {
			lineServer = "ONLINE";
		}
		
		}catch (Exception e) {
			e.printStackTrace();
			lineServer = "OFFLINE";
		}
		
        request.setAttribute("lineServer", lineServer);
        request.setAttribute("serverAddr", serverAddr);
        request.setAttribute("portNumber", portNumber);
        request.getRequestDispatcher("/pages/ping.jsp").forward(request, response);
	}
}
