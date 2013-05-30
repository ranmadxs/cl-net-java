package cl.net.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileOutputStream;

public class SMBServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8650176499742203591L;

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		 String user = "admin";
		    String pass ="epsilon1";

		    String sharedFolder="WDTVLiveHub";
		    String path="smb://190.162.154.51/"+sharedFolder+"/TwonkyMediaServer-log.txt";
		    NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication("",user, pass);
		    SmbFile smbFile = new SmbFile(path);
		    SmbFileOutputStream smbfos = new SmbFileOutputStream(smbFile);
		    smbfos.write("testing....and writing to a file".getBytes());
		    System.out.println("completed ...nice !");
		    request.getRequestDispatcher("/pages/smb.jsp").forward(request, response);
			
	}
}
