package cl.net.services.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.ws.rs.core.MediaType;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPFileFilters;
import org.apache.log4j.Logger;

import cl.net.services.ConnectionSvc;
import cl.net.services.RestSvc;
import cl.net.utils.GsonUtils;
import cl.net.utils.ObjectUtils;
import cl.net.utils.StringUtils;
import cl.net.vo.ConnectionVO;
import cl.net.vo.DirVO;
import cl.net.vo.FileVO;
import cl.net.vo.RestVO;


public class FtpSvcImpl implements ConnectionSvc {

	private static Logger log = Logger.getLogger(FtpSvcImpl.class);
	
	private FTPClient ftp;
	private RestSvc restSvc;
	private ConnectionVO connectionVO;
	private static ResourceBundle rb;
	private String restUrl;
	public FtpSvcImpl(ConnectionVO connectionVO) {
		super();
		this.connectionVO = connectionVO;
		
		this.ftp = new FTPClient();
		try {
			this.ftp.connect(this.connectionVO.getIp(), this.connectionVO.getPort());
			this.ftp.login(this.connectionVO.getUsername(), this.connectionVO.getPassword());
			this.ftp.setFileType(FTP.BINARY_FILE_TYPE);
			this.ftp.enterLocalPassiveMode();
			this.ftp.setUseEPSVwithIPv4(true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		rb = ResourceBundle.getBundle("constantes");
		this.restUrl = rb.getString("restUrl");
		this.restSvc = new RestSvcImpl();
	}

	public void scann(DirVO dirVO) throws Exception {
		FTPFile[] files;
		String breadcrumb;
		FileVO fileVO;
		DirVO newDirVO, parentDirVO;
		RestVO restVO;
		Map<String, Object> params;
		Date date = new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String fecha = sdf.format(date);
		String json;
		try{
			if(this.ftp.isConnected()){
				if(dirVO != null && dirVO.getId() != null){
					ftp.cwd(dirVO.getName());
				}
				
				files = ftp.listFiles(".", FTPFileFilters.ALL);
				
				for (FTPFile ftpFile : files) {					
	                log.info(ftpFile.toFormattedString());
	                params = new HashMap<String, Object>();
	                breadcrumb = "";
	                if(dirVO != null && dirVO.getId() != null){
	                	breadcrumb = breadcrumb.concat(dirVO.getBreadcrumb().concat("/").concat(dirVO.getName()));
	                }
	                if(ftpFile.isDirectory()){	                	
	                	newDirVO = new DirVO();
	                	restVO = new RestVO();
	                	newDirVO.setFecha(fecha);
	                	newDirVO.setSize(String.valueOf(ftpFile.getSize()));
	                	if(dirVO != null){
	                		newDirVO.setFK_dir(String.valueOf(dirVO.getId()));
	                		
	                	}
	                	
	                	
	                	newDirVO.setFK_system(dirVO.getFK_system());
	                	newDirVO.setName(ftpFile.getName());
	                	newDirVO.setTipo(String.valueOf(ftpFile.getType()));
	                	newDirVO.setBreadcrumb(breadcrumb);
	                	restVO.setUrl(this.restUrl.concat("rpc/svc.php/dir/create"));
	                	
	                	params = ObjectUtils.introspect(newDirVO);
	                	json = restSvc.post(restVO, params);
	                	parentDirVO = (DirVO) GsonUtils.json2obj(json, DirVO.class);
	                	this.scann(parentDirVO);
	                }else{
	                	restVO = new RestVO();
	                	restVO.setUrl(this.restUrl.concat("rpc/svc.php/file/create"));
	                	fileVO = new FileVO();
	                	fileVO.setAttr(ftpFile.getRawListing());
	                	fileVO.setFecha(fecha);
	                	if(dirVO != null){
	                		fileVO.setFK_dir(dirVO.getId());
	                	}	                	
	                	fileVO.setName(ftpFile.getName());
	                	fileVO.setSize(String.valueOf(ftpFile.getSize()));
	                	fileVO.setTipo(StringUtils.getFileExtension(ftpFile.getName()));
	                	fileVO.setBreadcrumb(breadcrumb);
	                	params = ObjectUtils.introspect(fileVO);
	                	json = restSvc.post(restVO, params);
	                	
	                }
	                
				}
				
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}