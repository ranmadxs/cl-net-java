package cl.net.utils;

public enum NetTipoEnum {

	FTP ("FTP"),
	SMB ("SMB"),
	HTTP("HTTP"),
	FILE_SYSTEM("FILE_SYSTEM"),
	;
	public String codigo;

	private NetTipoEnum(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}
}
