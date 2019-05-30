package hr.tomljanovic.matko.seminarskirad;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import butterknife.BindView;
import butterknife.ButterKnife;

import static hr.tomljanovic.matko.seminarskirad.MainActivity.URL_SENT;

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
