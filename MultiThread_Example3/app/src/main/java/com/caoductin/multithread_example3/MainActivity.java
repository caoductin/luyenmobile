package com.caoductin.multithread_example3;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.caoductin.multithread_example3.databinding.ActivityMainBinding;

import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ExecutorService excecutorService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        excecutorService = Executors.newSingleThreadExecutor();
        addEvent();
    };
    private void addEvent(){
        binding.btnDraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.containerLayout.removeAllViews();
                executeLongRunningTask();
            }
        });
    }
    private void executeLongRunningTask(){
        excecutorService.execute(new Runnable() {
            @Override
            public void run() {
                int numOfView = Integer.parseInt(binding.edtNumofView.getText().toString());
                Random random = new Random();
                for(int i =0 ; i<numOfView;i++){
                    final int percent = i*100/numOfView;
                    int randNum = random.nextInt(100);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            binding.txtPercent.setText(percent + "%");
                            binding.pbPercent.setProgress(percent);
                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(200, ViewGroup.LayoutParams.WRAP_CONTENT);
                            params.setMargins(10,10,10,10);
                            Button button = new Button(MainActivity.this);

                            button.setText(String.valueOf(randNum));
                            button.setTextSize(22);
                            button.setTextColor(Color.WHITE);
                            if(randNum%2 == 0){
                                button.setBackgroundColor(Color.rgb(0,150,136));
                                params.gravity = Gravity.END;

                            }
                            else{

                            }
                        }
                    });
                }
            }
        });
    }



}