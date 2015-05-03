package com.lkw.myapplication.tools;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by jash on 15-4-15.
 */
public class ImageViewWithWidth extends ImageView {

    public ImageViewWithWidth(Context context) {
        super(context);
    }

    public ImageViewWithWidth(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ImageViewWithWidth(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Drawable drawable = getDrawable();
        if (drawable != null && drawable.getIntrinsicWidth() != 0) {
            int wMode = MeasureSpec.getMode(widthMeasureSpec);//模式
            int wSize = MeasureSpec.getSize(widthMeasureSpec);//大小
            int hMode = MeasureSpec.getMode(heightMeasureSpec);
            int hSize = MeasureSpec.getSize(heightMeasureSpec);
            if (wMode == MeasureSpec.EXACTLY && hMode != MeasureSpec.EXACTLY){
                hSize = drawable.getIntrinsicHeight() * wSize / drawable.getIntrinsicWidth();
                heightMeasureSpec = MeasureSpec.makeMeasureSpec(hSize, MeasureSpec.EXACTLY);
            }
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
