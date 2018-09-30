package com.example.tripbus;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import static com.facebook.FacebookSdk.getApplicationContext;

public class myplaceAdapter extends BaseAdapter {
    private Context mContext = null;
    LayoutInflater inflater;
    private ArrayList<myplace> myplaces = new ArrayList<myplace>();


    public myplaceAdapter(Context context, ArrayList<myplace> mp) {
        super();
        this.mContext = context;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.myplaces = mp;
    }


    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return myplaces.size();    //그리드뷰에 출력할 목록 수
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return myplaces.get(position);    //아이템을 호출할 때 사용하는 메소드

    }



    @Override
    public long getItemId(int position) {
// TODO Auto-generated method stub
        return position;    //아이템의 아이디를 구할 때 사용하는 메소드
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder testViewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.myplace_item, null);
            testViewHolder = new ViewHolder();
            testViewHolder.placeImg = (ImageView) convertView.findViewById(R.id.myplace_img);
            testViewHolder.placeName = (TextView) convertView.findViewById(R.id.myplace_name);
            convertView.setTag(testViewHolder);
        } else {
            testViewHolder = (ViewHolder) convertView.getTag();
        }

        myplace mp = (myplace) getItem(position);
        int type = mp.getMyplaceType();

        Drawable drawable = null;
        switch (type) {
            case 1:
                drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.night2);
                break;
            case 2:
                drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.street2);
                break;
            case 3:
                drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.nature2);
                break;
            case 4:
                drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.history2);
                break;
        }
        testViewHolder.placeImg.setImageDrawable(drawable);
        testViewHolder.placeName.setText("" + mp.getMyplaceName());


        return convertView;

    }

    private class ViewHolder {
        public ImageView placeImg;
        public TextView placeName;
    }



}
