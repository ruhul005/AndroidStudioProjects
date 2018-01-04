package com.example.ruhulamin.nsu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Rds extends AppCompatActivity {

    WebView wv2;

    @Override

    public void onBackPressed(){

        if(wv2.canGoBack()){
            wv2.goBack();
        }
        else {
            super.onBackPressed();
        }
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rds);

        wv2= (WebView)findViewById(R.id.wv2);
        wv2.getSettings().setJavaScriptEnabled(true);
        wv2.setFocusable(true);
        wv2.setFocusableInTouchMode(true);
        //set render priority to high
        wv2.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        wv2.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        wv2.getSettings().setDomStorageEnabled(true);
        wv2.getSettings().setDatabaseEnabled(true);
        wv2.getSettings().setAppCacheEnabled(true);
        wv2.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        //Load URL

        wv2.loadUrl("https://rds3.northsouth.edu/index.php/welcome/enter_login");
        wv2.setWebViewClient(new WebViewClient());
    }
}
