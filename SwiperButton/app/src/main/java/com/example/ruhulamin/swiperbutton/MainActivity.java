package com.example.ruhulamin.swiperbutton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.view.MotionEvent;
import android.view.GestureDetector;
import android.support.v4.view.GestureDetectorCompat;
import android.widget.Button;



public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener{

    private TextView ruhulsMessage;
    private GestureDetectorCompat gestureDetector;
    private Button ruhulsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ruhulsMessage = (TextView)findViewById(R.id.ruhulsText);
        ruhulsButton =(Button)findViewById(R.id.ruhulsButton);
        this.gestureDetector= new GestureDetectorCompat(this,this);



        ruhulsButton.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        TextView ruhulsText = (TextView)findViewById(R.id.ruhulsText);
                        ruhulsText.setText("I got Tapped");

                    }
                }
        );



    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        ruhulsMessage.setText("Swiping Boss");


        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {

        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gestureDetector.onTouchEvent(event);

        return super.onTouchEvent(event);
    }

    @Override
    public void onBackPressed() {
        ruhulsMessage.setText("No Exit!!");
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {

        ruhulsMessage.setText("No Exit!!");

        super.onDestroy();

    }
}
