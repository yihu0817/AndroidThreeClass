package com.scxh.android.ui.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

import com.scxh.android.R;
import com.scxh.android.util.Logs;

public class PieChart extends TextView {
	private boolean showText;
	private int mTextPos;
	private String mContent;
	private Paint mTextPaint;
	private int mTextColor;
	private float mTextSize;
	public PieChart(Context context, AttributeSet attrs) {
		super(context, attrs);

		TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
				R.styleable.PieChart, 0, 0);

		try {
			showText = a.getBoolean(R.styleable.PieChart_showTexts, false);
			mTextPos = a.getInt(R.styleable.PieChart_labelPosition, 0);
			mContent = a.getString(R.styleable.PieChart_content);
			mTextColor = a.getColor(R.styleable.PieChart_textcolor,android.R.color.background_dark);
			mTextSize = a.getDimension(R.styleable.PieChart_textsize, 20);
		} finally {
			a.recycle();
		}

		Logs.v("showText" + showText + "  mTextPos :" + mTextPos
				+ " mContent :" + mContent);

	}

	public void setTextShowView(String text){
		mContent = text;
		invalidate();
		requestLayout();
	}
	
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		mTextPaint = new Paint();
		mTextPaint.setColor(mTextColor);
		mTextPaint.setTextSize(mTextSize);
		canvas.drawText(mContent, 100, 50, mTextPaint);
	}
}
