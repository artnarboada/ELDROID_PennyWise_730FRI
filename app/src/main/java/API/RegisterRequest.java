package API;

// RegisterRequest.java
public class RegisterRequest {
    private String userEmail;
    private String Password;
    private String confirmPassword;

    // Constructor
    public RegisterRequest(String userEmail, String userPassword, String confirmPassword) {
        this.userEmail = userEmail;
        this.Password = userPassword;
        this.confirmPassword = confirmPassword;
    }

    // Getters and Setters
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return Password;
    }

    public void setUserPassword(String userPassword) {
        this.Password = userPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
