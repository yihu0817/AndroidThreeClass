package com.scxh.android.ui.slidlemenu;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

public class ToutiaoFragment extends ListFragment {
	String[] mList = new String[] { "10月20日广西头条新闻焦点: 鸡蛋价格连升5个月",
			"三季度GDP今公布 增速或降至7.3%", "救市来袭? ”银十”楼市释放回暖信号",
			"房贷新政效果待考 业内人士认为不应理解为救市", "市场化调整 长效机制终极目标尚不明",
			"房贷新政难振信心 房企唯有以价换量去化库存", "湖南省第十二届文博会今日开幕 将预展400多件待拍品" };

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		ListAdapter adapter = new ArrayAdapter(getActivity(),
				android.R.layout.simple_list_item_1, mList);
		setListAdapter(adapter);
	}
}
