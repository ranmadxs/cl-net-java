package cl.net.vo;

public class DriverVO extends AbstractUtilDTO{

	private static final long serialVersionUID = 4701820862178260344L;

	private Integer id;
	private String name;
	private String ip;
	private Integer port;
	private String username;
	private String password;
	private String tipo;
	private String dns;
	private String fecha;
	private Integer time_out;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public Integer getTime_out() {
		return time_out;
	}
	public void setTime_out(Integer time_out) {
		this.time_out = time_out;
	}
	
	
	
}
