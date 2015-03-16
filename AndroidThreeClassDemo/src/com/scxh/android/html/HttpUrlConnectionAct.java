package com.scxh.android.html;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.scxh.android.R;
import com.scxh.android.util.Constances;
import com.scxh.android.util.HttpConnectUtil;
import com.scxh.android.util.HttpConnectUtil.HttpConnectInterface;
import com.scxh.android.util.HttpConnectUtil.HttpMethod;
import com.scxh.android.util.Logs;

public class HttpUrlConnectionAct extends Activity {
	private String httpUrls = Constances.BASE_URL+"/servlet/firstservlet";
	// private String httpUrls = "http://www.baidu.com";
	private TextView mShowMessageOneTxt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_httpurlconnnection_layout);
		mShowMessageOneTxt = (TextView) findViewById(R.id.showMessageTxtOne);
	}

	public void onConfirmHttpUrlConnectionClick(View v) {
		new AsyncTask<String, Void, String>() {

			@Override
			protected String doInBackground(String... params) {
				String httpurl = params[0];
				String content = null;
				try {
					content = getDataByHttpClientGetMethod(httpurl);
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return content;
			}

			protected void onPostExecute(String result) {
				mShowMessageOneTxt.setText(result);
			};

		}.execute(httpUrls);
	}

	public void onConfirmHttpClientClick(View v) {
		new AsyncTask<String, Void, String>() {

			@Override
			protected String doInBackground(String... params) {
				String httpurl = params[0];
				String content = null;
				try {
					content = getDataByHttpClientPostMethod(httpurl);
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

				return content;
			}

			protected void onPostExecute(String result) {
				mShowMessageOneTxt.setText(result);
			};

		}.execute(httpUrls);
	}

	
	public void onConfirmAndroidHttpClientGetClick(View v){
		HttpConnectInterface httpconnectIntentface = new HttpConnectInterface(){
			@Override
			public void execute(String result) {
				mShowMessageOneTxt.setText(result);
			}
		};
		
		HashMap<String, String> parameters = new HashMap<String,String>();
		parameters.put("username", "李四");
		parameters.put("password", "abcd");
		parameters.put("sex", "男");
		
		JSONObject jsonObject = new JSONObject(parameters);
		String jsonStr = jsonObject.toString();//{username:"李四",password:"abcd",sex:"男"}
		
		HashMap<String, String> parameterss = new HashMap<String,String>();
		parameterss.put("json", jsonStr);
		
		HttpConnectUtil httpConnectUtil = new HttpConnectUtil();
		httpConnectUtil.asyncConnect(httpUrls, HttpMethod.POST, parameterss);
		httpConnectUtil.setmHttpConnectInterface(httpconnectIntentface);
	}
	
	/**
	 * 从网络获取数据通过HttpURLConnection方式实现 Get
	 * 
	 * @param url1
	 * @return
	 */
	private String getDataByHttpUrlConnection(String url1) {

		StringBuffer sb = null;

		InputStream is = null;
		BufferedReader br = null;
		HttpURLConnection conn = null;

		OutputStream os = null;
		BufferedWriter bw = null;
		try {
			// 封装访问服务器的地址
			URL url = new URL(url1);
			// 打开对服务器的连接
			conn = (HttpURLConnection) url.openConnection();
			// 设置输入输出流
			// conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod("GET");
			// 连接服务器
			conn.connect();

			/** 读入服务器数据的过程 **/
			// 得到输入流
			Logs.v("3333333333  getResponseCode  :" + conn.getResponseCode());
			is = conn.getInputStream();
			// 创建包装流
			br = new BufferedReader(new InputStreamReader(is));
			// 定义String类型用于储存单行数据
			String line = null;
			// 创建StringBuffer对象用于存储所有数据
			sb = new StringBuffer();
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.disconnect();
				if (bw != null) {
					bw.close();
				}
				if (os != null) {
					os.close();
				}
				if (br != null) {
					br.close();
				}
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

	/**
	 * 从网络获取数据通过HttpClient方式实现 Get
	 * 
	 * @param url1
	 * @return
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	private String getDataByHttpClientGetMethod(String httpUrl)
			throws ClientProtocolException, IOException {
		String httpurl = httpUrl;// "http://192.168.1.148:8080/ServletProject/firstservlet";
		String params = "?username=张三&password=123456";
		String url = httpurl + params;// "http://192.168.1.148:8080/ServletProject/firstservlet?username=scxh&password=123456";

		StringBuilder sb = new StringBuilder();

		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);

		HttpResponse httpResponse = httpClient.execute(httpGet);

		InputStream is = httpResponse.getEntity().getContent();

		int statusCode = httpResponse.getStatusLine().getStatusCode();

		if (statusCode == 200) {
			// 创建包装流
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			// 定义String类型用于储存单行数据
			String line = null;
			// 创建StringBuffer对象用于存储所有数据
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		}

		return sb.toString();
	}

	private String getDataByHttpClientPostMethod(String httpUrl)
			throws ClientProtocolException, IOException {

		StringBuilder sb = new StringBuilder(); // 接收服务端响应返回的数据

		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(httpUrl);

//		BasicNameValuePair userNamepair = new BasicNameValuePair("username",URLEncoder.encode("张三", HTTP.UTF_8));
		BasicNameValuePair userNamepair = new BasicNameValuePair("username","张三");
		BasicNameValuePair passWordPair = new BasicNameValuePair("password","123456");
		BasicNameValuePair sexPair = new BasicNameValuePair("sex", "男");
//		BasicNameValuePair sexPair = new BasicNameValuePair("sex", URLEncoder.encode("男",HTTP.UTF_8));
		
		ArrayList<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();
		parameters.add(userNamepair);
		parameters.add(passWordPair);
		parameters.add(sexPair);
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(parameters,HTTP.UTF_8);
		httpPost.setEntity(entity);

		HttpResponse httpResponse = httpClient.execute(httpPost);

		InputStream is = httpResponse.getEntity().getContent();

		int statusCode = httpResponse.getStatusLine().getStatusCode();

		if (statusCode == 200) {
			// 创建包装流
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			// 定义String类型用于储存单行数据
			String line = null;
			// 创建StringBuffer对象用于存储所有数据
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		}

		return sb.toString();
	}
}
