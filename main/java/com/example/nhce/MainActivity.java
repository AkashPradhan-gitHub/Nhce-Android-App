package com.example.nhce;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    WebView mywebview;
    private ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mywebview=(WebView)findViewById(R.id.webView);

        progress=findViewById(R.id.progressBar);
        progress.setMax(100);

        WebSettings settings=mywebview.getSettings();
        settings.setJavaScriptEnabled(true);
        mywebview.loadUrl("http://newhorizonindia.edu/nhengineering/mca/");
        mywebview.getSettings().setBuiltInZoomControls(true);
        mywebview.getSettings().setUseWideViewPort(true);

         /**Progress Bar Load**/
        mywebview.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                progress.setVisibility(View.VISIBLE);
                setTitle("Loading...");
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                progress.setVisibility(View.GONE);
                setTitle(view.getTitle());
                super.onPageFinished(view, url);
            }
        });


    }

    @Override
    public void onBackPressed() {
        if(mywebview.canGoBack())
        {
            mywebview.goBack();
        }
        else
        {
            super.onBackPressed();
        }



    }
    public class myWebClint extends WebViewClient{
        public void onPageStarted(WebView view, String url, Bitmap favicon)
        {
            super.onPageStarted(view,url,favicon);
        }
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url)
        {
            view.loadUrl(url);
            return true;
        }
    }
}
