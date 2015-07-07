package io.realm.examples.intro;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by TheFinestArtist on 7/7/15.
 */
public class Preferences {
    public static final String SHARED_PREFERENCE_NAME = "Benchmark";

    private static SharedPreferences mPref = null;
    private static final Object mSingletonLock = new Object();

    private static SharedPreferences getInstance(Context context) {
        synchronized (mSingletonLock) {
            if (mPref != null)
                return mPref;

            if (context != null)
                mPref = context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);

            return mPref;
        }
    }

    public static void setData(Context context, String jsonString) {
        SharedPreferences.Editor edit = getInstance(context).edit();
        edit.putString("Data", jsonString);
        edit.apply();
    }
}
