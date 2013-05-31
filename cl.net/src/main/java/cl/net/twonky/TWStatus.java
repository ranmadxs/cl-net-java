package cl.net.twonky;

import java.io.Serializable;

public class TWStatus implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8013633733191312806L;
	private String serverkind;
	private String serverplatform;
	private String dbupdate;
	private Integer videos;
	private Long pictures;
	
	public String getServerkind() {
		return serverkind;
	}
	public void setServerkind(String serverkind) {
		this.serverkind = serverkind;
	}
	public String getServerplatform() {
		return serverplatform;
	}
	public void setServerplatform(String serverplatform) {
		this.serverplatform = serverplatform;
	}
	public String getDbupdate() {
		return dbupdate;
	}
	public void setDbupdate(String dbupdate) {
		this.dbupdate = dbupdate;
	}
	public Integer getVideos() {
		return videos;
	}
	public void setVideos(Integer videos) {
		this.videos = videos;
	}
	public Long getPictures() {
		return pictures;
	}
	public void setPictures(Long pictures) {
		this.pictures = pictures;
	}
	
	
	
}
