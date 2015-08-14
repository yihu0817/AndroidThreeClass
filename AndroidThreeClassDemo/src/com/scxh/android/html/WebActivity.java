package com.scxh.android.html;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.scxh.android.R;
import com.scxh.android.service.PlayerNotificationService;
import com.scxh.android.ui.UIGridViewHttpAct;

public class WebActivity extends Activity {
	private WebView mWebView;
	private String fileUrl = "file:///android_asset/test1.html";
	private String httpUrl = "http://www.baidu.com";
	private String httpUrl1 = "http://192.168.1.203/html/index.html";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_webview_layout);
		mWebView = (WebView) findViewById(R.id.webview1);

		mWebView.loadUrl(httpUrl1);

		// 第一步 WebView支持js脚本
		WebSettings webSetting = mWebView.getSettings();
		webSetting.setJavaScriptEnabled(true);

		// 第三步添加js接口
		mWebView.addJavascriptInterface(
				new StartMusicServiceJavaScriptInterface(),"musicServiceInterfaceName");

	}

	/**
	 * 第二步定义交互类与方法 <!-- Calls into the javascript interface for the activity -->
	 * <!--从HTML文件中调用activity中的函数 -->
	 * <!--也就是从HTML到android程序留意window.musicServiceInterfaceName.playMusic()这句话
	 * --> <a onClick="window.musicServiceInterfaceName.playMusic()">Click</a>
	 */
	final class StartMusicServiceJavaScriptInterface {

		StartMusicServiceJavaScriptInterface() {
		}

		public void playMusic() {
			Intent intent = new Intent(WebActivity.this,
					PlayerNotificationService.class);
			startService(intent);
		}

		public void startGridViewHttp() {
			startActivity(new Intent(WebActivity.this, UIGridViewHttpAct.class));
		}
	}
}
