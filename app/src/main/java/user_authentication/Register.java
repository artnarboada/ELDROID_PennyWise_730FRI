//package user_authentication;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.eldroid.pennywise.MainActivity;
//import com.eldroid.pennywise.R;
//
//import API.ApiService;
//import API.RetrofitClient;
//import Models.User;
//import Models.UserResponse;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class Register extends AppCompatActivity {
//    private EditText emailEditText, passwordEditText, confirmPasswordEditText;
//    private Button regButton;
//    private TextView loginLink;
//
//    private static final String TAG = "Register";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_register);
//
//        // Initialize views
//        emailEditText = findViewById(R.id.email);
//        passwordEditText = findViewById(R.id.password);
//        confirmPasswordEditText = findViewById(R.id.confirm_password);
//        regButton = findViewById(R.id.create_button);
//        loginLink = findViewById(R.id.login_link);
//
//        // Set listeners
//        regButton.setOnClickListener(view -> registerUser());
//        loginLink.setOnClickListener(view -> {
//            Intent intent = new Intent(Register.this, LoginActivity.class);
//            startActivity(intent);
//        });
//    }
//
//    private void registerUser() {
//        String email = emailEditText.getText().toString().trim();
//        String password = passwordEditText.getText().toString().trim();
//        String confirmPassword = confirmPasswordEditText.getText().toString().trim();
//
//        // Validate inputs
//        if (!validateInputs(email, password, confirmPassword)) return;
//
//        // Create User object
//        User newUser = new User(email, password, confirmPassword);
//
//        // API call
//        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
//        Call<UserResponse> call = apiService.registerUser(newUser);
//
//        call.enqueue(new Callback<UserResponse>() {
//            @Override
//            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
//                if (response.isSuccessful() && response.body() != null) {
//                    // Handle successful registration
//                    Toast.makeText(Register.this, "Registration successful! Please verify your email.", Toast.LENGTH_LONG).show();
//                    Log.d(TAG, "Success: " + response.body().getMessage());
//                    navigateToMainScreen();
//                } else {
//                    // Handle registration error
//                    try {
//                        if (response.errorBody() != null) {
//                            String errorResponse = response.errorBody().string();
//                            Log.e(TAG, "Error Response: " + errorResponse);
//
//                            // Display user-friendly error
//                            Toast.makeText(Register.this, "Registration failed: " + errorResponse, Toast.LENGTH_LONG).show();
//                        } else {
//                            Toast.makeText(Register.this, "Registration failed. Please try again.", Toast.LENGTH_SHORT).show();
//                        }
//                    } catch (Exception e) {
//                        Log.e(TAG, "Error parsing errorBody: ", e);
//                        Toast.makeText(Register.this, "An unexpected error occurred.", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<UserResponse> call, Throwable t) {
//                Toast.makeText(Register.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
//                Log.e(TAG, "API Error: ", t);
//            }
//        });
//    }
//
//    private boolean validateInputs(String email, String password, String confirmPassword) {
//        // Validate email
//        if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
//            Toast.makeText(this, "All fields are required.", Toast.LENGTH_SHORT).show();
//            return false;
//        }
//
//        // Validate email format
//        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//            Toast.makeText(this, "Invalid email format.", Toast.LENGTH_SHORT).show();
//            return false;
//        }
//
//        // Validate password length
//        if (password.length() < 6) {
//            Toast.makeText(this, "Password must be at least 6 characters.", Toast.LENGTH_SHORT).show();
//            return false;
//        }
//
//        // Validate if passwords match
//        if (!password.equals(confirmPassword)) {
//            Toast.makeText(this, "Passwords do not match.", Toast.LENGTH_SHORT).show();
//            return false;
//        }
//
//        return true;
//    }
//
//    private void navigateToMainScreen() {
//        // After successful registration, navigate to the main screen
//        Intent intent = new Intent(Register.this, LoginActivity.class);
//        startActivity(intent);
//        finish();
//    }
//}
package user_authentication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.eldroid.pennywise.R;

import API.ApiService;
import API.RetrofitClient;
import Models.User;
import Models.UserResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {
    private EditText emailEditText, passwordEditText, confirmPasswordEditText;
    private Button regButton;
    private TextView loginLink;

    private static final String TAG = "Register";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialize views
        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        confirmPasswordEditText = findViewById(R.id.confirm_password);
        regButton = findViewById(R.id.create_button);
        loginLink = findViewById(R.id.login_link);

        // Set listeners
        regButton.setOnClickListener(view -> registerUser());
        loginLink.setOnClickListener(view -> {
            Intent intent = new Intent(Register.this, LoginActivity.class);
            startActivity(intent);
        });
    }

    private void registerUser() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String confirmPassword = confirmPasswordEditText.getText().toString().trim();

        // Validate inputs
        if (!validateInputs(email, password, confirmPassword)) return;

        // Create User object
        User newUser = new User(email, password, confirmPassword);

        // API call
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<UserResponse> call = apiService.registerUser(newUser);

        regButton.setEnabled(false); // Disable button to prevent multiple submissions
        regButton.setText("Registering...");

        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                regButton.setEnabled(true); // Re-enable button
                regButton.setText("Register");

                if (response.isSuccessful() && response.body() != null) {
                    // Handle successful registration
                    Toast.makeText(Register.this, "Registration successful! Please verify your email.", Toast.LENGTH_LONG).show();
                    Log.d(TAG, "Success: " + response.body().getMessage());
                    navigateToMainScreen();
                } else {
                    // Handle registration error
                    try {
                        if (response.errorBody() != null) {
                            String errorResponse = response.errorBody().string();
                            Log.e(TAG, "Error Response: " + errorResponse);

                            // Display user-friendly error
                            Toast.makeText(Register.this, "Registration failed: " + errorResponse, Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(Register.this, "Registration failed. Please try again.", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Log.e(TAG, "Error parsing errorBody: ", e);
                        Toast.makeText(Register.this, "An unexpected error occurred.", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                regButton.setEnabled(true); // Re-enable button on failure
                regButton.setText("Register");

                Toast.makeText(Register.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean validateInputs(String email, String password, String confirmPassword) {
        // Validate email
        if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "All fields are required.", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Validate email format
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Invalid email format.", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Validate password length
        if (password.length() < 6) {
            Toast.makeText(this, "Password must be at least 6 characters.", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Validate if passwords match
        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords do not match.", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void navigateToMainScreen() {
        // After successful registration, navigate to the main screen (Login)
        Intent intent = new Intent(Register.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
