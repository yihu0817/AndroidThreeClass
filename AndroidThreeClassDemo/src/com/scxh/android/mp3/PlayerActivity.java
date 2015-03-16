package com.scxh.android.mp3;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.scxh.android.R;

public class PlayerActivity extends Activity {
	private Button mPlayBtn;
	private PlayManager mPlayManager;
	private String  mMaxTime;

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_player_layout);
		mPlayBtn = (Button) findViewById(R.id.player_music);

		mPlayBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					mPlayManager.startPlayVideo();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		mPlayManager = PlayManager.getInstance();
		mMaxTime = getDuration();
	}

	public String getCurrentPosition() {
		int pos = mPlayManager.getCurrentPosition();
		int min = (pos / 1000) / 60;
		int sec = (pos / 1000) % 60;

		String current = "";
		if (sec < 10) {
			current = "" + min + ":0" + sec + "/" + mMaxTime;
		} else {
			current = "" + min + ":" + sec + "/" + mMaxTime;
		}

		return current;
	}

	public String getDuration() {
		int maxPos = mPlayManager.getDuration();
		int maxMin = (maxPos / 1000) / 60;
		int maxSec = (maxPos / 1000) % 60;

		String maxTime = new String();
		if (maxSec < 10) {
			maxTime = "" + maxMin + ":0" + maxSec;
		} else {
			maxTime = "" + maxMin + ":" + maxSec;
		}

		return maxTime;

	}
	
}
