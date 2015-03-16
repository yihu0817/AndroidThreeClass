package com.scxh.android.json;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.test.AndroidTestCase;

import com.scxh.android.util.HttpConnectUtil;
import com.scxh.android.util.HttpConnectUtil.HttpConnectInterface;
import com.scxh.android.util.HttpConnectUtil.HttpMethod;
import com.scxh.android.util.Logs;

public class JSONTest extends AndroidTestCase {
	public void _test_Json(){
		HashMap<String, String> parameters = new HashMap<String,String>();
		parameters.put("username", "李四");
		parameters.put("password", "abcd");
		parameters.put("sex", "男");
		
		JSONObject jsonObject = new JSONObject(parameters);
		String jsonStr = jsonObject.toString(); //{username:"李四",password:"abcd",sex:"男"}
		
		Logs.v("jsonStr :"+jsonStr);
		
	}
	
	public void _test_myjson() throws JSONException{
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("username", "李四");
		jsonObject.put("password", "1234556");
		jsonObject.put("sex", "男");
		
		Logs.v("jsonStr :"+jsonObject.toString());
	}
	
	public void test_myjsonarray() throws JSONException{
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("username", "李四");
		jsonObject.put("password", "1234556");
		jsonObject.put("sex", "男");
		
		JSONObject jsonObject1 = new JSONObject();
		jsonObject1.put("username", "张三");
		
		JSONArray jsonArray = new JSONArray();
		jsonArray.put(0, jsonObject);
		jsonArray.put(1, jsonObject1);
		
		Logs.v("jsonStr :"+jsonArray.toString());
		
	}
	
	//如何生成JsonObject
	//生成JSONArray
	
	//解析JsonObject
	
	/**
	 * 测试HttpConnectUtil网络工具类接收请求响应数据是否正常
	 */
	public void _test_AndroidHttpClientGetClick(){
		HttpConnectUtil httpConnectUtil = new HttpConnectUtil();
		HttpConnectInterface httpconnectIntentface = new HttpConnectInterface(){
			@Override
			public void execute(String result) {  //接收处理服务端响应返回数据
				Logs.v(result);
			}
		};
		
		httpConnectUtil.setmHttpConnectInterface(httpconnectIntentface);
		
		String httpUrls = "http://192.168.1.148:8080/ServletProject/firstservlet";
		HashMap<String, String> parameters = new HashMap<String,String>();
		parameters.put("username", "李四");
		parameters.put("password", "abcd");
		parameters.put("sex", "男");
		
		//向服务端发起请求
		httpConnectUtil.asyncConnect(httpUrls, HttpMethod.POST, parameters);
	}
}
