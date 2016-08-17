package com.udacity.gradle.builditbigger;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.util.Pair;
import android.util.Log;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.jokes.myownjokes.jokend.myApi.MyApi;

import java.io.IOException;

import jokes.com.getjokes.JokesActivity;

/**
 * Created by Arjun on 25-Jul-2016 for FinalProject.
 */

class EndpointsAsyncTask extends AsyncTask<Void, Void, String> {
    private static MyApi myApiService = null;
    private Context context;
    private boolean showAd;
    private ProgressDialog dialog;
    private String name;

    public EndpointsAsyncTask(Context context, String name, boolean showAd) {
        this.showAd = showAd;
        this.context = context;
        this.name = name;
    }
    public void onPreExecute() {
        dialog = new ProgressDialog(context, AlertDialog.THEME_HOLO_DARK);
        dialog.setMessage("Getting an awesome joke...");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);
        if(!showAd)
            dialog.show();
    }

    @Override
    protected String doInBackground(Void... params) {
        return getAJoke(name);
    }

    @Override
    protected void onPostExecute(String result) {
        if(dialog.isShowing())
            dialog.dismiss();
        context.startActivity(new Intent(context, JokesActivity.class).putExtra("JOKE", result != null ? result : "Something went wrong... :("));
    }

    public static String getAJoke(String name) {
        if (myApiService == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null).setRootUrl("https://cloudjokes.appspot.com/_ah/api/");
            myApiService = builder.build();
        }

        try {
            Log.d("JOKING", "Getting an awesome joke...");
            return myApiService.sayHi(name).execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }
}
