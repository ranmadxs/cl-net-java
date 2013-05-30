package cl.net.vo;

public class ConnectionVO extends AbstractUtilDTO{

	private static final long serialVersionUID = -8561760591144733348L;

	private String username;
	private String password;
	private String ip;
	private Integer port;
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	
	
	
	
}