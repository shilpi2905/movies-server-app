package org.codejudge.sb.model.output;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TheatreOutput {

	@JsonProperty("theatre_id")
	private int theatreId;

	@JsonProperty("theatre_name")
	private String theatreName;

	@JsonProperty("theatre_location")
	private String theatreLocation;

	@JsonProperty("city")
	private String city;

	@JsonProperty("pincode")
	private Integer pincode;

	public int getTheatreId() {
		return theatreId;
	}

	public void setTheatreId(int theatreId) {
		this.theatreId = theatreId;
	}

	public String getTheatreName() {
		return theatreName;
	}

	public void setTheatreName(String theatreName) {
		this.theatreName = theatreName;
	}

	public String getTheatreLocation() {
		return theatreLocation;
	}

	public void setTheatreLocation(String theatreLocation) {
		this.theatreLocation = theatreLocation;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getPincode() {
		return pincode;
	}

	public void setPincode(Integer pincode) {
		this.pincode = pincode;
	}

	@Override
	public String toString() {
		return "TheatreOutput [theatreId=" + theatreId + ", theatreName=" + theatreName + ", theatreLocation="
				+ theatreLocation + ", city=" + city + ", pincode=" + pincode + "]";
	}
}
