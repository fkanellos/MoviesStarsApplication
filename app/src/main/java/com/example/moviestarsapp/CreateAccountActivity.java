package com.example.moviestarsapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;


public class CreateAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        Button btnLogin = findViewById(R.id.btn_crt_account);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText userNameEdit = findViewById(R.id.txt_editUser);
                EditText passwdEdit = findViewById(R.id.txt_editPass);
                String userStr = userNameEdit.getText().toString();
                String passwdStr = passwdEdit.getText().toString();

                if(userStr.matches("") || passwdStr.matches("")){
                    Toast.makeText(CreateAccountActivity.this, "username or password empty", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(CreateAccountActivity.this, User_Profile_Activity.class);
                    startActivity(intent);
                }
            }
        });
    }
}