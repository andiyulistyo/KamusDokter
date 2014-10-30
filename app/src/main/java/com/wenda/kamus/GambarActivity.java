package com.wenda.kamus;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.wenda.kamus.util.ConvertImage;
import com.wenda.kamus.util.DatabaseUtil;

public class GambarActivity extends ActionBarActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gambar);

        // ambil id yang dikirim dari layout sebelumnya
        int id = (Integer) getIntent().getExtras().get("id");

        // masukkan id ke database
        // dan ambil data dari database sesuai dengan id yang dimasukkan
        DatabaseUtil database = new DatabaseUtil(this);
        // ambil datanya dari database berdasarkan id
        Kamus kamus = database.getById(id);

        // initial variable image
        ImageView imageView = (ImageView) findViewById(R.id.imageViewGamar);
        // masukkan gambar ke image
        imageView.setImageBitmap(ConvertImage.getImage(kamus.getGambar()));

        // initial variable textview istilah
        TextView textViewIstilah = (TextView) findViewById(R.id.textViewIstilahGambar);
        // masukkan data ke variable istilah
        textViewIstilah.setText(kamus.getIstilah());
    }
}
