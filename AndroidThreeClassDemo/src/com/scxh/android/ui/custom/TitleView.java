package com.scxh.android.ui.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scxh.android.R;

public class TitleView extends RelativeLayout implements OnClickListener{
	private OnClickListener mOnClickListener;
	
	private Button leftBtn, rightBtn;
	private TextView titleTxt;

	public TitleView(Context context) {
		super(context);
		init();
	}

	public TitleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
		TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.TitleView);
		try {
			String title = a.getString(R.styleable.TitleView_title_text);
			String left = a.getString(R.styleable.TitleView_left_text);
			String right = a.getString(R.styleable.TitleView_right_text);

			if (left != null) {
				setLeftBtn(left);
			}
			if (right != null) {
				setRightBtn(right);
			}
			if (title != null) {
				setTitle(title);
			}

		} finally {
			a.recycle();
		}

	}

	public TitleView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	public void init() {
		LayoutInflater.from(getContext()).inflate(R.layout.view_title_layout,this);
	}

	
	
	public void setLeftBtn(String left) {
		if (leftBtn == null) {
			leftBtn = (Button) findViewById(R.id.left_imagebtn);
		}
		leftBtn.setText(left);
		leftBtn.setOnClickListener(this);
	}

	public void setRightBtn(String right) {
		if (rightBtn == null) {
			rightBtn = (Button) findViewById(R.id.right_imagebtn);
		}
		rightBtn.setText(right);
		rightBtn.setOnClickListener(this);
	}

	public void setTitle(String title) {
		if (titleTxt == null) {
			titleTxt = (TextView) findViewById(R.id.title_text);
		}
		titleTxt.setText(title);
	}

	public void setOnClickListener(OnClickListener l) {
        mOnClickListener = l;
    }
	
	@Override
	public void onClick(View v) {
		if(mOnClickListener != null){
			mOnClickListener.onClick(v);
		}
		
	}
}
