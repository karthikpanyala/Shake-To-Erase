package com.example.karthik.art_therapy_1;

import android.app.IntentService;
import android.content.Intent;
import android.media.MediaPlayer;

/**
 * Created by karthik on 3/5/16.
 */
public class MyIntentService extends IntentService {

    @Override
    protected void onHandleIntent(Intent arg0) {
        // TODO Auto-generated method stub
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.eraser);
        mp.start();


    }
    public MyIntentService() {
        super("MyIntentService");
    }


}
