package com.lww.shipei.baifenbi;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.lww.shipei.R;

public class PercentLayout extends RelativeLayout {
    public PercentLayout(Context context) {
        super(context);
    }

    public PercentLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PercentLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

//        获取父容器的大小
        int parentWidth = MeasureSpec.getSize(widthMeasureSpec);
        int parentHeight = MeasureSpec.getSize(heightMeasureSpec);

        int childCount = getChildCount();
        for (int a = 0; a < childCount; a++) {
            View childAt = getChildAt(a);
            ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
//           如果是 百分比 布局属性
            if (checkLayoutParams(layoutParams)) {
                LayoutParams lp = (LayoutParams) layoutParams;
                float widthPercent = lp.widthPercent;
                float heightPercent = lp.heightPercent;
                float marginLeftPercent = lp.marginLeftPercent;
                float marginRightPercent = lp.marginRightPercent;
                float marginTopPercent = lp.marginTopPercent;
                float marginBottomPercent = lp.marginBottomPercent;

                if (widthPercent > 0) {
                    layoutParams.width= (int) (parentWidth * widthPercent);
                }
                if (heightPercent > 0) {
                    layoutParams.height = (int) (parentHeight * heightPercent);
                }

                if (marginLeftPercent > 0) {
                    ((LayoutParams) layoutParams).leftMargin = (int) (parentWidth * marginLeftPercent);
                }

                if (marginRightPercent > 0) {
                    ((LayoutParams) layoutParams).rightMargin = (int) (parentWidth * marginRightPercent);
                }

                if (marginTopPercent > 0) {
                    ((LayoutParams) layoutParams).topMargin= (int) (parentHeight * marginTopPercent);
                }

                if (heightPercent > 0) {
                    ((LayoutParams) layoutParams).bottomMargin=(int) (parentHeight * marginBottomPercent);
                }
            }

        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }


    @Override
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return p instanceof LayoutParams;
    }

    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    public static class LayoutParams extends RelativeLayout.LayoutParams {

        private float widthPercent;
        private float heightPercent;
        private float marginLeftPercent;
        private float marginRightPercent;
        private float marginTopPercent;
        private float marginBottomPercent;


        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            TypedArray a = c.obtainStyledAttributes(attrs, R.styleable.PercentLayout);
            widthPercent = a.getFloat(R.styleable.PercentLayout_widthPercent, 0);
            heightPercent = a.getFloat(R.styleable.PercentLayout_heightPercent, 0);
            marginLeftPercent = a.getFloat(R.styleable.PercentLayout_marginLeftPercent, 0);
            marginRightPercent = a.getFloat(R.styleable.PercentLayout_marginRightPercent, 0);
            marginTopPercent = a.getFloat(R.styleable.PercentLayout_marginTopPercent, 0);
            marginBottomPercent = a.getFloat(R.styleable.PercentLayout_marginBottomPercent, 0);
            a.recycle();
        }
    }
}
