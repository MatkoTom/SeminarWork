package hr.tomljanovic.matko.seminarskirad.view.webActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.tomljanovic.matko.seminarskirad.R;

import static hr.tomljanovic.matko.seminarskirad.view.newsFeed.MainActivity.URL_SENT;

public class WebViewActivity extends AppCompatActivity {

    @BindView(R.id.webview)
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        ButterKnife.bind(this);

        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);

        Intent i = getIntent();
        webView.loadUrl(i.getStringExtra(URL_SENT));
    }
}
