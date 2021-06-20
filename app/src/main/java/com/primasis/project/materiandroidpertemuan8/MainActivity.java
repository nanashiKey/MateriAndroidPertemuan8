package com.primasis.project.materiandroidpertemuan8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btn1, btn2;
    private TextView tvHello;
    private EditText etPerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
        tvHello = findViewById(R.id.tvHello);
        etPerson = findViewById(R.id.etPersonName);

        String savedName = PrefsHelper.sharedInstance(MainActivity.this).getUsername();
        if(!savedName.equals("")){
            tvHello.setText("Hello "+savedName);
            btn2.setVisibility(View.VISIBLE);
            btn2.setText("LOG OUT");
        }

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setName();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearName();
            }
        });
    }

    private void setName(){
        String username = etPerson.getText().toString();
        if(username.equals("")){
            Toast.makeText(this, "nama tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }else{
            tvHello.setText("Hello "+username);
            PrefsHelper.sharedInstance(MainActivity.this).setUsername(username);
        }
    }

    private void clearName(){
        PrefsHelper.sharedInstance(MainActivity.this).setUsername("");
        PrefsHelper.sharedInstance(MainActivity.this).setStatusLogin(false);
        finish();
    }

}