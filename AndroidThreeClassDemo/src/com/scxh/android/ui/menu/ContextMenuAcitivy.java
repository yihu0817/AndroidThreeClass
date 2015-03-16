package com.scxh.android.ui.menu;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.scxh.android.R;

public class ContextMenuAcitivy extends ListActivity {
	private static int ORDER_NEW_ID = 1;
	private static int ORDER_EDIT_ID = 2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, new String[] { "选项菜单",
						"上下文菜单", "弹出菜单" });

		setListAdapter(adapter);
		
		registerForContextMenu(getListView());
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.add(0, 11, ORDER_EDIT_ID, "编辑");
		menu.add(0, 12, ORDER_NEW_ID,
				getResources().getString(R.string.menu_new));
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case 11:
			Toast.makeText(this, "编辑", Toast.LENGTH_SHORT).show();
			return true;
		case 12:
			Toast.makeText(this, getResources().getString(R.string.menu_new), Toast.LENGTH_SHORT).show();
			return true;
		}
		return super.onContextItemSelected(item);

	}
}
