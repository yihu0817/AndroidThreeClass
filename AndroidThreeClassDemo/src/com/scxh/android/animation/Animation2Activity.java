package com.scxh.android.animation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.Button;
import android.widget.ImageView;

import com.scxh.android.R;

public class Animation2Activity extends Activity {
	private Button addButton = null;
	private Button deleteButton = null;
//	private ImageView imageView = null;
	private ImageView newImageView;
	private ViewGroup viewGroup = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_layoutanimation_layout);
		addButton = (Button) findViewById(R.id.addButton);
		deleteButton = (Button) findViewById(R.id.deleteButton);
//		imageView = (ImageView) findViewById(R.id.image);
		// LinearLayout下的一组控件
		viewGroup = (ViewGroup) findViewById(R.id.layout);
		
		addButton.setOnClickListener(new AddButtonListener());
		deleteButton.setOnClickListener(new DeleteButtonListener());
	}
	
	private class AddButtonListener implements OnClickListener {
		public void onClick(View v) {
			// 淡入
			AlphaAnimation animation = new AlphaAnimation(0.0f, 1.0f);
			animation.setDuration(1000);
			animation.setStartOffset(500);
			// 创建一个新的ImageView
			newImageView = new ImageView(Animation2Activity.this);
			newImageView.setImageResource(R.drawable.m1);
			viewGroup.addView(newImageView, new LayoutParams(
					LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
			newImageView.startAnimation(animation);
		}
	}

	private class DeleteButtonListener implements OnClickListener {
		public void onClick(View v) {
			// 淡出
			AlphaAnimation animation = new AlphaAnimation(1.0f, 0.0f);
			animation.setDuration(1000);
			animation.setStartOffset(500);
			// 为Aniamtion对象设置监听器
			animation.setAnimationListener(new RemoveAnimationListener());
			newImageView.startAnimation(animation);
		}
	}

	private class RemoveAnimationListener implements AnimationListener {
		// 动画效果执行完时remove
		public void onAnimationEnd(Animation animation) {
			System.out.println("onAnimationEnd");
			viewGroup.removeView(newImageView);
		}

		public void onAnimationRepeat(Animation animation) {
			System.out.println("onAnimationRepeat");
		}

		public void onAnimationStart(Animation animation) {
			System.out.println("onAnimationStart");
		}
	}

}
