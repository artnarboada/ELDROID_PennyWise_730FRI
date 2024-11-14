package com.eldroid.pennywise;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import user_authentication.Forgot_Password;
import user_authentication.Register;
import user_interface.Dashboard;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //login button
        Button loginButton = findViewById(R.id.login_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Dashboard.class);
                startActivity(intent);


            }
        });

        //Forgot Password

        TextView forgotPass = findViewById(R.id.forgot_password);

        forgotPass.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Forgot_Password.class);
                startActivity(intent);
            }
        });

        //Sign up
        TextView signup = findViewById(R.id.signup_link);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, Register.class);
                startActivity(intent);

            }
        });



    }
}