package com.scxh.android.file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.scxh.android.R;

public class CopyOfSearchFileAct extends Activity implements Button.OnClickListener {
	/* 定义程序要使用的类对象 */
	private String mInputKeyWord;
	private EditText mSearchKeyEdit;
	private Button mSearchBtn;
	private ListView mListView;
	private ArrayList<SongFileBean> mList = new ArrayList<SongFileBean>();
	private MyBaseAdapter mAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.file_search_act);
		mSearchKeyEdit = (EditText) findViewById(R.id.input_KEY_EditText);
		mSearchBtn = (Button) findViewById(R.id.searchBtn);
		mListView = (ListView) findViewById(R.id.resultList);

		mSearchBtn.setOnClickListener(this);

		mAdapter = new MyBaseAdapter(this);
		mListView.setAdapter(mAdapter);
	}

	public void onClick(View v) {
		mInputKeyWord = mSearchKeyEdit.getText().toString();

		if (mInputKeyWord.equals("")) {
			Toast.makeText(this, "如果输入框没有输入点击搜索按钮，提示输入", Toast.LENGTH_SHORT).show();
		} else {
			mList.clear();
			
			getFileLists(Environment.getExternalStorageDirectory());

			mAdapter.setData(mList);// 添加搜索到的数据源mList到适配器 并刷新ListView

		}
	}
	/**
	 * 搜索指定文件目录下文件，得到数据源
	 * @param files
	 */
	public void getFileLists(File files) {
		File[] fileList = files.listFiles();

		if (null != fileList) {

			for (File file : fileList) {
				// 判断文件是否是目录
				if (file.isDirectory()) {

					getFileLists(file);

				} else { // 文件操作
					try {

						/* 是文件，进行比较，如果文件名称中包含输入搜索Key 就显示 */
						if (file.getName().endsWith(mInputKeyWord)) {

							SongFileBean songFileBean = new SongFileBean();
							songFileBean.setFileName(file.getName());
							songFileBean.setFilePath(file.getPath());

							mList.add(songFileBean);
						}
					} catch (Exception e) {
						Toast.makeText(this, "出错", Toast.LENGTH_SHORT).show();
					}
				}
			}
		}
	}

	class MyBaseAdapter extends BaseAdapter {
		private List<SongFileBean> mList = new ArrayList<SongFileBean>();

		private Context mContext;
		private LayoutInflater inflater;

		public MyBaseAdapter(Context context) {
			mContext = context;
			inflater = LayoutInflater.from(mContext);
		}

		public MyBaseAdapter(Context context, List<SongFileBean> list) {
			mContext = context;
			mList = list;
			inflater = LayoutInflater.from(mContext);
		}

		public void setData(List<SongFileBean> list) {
			mList = list;
			notifyDataSetChanged();
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

			ViewHolder holder;

			if (convertView == null) {
				// ====一级优化==缓存View=======
				convertView = inflater.inflate(R.layout.item_baseadapter_view,
						parent, false);

				// ====二级优化===缓存View对象里的控件=====
				holder = new ViewHolder(); // 因为View setTag方法只能放一个Object,
				holder.titleTxt = (TextView) convertView
						.findViewById(R.id.titletextView);
				holder.infoTxt = (TextView) convertView
						.findViewById(R.id.infotextView);
				convertView.setTag(holder);

			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			// ==========取数据项给View对应控件赋值=============
			SongFileBean msg = (SongFileBean) getItem(position);

			holder.titleTxt.setText(msg.getFileName());
			holder.infoTxt.setText(msg.getFilePath());
			// ===========================================
			return convertView;
		}

		class ViewHolder {
			ImageView imageView = null;
			TextView titleTxt = null;
			TextView infoTxt = null;
		}
	}
}