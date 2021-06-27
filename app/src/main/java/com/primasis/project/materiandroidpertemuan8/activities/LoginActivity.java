package com.primasis.project.materiandroidpertemuan8.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.primasis.project.materiandroidpertemuan8.R;
import com.primasis.project.materiandroidpertemuan8.helpers.PrefsHelper;

/**
 * created by Irfan Assidiq
 * email : assidiq.irfan@gmail.com
 * github.com/nanashiKey
 **/
public class LoginActivity extends AppCompatActivity {
    private EditText etUsername, etPassword;
    private Button btnLogin;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        boolean statusLogin = PrefsHelper.sharedInstance(LoginActivity.this).getStatusLogin();
        if(statusLogin){
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doLogin();
            }
        });
    }

    private void doLogin(){
        String username, password;
        username = etUsername.getText().toString();
        password = etPassword.getText().toString();
        if(username.equals("") || password.equals("")){
            Toast.makeText(this, "kolom tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }else{
            if(username.equals("naruto") && password.equals("password123")){
                PrefsHelper.sharedInstance(LoginActivity.this).setStatusLogin(true);
                PrefsHelper.sharedInstance(LoginActivity.this).setUsername(username);
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }else{
                Toast.makeText(this, "username/password salah", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
