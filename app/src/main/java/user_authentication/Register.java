package user_authentication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.eldroid.pennywise.MainActivity;
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

        // Register button click
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });

        // Login link click
        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to login screen
                Intent intent = new Intent(Register.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void registerUser() {
        // Get input data
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String confirmPassword = confirmPasswordEditText.getText().toString().trim();

        // Validate inputs
        if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() < 6) {
            Toast.makeText(this, "Password must be at least 6 characters long", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.matches(".*\\d.*")) {
            Toast.makeText(this, "Password must contain at least one digit", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.matches(".*[A-Z].*")) {
            Toast.makeText(this, "Password must contain at least one uppercase letter", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a new User object to send to the backend (without full name)
        User newUser = new User(email, password);

        // Make the API call to register the user
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<UserResponse> call = apiService.registerUser(newUser);

        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(Register.this, "Registration successful!", Toast.LENGTH_SHORT).show();
                    // Navigate to the main screen (or login screen)
                    Intent intent = new Intent(Register.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(Register.this, "Registration failed: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(Register.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
