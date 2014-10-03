package com.wenda.kamus;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

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
            final ListIstilah listIstilah = new ListIstilah();

            listIstilah.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, listIstilah).commit();

            final EditText editText = (EditText) findViewById(R.id.editText);
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    String filter = editText.getText().toString().trim();
                    listIstilah.updateSetAdapter(filter);
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
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