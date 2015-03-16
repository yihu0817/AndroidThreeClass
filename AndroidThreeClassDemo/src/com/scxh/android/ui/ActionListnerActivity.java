package com.scxh.android.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.scxh.android.R;
import com.scxh.android.pattern.SinglePattern;

public class ActionListnerActivity extends Activity implements OnClickListener {
    private Button button1;
    private Button button2, button3;
    private TextView textView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_ationlistner_layout);
        
        button1 = (Button) findViewById(R.id.action_button1);
        button2 = (Button) findViewById(R.id.action_button2);
        button3 = (Button) findViewById(R.id.action_button3);
        textView1 = (TextView) findViewById(R.id.action_textview1);

        button1.setOnClickListener(this);
//		button2.setOnClickListener(this);
//		button3.setOnClickListener(this);

        button2.setOnClickListener(new OnButtonClickListener());

        button3.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
//				textView1.setText("匿名内部类实现点击事件处理>>>>>>>");
                Toast.makeText(ActionListnerActivity.this, "匿名内部类实现点击事件处理>>>>>>>", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void onButton4Click(View v) {
        textView1.setText("基于Android事件实现点击事件处理>>>>>>>");
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.action_button1:
                textView1.setText("接口实现点击事件处理>>>>>>>Button 1    " + id);
                break;
            case R.id.action_button2:
                textView1.setText("接口实现点击事件处理>>>>>>>Button 2    " + id);
                break;
            case R.id.action_button3:
                textView1.setText("接口实现点击事件处理>>>>>>>Button 3     " + id);
                break;
        }

    }

    class OnButtonClickListener implements OnClickListener {
        @Override
        public void onClick(View v) {
            textView1.setText("内部类实现点击事件处理>>>>>>>");
        }
    }
}
