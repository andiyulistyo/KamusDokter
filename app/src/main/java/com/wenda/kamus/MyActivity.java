package com.wenda.kamus;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

public class MyActivity extends FragmentActivity implements ListIstilah.IstilahSelectListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }

            // buat instance dari fragment istilah
            ListIstilah listIstilah = new ListIstilah();

            listIstilah.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, listIstilah).commit();
        }
    }

    @Override
    public void onIstilahSelected(int position) {
        DetailFragment detailFragment = (DetailFragment) getSupportFragmentManager().findFragmentById(R.id.fragment2);

        if (detailFragment != null) {
            detailFragment.updateData(position);
        } else {
            DetailFragment newDetailFragment = new DetailFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(DetailFragment.ARG_POSITION, position);
            newDetailFragment.setArguments(bundle);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.fragment_container, newDetailFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}