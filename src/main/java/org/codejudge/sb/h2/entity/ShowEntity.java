package org.codejudge.sb.h2.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class ShowEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private EmbeddedShowId id;
	private String date;
	public ShowEntity() {
		
	}
	public ShowEntity(EmbeddedShowId id, String date) {
		super();
		this.id = id;
		this.date = date;
	}

	public EmbeddedShowId getId() {
		return id;
	}
	public void setId(EmbeddedShowId id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "ShowEntity [id=" + id + ", date=" + date + "]";
	}
	
}
