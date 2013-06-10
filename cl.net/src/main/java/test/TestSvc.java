package test;

import java.io.File;

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
import cl.net.services.impl.FileSystemSvcImpl;
import cl.net.services.impl.FtpSvcImpl;
import cl.net.services.impl.RestSvcImpl;
import cl.net.services.impl.SystemSvcImpl;
import cl.net.services.impl.TwonkySvcImpl;
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
		
		String json = "{\"id\":3,\"name\":\"torrent\",\"FK_system\":\"1\",\"tipo\":\"1\",\"fecha\":\"2013-06-02 01:19\",\"FK_dir\":\"\",\"size\":\"4096\",\"codigo\":\"\",\"breadcrumb\":\"\",\"class\":\"class cl.net.vo.DirVO\"}";
		try {
			dirVO = (DirVO) GsonUtils.json2obj(json, DirVO.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	@Test
	public void ejemplo(){
		String lol = ".miocrawler_cache";
		if(lol.startsWith(".")){
			System.out.println("parte con punto");
		}else{
			System.out.println("No parte con punto");
		}
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
	
	//@Test
	public void fileScann(){
		ConnectionVO conn = new ConnectionVO();
		conn.setDns("C:\\media\\");
		conn.setUri("Series");
		
		SystemVO systemVO = new SystemVO();
		systemVO.setId(1);
		
		DirVO dirVO= new DirVO();
		dirVO.setFK_system(systemVO.getId());
		
		SystemSvc systemSvc = new SystemSvcImpl();
		ConnectionSvc connSvc = new FileSystemSvcImpl(conn);
		try {
			systemSvc.resetFileSystem(systemVO);
			connSvc.scann(dirVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
//		  String path = "/"; 
//		  
//		  String files;
//		  File folder = new File(path);
//		  File[] listOfFiles = folder.listFiles(); 
//		  
//		  for (File file : listOfFiles) {
//			   files = file.getName();
//			   System.out.println(files);
//		}
		  
		
	}
	
	
	//@Test
	public void prueba(){		
		SystemVO systemVO = new SystemVO();
		systemVO.setId(1);
		ConnectionVO conn = new ConnectionVO();
		conn.setPort(201);
		//conn.setPort(9000);
		conn.setUsername("pi");
		conn.setPassword("epsilon1");
		//conn.setIp("ranmadxs.dyndns.org");
		conn.setIp("192.168.1.103");
		ConnectionSvc connSvc = new FtpSvcImpl(conn);
		//ConnectionSvc connSvc = new TwonkySvcImpl(conn);
		SystemSvc systemSvc = new SystemSvcImpl();
		DirVO dirVO = new DirVO();
		dirVO.setFK_system(systemVO.getId());
		try {
			systemSvc.resetFileSystem(systemVO);
			connSvc.scann(dirVO);
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
                
        DirVO dirVO;
		try {
			dirVO = (DirVO) GsonUtils.json2obj(json, DirVO.class);
	        System.out.println(dirVO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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