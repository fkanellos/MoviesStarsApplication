package com.example.moviestarsapp.register;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.moviestarsapp.R;
import com.example.moviestarsapp.home.HomeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    Button verify;
    private EditText editTextFullName, editTextAge, editTextPassword, editTextEmail;
    private ProgressBar progressbar;

    private FirebaseAuth mAuth;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();

        Button button = findViewById(R.id.ok);
        button.setOnClickListener(RegisterActivity.this);

        editTextEmail = (EditText)findViewById(R.id.email_edit);
        editTextFullName = (EditText)findViewById(R.id.username_edit);
        editTextPassword = (EditText)findViewById(R.id.password_edit);
        editTextAge = (EditText)findViewById(R.id.putAge);

        progressbar = (ProgressBar)findViewById(R.id.progressBar);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ok:
            registerUser();
            break;
        }

    }

    private void registerUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String fullname = editTextFullName.getText().toString().trim();
        String age = editTextAge.getText().toString().trim();

        if(fullname.isEmpty()){
            editTextFullName.setError("Full name is required");
            editTextFullName.requestFocus();
            return;
        }
        if(email.isEmpty()) {
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }
        if(age.isEmpty()){
            editTextAge.setError("Age is required");
            editTextAge.requestFocus();
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Please provide valid email!");
            editTextEmail.requestFocus();
            return;
        }

        if(password.isEmpty()){
           editTextPassword.setError("Password is required!");
           editTextPassword.requestFocus();
           return;
        }

        if(password.length() < 6){
           editTextPassword.setError("Min password length should be 6 characters!");
           editTextPassword.requestFocus();
           return;
        }

        progressbar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                       if(task.isSuccessful()){
                           User user = new User(fullname, email, age);

                           FirebaseDatabase.getInstance().getReference("Users")
                                   .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                   .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                               @Override
                               public void onComplete(@NonNull Task<Void> task) {

                                   if(task.isSuccessful()){
                                       Toast.makeText(RegisterActivity.this, "User has been registered successfully!", Toast.LENGTH_LONG).show();
                                       progressbar.setVisibility(View.VISIBLE);
                                       Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                       startActivity(intent);

                                   }else{
                                       Toast.makeText(RegisterActivity.this, "Failed to register! Try again", Toast.LENGTH_LONG).show();
                                       progressbar.setVisibility(View.GONE);
                                   }
                               }
                           });

                       }else{
                           Toast.makeText(RegisterActivity.this, "Failed to register! This user is already registered! Try again", Toast.LENGTH_LONG).show();
                           progressbar.setVisibility(View.GONE);

                    }
                };

    });

}}
