package cl.net.services;

import java.util.HashMap;
import java.util.Map;

import cl.net.vo.RestVO;

public interface RestSvc {

	public String get(RestVO restVO, Map<String, Object> params);
	
	public String post(RestVO restVO, Map<String, Object> params);
	
	public String delete(RestVO restVO);
	
}
