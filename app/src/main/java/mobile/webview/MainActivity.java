package mobile.webview;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {
    private WebView webView;
    private class SimpleWebViewClient extends WebViewClient {
        private Activity activity = null;

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            if (request.getUrl().toString().contains(getString(R.string.url))) {
                return false;
            }
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(request.getUrl().toString()));
            activity.startActivity(intent);
            return true;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = findViewById(R.id.webWindow);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        //Запрет на масштабирование страницы встроенными и дополнительными элементами управления
        webView.getSettings().setBuiltInZoomControls(false);
        webSettings.setDisplayZoomControls(false);
        //Загрузка видимой области страницы по умолчанию (как в десктопном браузере
        webSettings.setUseWideViewPort(true);
        //Загрузка веб-страницы без полос прокрутки, т.е. с полностью видимым содержимым
        webSettings.setLoadWithOverviewMode(true);

        SimpleWebViewClient webClient = new SimpleWebViewClient();
        webView.setWebViewClient(webClient);
        webView.loadUrl(getString(R.string.url));
    }
}