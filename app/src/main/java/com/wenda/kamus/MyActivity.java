package com.wenda.kamus;

import android.app.ListActivity;
import android.os.Bundle;

import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.ViewTarget;
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
        ListIstilahAdapter adapter = new ListIstilahAdapter(this, allKamus);
        setListAdapter(adapter);

        new ShowcaseView.Builder(this)
                .setTarget(new ViewTarget(R.id.editTextCari, this))
                .setContentTitle("Cari Istilah")
                .setContentText("Jika ingin cepet mencari makan bisa langsung ketik disini saja")
                .hideOnTouchOutside()
                .build();
    }

}