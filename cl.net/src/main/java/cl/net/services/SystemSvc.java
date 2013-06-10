package cl.net.services;

import cl.net.vo.ConnectionVO;
import cl.net.vo.SystemVO;

public interface SystemSvc {
	
	ConnectionVO getConnection(SystemVO systemVO)throws Exception;
	void resetFileSystem(SystemVO systemVO);

}
