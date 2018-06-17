package com.wili.android.bakingapp.utils;

import android.app.Activity;
import android.util.DisplayMetrics;

public class Utils {
    public static boolean isTablet(Activity activity){
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        float y = metrics.heightPixels/metrics.ydpi;
        float x = metrics.widthPixels/metrics.xdpi;
        double diagonalInches = Math.sqrt(x*x + y*y);
        if (diagonalInches>=6.5){
            return true;
        }
        return false;
    }
}
