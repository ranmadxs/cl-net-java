package cl.net.twonky;

import java.io.Serializable;

public class TwonkyFile implements Serializable{

	private static final long serialVersionUID = -2698388347697843198L;

	
	private String id;
	private String type;
	private String name;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
