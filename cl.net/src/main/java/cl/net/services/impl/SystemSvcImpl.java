package cl.net.services.impl;

import java.util.ResourceBundle;

import cl.net.services.RestSvc;
import cl.net.services.SystemSvc;
import cl.net.utils.GsonUtils;
import cl.net.vo.ConnectionVO;
import cl.net.vo.DirVO;
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


	public ConnectionVO getConnection(SystemVO systemVO) throws Exception{
		System.out.println(">> getConection sys_id:: ".concat(String.valueOf(systemVO.getId())));
		ConnectionVO connectionVO = null;
		RestVO restVO = new RestVO();
    	restVO.setUrl(this.restUrl.concat("rpc/svc.php/connection/readSys/").concat(String.valueOf(systemVO.getId())));
    	String json = restSvc.get(restVO, null);
    	connectionVO = (ConnectionVO) GsonUtils.json2obj(json, ConnectionVO.class);
		return connectionVO;
	}

	public void resetFileSystem(SystemVO systemVO){
		System.out.println(">>resetFileSystem sys_id:: ".concat(String.valueOf(systemVO.getId())));
		RestVO restVO = new RestVO();
    	restVO.setUrl(this.restUrl.concat("rpc/svc.php/dir/deleteAllFilesSystem/").concat(String.valueOf(systemVO.getId())));    	
    	restSvc.delete(restVO);
	    
	}
	
}
