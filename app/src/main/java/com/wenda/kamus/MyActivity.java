package com.wenda.kamus;

import android.app.Activity;
import android.os.Bundle;

import com.wenda.kamus.util.CopyDatabase;

import java.sql.SQLException;


public class MyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        // copy datanya dulu
        CopyDatabase copyDatabase = new CopyDatabase(this);
        copyDatabase.createdDatabase();
        try {
            copyDatabase.openDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}