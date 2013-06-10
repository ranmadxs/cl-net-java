package cl.net.vo;

public class ConnectionVO extends AbstractUtilDTO{

	private static final long serialVersionUID = -8561760591144733348L;

	private String username;
	private String password;
	private String ip;
	private Integer port;
	private Integer sys_id;
	private String sys_name;
	private String uri;
	private String sys_fecha;
	private Long driv_id;
	private String driv_name;
	private String tipo;
	private String dns;
	private String driv_fecha;
	private Integer driv_time_out;
	
	
	
	public Integer getSys_id() {
		return sys_id;
	}
	public void setSys_id(Integer sys_id) {
		this.sys_id = sys_id;
	}
	public String getSys_name() {
		return sys_name;
	}
	public void setSys_name(String sys_name) {
		this.sys_name = sys_name;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getSys_fecha() {
		return sys_fecha;
	}
	public void setSys_fecha(String sys_fecha) {
		this.sys_fecha = sys_fecha;
	}
	public Long getDriv_id() {
		return driv_id;
	}
	public void setDriv_id(Long driv_id) {
		this.driv_id = driv_id;
	}
	public String getDriv_name() {
		return driv_name;
	}
	public void setDriv_name(String driv_name) {
		this.driv_name = driv_name;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getDns() {
		return dns;
	}
	public void setDns(String dns) {
		this.dns = dns;
	}
	public String getDriv_fecha() {
		return driv_fecha;
	}
	public void setDriv_fecha(String driv_fecha) {
		this.driv_fecha = driv_fecha;
	}
	public Integer getDriv_time_out() {
		return driv_time_out;
	}
	public void setDriv_time_out(Integer driv_time_out) {
		this.driv_time_out = driv_time_out;
	}
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