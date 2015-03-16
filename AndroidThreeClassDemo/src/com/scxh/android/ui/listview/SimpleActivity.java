package com.scxh.android.ui.listview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.scxh.android.R;

public class SimpleActivity extends Activity {
	private ListView mListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_simpleadapter_layout);

		mListView = (ListView) findViewById(R.id.simplelist);

		String[] from = new String[] {"title", "info","img"};
		int[] to = new int[] { R.id.titletextView, R.id.infotextView,R.id.iconImg };
		
		SimpleAdapter adapter = new SimpleAdapter(this, getData(),
				R.layout.item_simpleadapter_view, from, to);
		
		mListView.setAdapter(adapter);

	}

	private List<Map<String, Object>> getData() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title", "[6店通用] 九锅一堂");
		map.put("info", "仅售37元，价值50元代金券，无需预约，全场通用，免费WIFI！");
		map.put("img", R.drawable.m1);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("title", "[4店通用] 千味涮");
		map.put("info",
				"仅售68元，价值100元电子现金券1张，可累计使用，节假日通用！全国连锁品牌！4店通用，全场通兑，随心随意，自助味碟！");
		map.put("img", R.drawable.m2);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("title", "[梁家巷] 骉骉老火锅");
		map.put("info",
				"仅售69.9元，价值100元代金券，提供免费wifi，免费停车！鲜香锅底+新鲜涮菜，一口浓汤涮出火锅新滋味！");
		map.put("img", R.drawable.m3);
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("title", "[4店通用] 千味涮");
		map.put("info",
				"仅售68元，价值100元电子现金券1张，可累计使用，节假日通用！全国连锁品牌！4店通用，全场通兑，随心随意，自助味碟！");
		map.put("img", R.drawable.m2);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("title", "[梁家巷] 骉骉老火锅");
		map.put("info",
				"仅售69.9元，价值100元代金券，提供免费wifi，免费停车！鲜香锅底+新鲜涮菜，一口浓汤涮出火锅新滋味！");
		map.put("img", R.drawable.m3);
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("title", "[4店通用] 千味涮");
		map.put("info",
				"仅售68元，价值100元电子现金券1张，可累计使用，节假日通用！全国连锁品牌！4店通用，全场通兑，随心随意，自助味碟！");
		map.put("img", R.drawable.m2);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("title", "[梁家巷] 骉骉老火锅");
		map.put("info",
				"仅售69.9元，价值100元代金券，提供免费wifi，免费停车！鲜香锅底+新鲜涮菜，一口浓汤涮出火锅新滋味！");
		map.put("img", R.drawable.m3);
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("title", "[4店通用] 千味涮");
		map.put("info",
				"仅售68元，价值100元电子现金券1张，可累计使用，节假日通用！全国连锁品牌！4店通用，全场通兑，随心随意，自助味碟！");
		map.put("img", R.drawable.m2);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("title", "[梁家巷] 骉骉老火锅");
		map.put("info",
				"仅售69.9元，价值100元代金券，提供免费wifi，免费停车！鲜香锅底+新鲜涮菜，一口浓汤涮出火锅新滋味！");
		map.put("img", R.drawable.m3);
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("title", "[4店通用] 千味涮");
		map.put("info",
				"仅售68元，价值100元电子现金券1张，可累计使用，节假日通用！全国连锁品牌！4店通用，全场通兑，随心随意，自助味碟！");
		map.put("img", R.drawable.m2);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("title", "[梁家巷] 骉骉老火锅");
		map.put("info",
				"仅售69.9元，价值100元代金券，提供免费wifi，免费停车！鲜香锅底+新鲜涮菜，一口浓汤涮出火锅新滋味！");
		map.put("img", R.drawable.m3);
		list.add(map);
		
		
		map = new HashMap<String, Object>();
		map.put("title", "[4店通用] 千味涮");
		map.put("info",
				"仅售68元，价值100元电子现金券1张，可累计使用，节假日通用！全国连锁品牌！4店通用，全场通兑，随心随意，自助味碟！");
		map.put("img", R.drawable.m2);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("title", "[梁家巷] 骉骉老火锅");
		map.put("info",
				"仅售69.9元，价值100元代金券，提供免费wifi，免费停车！鲜香锅底+新鲜涮菜，一口浓汤涮出火锅新滋味！");
		map.put("img", R.drawable.m3);
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("title", "[4店通用] 千味涮");
		map.put("info",
				"仅售68元，价值100元电子现金券1张，可累计使用，节假日通用！全国连锁品牌！4店通用，全场通兑，随心随意，自助味碟！");
		map.put("img", R.drawable.m2);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("title", "[梁家巷] 骉骉老火锅");
		map.put("info",
				"仅售69.9元，价值100元代金券，提供免费wifi，免费停车！鲜香锅底+新鲜涮菜，一口浓汤涮出火锅新滋味！");
		map.put("img", R.drawable.m3);
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("title", "[4店通用] 千味涮");
		map.put("info",
				"仅售68元，价值100元电子现金券1张，可累计使用，节假日通用！全国连锁品牌！4店通用，全场通兑，随心随意，自助味碟！");
		map.put("img", R.drawable.m2);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("title", "[梁家巷] 骉骉老火锅");
		map.put("info",
				"仅售69.9元，价值100元代金券，提供免费wifi，免费停车！鲜香锅底+新鲜涮菜，一口浓汤涮出火锅新滋味！");
		map.put("img", R.drawable.m3);
		list.add(map);

		return list;
	}
}
