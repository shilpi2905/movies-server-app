package org.codejudge.sb.model.input;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TheatreInput {

	@JsonProperty("theatre_name")
	private String theatreName;

	@JsonProperty("theatre_location")
	private String theatreLocation;

	@JsonProperty("city")
	private String city;

	@JsonProperty("pincode")
	private Integer pincode;
	
	@NotNull(message = "theatre_name must not be null")
	public String getTheatreName() {
		return theatreName;
	}

	public void setTheatreName(String theatreName) {
		this.theatreName = theatreName;
	}

	@NotNull(message = "theatre_location must not be null")
	public String getTheatreLocation() {
		return theatreLocation;
	}

	public void setTheatreLocation(String theatreLocation) {
		this.theatreLocation = theatreLocation;
	}
	
	@NotNull(message = "city must not be null")
	@Pattern(message = "Invalid city name. Valid values are: Bengaluru/Mumbai/Delhi/Lucknow", regexp = "Bengaluru|Mumbai|Delhi|Lucknow")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@NotNull(message = "pincode must not be null")
	public Integer getPincode() {
		return pincode;
	}

	public void setPincode(Integer pincode) {
		this.pincode = pincode;
	}

	@Override
	public String toString() {
		return "TheatreInput [theatreName=" + theatreName + ", theatreLocation=" + theatreLocation + ", city=" + city
				+ ", pincode=" + pincode + "]";
	}

}
