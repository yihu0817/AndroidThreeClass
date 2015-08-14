/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.scxh.android.fragement.imageloading;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;

import com.scxh.android.R;
import com.scxh.android.ui.tab.PagerSildingFragmentActivity.ZoomOutPageTransformer;
import com.scxh.android.util.Constances;
import com.scxh.android.util.Logs;

public class ImageDetailActivity extends FragmentActivity {
	private ImagePagerAdapter mAdapter;
	private ViewPager mPager;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.image_detail_pager);
		mPager = (ViewPager) findViewById(R.id.viewpagerone);
		
		mAdapter = new ImagePagerAdapter(getSupportFragmentManager(),Constances.imageThumbUrls);
		mPager.setAdapter(mAdapter);
		mPager.setOffscreenPageLimit(3);//ViewPager预加载页设置
		mPager.setPageTransformer(true, new DepthPageTransformer());
	}

	private class ImagePagerAdapter extends FragmentStatePagerAdapter {
		private final int mSize;
		private String[] mImageUrls;
		public ImagePagerAdapter(FragmentManager fm, String[] imageUrls) {
			super(fm);
			mImageUrls = imageUrls;
			mSize = mImageUrls.length;
		}

		@Override
		public int getCount() {
			return mSize;
		}

		@Override
		public Fragment getItem(int position) {
			return ImageDetailFragment.newInstance(mImageUrls[position]);
		}
	}
	public class DepthPageTransformer implements ViewPager.PageTransformer {
	    private static final float MIN_SCALE = 0.75f;

	    public void transformPage(View view, float position) {
	        int pageWidth = view.getWidth();

	        if (position < -1) { // [-∞ ,-1)
	            // 这一页已经是最左边的屏幕页
	            view.setAlpha(0);

	        } else if (position <= 0) { // [-1,0]
	            // 向左面滑屏使用默认的过渡动画
	            view.setAlpha(1);
	            view.setTranslationX(0);
	            view.setScaleX(1);
	            view.setScaleY(1);

	        } else if (position <= 1) { // (0,1]
	            // 淡出页面
	            view.setAlpha(1 - position);

	            // 抵消默认的整页过渡
	            view.setTranslationX(pageWidth * -position);

	            // 根据缩放系数变化 (在 MIN_SCALE 和 1 之间变化)
	            float scaleFactor = MIN_SCALE
	                    + (1 - MIN_SCALE) * (1 - Math.abs(position));
	            view.setScaleX(scaleFactor);
	            view.setScaleY(scaleFactor);

	        } else { // (1,+∞]
	            // 这一页已经是最右边的屏幕页
	            view.setAlpha(0);
	        }
	    }
	}
}
