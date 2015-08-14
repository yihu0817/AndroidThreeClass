package com.scxh.android.ui.menu;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.scxh.android.R;

public class OptionMenuActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_optionmenu_layout);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_options, menu);
//		menu.add(0, 11, 1, "新建");
//		menu.add(0,12,2,"退出");
		
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.new_action:
			Toast.makeText(this, "新建", Toast.LENGTH_SHORT).show();
			return true;
		case R.id.quit:
			Toast.makeText(this, "退出", Toast.LENGTH_SHORT).show();
			finish();
			return true;
		}
		return false;
	}
	
}
