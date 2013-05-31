package test;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import org.junit.Test;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.uri.UriBuilderImpl;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import cl.net.services.ConnectionSvc;
import cl.net.services.RestSvc;
import cl.net.services.SystemSvc;
import cl.net.services.impl.FtpSvcImpl;
import cl.net.services.impl.RestSvcImpl;
import cl.net.services.impl.SystemSvcImpl;
import cl.net.twonky.TwonkyClient;
import cl.net.twonky.TwonkyType;
import cl.net.utils.GsonUtils;
import cl.net.utils.StringUtils;
import cl.net.vo.ConnectionVO;
import cl.net.vo.DirVO;
import cl.net.vo.RestVO;
import cl.net.vo.SystemVO;


public class TestSvc {

	//@Test
	public void ejemploJson(){
		DirVO dirVO = new DirVO();
		
		String json = "{\"id\":5,\"name\":\"C\",\"FK_system\":\"1\",\"tipo\":\"1\",\"fecha\":\"2013-05-26 19:52\",\"FK_dir\":\"\",\"class\":\"class cl.net.vo.DirVO\"}";
		dirVO = (DirVO) GsonUtils.json2obj(json, DirVO.class);
		System.out.println(dirVO);
	}
	
	//@Test
	public void subStr(){
		String text = "{\"id\":5,\"name\":\"C\",\"FK_system\":\"1\",\"tipo\":\"1\",\"fecha\":\"2013-05-26 19:52\",\"FK_dir\":\"\",\"class\":\"class cl.net.vo.DirVO\"}"
					+	"<!-- Hosting24 Analytics Code -->"
					+	"<script type=\"text/javascript\" src=\"http://stats.hosting24.com/count.php\"></script>"
					+	"<!-- End Of Analytics Code -->";
		System.out.println(text);
		int inicio = text.indexOf("<!--");		
		
		System.out.println(text.substring(0,inicio));
	}

	//@Test
	public void twonkyClient(){
		//http://192.168.1.101:9000/rpc/getdir?path=001/001/001
		TwonkyClient twonyClient = new TwonkyClient();
		twonyClient.connect("192.168.1.101", 9000);
		if(twonyClient.isConnected()){
			twonyClient.listFiles("001/001/001", TwonkyType.FILTER_ALL);
		}else{
			System.out.println("OFFLINE");
		}
	}
	
	
	//@Test
	public void twonky(){
		RestSvc restSvc = new RestSvcImpl();
		RestVO restVO = new RestVO();
		restVO.setUrl("http://192.168.1.101:9000/rpc/getdir?path=001");
		String result = restSvc.get(restVO, null);
		String[] res = result.split("\\n");
		System.out.println(result);
//		for (String var : res) {
//			if(var.contains("D/")){
//				System.out.println("Es un directorio");
//			}
//		}
		System.out.println(res);
	}
	
	
	@Test
	public void prueba(){		
		SystemVO systemVO = new SystemVO();
		systemVO.setId(1);
		ConnectionVO conn = new ConnectionVO();
		conn.setPort(21);
		conn.setUsername("usuario1");
		conn.setPassword("nueva123");
		//conn.setIp("ranmadxs.dyndns.org");
		conn.setIp("192.168.1.254");
		ConnectionSvc connSvc = new FtpSvcImpl(conn);
		SystemSvc systemSvc = new SystemSvcImpl();
		try {
			systemSvc.resetFileSystem(systemVO);
			connSvc.scann(systemVO, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//@Test 
	public void ejemploGet(){
		
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        WebResource service = client.resource(UriBuilder.fromUri("http://localhost/net/rs/svc.php/dir/read/5").build());
        String json = service. path("restPath").path("resourcePath").accept(MediaType.APPLICATION_JSON).get(String.class);
        // getting XML data
        System.out.println(json);
                
        DirVO dirVO = (DirVO) GsonUtils.json2obj(json, DirVO.class);
        System.out.println(dirVO);
        // getting JSON data
        //System.out.println(service. path("restPath").path("resourcePath").accept(MediaType.APPLICATION_XML).get(String.class));

		
	}
	
	//@Test
	public void ejemploDelete(){
		 ClientConfig config = new DefaultClientConfig();
		 Client client = Client.create(config);
		 WebResource service = client.resource(UriBuilder.fromUri("http://localhost/net/rs/svc.php/dir/delete/3").build());
		 ClientResponse response = service.accept(MediaType.APPLICATION_JSON).delete(ClientResponse.class);
		 System.out.println("Response " + response.getEntity(String.class));
	}
	
	
	//@Test
	public void ejemploPost(){
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        WebResource webResource = client.resource(UriBuilderImpl.fromUri("http://localhost/net/rs/svc.php/dir/lista").build());
        MultivaluedMapImpl formData = new MultivaluedMapImpl();
        formData.add("FK_system", "1");
       // formData.add("name2", "val2");
        ClientResponse response = webResource.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE).post(ClientResponse.class, formData);
        System.out.println("Response " + response.getEntity(String.class));
	}
}