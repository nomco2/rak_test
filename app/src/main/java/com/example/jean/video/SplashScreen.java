package com.example.jean.video;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.jean.rakvideotest.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Locale;

public class SplashScreen extends Activity {
	/**
	 * The thread to process splash screen events
	 */
	private Thread mSplashThread;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.splash);
		// Start animating the image
		final ImageView splashImageView = (ImageView) findViewById(R.id.SplashImageView);
		splashImageView.setBackgroundResource(R.drawable.splash);
		final AnimationDrawable frameAnimation = (AnimationDrawable)splashImageView.getBackground();
		splashImageView.post(new Runnable() {
			@Override
			public void run() {
				frameAnimation.start();
			}
		});

		String sta=getResources().getConfiguration().locale.getCountry().toLowerCase();
		Log.e("sta=>",sta);
		if(sta.equals("cn"))
			sta="cn";
		else
			sta="en";
		Locale myLocale = new Locale(sta);
		Resources res = getResources();
		DisplayMetrics dm = res.getDisplayMetrics();
		Configuration conf = res.getConfiguration();
		conf.locale = myLocale;
		res.updateConfiguration(conf, dm);

		mSplashThread =  new Thread(){
			@Override
			public void run(){
				try {
					synchronized(this){
						// Wait given period of time or exit on touch
						wait(3000);
					}
				}
				catch(InterruptedException ex){
				}

				finish();
				{
					// Run next activity
					Intent intent = new Intent();
					intent.setClass(SplashScreen.this, MainActivity.class);
					startActivity(intent);
				}
			}
		};

		mSplashThread.start();


		try{
			String str = "[{'name':'배트맨','distance':43,'Xangle':'고담'},"+
					"{'name':'슈퍼맨','Xangle':36,'address':'뉴욕'},"+
					"{'name':'앤트맨','Yangle':25,'height':'10000'}]";



			JSONArray jarray = new JSONArray(str);
			String distance="";
			String Xangle="";
			String Yangle="";
			String height="";
			for(int i=0; i < jarray.length(); i++){
				JSONObject jObject = jarray.getJSONObject(i);  // JSONObject 추출
				distance= jObject.getString("distance");
				Xangle= jObject.getString("Xangle");
				Yangle= jObject.getString("Yangle");
				height= jObject.getString("height");
			}

			android.widget.Toast.makeText(getApplicationContext(),(distance+Xangle+Yangle+height), android.widget.Toast.LENGTH_LONG).show();
            Log.i("json",distance+Xangle+Yangle+height);
//            Message msg = handler.obtainMessage();
//            msg.what = 100;
//            handler.sendMessage(msg);
//            Log.i("receive data", deviceUart_import_class.Receive_data);
		}catch (Exception e){
			Log.i("json",e+"");

		}





	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		super.onCreateOptionsMenu(menu);
		return false;
	}

	/**
	 * Processes splash screen touch events
	 */
	@Override
	public boolean onTouchEvent(MotionEvent evt)
	{
		if(evt.getAction() == MotionEvent.ACTION_DOWN)
		{
			synchronized(mSplashThread){
				mSplashThread.notifyAll();
			}
		}
		return true;
	}


}
