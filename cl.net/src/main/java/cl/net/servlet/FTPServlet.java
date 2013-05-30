package cl.net.servlet;

import java.io.IOException;
import java.net.SocketException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPFileFilter;
import org.apache.commons.net.ftp.FTPFileFilters;



public class FTPServlet extends HttpServlet{

	private static final long serialVersionUID = 8690724832461937259L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		FTPClient ftp = new FTPClient();
		try {
			ftp.connect("ranmadxs.dyndns.org", 21);
			ftp.login("usuario1", "nueva123");
			
			
			 System.out.println("Remote system is " + ftp.getSystemType());
			 
			 ftp.setFileType(FTP.BINARY_FILE_TYPE);
			 ftp.enterLocalPassiveMode();
			 ftp.setUseEPSVwithIPv4(true);
			 
			 
             for (FTPFile f : ftp.listFiles("/C")) {
                 System.out.println(f.getRawListing());
                 System.out.println(f.toFormattedString());
             }
			
			
			
			
			String[] names = ftp.listNames();
			System.out.println(ftp.pwd());
			ftp.cwd("C");
			names = ftp.listNames();
			System.out.println("============== LIST DIRS =============");
			FTPFile[] dirs = ftp.listDirectories();
			FTPFile dir;
			for (int i = 0; i < dirs.length; i++) {
				dir = dirs[i];
				System.out.println(dir.getName());
			}
			
			System.out.println("============== LIST FILES =============");
			FTPFile[] files = ftp.listFiles(".", FTPFileFilters.ALL);
			for (int i = 0; i < files.length; i++) {
				dir = files[i];
				System.out.println(dir.getName());
			}
			request.getRequestDispatcher("/pages/ftp.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}