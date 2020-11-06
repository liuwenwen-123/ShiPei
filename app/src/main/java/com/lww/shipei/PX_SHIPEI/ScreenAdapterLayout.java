package com.lww.shipei.PX_SHIPEI;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class ScreenAdapterLayout extends RelativeLayout {
    public static boolean flag;
    private WindowManager windowManager;


    public ScreenAdapterLayout(Context context) {
        super(context);
    }

    public ScreenAdapterLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScreenAdapterLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        Configuration mConfiguration = this.getResources().getConfiguration(); //获取设置的配置信息
        int ori = mConfiguration.orientation; //获取屏幕方向
        if (ori == mConfiguration.ORIENTATION_LANDSCAPE) {
            //横屏
        } else if (ori == mConfiguration.ORIENTATION_PORTRAIT) {
            //竖屏
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        if (!flag) { // 只 计算一次
//        获取 缩放比例

            float scalyX = Utils.getInstance(getContext()).getHorizationScal();
            float scalY = Utils.getInstance(getContext()).getVerticalnScal();
            Log.e("eee ", scalyX + "---" + scalY);
            int childCount = getChildCount();
            for (int a = 0; a < childCount; a++) {
//            获取每个 view
                View childAt = getChildAt(a);
//             获取view 的 属性
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
//            根据缩放比例 重新给view 设置宽高 间距 等
                Log.e("eeee layoutParams width", layoutParams.width + "---" + scalyX);

                layoutParams.width = (int) (layoutParams.width * scalyX);
                layoutParams.height = (int) (layoutParams.height * scalY);
                layoutParams.leftMargin = (int) (layoutParams.leftMargin * scalyX);
                layoutParams.rightMargin = (int) (layoutParams.rightMargin * scalyX);
                layoutParams.topMargin = (int) (layoutParams.topMargin * scalY);
                layoutParams.bottomMargin = (int) (layoutParams.bottomMargin * scalY);

            }
            flag = true;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

  /*
        一般不用考虑 横竖屏

  @Override
    protected void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        flag = false;

        if (windowManager == null) {
            Log.e("eee ", "windowManager==null");
            windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.e("eee 横屏 ", displayMetrics.widthPixels + "---" + displayMetrics.heightPixels);
            Utils.getInstance(getContext()).setmDisplayWidth(displayMetrics.widthPixels);
            Utils.getInstance(getContext()).setmDisplayheight(displayMetrics.heightPixels);
        } else {
            Log.e("eee 竖屏 ", "kk==" + displayMetrics.widthPixels + "---" + displayMetrics.heightPixels);
            Utils.getInstance(getContext()).setmDisplayWidth(displayMetrics.widthPixels);
            Utils.getInstance(getContext()).setmDisplayheight(displayMetrics.heightPixels);
        }

    }*/


}
