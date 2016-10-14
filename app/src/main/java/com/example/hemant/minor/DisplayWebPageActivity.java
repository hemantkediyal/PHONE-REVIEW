package com.example.hemant.minor;

/**
 * Created by Hemant on 10/12/2016.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class DisplayWebPageActivity extends Activity {

    WebView webview;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);

        Intent in = getIntent();
        String page_url = in.getStringExtra("page_url");

        webview = (WebView) findViewById(R.id.webpage);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl(page_url);

        webview.setWebViewClient(new DisplayWebPageActivityClient());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webview.canGoBack()) {
            webview.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private class DisplayWebPageActivityClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    public void next(View view)
    {
        Intent intent=new Intent(this,LoginActivity.class);
        startActivity(intent);
    }
    public void addsite(View view)
    {
        Intent intent=new Intent(this,AddNewSiteActivity.class);
        startActivity(intent);
    }
    public void website(View view)
    {
        Intent intent=new Intent(this,ListRSSItemsActivity.class);
        startActivity(intent);
    }
}
