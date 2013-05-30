package cl.net.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class HttpClientServlet extends HttpServlet{

	private static final long serialVersionUID = 8082393098259940441L;


	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {       
        
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet("http://ranmadxs.dyndns.org:9000/");
     // Execute the request
        HttpResponse httpResponse = httpclient.execute(httpget);
        String statusLine = httpResponse.getStatusLine().toString();
        String content = null;
        // Examine the response status
        System.out.println(statusLine);
        HttpEntity entity = httpResponse.getEntity();
        if (entity != null) {
            InputStream instream = entity.getContent();
            try {

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(instream));
                // do something useful with the response
                content = reader.readLine();
                System.out.println(content);

            } catch (IOException ex) {

                // In case of an IOException the connection will be released
                // back to the connection manager automatically
                throw ex;

            } catch (RuntimeException ex) {

                // In case of an unexpected exception you may want to abort
                // the HTTP request in order to shut down the underlying
                // connection and release it back to the connection manager.
                httpget.abort();
                throw ex;

            } finally {

                // Closing the input stream will trigger connection release
                instream.close();

            }

            // When HttpClient instance is no longer needed,
            // shut down the connection manager to ensure
            // immediate deallocation of all system resources
            httpclient.getConnectionManager().shutdown();
        }
        
        request.setAttribute("ping", statusLine);
        request.setAttribute("content", content);
        request.getRequestDispatcher("/pages/index.jsp").forward(request, response);
	
	}
	
}
