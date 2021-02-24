package com.example.moviestarsapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.moviestarsapp.R;

public class MainKaterinaActivity extends AppCompatActivity {
    @Override
    int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    void setupValuesToUI() {
        Button btn=findViewById(R.id.btnmain);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonfunction();
            }
        });
    }

    @Override
    void startOperations() {
        Log.d("Action:","startOperations!");

    }

    @Override
    void stopOperations() {
        Log.d("Action:","stopOperations!");
    }

    private void buttonfunction(){
        Intent intent=new Intent(MainKaterinaActivity.this, HomeScreen.class);
        startActivity(intent);
        finish();

    }
}