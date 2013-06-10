package cl.net.ws.impl;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

import org.apache.log4j.Logger;

import cl.net.services.ConnectionSvc;
import cl.net.services.SystemSvc;
import cl.net.services.impl.FileSystemSvcImpl;
import cl.net.services.impl.FtpSvcImpl;
import cl.net.services.impl.SystemSvcImpl;
import cl.net.vo.ConnectionVO;
import cl.net.vo.DirVO;
import cl.net.vo.SystemVO;
import cl.net.ws.ConnectionWS;

@WebService(endpointInterface = "cl.net.ws.impl.ConnectionWSImpl")
@SOAPBinding(style = Style.RPC, use=Use.LITERAL)
public class ConnectionWSImpl implements ConnectionWS{

    private ConnectionSvc connectionSvc;
    private SystemSvc systemSvc;        
    private static Logger log = Logger.getLogger(ConnectionWSImpl.class);
    
	public ConnectionWSImpl() {
		super();
		this.systemSvc = new SystemSvcImpl();
	}

	
	@WebMethod
	public Boolean scann(Integer idSystem){
		Boolean res = Boolean.TRUE;
		ConnectionVO connectionVO;
    	SystemVO systemVO = new SystemVO();
    	systemVO.setId(idSystem);

    	DirVO dirVO = new DirVO();
		dirVO.setFK_system(systemVO.getId());
    	log.info("@WebMethod init [scann]");
    	try{
    		connectionVO = this.systemSvc.getConnection(systemVO);      		
    		log.info(connectionVO);
    		this.systemSvc.resetFileSystem(systemVO);
    		//TODO: if(tipo == FILE_SYSTEM){
   			connectionSvc = new FileSystemSvcImpl(connectionVO);
   			connectionSvc.scann(dirVO);
    		
    	}catch (Exception e) {
    		e.printStackTrace();
    		res = Boolean.FALSE;
		}   
    	log.info("@WebMethod end [scann]");
		return res;
	}
	
	@WebMethod
    public Boolean resetFileSystem(Integer idSystem){
    	Boolean res = Boolean.TRUE;
    	log.info("@WebMethod init [resetFileSystem]");
    	SystemVO systemVO = new SystemVO();
    	systemVO.setId(idSystem);
    	try{
    		this.systemSvc.resetFileSystem(systemVO);
    	}catch (Exception e) {
    		e.printStackTrace();
    		res = Boolean.FALSE;
		}    	
    	log.info("@WebMethod end [resetFileSystem]");
    	return res;
    	
    }
	
}
