package com.example.cardrunfast;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;

public class ButtomTab extends AppCompatActivity {
    private TabLayout tab_Layout;
    private Fragment[]fragments;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_tab);
        fragments=DataGenerator.getFragments();
        initView();
    }

    private void initView() {
        tab_Layout=findViewById(R.id.bottom_tab_layout);
        tab_Layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                onTabItemSelected(tab.getPosition());

                for (int i=0;i<tab_Layout.getTabCount();i++){
                    View view=tab_Layout.getTabAt(i).getCustomView();
                    ImageView icon=view.findViewById(R.id.tab_content_image);
                    TextView text=view.findViewById(R.id.tab_content_text);
                    if(i==tab.getPosition()){
                        icon.setImageResource(DataGenerator.mTabRes[i]);
                        text.setTextColor(getResources().getColor(android.R.color.darker_gray));
                    }else {
                        text.setTextColor(getResources().getColor(android.R.color.black));
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        for (int i=0;i<3;i++){
            tab_Layout.addTab(tab_Layout.newTab().setCustomView(DataGenerator.getTabView(this,i)));
        }
    }

    private void onTabItemSelected(int position) {
        Fragment frag=null;
        switch (position){
            case 0:
                frag=fragments[0];
                break;
            case 1:
                frag=fragments[1];
                break;
            case 2:
                frag=fragments[2];
                break;


        }
        if(frag!=null){
            getSupportFragmentManager().beginTransaction().replace(R.id.home_container,frag).
            commit();
        }
    }

}
