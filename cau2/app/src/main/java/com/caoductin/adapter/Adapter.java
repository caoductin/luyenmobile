package com.caoductin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.caoductin.de2016_03.MainActivity;
import com.caoductin.de2016_03.R;
import com.caoductin.model.student;

import java.util.List;

public class Adapter extends BaseAdapter {
    MainActivity context;

    int item_Layout;

    List<student> students;

    public Adapter(MainActivity context, int item_Layout, List<student> students) {
        this.context = context;
        this.item_Layout = item_Layout;
        this.students = students;
    }
    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Object getItem(int position) {
        return students.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(item_Layout, null);

            // Linking views
            holder.txtId = convertView.findViewById(R.id.txtId);
            holder.txtTen = convertView.findViewById(R.id.txtTen);
            holder.txtLop = convertView.findViewById(R.id.txtLop);
            holder.btnDelete = convertView.findViewById(R.id.btnDelete);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        student p = students.get(position);
        if (p != null) {
            holder.txtId.setText("Mã sinh viên:"+ p.getMasv());  // Ensure IDs are converted to string
            holder.txtTen.setText("Họ và tên: "+ p.getTensv());
            holder.txtLop.setText("Lớp:"+ p.getLop());
        }
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.deleteStudent(p);
            }
        });

        return convertView;
    }

    //    public View getView(int i, View view, ViewGroup parent) {
//        ViewHolder holder;
//        if(view == null) {
//            holder = new ViewHolder();
//            LayoutInflater inflater = (LayoutInflater)  context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            view = inflater.inflate(item_Layout,null);
//
//            //linking views
//            holder.txtId = view.findViewById(R.id.txtId);
//            holder.txtTen = view.findViewById(R.id.txtTen);
//            holder.txtLop = view.findViewById(R.id.txtLop);
//
//            view.setTag(holder);
//
//        }
//        else {
//            holder = (ViewHolder) view.getTag();
//        }
//        student p = students.get(i);
//
//        holder.txtId.setText(p.getMasv());
//        holder.txtTen.setText(p.getTensv());
//        holder.txtLop.setText(p.getLop());
//        return view;
//    }
    public static class ViewHolder{
        TextView txtId,txtTen,txtLop;
        Button btnDelete;

    }
}
