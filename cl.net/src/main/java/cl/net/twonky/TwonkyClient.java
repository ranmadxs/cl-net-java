package cl.net.twonky;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class TwonkyClient {

	
	private String serverAddr;
	private Integer portNumber;
	private Boolean isOnline;
	private Boolean isConnected;
	
	public TwonkyClient connect(String serverAddr, Integer portNumber){
		Socket socket = new Socket();
		this.serverAddr = serverAddr;
		this.portNumber = portNumber;
		this.isOnline = Boolean.FALSE;
		try{
			
			
		// Configure socket here
		socket.connect(new InetSocketAddress(serverAddr, portNumber), 3000);
		if (socket.isConnected()) {
			this.isOnline = Boolean.TRUE;
		} else {
			this.isOnline = Boolean.FALSE;
		}
		
		}catch (Exception e) {
			e.printStackTrace();
			this.isOnline = Boolean.FALSE;
		}
		this.getInfoStatus();
		return this;
	}
	
	public TwonkyClient login(String username, String password){
		//not implemented yet
		return this;
	}
	
	protected void getInfoStatus(){
		//TODO: Debo crear el VO del info status, revisar el systemout
		this.isConnected = Boolean.FALSE;
		try{		
			if(!this.isOnline){
				this.isConnected = Boolean.FALSE;
			}else{
				//http://ranmadxs.dyndns.org:9000/rpc/version
				String result = this.get(TwonkyType.CONNECTION_PROTOCOL.concat("://").concat(this.serverAddr)
						.concat(":").concat(String.valueOf(this.portNumber)).concat("/")
						.concat(TwonkyType.CONNECTION_RPC).concat("/info_status"));
				System.out.println(result);
				if(result == null){
					this.isConnected = Boolean.FALSE;
				}else if(result.contains("twonkymedia-server.ini")){
					this.isConnected = Boolean.TRUE;					
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			this.isConnected = Boolean.FALSE;
		}	
	}
	
	public List<TwonkyFile> listFiles(String queryParam){
		return this.listFiles(queryParam, null);
	}
	
	
	public List<TwonkyFile> listFiles(String queryParam, String filter){
		if(filter == null){
			filter = TwonkyType.FILTER_ALL;
		}
		List<TwonkyFile> list = new ArrayList<TwonkyFile>();
		return list;
	}
	
	public Boolean isConnected(){
		if(this.isConnected == null){
			this.isConnected = Boolean.FALSE;
		}
		return this.isConnected;
	}
	
	protected String get(String url){
		ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        WebResource service = client.resource(UriBuilder.fromUri(url).build());
        return(service. path("restPath").path("resourcePath").accept(MediaType.TEXT_HTML).get(String.class));
	}
}
