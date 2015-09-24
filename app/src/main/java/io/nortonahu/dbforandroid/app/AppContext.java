package io.nortonahu.dbforandroid.app;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

/**
 * Author:    Hong Yu
 * Version    V1.0
 * Date:      2015/9/24 09:43.
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * Why & What is modified:
 */
public class AppContext extends Application {

    private static Context mContext;

    private static Resources mResources;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        mResources = mContext.getResources();
    }

    public static Context getContext() {
        return mContext;
    }

    public static Resources getmResource() {
        return mResources;
    }

    public static String string(int resId) {
        return mResources.getString(resId);
    }

    public static String[] stringArray(int resId) {
        return mResources.getStringArray(resId);
    }


}
