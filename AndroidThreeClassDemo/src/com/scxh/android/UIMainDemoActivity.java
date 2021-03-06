package com.scxh.android;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.scxh.android.animation.Animation1Activity;
import com.scxh.android.animation.alpha.CrossfadeActivity;
import com.scxh.android.animation.gift.GiftActivity;
import com.scxh.android.asynctask.MyAsyncTaskActivity;
import com.scxh.android.bean.Model;
import com.scxh.android.file.FileActivity;
import com.scxh.android.file.SearchFileAct;
import com.scxh.android.fragement.ContainerFragmentActivity;
import com.scxh.android.fragement.communicate.one.MainFragementsActivity;
import com.scxh.android.fragement.communicate.two.FragementSendActivity;
import com.scxh.android.fragement.imageloading.ImageDetailActivity;
import com.scxh.android.fragement.stack.FragmentStack;
import com.scxh.android.html.HttpUrlConnectionAct;
import com.scxh.android.html.WebActivity;
import com.scxh.android.httpimage.HttpImageBitmapActivity;
import com.scxh.android.json.CityJosnAcitvity;
import com.scxh.android.mp3.Mp3;
import com.scxh.android.notification.MyNotification;
import com.scxh.android.pattern.ObserviesDemoActivity;
import com.scxh.android.receiver.TestBroadcastReceiver;
import com.scxh.android.receiver.sms.Globals;
import com.scxh.android.service.DownLoadActivity;
import com.scxh.android.service.PlayerUIActivity;
import com.scxh.android.sharepreference.SharePreferenceActivity;
import com.scxh.android.ui.ActionListnerActivity;
import com.scxh.android.ui.EditTextActivity;
import com.scxh.android.ui.IntentActivity;
import com.scxh.android.ui.LifeActivity;
import com.scxh.android.ui.RadioActivity;
import com.scxh.android.ui.ShapeAct;
import com.scxh.android.ui.StateActivty;
import com.scxh.android.ui.UIGridViewHttpAct;
import com.scxh.android.ui.ViewTextActivity;
import com.scxh.android.ui.autocompletetxt.AutoCompleteTxtActivity;
import com.scxh.android.ui.autocompletetxt.AutoCompleteTxtPinyinActivity;
import com.scxh.android.ui.bar.ProgressBarActivity;
import com.scxh.android.ui.custom.CustomViewAct;
import com.scxh.android.ui.db.DataBaseActivity;
import com.scxh.android.ui.db.DataBaseCursorActivity;
import com.scxh.android.ui.dialog.DialogAcitivity;
import com.scxh.android.ui.dialog.PopWindowActivity;
import com.scxh.android.ui.handler.CallBackActivity;
import com.scxh.android.ui.handler.HandlerActivity;
import com.scxh.android.ui.layout.AbosoluteActivity;
import com.scxh.android.ui.layout.CodeLayoutActivity;
import com.scxh.android.ui.layout.FramLayoutActivity;
import com.scxh.android.ui.layout.LinearLayoutActivity;
import com.scxh.android.ui.layout.RelativeLayoutActivity;
import com.scxh.android.ui.layout.TableLayoutActivity;
import com.scxh.android.ui.listview.ArrayActivity;
import com.scxh.android.ui.listview.MyBaseAdapterActivity;
import com.scxh.android.ui.listview.PageListAct;
import com.scxh.android.ui.listview.PullToRefreshActivity;
import com.scxh.android.ui.listview.PullToRefreshPageListAct;
import com.scxh.android.ui.listview.SimpleActivity;
import com.scxh.android.ui.menu.ContextMenuAcitivy;
import com.scxh.android.ui.menu.OptionMenuActivity;
import com.scxh.android.ui.menu.PopupMenuActivity;
import com.scxh.android.ui.provider.ContentProviderActivity;
import com.scxh.android.ui.slidlemenu.SlidenMenuMainAct;
import com.scxh.android.ui.spiners.SpinnersActivity;
import com.scxh.android.ui.surfaceview.BasicSurfaceViewActivity;
import com.scxh.android.ui.surfaceview.video.VoidePlayerActivity;
import com.scxh.android.ui.tab.PagerSildingFragmentActivity;
import com.scxh.android.ui.tab.TabFragmentActivity;
import com.scxh.android.ui.tab.TabRadioViewActivity;
import com.scxh.android.ui.tab.TabRadioViewFragmentActivity;
import com.scxh.android.ui.tab.TabViewActivity;
import com.scxh.android.ui.tab.ViewPagerFragmentActivity;
import com.scxh.android.ui.viewpager.MyViewPageActivity;
import com.scxh.android.volley.VolleyActivity;
import com.scxh.android.volley.VolleyCacheActivity;
import com.scxh.android.xml.XmlParserPullActivity;

