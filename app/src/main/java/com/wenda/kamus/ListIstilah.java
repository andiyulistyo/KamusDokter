package com.wenda.kamus;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.wenda.kamus.util.DatabaseUtil;

import java.util.ArrayList;
import java.util.List;

public class ListIstilah extends ListFragment {
    int layout;
    IstilahSelectListener selectListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // kita butuh layout berbeda untuk version android yang berbeda
        // untuk itu kita cek terlebih dahulu
        // jika android version 3 keatas maka kita gunakan simple_list_item_activated_1
        // jika dibawah itu kita gunakan simple_list_item_1
        layout = Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ?
                android.R.layout.simple_list_item_activated_1 : android.R.layout.simple_list_item_1;

        // ambil data yang ada dalam database
        // yang nantinya hasilnya kita gunakan untuk adapter listviewnya

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // panggil database util
        DatabaseUtil database = new DatabaseUtil(getActivity());
        // buat array string untuk adapternya
        List<String> istilahList = new ArrayList<String>();
        // loop semua data yang ada
        // dan masukkan ke array string
        for (Kamus kamus : database.getAllKamus()) {
            // masukkan semua istilah.db ke dalam list
            istilahList.add(kamus.getIstilah());
        }


        // masukan data ke dalam adapter listview
        setListAdapter(new ArrayAdapter<String>(getActivity(), layout, istilahList));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        selectListener.onIstilahSelected(position);

        // beri highlighted ketika user memilih, hanya berlaku ketika di table
        getListView().setItemChecked(position, true);
    }

    @Override
    public void onStart() {
        super.onStart();

        if (getFragmentManager().findFragmentById(R.id.fragment2) != null) {
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            selectListener = (IstilahSelectListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + "");
        }
    }

    public interface IstilahSelectListener {
        public void onIstilahSelected(int position);
    }
}
