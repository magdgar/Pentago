package com.example.makda.pentago.network;

import android.app.Activity;
import android.os.Bundle;

import com.example.makda.pentago.network.PentagoSpiceRetrofitService;
import com.octo.android.robospice.SpiceManager;

/**
 * Created by Makda on 2016-01-18.
 */
public class SpiceActivity extends Activity {
    private SpiceManager spiceManager = new SpiceManager(PentagoSpiceRetrofitService.class);
    //private SpiceManager bitmapSpiceManager = new SpiceManager(DoDocBitmapSpiceService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        spiceManager.setFailOnCacheError(false);
    }

    @Override
    protected void onStart() {
        super.onStart();
        spiceManager.start(this);
        // bitmapSpiceManager.start(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        spiceManager.shouldStop();
        // bitmapSpiceManager.shouldStop();
    }

    public final SpiceManager getSpiceManager() {
        return spiceManager;
    }

   /* public final SpiceManager getBitmapSpiceManager() {
        return bitmapSpiceManager;
    }*/
}
