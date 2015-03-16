package com.scxh.android.mp3;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.scxh.android.R;

public class ShowPlayer extends Activity implements OnClickListener {

	private Button stopButton;
	
	private Button restartButton;
	
	private Button nextSong;
	
	private Button resetButton;
	
	private TextView title;
	
	private ProgressBar progressBar;
	
	private boolean isPlaying;
	
	public static MediaPlayer mp;
	
	private final static int NEXT = 2;
	
	private String mPath;
	
	private Handler mHandler = new Handler();
	
	private TextView mTimepos;
	
	private UpdateStatus mus; 
	
	public void onCreate(Bundle icicle) {
        super.onCreate(icicle);      
        setContentView(R.layout.mediaplayer);
        
        if(mp == null){
        	mp = new MediaPlayer();
        }else{
           mp.stop();
           mp = null;
           mp = new MediaPlayer();
        }
        
        Intent intent = getIntent();    
        Bundle bundle = intent.getBundleExtra("playPara");
        mPath = bundle.getString("songPath");
        System.out.println("mPath="+mPath);
        
    	try {
			mp.setDataSource(mPath);
			mp.prepare();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		mp.start();
		
		//======================
        initPlayerControlsView();
        isPlaying = true;
        
	    mus = new UpdateStatus(); //start update thread and media player
	    mus.start();
    } 
    
    private void initPlayerControlsView() {
    	title = (TextView) findViewById(R.id.text_kb_streamed);
		stopButton = (Button) findViewById(R.id.button_stream_stop);
		stopButton.setId(1);
		stopButton.setOnClickListener(this);
		
		restartButton = (Button) findViewById(R.id.button_stream_play);
		restartButton.setId(2);
		restartButton.setOnClickListener(this);
		restartButton.setVisibility(View.GONE);
		
		nextSong = (Button) findViewById(R.id.nextsong);
		nextSong.setId(4);
		nextSong.setOnClickListener(this);
		
		mTimepos = (TextView)findViewById(R.id.timepos);
		
   		resetButton = (Button) findViewById(R.id.button_play);
   		resetButton.setId(3);
		resetButton.setOnClickListener(this);
	    
		progressBar = (ProgressBar)findViewById(R.id.progress_bar);
	    progressBar.setOnClickListener(this);
		progressBar.setMax(mp.getDuration());
		progressBar.setProgress(mp.getCurrentPosition());

		
		
    }

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		
		case 1:
			
			mp.pause();
			
			isPlaying = false;
			
			stopButton.setVisibility(View.GONE);
			
			restartButton.setVisibility(View.VISIBLE);
			
			 break;
			
		case 2:	
		
			mp.start();
			
			isPlaying = true;
			
			stopButton.setVisibility(View.VISIBLE);
			
			restartButton.setVisibility(View.GONE);
		
		    break;	
		     
		case 3:
			
			mp.stop();
		    
		    mp.reset();
		    
	    	try {
				mp.setDataSource(mPath);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				mp.prepare();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			isPlaying = false;
			
		    progressBar.setProgress(0);
			
			stopButton.setVisibility(View.GONE);
			
			restartButton.setVisibility(View.VISIBLE);
		    
			break;
			
		case 4:
			
			this.setResult(NEXT);
			
			finish();
			
			break;
	    default :
	    	
	    	break;
		}
	}
	
    class UpdateStatus extends Thread{   	
    	@Override
		public void run() {
			while (true) {
				if( isPlaying)
					mHandler.post(new Runnable() {
						public void run(){ 								
							try {							
								progressBar.setMax(mp.getDuration());
								progressBar.setProgress(mp.getCurrentPosition());
								int pos = 0;
								
								pos = mp.getCurrentPosition(); 
								int min = (pos/1000)/60;
								int sec = (pos/1000)%60;
								
								int maxPos = mp.getDuration(); 
								int maxMin = (maxPos/1000)/60;
								int maxSec = (maxPos/1000)%60;
								
								String maxTime = new String();
								if(maxSec<10){
									maxTime = ""+maxMin+":0"+maxSec;
								}else{
									maxTime = ""+maxMin+":"+maxSec; 
								}
								
								if(sec<10)
									mTimepos.setText(""+min+":0"+sec+"/"+maxTime);
								else
									mTimepos.setText(""+min+":"+sec+"/"+maxTime);
								
							} catch (Exception e) {
								e.printStackTrace();
							}									
						}
					});
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}						
    	}
    }
    

    
}
