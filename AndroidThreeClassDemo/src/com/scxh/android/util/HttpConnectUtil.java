package com.scxh.android.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import android.os.AsyncTask;

/**
 * 网络处理工具类
 * 
 * @author Administrator
 * 
 */
public class HttpConnectUtil {
	public static enum HttpMethod {
		GET, POST
	};

	// ---------------回调接口实现 参见接口实现步骤--------------------
	private HttpConnectInterface mHttpConnectInterface;

	public interface HttpConnectInterface {
		public void execute(String result);
	}

	public void setmHttpConnectInterface(
			HttpConnectInterface mHttpConnectInterface) {
		this.mHttpConnectInterface = mHttpConnectInterface;
	}

	// ---------------------------------------------
	public void asyncConnect(final String httpUrl, final HttpMethod httpMethod) {
		asyncConnect(httpUrl, httpMethod, null);
	}

	public void asyncConnectJson(final String httpUrl,final HttpMethod httpMethod) {
		asyncConnectJson(httpUrl, httpMethod, null);
	}

	public void asyncConnect(final String httpUrl, final HttpMethod httpMethod,
			final HashMap<String, String> parameters) {
		Logs.v("httpUrl  :" + httpUrl);

		new AsyncTask<String, Void, String>() {
			@Override
			protected String doInBackground(String... params) {
				String url = params[0];
				return getDataByHttpClient(url, httpMethod, parameters);
			}

			@Override
			protected void onPostExecute(String result) {
				super.onPostExecute(result);
				mHttpConnectInterface.execute(result);
			}
		}.execute(httpUrl);
	}

	public void asyncConnectJson(final String httpUrl,
			final HttpMethod httpMethod,
			final HashMap<String, String> parameters) {
		new AsyncTask<String, Void, String>() {
			@Override
			protected String doInBackground(String... params) {
				String url = params[0];
				return getJsonDataByHttpClient(url, httpMethod, parameters);
			}

			@Override
			protected void onPostExecute(String result) {
				super.onPostExecute(result);
				mHttpConnectInterface.execute(result);
			}
		}.execute(httpUrl);
	}
	/**
	 * 
	 * @param httpUrl
	 *            :http://192.168.1.11/xinhuaApp/login
	 * @param httpMethod
	 * @param parameters
	 *            httpUrl = httpUrl + "?name=xinhua&password=123456";
	 * @return
	 */
	private HttpUriRequest getHttpRequest(String httpUrl,
			final HttpMethod httpMethod,
			final HashMap<String, String> parameters) {
		if (httpMethod.equals(HttpMethod.GET)) {
			if (parameters != null) {
				StringBuilder sb = new StringBuilder("?");
				for (String name : parameters.keySet()) { // 循环遍历HashMap取出参数组装成字符串
					String key = name;
					String value = parameters.get(key);

					sb.append(key);
					sb.append("=");
					try {
						sb.append(URLEncoder.encode(value, HTTP.UTF_8));
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}

					sb.append("&");
				}

				sb.substring(0, sb.length() - 1);

				httpUrl = httpUrl + sb.toString();
			}
			HttpGet httpGet = new HttpGet(httpUrl);

			return httpGet;
		} else {
			HttpPost httpPost = new HttpPost(httpUrl);

			if (parameters != null) {
				List<BasicNameValuePair> listParams = new ArrayList<BasicNameValuePair>();

				for (String name : parameters.keySet()) {
					String key = name;
					String value = null;
					value = parameters.get(key);
					listParams.add(new BasicNameValuePair(key, value));
				}

				// 用UrlEncodedFormEntity来封装List对象
				UrlEncodedFormEntity urlEntity;
				try {
					urlEntity = new UrlEncodedFormEntity(listParams, HTTP.UTF_8);
					// 设置使用的Entity
					httpPost.setEntity(urlEntity);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			return httpPost;

		}
	}

	/**
	 * 从网络获取数据通过HttpClient方式实现 get方式
	 * 
	 * @param url
	 * @return
	 */
	private String getDataByHttpClient(String httpUrl,
			final HttpMethod httpMethod,
			final HashMap<String, String> parameters) {
		BufferedReader br = null;
		InputStream is = null;
		StringBuilder sb = new StringBuilder();

		HttpClient httpClient = new DefaultHttpClient();

		HttpUriRequest httpUriRequest = getHttpRequest(httpUrl, httpMethod,
				parameters);

		HttpResponse httpResponse;
		try {
			httpResponse = httpClient.execute(httpUriRequest);

			HttpEntity httpEntity = httpResponse.getEntity();
			is = httpEntity.getContent();

			int statusCode = httpResponse.getStatusLine().getStatusCode();
			Logs.v("getDataByHttpClient  statusCode  :" + statusCode);
			if (statusCode == HttpURLConnection.HTTP_OK) {
				// 创建包装流
				br = new BufferedReader(new InputStreamReader(is));
				// 定义String类型用于储存单行数据
				String line = null;
				// 创建StringBuffer对象用于存储所有数据
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}

				Logs.v("getDataByHttpClient  sb.toString():  " + sb.toString());
			}

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return sb.toString();
	}

	private String getJsonDataByHttpClient(String httpUrl,
			final HttpMethod httpMethod,
			final HashMap<String, String> parameters) {
		BufferedReader br = null;
		InputStream is = null;
		StringBuilder sb = new StringBuilder();

		HttpClient httpClient = new DefaultHttpClient();

		HttpUriRequest httpUriRequest = getHttpRequest(httpUrl, httpMethod,
				parameters);

		HttpResponse httpResponse;
		try {
			httpResponse = httpClient.execute(httpUriRequest);

			HttpEntity httpEntity = httpResponse.getEntity();
			is = httpEntity.getContent();

			return readStream(is);

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return sb.toString();
	}

	public String readStream(InputStream is) {

		try {
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			int i = is.read();
			while (i != -1) {
				bo.write(i);
				i = is.read();
			}
			Logs.v("readStream :" + bo.toString());
			return bo.toString();
		} catch (IOException e) {
			return "";
		}
	}
}
