package com.example.cardrunfast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginUserPwdActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private EditText user;
    private EditText pwd;
    private CheckBox checkBox;
    private Boolean isRemembered;
    private String datauser;
    private String datapwd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user_pwd);
        TextView codeLogin = findViewById(R.id.code_login);
        codeLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginUserPwdActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        TextView login = findViewById(R.id.login);
        checkBox = findViewById(R.id.checkbox2);
        user = findViewById(R.id.user);
        pwd = findViewById(R.id.pwd);

        sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
        isRemembered = sharedPreferences.getBoolean("isRemembered", false);



        if (isRemembered) {
            datauser = sharedPreferences.getString("username", "");
            datapwd = sharedPreferences.getString("password", "");
            user.setText(datauser);
            pwd.setText(datapwd);
        }else {
            datauser = "987";
            datapwd = "123";
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usertext = user.getText().toString();
                String pwdtext = pwd.getText().toString();
                if (usertext.equals(datauser) && pwdtext.equals(datapwd)) {
                    editor = sharedPreferences.edit();
                    if (checkBox.isChecked()) {
                        editor.putBoolean("isRemembered", true);
                    } else {
                        editor.remove("isRemembered");
                    }

                    editor.apply();
                    Toast.makeText(LoginUserPwdActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginUserPwdActivity.this, ButtomTab.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginUserPwdActivity.this, "用户名或密码不正确", Toast.LENGTH_SHORT).show();
                }



            }
        });
    }
}