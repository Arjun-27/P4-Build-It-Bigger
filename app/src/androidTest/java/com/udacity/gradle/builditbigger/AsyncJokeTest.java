package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;
import android.test.AndroidTestCase;
import android.test.UiThreadTest;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.jokes.myownjokes.jokend.myApi.MyApi;

import junit.framework.Assert;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by Arjun on 17-Aug-2016 for FinalProject.
 */
public class AsyncJokeTest extends AndroidTestCase {
    String testResult;
    CountDownLatch latch;

    protected void setUp() throws Exception {
        super.setUp();
        testResult = "";

        latch = new CountDownLatch(1);
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

    @UiThreadTest
    public final void testIt() throws Throwable {
        Log.d("TEST", "Starting AsyncTaskTest...");

        new AsyncTask<String, String, String>() {

            @Override
            protected String doInBackground(String... params) {
                return EndpointsAsyncTask.getAJoke("Test");
            }

            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                testResult = result;
                latch.countDown();
            }

        }.execute("Test");

        latch.await(15, TimeUnit.SECONDS);

        Assert.assertNotSame(testResult, "");
        Log.d("TEST", "AsyncTaskTest Completed...");
    }
}
