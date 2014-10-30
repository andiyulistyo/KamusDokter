package com.wenda.kamus.util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;
import com.wenda.kamus.Kamus;

import java.util.ArrayList;
import java.util.List;

public class DatabaseUtil extends SQLiteAssetHelper {

    private static String _DATABASE_NAME = "istilah.db";
    private static int _DATABASE_VERSION = 1;


    public DatabaseUtil(Context context) {
        super(context, _DATABASE_NAME, null, _DATABASE_VERSION);
    }

    public List<Kamus> getAllKamus() {
        List<Kamus> kamusList = new ArrayList<Kamus>();
        // query untuk ambil semua data yang ada di dalam table
        String query = "SELECT * FROM Tabel_istilah order by istilah asc";
        // masukkan query ke dalam cursor
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery(query, null);

        // loop data yang ada di cursor
        try {
            // cek apakah cursor kosong atau tidak
            if (cursor != null && cursor.moveToNext()) {
                // jika tidak kosong maka masukkan data ke dalam entitas
                // yang nantinya entitas akan di masukkan ke list
                Kamus kamus;
                // lakukan loop sebanyak data yang ada
                do {
                    kamus = new Kamus();
                    // masukkan gambar ke entity
                    kamus.setGambar(cursor.getBlob(cursor.getColumnIndex("keterangan gambar")));
                    // masukan istilah.db ke entity
                    kamus.setIstilah(cursor.getString(cursor.getColumnIndex("istilah")));
                    // masukan arti/penjelasan ke entity
                    kamus.setArti(cursor.getString(cursor.getColumnIndex("arti")));
                    // masukkan id ke entity
                    kamus.setId(cursor.getInt(cursor.getColumnIndex("id")));

                    // masukkan entity ke dalam list
                    kamusList.add(kamus);
                } // lakukan perulanan sampai data tidak ada lagi
                while (cursor.moveToNext());
            }
        } // setelah semua proses berakhir jangan lupa untuk menutup koneksi ke database
        finally {
            database.close();
        }

        // return
        return kamusList;
    }

    public Kamus getById(int id) {
        String query = "SELECT * FROM Tabel_istilah WHERE id =" + id;
        SQLiteDatabase database = getReadableDatabase();

        Cursor cursor = database.rawQuery(query, null);

        Kamus kamus = null;
        if (cursor != null & cursor.moveToNext()) {
            kamus = new Kamus();
            kamus.setGambar(cursor.getBlob(cursor.getColumnIndex("keterangan gambar")));
            kamus.setIstilah(cursor.getString(cursor.getColumnIndex("istilah")));
        }
        return kamus;
    }
}
