package cl.net.vo;

import java.util.Date;

public class DirVO extends AbstractUtilDTO{

	private static final long serialVersionUID = 3417388374937495388L;
	
	private Integer id;
	private String name;
	private Integer FK_system;
	private String tipo;
	private String fecha;
	private String FK_dir;
	private String size;
	private String codigo;
	
	
			
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
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
	public Integer getFK_system() {
		return FK_system;
	}
	public void setFK_system(Integer fK_system) {
		FK_system = fK_system;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getFK_dir() {
		return FK_dir;
	}
	public void setFK_dir(String fK_dir) {
		FK_dir = fK_dir;
	}
	
	

}
