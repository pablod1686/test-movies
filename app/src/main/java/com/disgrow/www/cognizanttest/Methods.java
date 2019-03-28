package com.disgrow.www.cognizanttest;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

public class Methods {

    public static void setParamsView(View v,int w, int h){

        ViewGroup.LayoutParams params = v.getLayoutParams();
        if(w!=0)params.width = w;
        if(h!=0)params.height = h;
        v.setLayoutParams(params);

    }

    public static void setMargenes(View v, int izquierda, int arriba, int derecha, int abajo) {
        ViewGroup.MarginLayoutParams llhelpParams = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
        llhelpParams.setMargins(izquierda, arriba, derecha, abajo);
    }

    public static int getHeightScreen(Activity activity, Resources resources) {

        int heightScreen;

        WindowManager windowManager = (WindowManager) activity.getApplication().getSystemService(Context.WINDOW_SERVICE);
        final Display display = windowManager.getDefaultDisplay();
        Point outPoint = new Point();

        if (Build.VERSION.SDK_INT >= 19) {
            display.getRealSize(outPoint);
        }else {
            display.getSize(outPoint);
        }
        if (outPoint.y > outPoint.x) {
            heightScreen = outPoint.y;
        } else {
            heightScreen = outPoint.x;
        }

        int result = 0;
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = resources.getDimensionPixelSize(resourceId);
        }

        heightScreen = heightScreen - result;
        return heightScreen;
    }

    public static int getWidthScreen(Activity activity){

        int widthScreen;

        WindowManager windowManager = (WindowManager) activity.getApplication().getSystemService(Context.WINDOW_SERVICE);
        final Display display = windowManager.getDefaultDisplay();
        Point outPoint = new Point();

        if (Build.VERSION.SDK_INT >= 19) {
            display.getRealSize(outPoint);
        }else {
            display.getSize(outPoint);
        }

        if (outPoint.y > outPoint.x) {
            widthScreen = outPoint.x;
        } else {
            widthScreen = outPoint.y;
        }

        return widthScreen;
    }

    public static Bitmap resizeImage(Bitmap mBitmap, float newWidth, float newHeigth) {

        int width = mBitmap.getWidth();
        int height = mBitmap.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeigth) / height;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        return Bitmap.createBitmap(mBitmap, 0, 0, width, height, matrix, false);

    }

}
