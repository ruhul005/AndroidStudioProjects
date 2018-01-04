package com.example.ruhulamin.buttonevent;
import android.view.View;
import android.widget.Button;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;

public class SecondPage extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondpage);
        Button ruhulsButton=(Button)findViewById(R.id.ruhulsButton);

        ruhulsButton.setOnClickListener(
                new Button.OnClickListener(){
                  public void onClick(View v){
                      TextView ruhulsText = (TextView)findViewById(R.id.ruhulsText);
                      ruhulsText.setText("Workd Done Boss");
                      setContentView(R.layout.activity_main);
                      



                  }


                }
        );

    }
}
