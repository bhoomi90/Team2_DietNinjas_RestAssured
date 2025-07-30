package pojo;

public class userLoginPojo {

    private String userLoginEmail; // match JSON key
    private String password;       // lowercase to match JSON key
    private String adminToken;
    
       private String endpoint;

    public userLoginPojo() {}

    public userLoginPojo(String userLoginEmail, String password) {
        this.userLoginEmail = userLoginEmail;
        this.password = password;
    }

    public String getUserLoginEmail() {
        return userLoginEmail;
    }

    public void setUserLoginEmail(String userLoginEmail) {
        this.userLoginEmail = userLoginEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAdminToken() {
        return adminToken;
    }

    public void setAdminToken(String adminToken) {
        this.adminToken = adminToken;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }
}
