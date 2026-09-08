package com.example.myapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

/** אפליקציה עצמאית לחלוטין: כל התוכן נמצא בתוך ה-APK, בלי אינטרנט ובלי שירותי גוגל. */
public class MainActivity extends AppCompatActivity {
    private WebView webView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        webView = new WebView(this);
        setContentView(webView);

        WebSettings s = webView.getSettings();
        s.setJavaScriptEnabled(true);
        s.setDomStorageEnabled(true);
        s.setAllowFileAccess(true);
        s.setBlockNetworkLoads(true);
        s.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("file:///android_asset/www/index.html");

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override public void handleOnBackPressed() {
                if (webView.canGoBack()) webView.goBack();
                else finish();
            }
        });
    }
}
