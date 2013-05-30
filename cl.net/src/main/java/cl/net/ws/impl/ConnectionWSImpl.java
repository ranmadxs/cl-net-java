package cl.net.ws.impl;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

import cl.net.services.ConnectionSvc;
import cl.net.services.SystemSvc;
import cl.net.services.impl.FtpSvcImpl;
import cl.net.services.impl.SystemSvcImpl;
import cl.net.vo.ConnectionVO;
import cl.net.vo.SystemVO;
import cl.net.ws.ConnectionWS;

@WebService(endpointInterface = "cl.net.ws.impl.ConnectionWSImpl")
@SOAPBinding(style = Style.RPC, use=Use.LITERAL)
public class ConnectionWSImpl implements ConnectionWS{

    private ConnectionSvc connectionSvc;
    private SystemSvc systemSvc;        
    
    
	public ConnectionWSImpl() {
		super();
		this.systemSvc = new SystemSvcImpl();
	}

	
	@WebMethod
	public Boolean scann(Integer idSystem){
		Boolean res = Boolean.TRUE;
		
    	SystemVO systemVO = new SystemVO();
    	systemVO.setId(idSystem);

    	//TODO: Debo ir a buscar el DriverVO dado el SystemVO que tiene dentro el tipo de coneccion que debo hacer, por el momento dejo FTP en duro
    	ConnectionVO connectionVO = new ConnectionVO();
    	connectionVO.setPort(21);
    	connectionVO.setUsername("usuario1");
		connectionVO.setPassword("nueva123");
		connectionVO.setIp("ranmadxs.dyndns.org");
		
    	
    	try{
    		this.systemSvc.resetFileSystem(systemVO);
    		//TODO: if(tipo == FTP){
   			connectionSvc = new FtpSvcImpl(connectionVO);
   			connectionSvc.scann(systemVO, null);
    		
    	}catch (Exception e) {
    		e.printStackTrace();
    		res = Boolean.FALSE;
		}   
    	
		return res;
	}
	
	@WebMethod
    public Boolean resetFileSystem(Integer idSystem){
    	Boolean res = Boolean.TRUE;
    	
    	SystemVO systemVO = new SystemVO();
    	systemVO.setId(idSystem);
    	//TODO: Debo ir a buscar el DriverVO dado el SystemVO que tiene dentro el tipo de coneccion que debo hacer, por el momento dejo FTP en duro
    	ConnectionVO connectionVO = new ConnectionVO();
    	connectionVO.setPort(21);
    	connectionVO.setUsername("usuario1");
		connectionVO.setPassword("nueva123");
		connectionVO.setIp("ranmadxs.dyndns.org");
		    	
    	try{
    		this.systemSvc.resetFileSystem(systemVO);
    	}catch (Exception e) {
    		e.printStackTrace();
    		res = Boolean.FALSE;
		}    	
    	
    	return res;
    	
    }
	
}
