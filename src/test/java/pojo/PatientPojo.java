
package pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PatientPojo {



    @JsonProperty("FirstName")
    private String firstname;
    @JsonProperty("LastName")
    private String lastname;
    @JsonProperty("ContactNumber")
    private String contactNumber;
    @JsonProperty("Email")
    private String email;
    @JsonProperty("Allergy")
    private String allergy;
    @JsonProperty("FoodPreference")
    private String foodPreference;
    @JsonProperty("CuisineCategory")
    private String cuisineCategory;
    @JsonProperty("DateOfBirth")
    private String dateOfBirth;

    //private VitalsPojo vitals;

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
    public void setContactNumber(String contactNO) {
        this.contactNumber = contactNO;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getAllergy() {
        return allergy;
    }
    public void setAllergy(String allergy) {
        this.allergy = allergy;
    }
    public String getFoodPreference() {
        return foodPreference;
    }
    public void setFoodPreference(String foodPreference) {
        this.foodPreference = foodPreference;
    }
    public String getCuisineCategory() {
        return cuisineCategory;
    }
    public void setCuisineCategory(String cuisineCategory) {
        this.cuisineCategory = cuisineCategory;
    }
    public String getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
//	public VitalsPojo getVitals() { 
//		return vitals; 
//	}
//    public void setVitals(VitalsPojo vitals) { 
//    	this.vitals = vitals; 
//    }
}