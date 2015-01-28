package com.example.mutarjun.notwget;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by mutarjun on 1/28/15.
 */
public class DownloadReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        final long reference = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID,0);
        final long downloadRef = MainActivity.getDref();
        Log.d("Info", "Entered onReceive");
        Log.d("Info", String.valueOf(reference));
        Log.d("Info", String.valueOf(downloadRef));

        if (downloadRef == reference) {
            Log.d("Info", "Condition Pass");
           Toast.makeText(context, "Download Complete!", Toast.LENGTH_LONG).show();
        }
    }
}
