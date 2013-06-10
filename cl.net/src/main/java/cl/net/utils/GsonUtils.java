package cl.net.utils;

import org.apache.log4j.Logger;
import com.google.gson.Gson;

public class GsonUtils {
	private static Logger log = Logger.getLogger(GsonUtils.class);
	
	public static Object json2obj(String json, Class clase)throws Exception{
		Object obj = null;
		Gson gson = new Gson();
		//json = clean(json);
		try{
			obj = gson.fromJson(json, clase);	
		}catch(Exception e){
			log.error(json);
			throw new Exception(e);
		}
		
		return obj; 
    }
		
	public static String obj2json(Object obj){		
		Gson gson = new Gson();	 
		String json = gson.toJson(obj);
		return json;
		
	}
}
