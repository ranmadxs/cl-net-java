package cl.net.services;

import java.io.IOException;

import org.apache.commons.net.ftp.FTPFile;

import cl.net.vo.DirVO;
import cl.net.vo.SystemVO;

public interface ConnectionSvc {

	void scann(SystemVO systemVO,DirVO dirVO)throws IOException;
	
}
