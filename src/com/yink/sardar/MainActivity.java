package com.yink.sardar;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;


public class MainActivity extends Activity {

    public Compressor com;
    String cmd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cmd = "-y -i /mnt/sdcard/videokit/in1.mp4 -strict -2 -vcodec libx264 -preset ultrafast -crf 24 -acodec aac -ar 44100 -ac 2 -b:a 96k -s 640x352 -aspect 16:9 /mnt/sdcard/videokit/out8.mp4";
        com = new Compressor(this);

        com.loadBinary(new InitListener() {
            @Override
            public void onLoadSuccess() {
                com.execCommand(cmd,new CompressListener() {
                    @Override
                    public void onExecSuccess(String message) {
                        Log.i("success",message);
                    }

                    @Override
                    public void onExecFail(String reason) {
                        Log.i("fail",reason);
                    }

                    @Override
                    public void onExecProgress(String message) {
                        Log.i("progress",message);
                    }
                });
            }

            @Override
            public void onLoadFail(String reason) {
                Log.i("fail",reason);
            }
        });

    }

}