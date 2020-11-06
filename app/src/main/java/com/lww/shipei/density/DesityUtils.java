package com.lww.shipei.density;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.util.DisplayMetrics;

import androidx.annotation.NonNull;

public class DesityUtils {
    private static final float WIDTH = 320; // 参考设备 的 像素密度 dpi 安卓 默认160
    private static float appDensity;  //  屏幕密度
    private static float appScaleDensity;  // 字体缩放比例 默认是 appDensity

    public static void setDensity(final Application application, Activity activity) {
//         获取当前app的屏幕信息
        DisplayMetrics displayMetrics = application.getResources().getDisplayMetrics();
//       初始化 赋值操作
        if (appDensity == 0) {
            appDensity = displayMetrics.density;
            appScaleDensity = displayMetrics.scaledDensity;
//            添加 字体变化家监听 回调
            application.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(@NonNull Configuration newConfig) {
                    //  表示字体发生改变  重新对ScaleDensity进行赋值
                    if (newConfig!=null && newConfig.fontScale>0) {
                        appScaleDensity=application.getResources().getDisplayMetrics().scaledDensity;
                    }
                }

                @Override
                public void onLowMemory() {


                }
            });
        }
//        计算 目标值  density  scaldensity   densityDpi
//      缩放比例
        float targetDensity = displayMetrics.widthPixels / WIDTH;  // 1080/360 =3.0
//        字体的缩放比例
        float targetScaldensity = targetDensity * (appScaleDensity / appDensity);
//        屏幕像素密度
//        Android将mdpi即中密度作为基准线，即以密度160 dpi 作为参考值。
//        // 例如  缩放比例是 3  说明当前屏幕 宽度 = 720
        int targetDensityDpi = (int) (targetDensity * 160);

//        替换 acticity的   density  scaldensity   densityDpi
        DisplayMetrics dm = activity.getResources().getDisplayMetrics();
        dm.density = targetDensity;
        dm.scaledDensity = targetScaldensity;
        dm.densityDpi = targetDensityDpi;
    }
}
