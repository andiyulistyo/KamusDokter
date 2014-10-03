package com.wenda.kamus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wenda.kamus.util.DatabaseUtil;

import java.util.List;

public class DetailFragment extends Fragment {


    final static String ARG_POSITION = "position";
    int mCurrentPosition = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);
        }

        return inflater.inflate(R.layout.layout_detail, container, false);
    }

    public void updateData(int position) {
        DatabaseUtil database = new DatabaseUtil(getActivity());
        List<Kamus> allKamus = database.getAllKamus();
        TextView textViewIstilah = (TextView) getActivity().findViewById(R.id.textViewIstilah);
        TextView textViewPenjelasan = (TextView) getActivity().findViewById(R.id.textViewPenjelasan);
        textViewIstilah.setText(allKamus.get(position).getIstilah());
        textViewPenjelasan.setText(allKamus.get(position).getArti());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // simpan istilah yang terseleksi sekarang
        // kalau - kalau nanti kita butuhin lagi ketika fragment di buat lagi
        outState.putInt(ARG_POSITION, mCurrentPosition);
    }

    @Override
    public void onStart() {
        super.onStart();

        Bundle bundle = getArguments();
        if (bundle != null) {
            // update data sesuai dengan yang di kirim
            updateData(bundle.getInt(ARG_POSITION));
        } else if (mCurrentPosition != -1) {
            // update data berdasarkan position yang tersimpang di sebelumnya
            updateData(mCurrentPosition);
        }
    }
}