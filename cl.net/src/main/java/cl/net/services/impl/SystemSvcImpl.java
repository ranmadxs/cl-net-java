package cl.net.services.impl;

import java.util.ResourceBundle;

import cl.net.services.RestSvc;
import cl.net.services.SystemSvc;
import cl.net.vo.RestVO;
import cl.net.vo.SystemVO;

public class SystemSvcImpl implements SystemSvc{

	private static ResourceBundle rb;
	private String restUrl;
	private RestSvc restSvc;
	
	
	public SystemSvcImpl() {
		super();
		rb = ResourceBundle.getBundle("constantes");
		this.restUrl = rb.getString("restUrl");	
		this.restSvc = new RestSvcImpl();
	}



	public void resetFileSystem(SystemVO systemVO){
		RestVO restVO = new RestVO();
    	restVO.setUrl(this.restUrl.concat("rs/svc.php/dir/deleteAllFilesSystem/").concat(String.valueOf(systemVO.getId())));    	
    	restSvc.delete(restVO);
	    
	}
	
}
