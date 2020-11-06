package com.lww.shipei.PX_SHIPEI;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

public class Utils {
    private static Utils utils;
    private int mDisplayWidth;
    private int mDisplayheight;
    //    参考（设计稿） 设备的宽高
    private static final float STAND_WIDTH = 1080;
    private static final float STAND_HEIGHT = 1920;

    public static Utils getInstance(Context context) {
        if (utils == null) {
            utils = new Utils(context);
        }
        return utils;
    }

    private Utils(Context context) {
        if (mDisplayWidth == 0 || mDisplayheight == 0) {
            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            if (displayMetrics.widthPixels > displayMetrics.heightPixels) {
                //横屏
                  setmDisplayWidth(displayMetrics.heightPixels); ;
                  setmDisplayheight(displayMetrics.widthPixels);
            } else {
//                竖屏
                setmDisplayWidth(displayMetrics.widthPixels); ;
                setmDisplayheight(displayMetrics.heightPixels);
            }
        }
    }

    //  获取 状态栏
    public int getStatueBar(Context context) {
        int resId = context.getResources().getIdentifier("status bar height", "dimen",
                "android");
        if (resId > 0) {
            return context.getResources().getDimensionPixelSize(resId);
        }
        return 0;
    }

    //     获取 水平方向 缩放比例
    public float getHorizationScal() {
        Log.e("eee 宽",mDisplayWidth+"---");
        return mDisplayWidth / STAND_WIDTH;
    }

    //     获取 垂直方向 缩放比例
    public float getVerticalnScal() {
        Log.e("eee 高 ",mDisplayheight+"---");
        return mDisplayheight / STAND_HEIGHT;
    }



    public void setmDisplayWidth(int mDisplayWidth) {
        this.mDisplayWidth = mDisplayWidth;
    }
    public void setmDisplayheight(int mDisplayheight) {
        this.mDisplayheight = mDisplayheight;
    }
}
