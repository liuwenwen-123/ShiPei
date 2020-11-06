package com.lww.shipei.shipeiliuhai;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.DisplayCutout;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.lww.shipei.R;

public class DisplayCutouActivity extends AppCompatActivity {


    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


//       1  设置全屏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        /**
         *    1：  判断手机厂商
         *    2:   判断手机是否有 刘海屏
         *    3：设置是否让内筒区域 延伸到 刘海屏
         *    4:  设置控件 是否 避开刘海 区域
         *    5： 获取刘海屏 高度
         */

//       2  判断手机是否有 刘海屏
        boolean hasDisplayCutou = hasDisplayCutou(window);
        if (hasDisplayCutou == true) {


//      3  让内容区域 延伸到刘海屏
            WindowManager.LayoutParams layoutParams = window.getAttributes();
//        全屏模式  内容下移 非全屏不受 影响
            layoutParams.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_DEFAULT;
//        不允许进去 刘海屏
//        layoutParams.layoutInDisplayCutoutMode =WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_NEVER;
            layoutParams.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
            window.setAttributes(layoutParams);
//  4  设置 沉浸式
            int flag = View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            int systemUiVisibility = window.getDecorView().getSystemUiVisibility();
            systemUiVisibility |= flag;
            window.getDecorView().setSystemUiVisibility(systemUiVisibility);

        }
        setContentView(R.layout.activity_display_cutou);

//         如果内容区域 的额 btn 或者 text  被刘海遮挡
        RelativeLayout viewById = findViewById(R.id.rl_root);
        viewById.setPadding(viewById.getPaddingLeft(),heightForDisplayCutoy(),viewById.getRight(),viewById.getPaddingBottom());
    }


    private boolean hasDisplayCutou(Window window) {
        DisplayCutout displayCutout;
        View rootView = window.getDecorView();
        WindowInsets rootWindowInsets = null;

        if (Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            rootWindowInsets = rootView.getRootWindowInsets();
            if (rootWindowInsets != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                displayCutout = rootWindowInsets.getDisplayCutout();
                if (displayCutout != null) {
                    if (displayCutout.getBoundingRects() != null &&
                            displayCutout.getBoundingRects().size() > 0 &&
                            displayCutout.getSafeInsetTop() > 0) {
                        return true;
                    }
                }
            }


        }
        return false;
    }

//     通常 情况 刘海屏的高度就是 状态栏高
    public int heightForDisplayCutoy() {
//           displayCutout.getSafeInsetTop()  这个方法 也可以获取 刘海 高度
        int resId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resId > 0) {
            return getResources().getDimensionPixelSize(resId);
        }
        return  36;
    }
}