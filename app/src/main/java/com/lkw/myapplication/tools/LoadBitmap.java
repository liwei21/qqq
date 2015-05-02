package com.lkw.myapplication.tools;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.widget.ImageView;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.callback.BitmapLoadCallBack;
import com.lidroid.xutils.bitmap.callback.BitmapLoadFrom;

/**
 * Created by LKW on 2015/5/1.
 */
public class LoadBitmap {
    public static void getBitmap(String url,ImageView imageView,Context context){
        BitmapUtils bitmapUtils = new BitmapUtils(context, Environment.getExternalStorageDirectory().getAbsolutePath(),
                1 / 8.0f, 1024 * 1024);
        bitmapUtils.display(imageView,url,new BitmapLoadCallBack<ImageView>() {
            @Override
            public void onLoadCompleted(ImageView imageView, String s, Bitmap bitmap, BitmapDisplayConfig bitmapDisplayConfig, BitmapLoadFrom bitmapLoadFrom) {
                imageView.setImageBitmap(bitmap);
            }

            @Override
            public void onLoadFailed(ImageView imageView, String s, Drawable drawable) {

            }
        });
    }
}
