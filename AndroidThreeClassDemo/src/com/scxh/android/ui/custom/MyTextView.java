package com.scxh.android.ui.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.scxh.android.R;

public class MyTextView extends View {
	private int mGreenColor;
	private float mTextSize;
	private String mContent = "";

	public MyTextView(Context context, AttributeSet attrs) {
		super(context, attrs);

		TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
				R.styleable.MyTextView, 0, 0);
		try {
			mGreenColor = a.getColor(R.styleable.MyTextView_myColor, getResources().getColor(
					android.R.color.holo_green_dark));
			mTextSize = a.getDimension(R.styleable.MyTextView_myFontSize, 20);
			mContent = a.getString(R.styleable.MyTextView_myText);
			
		} finally {
			a.recycle();
		}
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		Paint mTextPaint = new Paint();
		mTextPaint.setColor(mGreenColor);
		mTextPaint.setTextSize(mTextSize);
		
		canvas.drawText(mContent, 100, 100, mTextPaint);
	}

	public void setColor(int color) {
		mGreenColor = color;
	}

	public void setText(String text) {
		mContent = text;
	}

}
