package com.example.ruhulamin.login;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button ruhulsButton;
    private String mailText;
    private String passText;
    private TextView ruhulsMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button ruhulsButton =(Button)findViewById(R.id.ruhulsButton);
         final TextView mailText= (TextView)findViewById(R.id.mailText);
         final TextView passText= (TextView)findViewById(R.id.passText);
        TextView ruhulsTex=(TextView)findViewById(R.id.ruhulsText);

        ruhulsButton.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        if(mailText.length()>=5&&passText.length()>=8)
                        {
                            ruhulsMessage.setText("Comming Soon");
                        }
                        else
                            ruhulsMessage.setText("Mail should be >5\nPass Should be >8");

                    }
                }
        );

    }
}
