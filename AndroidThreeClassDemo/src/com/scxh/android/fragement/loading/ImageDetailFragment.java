package com.scxh.android.fragement.loading;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.scxh.android.R;
import com.scxh.android.util.AsyncImageLoader;
import com.scxh.android.util.AsyncImageLoader.ImageCallbackForBitmap;

public class ImageDetailFragment extends Fragment {
	private static final String IMAGE_DATA_EXTRA = "extra_image_data";
	private String mImageUrl;
	private ImageView mImageView;
	private ProgressBar mProgressBar;
	private final int mShortAnimationDuration = 1000;

	public static ImageDetailFragment newInstance(String imageUrl) {
		final ImageDetailFragment f = new ImageDetailFragment();

		final Bundle args = new Bundle();
		args.putString(IMAGE_DATA_EXTRA, imageUrl);
		f.setArguments(args);

		return f;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mImageUrl = getArguments() != null ? getArguments().getString(
				IMAGE_DATA_EXTRA) : null;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View v = inflater.inflate(R.layout.image_detail_fragment,
				container, false);
		mImageView = (ImageView) v.findViewById(R.id.imageView);
		mProgressBar = (ProgressBar) v.findViewById(R.id.progressBarone);
		return v;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		AsyncImageLoader asyncImageLoader = new AsyncImageLoader();
		
//		setAinimation();
		
		mProgressBar.setVisibility(View.VISIBLE);
		Bitmap bitmap = asyncImageLoader.loadBitmap(mImageUrl,
				new ImageCallbackForBitmap() {

					@Override
					public void imageLoaded(Bitmap bitmap, String imageUrl) {
						mImageView.setImageBitmap(bitmap);
						mProgressBar.setVisibility(View.GONE);
					}
				});
		if (bitmap != null) {
			mImageView.setImageBitmap(bitmap);
			mProgressBar.setVisibility(View.GONE);
		}
	}
	/**
	 * 设置动画效果
	 */
	public void setAinimation() {
		mImageView.animate().alpha(1f).setDuration(mShortAnimationDuration)
				.setListener(null);

		mProgressBar.animate().alpha(0f).setDuration(mShortAnimationDuration)
				.setListener(new AnimatorListenerAdapter() {
					@Override
					public void onAnimationEnd(Animator animation) {
						mProgressBar.setVisibility(View.GONE);
					}
				});
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (mImageView != null) {
			mImageView.setImageDrawable(null);
		}
	}
}
