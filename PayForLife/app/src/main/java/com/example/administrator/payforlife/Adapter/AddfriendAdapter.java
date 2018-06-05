package com.example.administrator.payforlife.Adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.payforlife.Entity.Friend;
import com.example.administrator.payforlife.R;

import java.util.List;

/**
 * Created by Administrator on 2018/6/5.
 */

public class AddfriendAdapter extends ArrayAdapter<Friend> {
    private int Id;
    public AddfriendAdapter(Context context, int textViewResourceId, List<Friend> objects){
        super(context,textViewResourceId,objects);
        Id = textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Friend friend = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(Id,parent,false);
        TextView tvaddfriendname = view.findViewById(R.id.tv_addfriend);
        Button btnaddfriend = view.findViewById(R.id.btn_addfriend);
        tvaddfriendname.setText(friend.getFriendname());
        btnaddfriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;
    }
}
