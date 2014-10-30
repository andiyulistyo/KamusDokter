package com.wenda.kamus;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

        final ListIstilahAdapter adapter = new ListIstilahAdapter(this, allKamus);

        setListAdapter(adapter);

        ListView listView = getListView();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView id = (TextView) view.findViewById(R.id.textViewId);

                // ambil variable gambar
                ImageView image = (ImageView) view.findViewById(R.id.imageView);
                // cek apakah gambar ditampilkan atau tidak
                // jka di tampilkan maka ada gambar
                // jika tidak maka tidak ada gambar
                // ada gambar
                if (image.getVisibility() == View.VISIBLE) {
                    Intent intent = new Intent(getApplicationContext(), GambarActivity.class);
                    intent.putExtra("id", Integer.valueOf(String.valueOf(id.getText().toString())));
                    startActivity(intent);
                } else
                    Toast.makeText(getApplicationContext(), "Tidak ada gambar", Toast.LENGTH_SHORT).show();
            }
        });


        final EditText editTextCari = (EditText) findViewById(R.id.editTextCari);

        editTextCari.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String cari = editTextCari.getText().toString();
                adapter.cari(cari);
            }
        });
    }

}