package com.example.cardrunfast;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class DataGenerator {
    public static final int[]mTabRes=new int[]{R.drawable.word,R.drawable.find,R.drawable.me};
    public static final String[]mTabTitle=new String[]{"单词","发现","我的"};

    public static Fragment[] getFragments(){
        Fragment fragments[]=new Fragment[3];
        fragments[0]=new HomePageFragment();
        fragments[1]=new FindPageFragment();
        fragments[2]=new MePageFragment();
        return fragments;

    }
    public static View getTabView(Context context,int position){
        View view= LayoutInflater.from(context).inflate(R.layout.tab_bottom,null);
        ImageView tabIcon=view.findViewById(R.id.tab_content_image);
        tabIcon.setImageResource(DataGenerator.mTabRes[position]);
        TextView tabText=view.findViewById(R.id.tab_content_text);
        tabText.setText(mTabTitle[position]);
        return view;

    }
}
