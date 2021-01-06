package com.example.quiz_app;

import java.util.List;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.Random;

public class activity_CatAdapter_t extends BaseAdapter{
    private List<String> catList = null;

    public activity_CatAdapter_t(List<String> catList) {
        this.catList = catList;
    }

    @Override
    public int getCount() {
        return catList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if(convertView == null)
        {
            view= LayoutInflater.from(parent.getContext()).inflate(R.layout.set_item_layout_t, parent, false);
        }
        else
        {
            view = convertView;
        }

        ((TextView) view.findViewById(R.id.catName)).setText(catList.get(position));

        Random random = new Random();
        int color = Color.argb(255, random.nextInt(255),random.nextInt(255), random.nextInt(255));
        view.setBackgroundColor(color);

        return view;

    }
}

