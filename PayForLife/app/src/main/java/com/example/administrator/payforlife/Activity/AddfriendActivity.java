package com.example.administrator.payforlife.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.administrator.payforlife.Adapter.AddfriendAdapter;
import com.example.administrator.payforlife.Entity.Friend;
import com.example.administrator.payforlife.R;

import java.util.List;

public class AddfriendActivity extends AppCompatActivity {
    private ListView lvaddfriend;
    private AddfriendAdapter addfriendAdapter;
    private List<Friend> friends;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addfriend);
        lvaddfriend = findViewById(R.id.lv_addfriend);
        addfriendAdapter = new AddfriendAdapter(AddfriendActivity .this,R.layout.addfriend_item,friends);
        lvaddfriend.setAdapter(addfriendAdapter);
    }
}
