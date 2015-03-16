package com.scxh.android.pattern;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.scxh.android.R;

public class ObserviesDemoActivity extends Activity {
    private TextView showTxt;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pattern_main);
        showTxt = (TextView)findViewById(R.id.showtxt);
        
        Blogger blogger = new Blogger();
        ObserverOne observerOne = new ObserverOne();
        ObserverTwo observerTwo = new ObserverTwo();
        ObserverThree observerThree = new ObserverThree();
  
        blogger.addObserver(observerOne);
        blogger.addObserver(observerTwo);
        blogger.addObserver(observerThree);
        
        blogger.removeObserver(observerOne);
        
        blogger.writeNewBlog("欢迎阅读我的新blog");

    }
	
	
	class ObserverOne implements Observer{

		@Override
		public void update(String blog) {
			showTxt.append("ObserverOne :"+ blog);
		}
		
	}
	
	class ObserverTwo implements Observer{

		@Override
		public void update(String blog) {
			showTxt.append("ObserverTwo :"+ blog);
		}
		
	}
}