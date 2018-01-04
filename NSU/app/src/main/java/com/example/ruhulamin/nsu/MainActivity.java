package com.example.ruhulamin.nsu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button website = (Button)findViewById(R.id.website);
        Button rds = (Button)findViewById(R.id.rds);
        Button erm = (Button)findViewById(R.id.erm);

        website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int1 = new Intent(MainActivity.this,Website.class);
                startActivity(int1);
            }


        });

        rds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int2 = new Intent(MainActivity.this,Rds.class);
                startActivity(int2);
            }


        });
        erm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int3 = new Intent(MainActivity.this,Erm.class);
                startActivity(int3);
            }


        });





    }
}
