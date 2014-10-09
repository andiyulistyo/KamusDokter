package com.wenda.kamus;

import android.app.ListActivity;
import android.os.Bundle;

import com.wenda.kamus.adapter.ListIstilahAdapter;
import com.wenda.kamus.util.DatabaseUtil;

import java.util.List;

public class MyActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        DatabaseUtil database = new DatabaseUtil(this);
        List<Kamus> allKamus = database.getAllKamus();
        setListAdapter(new ListIstilahAdapter(this, allKamus));
    }

}