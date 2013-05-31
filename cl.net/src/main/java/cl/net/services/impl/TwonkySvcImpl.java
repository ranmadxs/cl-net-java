package cl.net.services.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import cl.net.services.ConnectionSvc;
import cl.net.services.RestSvc;
import cl.net.twonky.TwonkyClient;
import cl.net.twonky.TwonkyFile;
import cl.net.twonky.TwonkyType;
import cl.net.utils.GsonUtils;
import cl.net.utils.ObjectUtils;
import cl.net.vo.ConnectionVO;
import cl.net.vo.DirVO;
import cl.net.vo.FileVO;
import cl.net.vo.RestVO;
import cl.net.vo.SystemVO;

public class TwonkySvcImpl implements ConnectionSvc{

	private ConnectionVO connectionVO;
	private static ResourceBundle rb;
	private TwonkyClient twonyClient;
	private RestSvc restSvc;
	private String restUrl;
	
	public TwonkySvcImpl(ConnectionVO connectionVO) {
		super();
		this.connectionVO = connectionVO;
		
		this.twonyClient = new TwonkyClient();
		this.twonyClient.connect(this.connectionVO.getIp(), this.connectionVO.getPort());
		
		rb = ResourceBundle.getBundle("constantes");
		this.restUrl = rb.getString("restUrl");
		this.restSvc = new RestSvcImpl();
	}



	public void scann(SystemVO systemVO, DirVO dirVO) throws Exception {
		String queryParam = "001";
		List<TwonkyFile> list;
		FileVO fileVO;
		DirVO newDirVO, parentDirVO;
		RestVO restVO;
		Map<String, Object> params;
		Date date = new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String fecha = sdf.format(date);
		String json;
		if(dirVO != null){
			queryParam = queryParam.concat("/").concat(dirVO.getCodigo());
		}
		if(twonyClient.isConnected()){
			
			list = twonyClient.listFiles(queryParam, TwonkyType.FILTER_ALL);
			
			for (TwonkyFile twonkyFile : list) {
				params = new HashMap<String, Object>();
				if(twonkyFile.getType().equals(TwonkyType.FILTER_DIR)){
                	newDirVO = new DirVO();
                	restVO = new RestVO();
                	newDirVO.setFecha(fecha);
                	if(dirVO != null){
                		newDirVO.setFK_dir(String.valueOf(dirVO.getId()));	
                	}
                	newDirVO.setFK_system(systemVO.getId());
                	newDirVO.setName(twonkyFile.getName());
                	newDirVO.setTipo(String.valueOf(twonkyFile.getType()));
                	restVO.setUrl(this.restUrl.concat("rs/svc.php/dir/create"));
                	
                	params = ObjectUtils.introspect(newDirVO);
                	json = restSvc.post(restVO, params);
                	parentDirVO = (DirVO) GsonUtils.json2obj(json, DirVO.class);
                	parentDirVO.setCodigo(twonkyFile.getId());
                	this.scann(systemVO, parentDirVO);
				}else{
                	restVO = new RestVO();
                	restVO.setUrl(this.restUrl.concat("rs/svc.php/file/create"));
                	fileVO = new FileVO();
                	fileVO.setCodigo(twonkyFile.getId());
                	fileVO.setFecha(fecha);
                	if(dirVO != null){
                		fileVO.setFK_dir(dirVO.getId());
                	}	                	
                	fileVO.setName(twonkyFile.getName());
                	//fileVO.setSize(String.valueOf(ftpFile.getSize()));
                	fileVO.setTipo(String.valueOf(twonkyFile.getType()));
                	params = ObjectUtils.introspect(fileVO);
                	json = restSvc.post(restVO, params);					
				}
			}
			
		}
		
		
	}

}
