package com.example.ruhulamin.buttonevent;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button ruhulsButton = (Button)findViewById(R.id.ruhulsButton);

        ruhulsButton.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        TextView ruhulsText = (TextView)findViewById(R.id.ruhulsText);
                        ruhulsText.setText("Workd Done Boss");
                        setContentView(R.layout.activity_secondpage);
                    }
                }
        );
        ruhulsButton.setOnLongClickListener(
                new Button.OnLongClickListener(){
                    public boolean onLongClick(View v){
                        TextView ruhulsText = (TextView)findViewById(R.id.ruhulsText);
                        ruhulsText.setText("It is Long man!!#@");
                        setContentView(R.layout.activity_secondpage);
                        return true;
                    }
                }
        );
    }

}
