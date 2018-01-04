package com.example.ruhulamin.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String Tag = "Ruhul'sMessage";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(Tag,"onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(Tag,"onStart");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.i(Tag, "onResume");
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.i(Tag, "onPause");
    }


    @Override
    protected void onStop() {
        super.onStop();
        Log.i(Tag, "onStop");
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(Tag, "onRestart");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(Tag, "onDestroy");
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(Tag, "onSaveInstanceState");
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(Tag, "onRestoreInstanceState");
    }
}
