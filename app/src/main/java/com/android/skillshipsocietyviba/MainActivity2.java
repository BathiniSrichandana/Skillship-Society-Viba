package com.android.skillshipsocietyviba;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity2 extends AppCompatActivity {

    EditText email, password;
    Button login;
    com.google.firebase.auth.FirebaseAuth FirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        FirebaseAuth = FirebaseAuth.getInstance();

        email = findViewById(R.id.editemaillogin);
        password = findViewById(R.id.editpasswordlogin);

        login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(email.getText().toString()) || TextUtils.isEmpty(password.getText().toString()))
                    Toast.makeText(MainActivity2.this, "Enter all Details", Toast.LENGTH_SHORT).show();
                else if (password.getText().toString().length() < 6) {
                    Toast.makeText(MainActivity2.this, "password is too weak,keep minimum 6 characters", Toast.LENGTH_SHORT).show();
                } else {
                    FirebaseAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                            .addOnCompleteListener(MainActivity2.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information

                                        startActivity(new Intent(MainActivity2.this,HomeActivity.class));
                                        finish();


                                    } else {

                                        Toast.makeText(MainActivity2.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }

            };

        });
    }
}