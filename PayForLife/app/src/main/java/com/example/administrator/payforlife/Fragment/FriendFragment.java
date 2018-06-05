package com.example.administrator.payforlife.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.payforlife.Activity.ChatActivity;
import com.example.administrator.payforlife.Activity.Setting_Activity;
import com.example.administrator.payforlife.Adapter.FriendAdapter;
import com.example.administrator.payforlife.Entity.Friend;
import com.example.administrator.payforlife.R;
import com.example.administrator.payforlife.util.OkhttpUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/5/10.
 */

public class FriendFragment extends Fragment {
    private Context context;
    private List<Friend> friends;
    private FriendAdapter friendAdapter;
    private ListView lvFriend;
    private Button btnSetting;
    private EditText etsearchfriend;
    private Button btnsearchfriend;
    private onclickedImpl listener = new onclickedImpl();
    private Intent intent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.friendlist,container,false);
        lvFriend = view.findViewById(R.id.lv_friend);
        btnSetting = view.findViewById(R.id.btn_tosetting);
        etsearchfriend = view.findViewById(R.id.et_searchfriend);
        btnsearchfriend = view.findViewById(R.id.btn_searchfriend);
        friends = getdata();
        friendAdapter = new FriendAdapter(context, R.layout.friend_item,friends);
        lvFriend.setAdapter(friendAdapter);
        lvFriend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(context, ChatActivity.class);
                startActivity(intent);
            }
        });
        btnSetting.setOnClickListener(listener);
        btnsearchfriend.setOnClickListener(listener);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }

    private List<Friend> getdata(){
        List<Friend> friends = new ArrayList<>();
        Friend fri1 = new Friend();
        fri1.setFriendname("Lee");
        fri1.setFriendtime("5-21");
        fri1.setImg(R.mipmap.header_first);
        friends.add(fri1);

        Friend fri2 = new Friend();
        fri2.setFriendname("Davies");
        fri2.setFriendtime("5-20");
        fri2.setImg(R.mipmap.header_second);
        friends.add(fri2);

        Friend fri3 = new Friend();
        fri3.setFriendname("LiLy");
        fri3.setFriendtime("5-22");
        fri3.setImg(R.mipmap.header_four);
        friends.add(fri3);
        return friends;
    }

    private class onclickedImpl implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_tosetting:
                    intent.setClass(context,Setting_Activity.class);
                    startActivity(intent);
                    break;
                case R.id.btn_searchfriend:
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String friendname = etsearchfriend.getText().toString();
                            String address = "http://10.7.85.227:8080/OkHttpServerForAndroid/searchfriend.action";
                            if (friendname.equals("")){
                                Looper.prepare();
                                Toast.makeText(context,"用户名不能为空",Toast.LENGTH_SHORT).show();
                                Looper.loop();
                            } else {
                                FormBody body = new FormBody.Builder().add("friendname",friendname).build();
                                OkhttpUtil.sendOkHttpFormBodyRequest(address, body, new Callback() {
                                    @Override
                                    public void onFailure(Call call, IOException e) {
                                        Looper.prepare();
                                        Toast.makeText(context,"请求失败",Toast.LENGTH_SHORT).show();
                                        Looper.loop();
                                    }

                                    @Override
                                    public void onResponse(Call call, Response response) throws IOException {
                                        String responsetext = response.body().string();
                                        Log.d("lhy", responsetext);
                                        if (!responsetext.equals("")) {
                                            Looper.prepare();
                                            Toast.makeText(context,"有该用户",Toast.LENGTH_SHORT).show();
                                            Looper.loop();
                                        } else {
                                            Looper.prepare();
                                            Toast.makeText(context,"没有该用户",Toast.LENGTH_SHORT).show();
                                            Looper.loop();
                                        }
                                    }
                                });
                            }

                        }
                    }).start();
                    break;
            }
        }
    }
}
