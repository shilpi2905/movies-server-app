package org.codejudge.sb.model.output;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Shows {

	@JsonProperty("date")
	private String date;
	@JsonProperty("time")
	private String time;

	public Shows(String date, String time) {
		super();
		this.date = date;
		this.time = time;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "Shows [date=" + date + ", time=" + time + "]";
	}

}