public class UIMainDemoActivity extends ListActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setListAdapter(new MyBaseAdapter(this, getData()));

		getListView().setStackFromBottom(true);

		Intent intents = new Intent();
		intents.setAction(Globals.IMICHAT_SERVICE);
		startService(intents);

	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		MyBaseAdapter adapter = (MyBaseAdapter) l.getAdapter();
		Model model = (Model) adapter.getItem(position);

		Intent intent = new Intent(this, model.getIntentObject());
		startActivity(intent);
	}

	public Model createModel(String title, Class<?> intentObject) {
		Model model = new Model();
		model.setTitle(title);
		model.setIntentObject(intentObject);
		return model;
	}

	public List<Model> getData() {
		ArrayList<Model> list = new ArrayList<Model>();
		list.add(createModel("布局学习", LayoutActivity.class));
		list.add(createModel("基础控件之TextView", ViewTextActivity.class));
		list.add(createModel("基础控件之EditText", EditTextActivity.class));
		list.add(createModel("基础控件之RadioGroup与RadioButton", RadioActivity.class));
		list.add(createModel("基础控件之AutoCompleteText",AutoCompleteTxtActivity.class));
		list.add(createModel("基础控件之AutoCompleteTxtPinyin",AutoCompleteTxtPinyinActivity.class));
		list.add(createModel("适配器控件之Spinners", SpinnersActivity.class));
		list.add(createModel("事件处理", ActionListnerActivity.class));
		list.add(createModel("Activity之生命周期", LifeActivity.class));
		list.add(createModel("Activity之状态保存", StateActivty.class));
		list.add(createModel("Activity之Intent隐式调用", IntentActivity.class));
		list.add(createModel("适配器之ArrayAdapter", ArrayActivity.class));
		list.add(createModel("适配器之SimpleAdapter", SimpleActivity.class));
		list.add(createModel("适配器之BaseAdapter+ListView分类Item", MyBaseAdapterActivity.class));
		list.add(createModel("对话框Dialog", DialogAcitivity.class));
		list.add(createModel("弹出窗口Popwindow", PopWindowActivity.class));
		list.add(createModel("菜单之OptionMenu", OptionMenuActivity.class));
		list.add(createModel("菜单之ContextMenu", ContextMenuAcitivy.class));
		list.add(createModel("菜单之PopupMenu", PopupMenuActivity.class));
		list.add(createModel("Tab", TabViewActivity.class));
		list.add(createModel("TabRadio", TabRadioViewActivity.class));
		list.add(createModel("高级控件之ViewPage", MyViewPageActivity.class));
		list.add(createModel("高级控件之ProgressBar", ProgressBarActivity.class));
		list.add(createModel("高级控件之GridView+网络取图片", UIGridViewHttpAct.class));
		list.add(createModel("高级控件之WebView", WebActivity.class));
		list.add(createModel("消息处理机制之 Handler", HandlerActivity.class));
		list.add(createModel("回调机制处理之 CallBack", CallBackActivity.class));
		list.add(createModel("观察者模式之Observies", ObserviesDemoActivity.class));
		list.add(createModel("数据存储之DataBase", DataBaseActivity.class));
		list.add(createModel("数据存储之DataBaseCursor",DataBaseCursorActivity.class));
		list.add(createModel("数据存储之SharePreference",SharePreferenceActivity.class));
		list.add(createModel("数据存储之File", FileActivity.class));
		list.add(createModel("数据存储之ContentProvider",ContentProviderActivity.class));
		list.add(createModel("播放器之Mp3", Mp3.class));
		list.add(createModel("播放器之Player", PlayerUIActivity.class));
		list.add(createModel("文件搜索SearchFile", SearchFileAct.class));
		list.add(createModel("UI界面Shape使用", ShapeAct.class));
		list.add(createModel("下载DownLoad", DownLoadActivity.class));
		list.add(createModel("广播之BroadcastReceiver",TestBroadcastReceiver.class));
		list.add(createModel("视频播放VoidePlayer", VoidePlayerActivity.class));
		// list.add(createModel("VideoSurfaceDemo", VideoSurfaceDemo.class));
		list.add(createModel("通知Notification", MyNotification.class));
		list.add(createModel("异步任务之AsyncTask", MyAsyncTaskActivity.class));
		list.add(createModel("网络取图片HttpImageBitmap", HttpImageBitmapActivity.class));
		list.add(createModel("网络连接HttpUrlConnection", HttpUrlConnectionAct.class));
		list.add(createModel("Json解析之CityJson", CityJosnAcitvity.class));
		list.add(createModel("Xml解析之XmlParserPull", XmlParserPullActivity.class));
		list.add(createModel("分页处理PageList", PageListAct.class));
		list.add(createModel("下拉刷新之PullToRefreshPageList", PullToRefreshPageListAct.class));
		list.add(createModel("下拉刷新之PullToRefreshActivity", PullToRefreshActivity.class));
		list.add(createModel("Fragments之MainFragements", MainFragementsActivity.class));
		list.add(createModel("Fragement交互例子", FragementSendActivity.class));
		list.add(createModel("Fragment栈", FragmentStack.class));
		list.add(createModel("Fragment栈、交互、子类综合", ContainerFragmentActivity.class));
		list.add(createModel("UI框架之TabRadio+Fragment", TabRadioViewFragmentActivity.class));
		list.add(createModel("UI框架之Tab+Fragmen", TabFragmentActivity.class));
		list.add(createModel("高级控件之ViewPager+Fragment", ViewPagerFragmentActivity.class));
		list.add(createModel("高级控件之ViewPager+Fragment+FragmentStatePagerAdapter", ImageDetailActivity.class));
		list.add(createModel("高级控件之SurfaceView基础", BasicSurfaceViewActivity.class));
		list.add(createModel("自定义控件", CustomViewAct.class));
		list.add(createModel("自定义滑动Tab之PagerSildingFragment", PagerSildingFragmentActivity.class));
		list.add(createModel("第三方侧滑菜单SlidenMenu", SlidenMenuMainAct.class));
		list.add(createModel("Android动画基础", Animation1Activity.class));
		list.add(createModel("Android动画基础之View间渐变", CrossfadeActivity.class));
		list.add(createModel("Android动画基础之宝箱实例", GiftActivity.class));
		list.add(createModel("Android网络通讯框架之Volley", VolleyActivity.class));
		list.add(createModel("Android网络通讯框架之Volley缓存", VolleyCacheActivity.class));
		return list;
	}
	class MyBaseAdapter extends BaseAdapter {
		private List<Model> mList = new ArrayList<Model>();

		private Context mContext;
		private LayoutInflater inflater;

		public MyBaseAdapter(Context context) {
			mContext = context;
			inflater = LayoutInflater.from(mContext);
		}

		public MyBaseAdapter(Context context, List<Model> list) {
			mContext = context;
			mList = list;
			inflater = LayoutInflater.from(mContext);
		}

		public void setData(List<Model> list) {
			mList = list;
		}

		/**
		 * 数据源记录条数,ListView项数
		 */
		@Override
		public int getCount() {
			return mList.size();
		}

		/**
		 * 获取数据源指定位置(position)的数据项对象
		 */
		@Override
		public Object getItem(int position) {
			return mList.get(position);
		}

		/**
		 * 获取指定位置ID
		 */
		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			TextView text;
			if (convertView == null) {
				convertView = inflater.inflate(
						android.R.layout.simple_list_item_1, null);
				text = (TextView) convertView.findViewById(android.R.id.text1);
				convertView.setTag(text);

			} else {
				text = (TextView) convertView.getTag();
			}

			Model msg = (Model) getItem(position);

			text.setText(msg.getTitle());

			return convertView;
		}

	}
}
