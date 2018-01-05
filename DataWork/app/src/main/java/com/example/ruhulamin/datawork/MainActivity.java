package com.example.ruhulamin.datawork;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    //variables of input fields and button
    EditText editTextName;
    Button buttonOk;
    Spinner spinnerId;

    DatabaseReference databaseDonor; //Database Declaration




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseDonor= FirebaseDatabase.getInstance().getReference("donors");


        editTextName = (EditText) findViewById(R.id.editTextName);
        buttonOk = (Button) findViewById(R.id.buttonOk);
        spinnerId=(Spinner) findViewById(R.id.spinnerId);

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addBlood(); // method of info

            }
        });

    }
    private void addBlood(){
        String name = editTextName.getText().toString().trim();
        String blood = spinnerId.getSelectedItem().toString();

        if(!TextUtils.isEmpty(name)){  //Check weather empty or not if not it will push info to db
            String id= databaseDonor.push().getKey();
            Donor donor= new Donor(id,name,blood);
            databaseDonor.child(id).setValue(donor);
            Toast.makeText(this,"Entry Added",Toast.LENGTH_LONG).show();

        }
        else{
            Toast.makeText(this,"Enter the NAme",Toast.LENGTH_LONG).show();

        }
    }
}
