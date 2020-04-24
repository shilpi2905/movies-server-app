package org.codejudge.sb.h2.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Embeddable
public class EmbeddedShowId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int movieId;
	private int theatreId;
	private String time;
	
	public EmbeddedShowId() {
		
	}

	public EmbeddedShowId(int movieId, int theatreId, String time) {
		super();
		this.movieId = movieId;
		this.theatreId = theatreId;
		this.time = time;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public int getTheatreId() {
		return theatreId;
	}

	public void setTheatreId(int theatreId) {
		this.theatreId = theatreId;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + movieId;
		result = prime * result + theatreId;
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmbeddedShowId other = (EmbeddedShowId) obj;
		if (movieId != other.movieId)
			return false;
		if (theatreId != other.theatreId)
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EmbeddedShowId [movieId=" + movieId + ", theatreId=" + theatreId + ", time=" + time + "]";
	}
	
}
