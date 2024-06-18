package com.caoductin.multithreadde1;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.caoductin.multithreadde1.databinding.ActivityMainBinding;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Handler handler = new Handler();
    int ranNumber;

    Random random = new Random();
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        addEvent();
    }
//    public void addEvent(){
//        Thread backgroundThread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                    handler.post(foregroundThread);
//            }
//        });
//        backgroundThread.start();
//
//    }
//   Runnable foregroundThread = new Runnable() {
//       @Override
//       public void run() {
//           for(int i=0; i< 12;i++) {
//               GridLayout.LayoutParams params = new GridLayout.LayoutParams();
//               DisplayMetrics displayMetrics = new DisplayMetrics();
//               getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//               int getWidth = displayMetrics.widthPixels - 120;
//               params.width = getWidth / 3;
//               params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
//               params.setMargins(15, 15, 15, 15);
//
//               Button button = new Button(MainActivity.this);
//               button.setLayoutParams(params);
//               if(i == 9){
//                   button.setText("*");
//               }
//               else if(i == 10){
//                   button.setText("0");
//               }
//               else if(i == 11){
//                   button.setText("#");
//               }
//               else {
//                   button.setText(String.valueOf(i));
//               }
//               button.setTextSize(22);
//               button.setOnClickListener(new View.OnClickListener() {
//                   @Override
//                   public void onClick(View v) {
//                       EditText edt = binding.edtDrawVaule;
//                        edt.append(button.getText().toString());
//                   }
//               });
//
//
//
//               binding.containerView.addView(button);
//
//           }
//       }
//   };

    public void addEvent(){
        binding.btnDraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.containerView1.removeAllViews();
                excecBackgroundThread();
            }
        });
    }
    public void  excecBackgroundThread(){
        String number = binding.edtDrawVaule.getText().toString();
        int num = Integer.parseInt(number);

        Thread backgroundThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<num;i++){
                    ranNumber = random.nextInt(9);
                    handler.post(foregroundThread);
                    SystemClock.sleep(300);
                }
            }
        });
        backgroundThread.start();

    }
    int k= 0;
    Runnable foregroundThread = new Runnable() {

        @Override
        public void run() {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(15,15,15,15);

            Button button = new Button(MainActivity.this);
            button.setText(String.valueOf(ranNumber));
            button.setLayoutParams(params);

            EditText edt = new EditText(MainActivity.this);
            edt.setText(String.valueOf(ranNumber));
            edt.setLayoutParams(params);

            if (k % 2 == 0) {
                binding.containerView1.addView(button);
            }
            else{
                binding.containerView1.addView(edt);
            }
            k++;

        }
    };



}