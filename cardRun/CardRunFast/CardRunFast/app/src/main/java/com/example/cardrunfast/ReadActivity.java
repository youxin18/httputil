package com.example.cardrunfast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ReadActivity extends AppCompatActivity {
    private ViewPager pager_two;
    private Button share;
    private ArrayList<View> alist;
    private ArrayList<String> slist;
    private ListView list;
    private pageeAdapter adapter;
    ArrayAdapter<String> contactsListAdapter;
    List<String> contactsList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpagermain);

        pager_two = findViewById(R.id.view_pager);

        alist = new ArrayList<View>();
        LayoutInflater layout = getLayoutInflater();
        View aa = layout.inflate(R.layout.viewpager1, null, false);
        alist.add(aa);
        alist.add(layout.inflate(R.layout.viewpager2, null, false));
        alist.add(layout.inflate(R.layout.viewpager3, null, false));
        slist = new ArrayList<String>();
        slist.add("aa");
        slist.add("bb");
        slist.add("cc");
        adapter = new pageeAdapter(alist, slist);
        pager_two.setAdapter(adapter);

        //分享获取联系人

        share = findViewById(R.id.share);
        list = aa.findViewById(R.id.list);
        contactsListAdapter = new ArrayAdapter<>(ReadActivity.this, android.R.layout.simple_list_item_1, contactsList);
        list.setAdapter(contactsListAdapter);

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(ReadActivity.this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(ReadActivity.this, new String[]{Manifest.permission.READ_CONTACTS}, 1);
                } else {
                    readContacts();
                }
            }
        });

    }

    @SuppressLint("Range")
    private void readContacts() {
        Cursor cursor = null;
        try {
            cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI
                    , null, null, null, null);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    contactsList.add(name + "\n" + number);
                }
                contactsListAdapter.notifyDataSetChanged();
            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    readContacts();
                } else {
                    Toast.makeText(this, "You denied the permission", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }
}