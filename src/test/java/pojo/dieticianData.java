package pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class dieticianData {

	@JsonProperty("firstName")
	private String firstname;

	@JsonProperty("lastName")
	private String lastname;

	@JsonProperty("contact")
	private String contactNumber;

	private String dateOfBirth;

	@JsonProperty("userLoginEmail")
	private String email;

	@JsonProperty("dHospitalName")
	private String hospitalName;

	@JsonProperty("dHospitalStreetName")
	private String hospitalStreet;

	@JsonProperty("dHospitalCityName")
	private String hospitalCity;

	@JsonProperty("dHospitalPinCode")
	private String hospitalPincode;

	@JsonProperty("dEducation")
	private String education;

	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	public String getHospitalStreet() {
		return hospitalStreet;
	}
	public void setHospitalStreet(String hospitalStreet) {
		this.hospitalStreet = hospitalStreet;
	}
	public String getHospitalCity() {
		return hospitalCity;
	}
	public void setHospitalCity(String hospitalCity) {
		this.hospitalCity = hospitalCity;
	}
	public String getHospitalPincode() {
		return hospitalPincode;
	}
	public void setHospitalPincode(String hospitalPincode) {
		this.hospitalPincode = hospitalPincode;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
}