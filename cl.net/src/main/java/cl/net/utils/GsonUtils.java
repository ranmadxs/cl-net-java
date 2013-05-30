package cl.net.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;

public class GsonUtils {

	private static String clean(String json){
		
		int inicio = json.indexOf("<!--");
		
		return json.substring(0,inicio);
	}
	
	public static Object json2obj(String json, Class clase){
		Object obj = null;
		Gson gson = new Gson();
		json = clean(json);
		obj = gson.fromJson(json, clase);
		return obj; 
    }
		
	public static String obj2json(Object obj){		
		Gson gson = new Gson();	 
		String json = gson.toJson(obj);
		return json;
		
	}
}
