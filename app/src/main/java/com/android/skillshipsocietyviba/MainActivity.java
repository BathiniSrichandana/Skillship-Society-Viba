package com.android.skillshipsocietyviba;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    EditText email, password;
    Button register;
    TextView Alreadyregistered;
    FirebaseAuth FirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Alreadyregistered = findViewById(R.id.AR);
        Alreadyregistered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MainActivity2.class));
            }
        });

        FirebaseAuth = FirebaseAuth.getInstance();

        email = findViewById(R.id.editemail);
        password = findViewById(R.id.editpassword);

        register = findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(email.getText().toString()) || TextUtils.isEmpty(password.getText().toString()))
                    Toast.makeText(MainActivity.this, "Enter all Details", Toast.LENGTH_SHORT).show();
                else if (password.getText().toString().length() < 6) {
                    Toast.makeText(MainActivity.this, "password is too weak,keep minimum 6 characters", Toast.LENGTH_SHORT).show();
                } else {
                    FirebaseAuth.createUserWithEmailAndPassword(email.getText().toString().trim(), password.getText().toString().trim())
                            .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        startActivity(new Intent(MainActivity.this, HomeActivity.class));
                                        finish();

                                    } else {

                                        Toast.makeText(MainActivity.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }
}
