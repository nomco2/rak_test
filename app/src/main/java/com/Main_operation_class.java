package com;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.jean.rakvideotest.R;
import com.example.jean.video.VideoPlay_import_class;
import com.demo.sdk.DisplayView;







public class Main_operation_class extends Activity{


    private com.demo.sdk.DisplayView _videoView;
    private com.demo.sdk.DisplayView _videoView2;


    KeyguardManager mKeyguardManager = null;
    private PowerManager pm;

    VideoPlay_import_class videoPlay_import_class;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        mKeyguardManager = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_operaion_class);




        _videoView=(com.demo.sdk.DisplayView)findViewById(R.id.video_view);
        _videoView.setOnClickListener(_videoView_Click);
        _videoView2=(com.demo.sdk.DisplayView)findViewById(R.id.video_view2);
        _videoView2.setOnClickListener(_videoView_Click);


        String[] connection_info = {
                "devicename", // 수정해야됨
                "deviceid", // 수정해야됨
                "127.0.0.1", //고정값임
                "devicepsk", //수정해야됨
                "version"//수정해야됨
        };
//        videoPlay_import_class = new VideoPlay_import_class(this, pm, mKeyguardManager, );


    }


    View.OnClickListener _videoView_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            if(_videoTitle.getVisibility()==View.VISIBLE){
//                _videoTitle.setVisibility(View.GONE);
//                _videoChangePipe.setVisibility(View.GONE);
//                _videoControl.setVisibility(View.GONE);
//            }
//            else{
//                _videoTitle.setVisibility(View.VISIBLE);
//                _videoControl.setVisibility(View.VISIBLE);
//            }
        }
    };

}
