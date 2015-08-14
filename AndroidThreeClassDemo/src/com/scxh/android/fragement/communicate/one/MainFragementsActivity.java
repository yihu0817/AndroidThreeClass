package com.scxh.android.fragement.communicate.one;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.scxh.android.R;
import com.scxh.android.fragement.communicate.one.ListFragments.OnHeadlineSelectedListener;

public class MainFragementsActivity extends Activity implements OnHeadlineSelectedListener{
	Fragment contentFragment,listFragment;
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_main_trends_fragment_layout);
		
		listFragment = new ListFragments();
		contentFragment = new ContentFramgets();
		
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.add(R.id.listFragmentsLayout, listFragment);
		transaction.add(R.id.viewerFramgmentsLayout, contentFragment);

		transaction.commit();
	}

	@Override
	public void onSelectItemClick(String message) {
//		Toast.makeText(this, "onSelectItemClick  :"+message, Toast.LENGTH_SHORT).show();
		((ContentFramgets) contentFragment).setMessageToTextView(message);
	}
}
