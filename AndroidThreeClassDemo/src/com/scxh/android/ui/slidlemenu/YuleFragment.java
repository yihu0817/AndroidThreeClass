package com.scxh.android.ui.slidlemenu;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

public class YuleFragment extends ListFragment {
	String[] mList = new String[] { "李秉宪因桃色事件暴瘦 遗憾让妻子和粉丝失望(图)",
			"娱乐新闻(组图)(2014-10-20 14:15:26)", "救市来袭? ”银十”楼市释放回暖信号",
			"房贷新政效果待考 业内人士认为不应理解为救市", "市场化调整 长效机制终极目标尚不明",
			"房贷新政难振信心 房企唯有以价换量去化库存", "湖南省第十二届文博会今日开幕 将预展400多件待拍品" };

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		ListAdapter adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1, mList);
		setListAdapter(adapter);
	}
}
