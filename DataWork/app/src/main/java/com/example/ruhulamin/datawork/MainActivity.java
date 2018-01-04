package com.example.ruhulamin.datawork;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editTextName;
    Button buttonOk;
    Spinner spinnerId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = (EditText) findViewById(R.id.editTextName);
        buttonOk = (Button) findViewById(R.id.buttonOk);
        spinnerId=(Spinner) findViewById(R.id.spinnerId);

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addBlood();

            }
        });

    }
    private void addBlood(){
        String name = editTextName.getText().toString().trim();
        String blood = spinnerId.getSelectedItem().toString();

        if(!TextUtils.isEmpty(name)){

        }
        else{
            Toast.makeText(this,"Enter the NAme",Toast.LENGTH_LONG).show();

        }
    }
}
