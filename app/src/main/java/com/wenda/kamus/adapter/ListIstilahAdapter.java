package com.wenda.kamus.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wenda.kamus.Kamus;
import com.wenda.kamus.R;

import java.util.List;

public class ListIstilahAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater inflater;
    private List<Kamus> kamusList;

    public ListIstilahAdapter(Context mContext, List<Kamus> kamusList) {
        this.mContext = mContext;
        this.kamusList = kamusList;
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
            holder.textViewPenjelasan = (TextView) view.findViewById(R.id.textViewPenjelasan);
        } else holder = (ViewHolder) view.getTag();

        holder.textViewPenjelasan.setText(kamusList.get(position).getArti());
        holder.textViewIstilah.setText(kamusList.get(position).getIstilah());

        return view;
    }

    class ViewHolder {
        TextView textViewIstilah, textViewPenjelasan;
    }

}
