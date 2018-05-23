package com.example.administrator.payforlife.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Personal_propertyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_property);
    }
    public void tixian(View view){
        Intent intent = new Intent();
        intent.setClass(MainActivity.this,TixianActivity.class);
        startActivity(intent);
    }
    public void chongzhi(View view){
        Intent intent = new Intent();
        intent.setClass(MainActivity.this,ChongzhiActivity.class);
        startActivity(intent);
}
