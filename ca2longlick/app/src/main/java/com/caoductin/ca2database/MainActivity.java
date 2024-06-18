package com.caoductin.ca2database;

import android.app.AlertDialog;
import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.caoductin.Adapter.adapter;
import com.caoductin.Model.product;

import com.caoductin.ca2database.databinding.ActivityMainBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    database db;

    adapter adapter;
    ArrayList<product> products;

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        prepraDB();
        LoadDB();
        binding.lvItem.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                showDialog(products.get(position));
                return true;
            }
        });
    }

    // Show dialog with product details
    private void showDialog(product p) {
        // Create the BottomSheetDialog
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);

        // Inflate custom layout for dialog
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.add_student, null);
        bottomSheetDialog.setContentView(dialogView);

        EditText edtmasv = dialogView.findViewById(R.id.masp);
        EditText edtten = dialogView.findViewById(R.id.tensp);
        EditText edtGia = dialogView.findViewById(R.id.giasp);
        Button btnTrove = dialogView.findViewById(R.id.btntrove);
        Button btnDelete = dialogView.findViewById(R.id.btnXoa);
        edtmasv.setText(String.valueOf(p.getMasp()));
        edtten.setText(p.getTenSp());
        edtGia.setText(String.valueOf(p.getGia()));

        // Show the dialog
        bottomSheetDialog.show();
        btnTrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int masv = Integer.parseInt(edtmasv.getText().toString());
//                String ten = edtTen.getText().toString();
//                String Lop = edtLop.getText().toString();
//                String sql = "Insert into " + database.tbl_name + " values("+ masv+",'"+ten+"','"+Lop+"')";
                String sql = "delete from "+ database.tbl_name + " where "+ database.col_ma + " = " + p.getMasp();
                db.exec(sql);
                LoadDB();
                bottomSheetDialog.dismiss();
            }
        });

        // Get the screen height
        int screenHeight = getResources().getDisplayMetrics().heightPixels;

        // Set the dialog's height to half the screen height
        //bottomSheetDialog.getBehavior().setPeekHeight(screenHeight / 2, true);
    }
    public void prepraDB(){
        db = new database(this);

        db.CreateSampleData();

    }
    public void LoadDB(){
        adapter = new adapter(MainActivity.this,R.layout.item_layout,getData());
        binding.lvItem.setAdapter(adapter);

    }
    public List<product> getData(){
        products = new ArrayList<>();
        Cursor cursor = db.querydata("SElect * from "+ database.tbl_name);
        while(cursor.moveToNext()){
            products.add(new product(cursor.getInt(0),cursor.getString(1),cursor.getDouble(2)));
        }
        cursor.close();

        return products;
    }



}