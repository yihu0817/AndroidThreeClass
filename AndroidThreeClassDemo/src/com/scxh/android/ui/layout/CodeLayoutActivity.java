package com.scxh.android.ui.layout;

import com.scxh.android.R;
import com.scxh.android.R.color;
import com.scxh.android.R.dimen;
import com.scxh.android.R.id;
import com.scxh.android.R.layout;
import com.scxh.android.R.string;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class CodeLayoutActivity extends Activity {
	private Button oneButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setLinearLayoutContentView();

	}

	/**
	 * 代码动态实现线型布局
	 */
	private void setLinearLayoutContentView() {
		LinearLayout linearLayout = new LinearLayout(this);

		linearLayout.setLayoutParams(new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		linearLayout.setOrientation(LinearLayout.VERTICAL);

		linearLayout.setBackgroundColor(getResources()
				.getColor(R.color.blue_bg));

		TextView textView = new TextView(this);
		textView.setLayoutParams(new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		textView.setText(getResources().getString(R.string.one_name));
		textView.setTextSize(getResources().getDimension(
				R.dimen.text_size_small));
		textView.setBackgroundColor(getResources().getColor(
				android.R.color.holo_green_dark));

		TextView textView1 = new TextView(this);
		textView1.setLayoutParams(new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		textView1.setText(getResources().getString(R.string.two_name));
		textView1.setTextSize(getResources().getDimension(
				R.dimen.text_size_small));

		linearLayout.addView(textView);
		linearLayout.addView(textView1);

		// LayoutInflater inflater = (LayoutInflater)
		// getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		LayoutInflater inflater = LayoutInflater.from(this);

		View view = inflater.inflate(R.layout.item_code_layout, null);

		oneButton = (Button) view.findViewById(R.id.oneBtn);

		linearLayout.addView(view);

		setContentView(linearLayout);

		oneButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(CodeLayoutActivity.this, "hello ",
						Toast.LENGTH_SHORT).show();
			}
		});
	}

	/**
	 * 代码实现相对布局
	 */
	private void setRelativiLayoutContentView() {
		RelativeLayout rl = new RelativeLayout(this);

		Button btn1 = new Button(this);
		btn1.setText("----------1------------");
		btn1.setId(1);

		RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);

		lp1.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		lp1.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
		// btn1 位于父 View 的顶部，在父 View 中水平居中
		rl.addView(btn1, lp1);

		Button btn2 = new Button(this);
		btn2.setText("|\n|\n2\n|\n|\n|");
		btn2.setId(2);

		RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		lp2.addRule(RelativeLayout.BELOW, 1);
		lp2.addRule(RelativeLayout.ALIGN_LEFT, 1);
		// btn2 位于 btn1 的下方、其左边和 btn1 的左边对齐
		rl.addView(btn2, lp2);

		Button btn3 = new Button(this);
		btn3.setText("|\n|\n3\n|\n|\n|");
		btn3.setId(3);

		RelativeLayout.LayoutParams lp3 = new RelativeLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		lp3.addRule(RelativeLayout.BELOW, 1);
		lp3.addRule(RelativeLayout.RIGHT_OF, 2);
		lp3.addRule(RelativeLayout.ALIGN_RIGHT, 1);
		// btn3 位于 btn1 的下方、btn2 的右方且其右边和 btn1 的右边对齐（要扩充）
		rl.addView(btn3, lp3);

		Button btn4 = new Button(this);
		btn4.setText("-----------------4--------------");
		btn4.setId(4);

		RelativeLayout.LayoutParams lp4 = new RelativeLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		lp4.addRule(RelativeLayout.BELOW, 2);
		lp4.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
		// btn4 位于 btn2 的下方，在父 Veiw 中水平居中
		rl.addView(btn4, lp4);

		setContentView(rl);

	}

}
