package org.codejudge.sb.model.input;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ShowsInput {

	@JsonProperty("movie_id")
	private int movieId;
	@JsonProperty("theatre_id")
	private int theatreId;
	@JsonProperty("date")
	private String date;
	@JsonProperty("time")
	private String time;
	
	@NotNull(message = "movie_id must not be null")
	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	
	@NotNull(message = "theatre_id must not be null")
	public int getTheatreId() {
		return theatreId;
	}

	public void setTheatreId(int theatreId) {
		this.theatreId = theatreId;
	}

	@NotNull(message = "date must not be null")
	@Pattern(regexp="^\\d{4}-\\d{2}-\\d{2}$", message = "Invalid date, valid date format is: yyyy-mm-dd")
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@NotNull(message = "time must not be null")
	@Pattern(regexp="(20|21|22|23|[0-1]?\\d):[0-5]?\\d:[0-5]?\\d$", message = "Invalid time, valid format is: hh24:mm:ss")
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "ShowsInput [movieId=" + movieId + ", theatreId=" + theatreId + ", date=" + date + ", time=" + time
				+ "]";
	}
}
