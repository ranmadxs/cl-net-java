package cl.net.vo;

public class SystemVO extends AbstractUtilDTO{

	private static final long serialVersionUID = 4800937484646997568L;


	private Integer id;
	private String name;
	private String FK_driver;
	private String fecha;
	private String uri;
		
	
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
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
	public String getFK_driver() {
		return FK_driver;
	}
	public void setFK_driver(String fK_driver) {
		FK_driver = fK_driver;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	
	
}
