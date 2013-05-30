package cl.net.services.impl;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.uri.UriBuilderImpl;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import cl.net.services.RestSvc;
import cl.net.utils.StringUtils;
import cl.net.vo.RestVO;

public class RestSvcImpl implements RestSvc{
	
	public String get(RestVO restVO, Map<String, Object> params) {
        ClientConfig config = new DefaultClientConfig();
        String url = restVO.getUrl();
        String[] getData;
        int j;
        if (params != null){        	
        	j = 0;
        	if(params.size() > 0){
        		url = url.concat("?");
	        	getData = new String[params.size()]; 
		        for (Map.Entry<String, Object> entry : params.entrySet()) {
		            String key = entry.getKey();
		            Object value = entry.getValue();
		            getData[j++] = key.concat("=").concat(value.toString());  
		        }
		        url = url.concat(StringUtils.implode("&", getData));
        	}
        }
        
        Client client = Client.create(config);
        WebResource service = client.resource(UriBuilder.fromUri(url).build());
        return(service. path("restPath").path("resourcePath").accept(MediaType.APPLICATION_JSON).get(String.class));
	}

	public String post(RestVO restVO, Map<String, Object> params) {
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        WebResource webResource = client.resource(UriBuilderImpl.fromUri(restVO.getUrl()).build());
        MultivaluedMapImpl formData = new MultivaluedMapImpl();
        if(params != null){
	        for (Map.Entry<String, Object> entry : params.entrySet()) {
	            String key = entry.getKey();
	            Object value = entry.getValue();
	            formData.add(key, value);
	        }
        }
        ClientResponse response = webResource.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE).post(ClientResponse.class, formData);
        return(response.getEntity(String.class));
	}

	public String delete(RestVO restVO) {
		 ClientConfig config = new DefaultClientConfig();
		 Client client = Client.create(config);
		 WebResource service = client.resource(UriBuilder.fromUri(restVO.getUrl()).build());
		 ClientResponse response = service.accept(MediaType.APPLICATION_JSON).delete(ClientResponse.class);
		 return(response.getEntity(String.class));
	}

	
	
}
