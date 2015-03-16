package com.scxh.android.ui;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.scxh.android.R;
import com.scxh.android.service.MyService;
import com.scxh.android.util.Logs;

public class ShapeAct extends Activity {
	private TextView startServiceTxt;
	
	private ServiceConnection  mServiceConnection = new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			Logs.v("onServiceDisconnected  >>>>");
			
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			Logs.v("onServiceConnected >>>> ");
			
		}
	};
	
	public static void actionShapeAct(Context context){
		Intent intent = new Intent(context,ShapeAct.class);
		context.startActivity(intent);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_shape_layout);
		
		startServiceTxt = (TextView) findViewById(R.id.shape_textView5);
		
		startServiceTxt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ShapeAct.this,MyService.class);
				startService(intent);
				
//				bindService(intent, mServiceConnection, BIND_AUTO_CREATE);
				
			}
		});
	}
	@Override
	protected void onDestroy() {
//		unbindService(mServiceConnection);
		super.onDestroy();
	}
}
