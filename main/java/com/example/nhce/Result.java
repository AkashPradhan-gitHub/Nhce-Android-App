package com.example.nhce;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

public class Result extends AppCompatActivity {
    WebView RwebView;
    private ProgressBar progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        RwebView=(WebView) findViewById(R.id.webView2);

        progress=findViewById(R.id.progressBar3);
        progress.setMax(100);

        WebSettings settings=RwebView.getSettings();
        settings.setJavaScriptEnabled(true);
        RwebView.loadUrl("http://eresults.newhorizonindia.edu/");
        RwebView.getSettings().setBuiltInZoomControls(true);
        RwebView.getSettings().setUseWideViewPort(true);

        /**Progress Bar Load**/
        RwebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                progress.setVisibility(View.VISIBLE);
                setTitle("Loading Your Result...");
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                progress.setVisibility(View.GONE);
                setTitle(view.getTitle());
                super.onPageFinished(view, url);
            }
        });

        RwebView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                DownloadManager.Request request=new DownloadManager.Request(Uri.parse(url));

                request.allowScanningByMediaScanner();
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

                DownloadManager dm = (DownloadManager)getSystemService(DOWNLOAD_SERVICE);
                dm.enqueue(request);

                //To notify the Client that the file is being downloaded
                Toast.makeText(Result.this, "Downloading File...",Toast.LENGTH_LONG).show();


            }
        });


    }

    @Override
    public void onBackPressed() {
        if(RwebView.canGoBack())
        {
            RwebView.goBack();
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
