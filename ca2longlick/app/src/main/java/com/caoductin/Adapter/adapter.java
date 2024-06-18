package com.caoductin.Adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.caoductin.ca2database.R;

import com.caoductin.ca2database.MainActivity;

import java.util.List;
import com.caoductin.Model.product;

public class adapter extends BaseAdapter {
    MainActivity context;
    int list_item;
    List<product> products;

    public adapter(MainActivity context, int list_item, List<product> products) {
        this.context = context;
        this.list_item = list_item;
        this.products = products;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater)  context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(list_item,null);

            //link to the view
            holder.txtName = view.findViewById(R.id.txtName);

            view.setTag(holder);

        }
        else{
            holder = (ViewHolder) view.getTag();
        }
        product p = products.get(position);
        holder.txtName.setText(p.getTenSp());
        return view;
    }
    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    public static class ViewHolder{
        TextView txtName;
    }



}
