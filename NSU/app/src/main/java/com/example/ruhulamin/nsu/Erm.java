package com.example.ruhulamin.nsu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Erm extends AppCompatActivity {
    WebView wv3;

    @Override

    public void onBackPressed() {

        if (wv3.canGoBack()) {
            wv3.goBack();
        } else {
            super.onBackPressed();
        }
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_erm);

        wv3= (WebView)findViewById(R.id.wv3);
        wv3.getSettings().setJavaScriptEnabled(true);
        wv3.setFocusable(true);
        wv3.setFocusableInTouchMode(true);
        //set render priority to high
        wv3.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        wv3.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        wv3.getSettings().setDomStorageEnabled(true);
        wv3.getSettings().setDatabaseEnabled(true);
        wv3.getSettings().setAppCacheEnabled(true);
        wv3.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        //Load URL

        wv3.loadUrl("http://erm.northsouth.edu");
        wv3.setWebViewClient(new WebViewClient());
    }
}
