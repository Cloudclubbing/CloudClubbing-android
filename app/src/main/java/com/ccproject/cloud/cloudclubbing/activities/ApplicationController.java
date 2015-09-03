package com.ccproject.cloud.cloudclubbing.activities;

import android.app.Application;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.ccproject.cloud.cloudclubbing.models.Events;
import com.ccproject.cloud.cloudclubbing.models.Newsfeeds;
import com.ccproject.cloud.cloudclubbing.tools.HttpRequest;

import org.apache.http.impl.client.DefaultHttpClient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by priteshasvinetsakou on 19/03/15.
 */

public class ApplicationController extends Application {

    public static final String TAG = "VolleyPatterns";

    private RequestQueue mRequestQueue;

    private static          ApplicationController           sInstance;
    private                 DefaultHttpClient               mHttpClient;
    private                 ImageLoader                     imageLoader;
    private                 List<Events>                    eventsList;
    private                 List<Newsfeeds>                 newsfeedsList;


    private static HttpRequest my_httpRequest;


    @Override
    public void onCreate() {
        super.onCreate();

        // initialize the singleton
        sInstance = this;
        eventsList = new ArrayList<Events>();
        newsfeedsList = new ArrayList<Newsfeeds>();
        this.imageLoader = new ImageLoader(this.getRequestQueue(), new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap> cache = new LruCache<String, Bitmap>(20);
            @Override
            public Bitmap getBitmap(String url) {
                return cache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                cache.put(url, bitmap);
            }
        });
    }

    /**
     * @return ApplicationController singleton instance
     */
    public static synchronized ApplicationController getInstance() {
        return sInstance;
    }


    public RequestQueue getRequestQueue() {
        // lazy initialize the request queue, the queue instance will be
        // created when it is accessed for the first time
        if (mRequestQueue == null) {
            mHttpClient = new DefaultHttpClient();
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }


    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);

        VolleyLog.d("Adding request to queue: %s", req.getUrl());

        getRequestQueue().add(req);
    }


    public <T> void addToRequestQueue(Request<T> req) {
        // set the default tag if tag is empty
        req.setTag(TAG);

        getRequestQueue().add(req);
    }

    public void initializeRequestQueue() {

        my_httpRequest.getRequest();

    }


    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
    public List<Events> getEventsList() {
        return eventsList;
    }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }

    public List<Newsfeeds> getNewsfeedsList() {
        return newsfeedsList;
    }

}
