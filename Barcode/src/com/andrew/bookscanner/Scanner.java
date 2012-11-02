package com.andrew.bookscanner;
import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.webkit.WebView;
import android.view.Window;
import android.webkit.WebViewClient;
import android.webkit.WebChromeClient;
import android.net.Uri;
import java.net.URLEncoder;
import android.content.Intent;
import com.andrew.ISBNLookup.ISBNLookup;

public class Scanner extends Activity {

	private WebView webview;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		webview = new WebView(this);
		webview.getSettings().setJavaScriptEnabled(true);
		scannerJSI jsi = new scannerJSI(this);
		webview.addJavascriptInterface(jsi, "Android");
		webview.setWebChromeClient(new WebChromeClient());
		setContentView(webview);
		webview.setWebViewClient(new WebViewClient() {   
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				if (Uri.parse(url).getHost().equals("scanner.55word.com")) {
					return false;
				} else {
					Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
					startActivity(intent);
					return true;
				}
			}
		});
		webview.loadUrl("http://scanner.55word.com/bs/");
		
	}
	public void scanSomething() {
		// I need things done!  Do I have any volunteers?
		Intent intent = new Intent("com.google.zxing.client.android.SCAN");

		// This flag clears the called app from the activity stack, so users arrive in the expected
		// place next time this application is restarted.
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

		intent.putExtra("SCAN_MODE", "PRODUCT_MODE");
		startActivityForResult(intent, 0);
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if (requestCode == 0) {
			if (resultCode == RESULT_OK) {
				//  The Intents Fairy has delivered us some data!
				final String contents = intent.getStringExtra("SCAN_RESULT");
				String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
				new Thread ( new Runnable () { public void run () { 
					lookUp(contents);
				}}).start();
				
				// Handle successful scan
			} else if (resultCode == RESULT_CANCELED) {
				// Handle cancel
			}
		}
	}
	
	public void lookUp(String contents) {
        try {
            String res = ISBNLookup.getBookDetails(contents);
            webview.loadUrl("javascript:returnData("+res.replace("'", "\\'")+",)");
        } catch (Exception ex) {
            webview.loadUrl("javascript:throwError('"+ex.toString()+"')");
        }	
	}

}