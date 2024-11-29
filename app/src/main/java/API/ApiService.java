package API;

import Models.User;
import Models.UserResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    @POST("register")
    Call<UserResponse> registerUser(@Body User user);

//    @POST("login")
//    Call<LoginResponse> login(@Body LoginRequest loginRequest);
//
//    @GET("verify-email")
//    Call<GenericResponse> verifyEmail(@Body VerifyEmailRequest verifyEmailRequest);
//
//    @GET("users/{id}")
//    Call<UserResponse> getUser(@Path("id") int id);
}
