package com.caoductin.de2016_03;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.caoductin.adapter.Adapter;
import com.caoductin.de2016_03.databinding.ActivityMainBinding;
import com.caoductin.model.student;

import java.util.ArrayList;
import com.caoductin.de2016_03.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    database db;
    ArrayList<student> students;
    ActivityMainBinding binding;
    Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        prepareBD();
        loadData();
        binding.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddStudentDialog();
            }
        });
    }
    private void showAddStudentDialog() {
        // Create the BottomSheetDialog
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);

        // Inflate custom layout for dialog
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.add_student, null);
        bottomSheetDialog.setContentView(dialogView);

        EditText edtmasv = dialogView.findViewById(R.id.edtMasv);
        EditText edtTen = dialogView.findViewById(R.id.edtten);
        EditText edtLop = dialogView.findViewById(R.id.edtlop);
        Button btnAdd = dialogView.findViewById(R.id.btnAdd);
        ImageView close = dialogView.findViewById(R.id.close);

        // Show the dialog
        bottomSheetDialog.show();
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int masv = Integer.parseInt(edtmasv.getText().toString());
                String ten = edtTen.getText().toString();
                String Lop = edtLop.getText().toString();
                String sql = "Insert into " + database.Tbl_name + " values("+ masv+",'"+ten+"','"+Lop+"')";
                db.execSql(sql);
                loadData();
                bottomSheetDialog.dismiss();
            }
        });

        // Get the screen height
        int screenHeight = getResources().getDisplayMetrics().heightPixels;

        // Set the dialog's height to half the screen height
        bottomSheetDialog.getBehavior().setPeekHeight(screenHeight / 2, true);

    }

    public void loadData(){
        adapter = new Adapter(MainActivity.this,R.layout.list_item,getDataFromDb());
        binding.lvStudent.setAdapter(adapter);
    }
    private List<student> getDataFromDb() {
        students = new ArrayList<>();
        Cursor cursor = db.queryData("SELECT * FROM " + database.Tbl_name);
        while (cursor.moveToNext()){
            students.add(new student(cursor.getInt(0),cursor.getString(1),cursor.getString(2)));
        }
        cursor.close();
        return students;
    }
    public void prepareBD(){
        db = new database(this);
        db.CreateSampleData();
        Log.d("Database", "Database prepared with sample data.");
    }
    public void deleteStudent(student st){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("xac nhan xoa");
        builder.setMessage("ban co chac muon xoa khong");
        builder.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String sql = "Delete from "+ database.Tbl_name + " where "+ database.Col_ma+ " = "+ st.getMasv();
                db.execSql(sql);
                loadData();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

       Dialog dialog = builder.create();
       dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
       dialog.show();

    }

}