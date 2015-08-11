/**
 * 
 */
package com.aperfectpc.nuera_id.barcode.reader;

import com.aperfectpc.nuera_id.barcode.reader.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

/**
 * @author mrpfisher
 * @author wyoung
 */
public class DisplayActivity extends Activity
{
  WebView mWebView;

  // private Handler handler = new Handler();

  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.webview_activity);

    // final String mimeType = "text/html";

    mWebView = (WebView) findViewById(R.id.webview_1);

    // WebSettings settings = mWebView.getSettings();
    // settings.setDefaultZoom(zoom);

    // settings.setJavaScriptEnabled(true);
    //
    // webView.setWebChromeClient(new WebChromeClient() {
    // public boolean onJSAlert(WebView view, String url, String message,
    // JsResult result) {
    // return true;
    // }
    // });
    //
    // //webView.addJavascriptInterface(new JSInterface(), "jsinterface");
    // webView.addJavascriptInterface(new WebAppInterface(this), "Android");
    //
    // //webView.loadData("<a href='x'>Hello World! - 1</a>", mimeType, null);

    mWebView.setWebViewClient(new NuEraWebClient());

    // String message = "No url in bundle";
    // Intent i = getIntent();
    // String url = "";
    // if(i != null) {
    // url = i.getStringExtra("url");
    // if(url != null) {
    // message = "Url: " + url;
    // }
    // } else {
    // message = "Null bundle";
    // }
    // Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    // mWebView.loadUrl(getIntent().getStringExtra("url"));
    mWebView.loadUrl(UrlUtils.cleanUrl(getIntent().getDataString()));
  }

  public class NuEraWebClient extends WebViewClient
  {
    private ProgressDialog progressDialog = null;

    public NuEraWebClient()
    {
      progressDialog = new ProgressDialog(DisplayActivity.this);
      progressDialog.setTitle(R.string.please_wait);
      progressDialog.setMessage(getString(R.string.loading_message));
      progressDialog.show();
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url)
    {
      view.loadUrl(url);
      return true;
    }

    @Override
    public void onPageFinished(WebView view, String url)
    {
      if(progressDialog.isShowing())
      {
        progressDialog.dismiss();
      }
    }

    @Override
    public void onReceivedError(WebView view, int errorCode,
        String description, String failingUrl)
    {
      Toast.makeText(DisplayActivity.this, description, Toast.LENGTH_SHORT)
          .show();
    }
  }

  // //@JavasciptInterface
  // public class WebAppInterface {
  // Context context;
  //
  // WebAppInterface(Context c) {
  // this.context = c;
  // }
  //
  // // @JavascriptInterface // for target_sdk >= 17
  // public void showToast(String toast) {
  // Toast.makeText(context, toast, Toast.LENGTH_SHORT).show();
  // }
  //
  // // This is not called on UI Thread -
  // // Need to post a runnable to call loadUrl on UI thread.
  // public void specialClick() {
  // WebViewDisplayActivity.this.runOnUiThread(new Runnable() {
  // public void run() {
  // webView.loadUrl("javascript:alert('Special Click was Called');");
  // }
  // });
  // // handler.post(new Runnable() {
  // // public void run() {
  // // webView.loadUrl("javascript:alert('Special Click was Called');");
  // // }
  // // });
  // }
  // }

  @Override
  public void onPause()
  {
    super.onPause();
  }
}
