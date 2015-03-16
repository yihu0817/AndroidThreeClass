package com.scxh.android.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.test.AndroidTestCase;

import com.alibaba.fastjson.JSON;
import com.scxh.android.json.bean.CityBean;
import com.scxh.android.json.bean.CityMessage;
import com.scxh.android.util.Logs;
import com.scxh.android.util.ReadAssetsFile;

public class JsonUnit extends AndroidTestCase {
	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}

	public void _testMapToJsonObject() throws JSONException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "zhangsan");
		map.put("age", 24);
		map.put("sex", "女");
		JSONObject jsons = new JSONObject(map);

		Logs.v(jsons.toString()); // {"sex":"女","name":"zhangsan","age":24}

		JSONObject jsonObject = new JSONObject(jsons.toString());
		String name = jsonObject.getString("name");
		Logs.v("name :" + name);
		int age = jsonObject.getInt("age");
		Logs.v("age :" + age);
	}

	public void _testJsonObject() throws JSONException {
		JSONObject json = new JSONObject();
		json.put("name", "张三");
		json.put("age", 24);

		Logs.v(json.toString());
	}

	public void _testMapToJsonArray() {
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("name", "李四");
		map1.put("age", 24);

		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("name", "lisi");
		map2.put("age", 25);

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list.add(map1);
		list.add(map2);

		JSONArray array = new JSONArray(list);

		Logs.v(array.toString());// [{"name":"李四","age":24},{"name":"lisi","age":25}]

	}

	public void _testJsonArray() throws JSONException {
		JSONArray array = new JSONArray();
		array.put(0, "zhangsan");
		array.put(1, "lisi");
		Logs.v(array.toString());
	}

	// =====================================================
	public void _testfastjsonobject() {
		String json = ReadAssetsFile.readtxt(getContext(), "update");
		Logs.v(" json :" + json);

		UpdateDao mUpdateDao = JSON.parseObject(json.toString(),
				UpdateDao.class);

		Logs.v(" version :" + mUpdateDao.getInfo().getVersion());
	}

	public void _testFastJsonToUserOject() {
		String json = ReadAssetsFile.readtxt(getContext(), "user");
		Logs.v("json " + json);
		// {"id":100,"userName":"admin","password":"123456","email":"admin@xinhua.com"}

		UserMessage user = JSON.parseObject(json, UserMessage.class);

		Logs.v("userName : " + user.getUser().getUserName() + " message :"
				+ user.getMessage());

	}

	public void _test_FastJsonToCityMessage(){
		String json = ReadAssetsFile.readtxt(getContext(), "json_list_test");

		CityMessage cityMessage = JSON.parseObject(json, CityMessage.class);
		List<CityBean> cityList = cityMessage.getInfo();
		for(int i = 0; i< cityList.size(); i++){
			CityBean city = cityList.get(i);
			String cityName = city.getValue();
			String cityCode = city.getCode();
			
			Logs.v("cityName :"+cityName + " cityCode :"+cityCode);
		}
		
	}
	
	// {"id":712,"name":"android班","users":[{"id":12,"userName":"gm","password":"","email":""},{"id":12,"userName":"ldj","password":"","email":""},{"id":12,"userName":"tmb","password":"","email":""}]}
	public void testFastJsonObjectToJsonString() {
		Group group = new Group();
		group.setId(712);
		group.setName("android班");

		User guestUser = new User();
		guestUser.setId(11);
		guestUser.setUserName("gm");
		guestUser.setPassWord("1234");
		guestUser.setEmail("gm@xinhua.com");

		User rootUser = new User();
		rootUser.setId(12);
		rootUser.setUserName("tmb");
		rootUser.setPassWord("1234");
		rootUser.setEmail("tmb@xinhua.com");

		User ldjUser = new User();
		ldjUser.setId(13);
		ldjUser.setUserName("ldj");
		ldjUser.setPassWord("1234");
		ldjUser.setEmail("ldj@xinhua.com");

		group.getUsers().add(guestUser);
		group.getUsers().add(rootUser);
		group.getUsers().add(ldjUser);

		String jsonString = JSON.toJSONString(group);

		Logs.v(jsonString);
	}

}
