package com.wenda.kamus.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wenda.kamus.Kamus;
import com.wenda.kamus.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListIstilahAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater inflater;
    private List<Kamus> kamusList;
    private List<Kamus> filter;

    private String[] color = {"FFE47A48",
            "FFDBBF10",
            "FFEB9B2A",
            "FF5A94B5",
            "FFDD5956",
            "FF6EB562"};

    public ListIstilahAdapter(Context context, List<Kamus> kamusListt) {
        this.mContext = context;
        this.kamusList = kamusListt;
        this.filter = new ArrayList<Kamus>();
        this.filter.addAll(kamusList);
        this.inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return kamusList.size();
    }

    @Override
    public Object getItem(int i) {
        return kamusList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.item_list, viewGroup, false);
            view.setTag(holder);

            holder.textViewIstilah = (TextView) view.findViewById(R.id.textViewIstilah);
            holder.imageView = (ImageView) view.findViewById(R.id.imageView);
        } else holder = (ViewHolder) view.getTag();

        holder.textViewIstilah.setText(Html.fromHtml("<b> " + kamusList.get(position).getIstilah() + " :</b>  " + kamusList.get(position).getArti()));

        int randomNum = (int) (Math.random() * ((5) + 1));

        if (kamusList.get(position).getGambar() != null)
            holder.imageView.setBackgroundColor(Color.parseColor("#" + color[randomNum]));
//        else holder.imageView.setBackgroundColor(Color.parseColor("#FFFFFFFF"));

        return view;
    }

    public void cari(String cari) {
        cari = cari.toLowerCase(Locale.getDefault());
        kamusList.clear();
        if (cari.length() == 0) {
            kamusList.addAll(filter);
        } else {
            for (Kamus kamus : filter) {
                if (kamus.getIstilah().toLowerCase(Locale.getDefault()).contains(cari)) {
                    Log.i(">>>>>", kamus.getIstilah() + " = " + cari);
                    kamusList.add(kamus);
                }
            }
        }
        notifyDataSetChanged();
    }


    class ViewHolder {
        TextView textViewIstilah;
        ImageView imageView;
    }

}
