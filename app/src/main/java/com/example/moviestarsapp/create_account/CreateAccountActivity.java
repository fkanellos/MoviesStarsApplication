package com.example.moviestarsapp.create_account;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.moviestarsapp.R;
import com.example.moviestarsapp.home.HomeActivity;
import com.example.moviestarsapp.register.RegisterActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class CreateAccountActivity<DEFAULT_SIGN_IN> extends AppCompatActivity implements View.OnClickListener {
    private GoogleSignInClient mGoogleSignInClient;
    private final static int RC_SIGN_IN = 123;
    Button verify;
    private FirebaseAuth mAuth;
    private TextView register;
    private Button btnLogin;
    private ProgressBar progressBar;
    private EditText editTextEmail;
    private EditText editTextPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        register = (TextView)findViewById(R.id.register);
        register.setOnClickListener(this);

        editTextEmail = (EditText)findViewById(R.id.username_edit);
        editTextPassword = (EditText)findViewById(R.id.password_edit);

        btnLogin =(Button)findViewById(R.id.sign_in);
        btnLogin.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        findViewById(R.id.btn_signIn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }



    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                            startActivity(intent);

                        } else {
                            Toast.makeText(CreateAccountActivity.this, "Sorry auth failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }


    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);


    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.register:
                startActivity(new Intent(this,RegisterActivity.class));
                break;
            case R.id.sign_in:
                userLogin();
                break;

        }

    }
    private void userLogin() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextEmail.getText().toString().trim();

        if(email.isEmpty()){
            editTextEmail.setError("Email is required!!");
            editTextEmail.requestFocus();
            return;
        }
        if(Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please enter a valid email!!");
            editTextEmail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            editTextPassword.setError("Password is required!!");
            editTextPassword.requestFocus();
            return;
        }
        if(password.length() < 6){
            editTextPassword.setError("Min password length is 6 characters!!");
            editTextPassword.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if(user.isEmailVerified()){
                        EditText userNameEdit = findViewById(R.id.username_edit);
                        EditText passwdEdit = findViewById(R.id.password_edit);
                        String userStr = userNameEdit.getText().toString();
                        String passwdStr = passwdEdit.getText().toString();
                        Intent intent = new Intent(CreateAccountActivity.this, HomeActivity.class);
                        startActivity(intent);
                    }else {
                        user.sendEmailVerification();
                        Toast.makeText(CreateAccountActivity.this, "Check your email to verify your account", Toast.LENGTH_LONG).show();
                    }
                    
                    


                }else{
                    Toast.makeText(CreateAccountActivity.this, "Failed to login! Please check your credentials!", Toast.LENGTH_LONG).show();

                }
            }
        });



    }


//        btnLogin.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            EditText userNameEdit = findViewById(R.id.username_edit);
//            EditText passwdEdit = findViewById(R.id.password_edit);
//            String userStr = userNameEdit.getText().toString();
//            String passwdStr = passwdEdit.getText().toString();
//
//            if (userStr.matches("") || passwdStr.matches("")) {
//                Toast.makeText(CreateAccountActivity.this, "username or password empty", Toast.LENGTH_SHORT).show();
//            } else {
//                Intent intent = new Intent(CreateAccountActivity.this, HomeActivity.class);
//                startActivity(intent);
//            }
//        }
//    });


}