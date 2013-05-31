package cl.net.vo;

public class FileVO extends AbstractUtilDTO{


	private static final long serialVersionUID = 1251628547582371509L;

	private Integer id;
	private String name;
	private String tipo;
	private Integer FK_dir;
	private String fecha;
	private String size;
	private String mime;
	private String attr;
	private String codigo;
	private String breadcrumb;		
	
	public String getBreadcrumb() {
		return breadcrumb;
	}
	public void setBreadcrumb(String breadcrumb) {
		this.breadcrumb = breadcrumb;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
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
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Integer getFK_dir() {
		return FK_dir;
	}
	public void setFK_dir(Integer fK_dir) {
		FK_dir = fK_dir;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getMime() {
		return mime;
	}
	public void setMime(String mime) {
		this.mime = mime;
	}
	public String getAttr() {
		return attr;
	}
	public void setAttr(String attr) {
		this.attr = attr;
	}
	
	
	
}
