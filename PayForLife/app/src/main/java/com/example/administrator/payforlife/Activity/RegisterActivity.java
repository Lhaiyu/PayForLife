package com.example.administrator.payforlife.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.payforlife.R;
import com.example.administrator.payforlife.util.OkhttpUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity {
    private Button btnRegistertoLogin;
    private Button btnRegistered;
    private EditText etregistmail;
    private EditText etregistname;
    private EditText etregistpwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        getview();
        onclickedImpl listener = new onclickedImpl();
        setListener(listener);
    }

    private void setListener(onclickedImpl listener) {
        btnRegistertoLogin.setOnClickListener(listener);
        btnRegistered.setOnClickListener(listener);
    }

    private void getview() {
        btnRegistertoLogin = findViewById(R.id.btn_registertologin);
        btnRegistered = findViewById(R.id.btn_registered);
        etregistmail = findViewById(R.id.et_regist_mail);
        etregistname = findViewById(R.id.et_regist_name);
        etregistpwd = findViewById(R.id.et_regist_pwd);
    }
    private class onclickedImpl implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_registertologin:
                    Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                    startActivity(intent);
                    break;
                case R.id.btn_registered:
                            String mail = etregistmail.getText().toString();
                            final String username = etregistname.getText().toString();
                            String password = etregistpwd.getText().toString();
                            String address = "http://10.7.85.136:8080/OkHttpServerForAndroid/register.action";
                            OkHttpClient okHttpClient = new OkHttpClient();
                            FormBody.Builder builder = new FormBody.Builder();
                            builder.add("email",mail);
                            builder.add("username",username);
                            builder.add("password",password);
                            FormBody body = builder.build();
                            Request request = new Request.Builder().post(body).url(address).build();
                            OkhttpUtil.sendOkHttpFormBodyRequest(address, body, new Callback() {
                                @Override
                                public void onFailure(Call call, IOException e) {
                                    Toast.makeText(RegisterActivity.this,"请求失败",Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onResponse(Call call, Response response) throws IOException {
                                    final String responseText = response.body().string();
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            if(responseText.equals("registersuccess")){
                                                Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                                                Intent intent1 = new Intent(getApplicationContext(),LoginActivity.class);
                                                startActivity(intent1);
                                            } else {
                                                etregistmail.setText("");
                                                etregistname.setText("");
                                                etregistpwd.setText("");
                                                Toast.makeText(RegisterActivity.this,"该用户已注册",Toast.LENGTH_SHORT).show();

                                            }
                                        }
                                    });
                                }
                            });
                    break;
            }
        }
    }
}
