package com.example.mutarjun.notwget;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.DownloadListener;
import android.widget.EditText;
import android.widget.Toast;

import static android.app.DownloadManager.*;


public class MainActivity extends ActionBarActivity {

    EditText URL;
    static long pubDRef = 0;

    DownloadReceiver downloadReceiver = new DownloadReceiver();

    protected void setDRef(long x){
        pubDRef = x;
    }

    public static long getDref(){
        return pubDRef;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startDownload(View v) {

        URL = (EditText) this.findViewById(R.id.URLbar);
        String URI = URL.getText().toString();
        String fileName = URI.substring(URI.lastIndexOf("/"), URI.length());

        String serviceString = Context.DOWNLOAD_SERVICE;

        final DownloadManager downloadManager = (DownloadManager) getSystemService(serviceString);

        Uri parsedURI = Uri.parse(URI);

        Request request = new Request(parsedURI);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName);
        request.setNotificationVisibility (1);
        final long downloadRef = downloadManager.enqueue(request);
        setDRef(downloadRef);

        IntentFilter filter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        registerReceiver(downloadReceiver, filter);
    }

    @Override
    public void onPause(){
        super.onPause();
        unregisterReceiver(downloadReceiver);
    }

    public void onResume(){
        super.onResume();
        registerReceiver(downloadReceiver, new IntentFilter(ACTION_DOWNLOAD_COMPLETE));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
