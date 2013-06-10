package cl.net.services.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

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

public class FileSystemSvcImpl implements ConnectionSvc{

	private static Logger log = Logger.getLogger(FileSystemSvcImpl.class);
	
	private ConnectionVO connectionVO;
	private static ResourceBundle rb;
	private String restUrl;
	private RestSvc restSvc;
	
	public FileSystemSvcImpl(ConnectionVO connectionVO) {
		this.connectionVO = connectionVO;
		rb = ResourceBundle.getBundle("constantes");
		this.restUrl = rb.getString("restUrl");
		this.restSvc = new RestSvcImpl();
	}

	public void scann(DirVO dirVO) throws Exception {
		
		FileVO fileVO;
		String breadcrumb;
		DirVO newDirVO, parentDirVO;
		RestVO restVO;
		Map<String, Object> params;
		Date date = new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String fecha = sdf.format(date);
		String json;

		String path = connectionVO.getDns().concat(connectionVO.getUri());
		
		if(dirVO != null && dirVO.getId() != null){
			path = path.concat(dirVO.getBreadcrumb()).concat("/").concat(dirVO.getName());
		}else{
			log.info(">> scann ".concat(path));
		}
		
		File folder = new File(path);
		
		if(folder != null){
			
			File[] listOfFiles = folder.listFiles(); 
			
			if(listOfFiles != null){						
			
				for (File file : listOfFiles) {			
					if(!file.getName().startsWith(".")){						   					   
					   params = new HashMap<String, Object>();
					   breadcrumb = "";
		               if(dirVO != null && dirVO.getId() != null){
		               	breadcrumb = breadcrumb.concat(dirVO.getBreadcrumb().concat("/").concat(dirVO.getName()));
		               }
		               
		               if(file.isDirectory()){
		            	   
			               	newDirVO = new DirVO();
			               	restVO = new RestVO();
			               	newDirVO.setFecha(fecha);
		                	if(dirVO != null && dirVO.getId() != null){
		                		newDirVO.setFK_dir(String.valueOf(dirVO.getId()));	
		                	}
		                	newDirVO.setFK_system(dirVO.getFK_system());
		                	newDirVO.setName(file.getName());
		                	newDirVO.setSize(String.valueOf(file.length()));
		                	newDirVO.setTipo("D");
		                	log.info("D :: "+file.getAbsolutePath());
		                	newDirVO.setBreadcrumb(breadcrumb);
		                	restVO.setUrl(this.restUrl.concat("rpc/svc.php/dir/create"));
		                	params = ObjectUtils.introspect(newDirVO);
		                	json = restSvc.post(restVO, params);
		                	parentDirVO = (DirVO) GsonUtils.json2obj(json, DirVO.class);
		                	this.scann(parentDirVO);
		                	
		               }else{
		            	    log.info("    F :: ".concat(file.getName()));
			               	restVO = new RestVO();
			               	restVO.setUrl(this.restUrl.concat("rpc/svc.php/file/create"));
			               	fileVO = new FileVO();
			               	//fileVO.setAttr(file.get);
			               	fileVO.setFecha(fecha);
			               	if(dirVO != null){
			               		fileVO.setFK_dir(dirVO.getId());
			               	}	                	
			               	fileVO.setName(file.getName());
			               	fileVO.setSize(String.valueOf(file.length()));
			               	fileVO.setTipo(StringUtils.getFileExtension(file.getName()));
			               	fileVO.setBreadcrumb(breadcrumb);
			               	params = ObjectUtils.introspect(fileVO);
			               	restSvc.post(restVO, params);
		               }
				   }else{
					   log.info("E :: ".concat(file.getName()));
				   }
				}
			}
		}
	}

	
	
}