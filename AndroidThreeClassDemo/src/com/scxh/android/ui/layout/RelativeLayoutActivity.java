package com.scxh.android.ui.layout;

import com.scxh.android.R;
import com.scxh.android.R.id;
import com.scxh.android.R.layout;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

public class RelativeLayoutActivity extends Activity {
	private Switch mSwitch;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_relative_layout);
		
		mSwitch = (Switch) findViewById(R.id.switchbutton);
		
		mSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				Switch switchs = (Switch)buttonView;
				if (isChecked) {
			        Toast.makeText(RelativeLayoutActivity.this, switchs.getTextOn(), Toast.LENGTH_SHORT).show();
			    } else {
			    	Toast.makeText(RelativeLayoutActivity.this, switchs.getTextOff(), Toast.LENGTH_SHORT).show();
			    }
			}
		});
		
	}
	public void onToggleClicked(View v){

	    boolean on = ((ToggleButton) v).isChecked();
	    
	    if (on) {
	        Toast.makeText(this, "打开", Toast.LENGTH_SHORT).show();
	    } else {
	    	Toast.makeText(this, "关", Toast.LENGTH_SHORT).show();
	    }
	}
}
