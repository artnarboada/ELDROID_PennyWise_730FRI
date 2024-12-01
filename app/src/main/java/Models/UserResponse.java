//package Models;
//
//public class UserResponse {
//    private String message;
//    private User user;
//
//    // Getters and Setters
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//}
//
package Models;

public class UserResponse {
    private String message;
    private String token;  // Assuming the token is returned
    private User user;     // Assuming you also get user info in the response

    // Getters and Setters

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // You can add more fields here based on the response structure
}
