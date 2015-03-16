package com.scxh.android.ui.menu;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.Toast;

import com.scxh.android.R;

public class PopupMenuActivity extends Activity implements
		OnMenuItemClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_optionmenu_layout);
		Button textView = (Button) findViewById(R.id.popmenutxt);
		textView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				PopupMenu popupMenu = new PopupMenu(PopupMenuActivity.this, v);
				popupMenu.setOnMenuItemClickListener(PopupMenuActivity.this);
				MenuInflater inflater = popupMenu.getMenuInflater();
				inflater.inflate(R.menu.menu_popup, popupMenu.getMenu());
				popupMenu.show();
			}
		});

	}

	@Override
	public boolean onMenuItemClick(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_news:
			Toast.makeText(this, "新建", Toast.LENGTH_SHORT).show();
			return true;

		case R.id.menu_exit:
			Toast.makeText(this, "退出", Toast.LENGTH_SHORT).show();
			return true;
		}
		return false;
	}
}
