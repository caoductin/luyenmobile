package com.caoductin.multiple_threadde2016_05;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.caoductin.multiple_threadde2016_05.databinding.ActivityMainBinding;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    int ranNumber;
    Handler handler = new Handler();
    Random random = new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        addEvent();
    }
    public void addEvent(){
        binding.btnDraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.containerLayout.removeAllViews();
                    execBackgroundThread();
            }
        });
    }
    private void execBackgroundThread(){
//      int numberOfView = Integer.parseInt(binding.btnDraw.getText().toString());
        String buttonText = binding.drawValue.getText().toString();
        int numberOfView;
        try {
            numberOfView = Integer.parseInt(buttonText);
        } catch (NumberFormatException e) {
            Toast.makeText(MainActivity.this, "Invalid number", Toast.LENGTH_SHORT).show();
            return;
        }
        Thread backgroundThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i= 0;i<numberOfView;i++){
                     ranNumber = random.nextInt(9);
                    handler.post(foregroundThread);
                    SystemClock.sleep(300);
                }
            }

        });
        backgroundThread.start();
    }
//    Runnable foregroundThread = new Runnable() {
//        int count = 0;
//        @Override
//        public void run() {
//            //GripLayout
//            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
//            if((count +1) %4  == 0 || count %4 == 0 ){
//                params.width = 750;
//            }
//            else{
//                params.width = 250;
//            }
//            params.height = GridLayout.LayoutParams.WRAP_CONTENT;
//
//
//            params.setMargins(15, 15, 15, 15);
//     // New row every two buttons
//
//            Button button = new Button(MainActivity.this);
//            button.setLayoutParams(params);
//            button.setText(String.valueOf(ranNumber));
//            button.setTextSize(22);
//
//            if (ranNumber % 2 == 0) {
//                button.setBackgroundColor(Color.rgb(0, 150, 136));
//            } else {
//                button.setBackgroundColor(Color.rgb(224, 67, 54));
//            }
//            binding.containerLayout.addView(button);
//
//            count++;
//
//        }
//    };

//    Runnable foregroundThread = new Runnable() {
//        int count = 0;
//        @Override
//        public void run() {
//              //  ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(200,ViewGroup.LayoutParams.WRAP_CONTENT);
//                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(450, ViewGroup.LayoutParams.WRAP_CONTENT);
//                params.setMargins(15,15,15,15);
//
//                Button button = new Button(MainActivity.this);
//
//                button.setLayoutParams(params);
//                button.setText(String.valueOf(ranNumber));
//                button.setTextSize(22);
//
//    //            int gravity = count % 2 == 0 ? Gravity.RIGHT : Gravity.LEFT;
//    //            params.gravity = gravity;
//
//                if (ranNumber % 2 == 0){
//                    button.setBackgroundColor(Color.rgb(0,150,136));
//                }else{
//                    button.setBackgroundColor(Color.rgb(224,67,54));
//                }
//                binding.containerLayout.addView(button);
//        }
//    };
int k = 0;
//Runnable foregroundThread = new Runnable() {
//
//    @Override
//    public void run() {
//            // Create a new horizontal LinearLayout
//            LinearLayout horizontalLayout = new LinearLayout(MainActivity.this);
//            horizontalLayout.setOrientation(LinearLayout.HORIZONTAL);
//            LinearLayout.LayoutParams horizontalParams = new LinearLayout.LayoutParams(
//                    ViewGroup.LayoutParams.MATCH_PARENT,
//                    ViewGroup.LayoutParams.WRAP_CONTENT
//            );
//            horizontalLayout.setLayoutParams(horizontalParams);
//
//            // LayoutParams for buttons
//            LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(
//                    750,
//                    ViewGroup.LayoutParams.WRAP_CONTENT// Weight to distribute evenly in the row
//            );
//            buttonParams.setMargins(15, 15, 15, 15);
//            LinearLayout.LayoutParams buttonParams1 = new LinearLayout.LayoutParams(
//                    250,
//                    ViewGroup.LayoutParams.WRAP_CONTENT// Weight to distribute evenly in the row
//            );
//            buttonParams1.setMargins(15, 15, 15, 15);
//
//            // Create two buttons for each row
//            for (int j = 0; j < 2; j++) {
//                int ranNumber = (int) (Math.random() * 9); // Generate random number for demonstration
//                Button button = new Button(MainActivity.this);
//                if (k %2 ==0) {
//                    button.setLayoutParams(buttonParams);
//                }
//                else{
//                    button.setLayoutParams(buttonParams1);
//                }
//                button.setText(String.valueOf(ranNumber));
//                button.setTextSize(22);
//
//                // Set background color based on the random number
//                if (ranNumber % 2 == 0) {
//                    button.setBackgroundColor(Color.rgb(0, 150, 136));
//                } else {
//                    button.setBackgroundColor(Color.rgb(224, 67, 54));
//                }
//
//                // Add the button to the horizontal layout
//                horizontalLayout.addView(button);
//                k = k+1;
//            }
//            k= k-1;
//
//            // Add the horizontal layout to the parent container layout
//            binding.containerLayout.addView(horizontalLayout);
//
//    }
//};
    Runnable foregroundThread = new Runnable() {
    @Override
    public void run() {
        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenWidth = displayMetrics.widthPixels - 150;
        params.width = screenWidth/3;
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        params.setMargins(15,15,15,15);


        Button button = new Button(MainActivity.this);
        button.setLayoutParams(params);
        button.setText(String.valueOf(ranNumber));
        button.setTextSize(22);
        if(ranNumber %2 == 0){
            button.setBackgroundColor(Color.rgb(49,56,60));
        }
        else{
            button.setBackgroundColor(Color.rgb(4,23,43));
        }


        binding.containerLayout.addView(button);
    }
};

}