package com.lkw.myapplication.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

/**
 * 
 * @类名称: BitmapUtil
 * @类描述: 关于图片的工具类
 */

public class BitmapUtil {

	//设置图片采样率,返回Bitmap的方法
	public static Bitmap decodeSampledBitmapFromByteArray(byte[] data,
			BitmapSize maxSize, Bitmap.Config config) {
		
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;// 解码边缘区域,不分配内存,只获取图片的宽度和高度
		options.inPurgeable = true;
		options.inInputShareable = true;
		BitmapFactory.decodeByteArray(data, 0, data.length, options);// 此时返回的Bitmap为null,因为并没有真实的分配内存
		// 设置采样率
		options.inSampleSize = calculateInSampleSize(options,
				maxSize.getWidth(), maxSize.getHeight());
		options.inJustDecodeBounds = false;
		if (config != null) {
			options.inPreferredConfig = config;
		}

		try {
			return BitmapFactory.decodeByteArray(data, 0, data.length, options);
		} catch (Throwable e) {
			Log.i("TAG", e.getMessage());
			return null;
		}
	}

	// 计算图片采用率:根据图片真实的宽和高,来动态的计算出采样率
	public static int calculateInSampleSize(BitmapFactory.Options options,
			int maxWidth, int maxHeight) {
		//图片真实的宽度和高度
		final int height = options.outHeight;
		final int width = options.outWidth;
		//设置默认的采样率
		int inSampleSize = 1;

		Log.i("TAG", "maxWidth=" + maxWidth + ",maxHeight=" + maxHeight);
		Log.i("TAG", "height=" + height + ",width=" + width);

		if (width > maxWidth || height > maxHeight) {
			//在宽和高中,选一个小的值来作为采样率
			if (width > height) {
				// round(n):将n+0.5后向下取整.例如n=3.2,Math.round(3.2);
				inSampleSize = Math.round((float) height / (float) maxHeight);
			} else {
				inSampleSize = Math.round((float) width / (float) maxWidth);
			}

			//如果采样率太小,++,变大一些
			final float totalPixels = width * height;

			final float maxTotalPixels = maxWidth * maxHeight * 2;

			while (totalPixels / (inSampleSize * inSampleSize) > maxTotalPixels) {
				inSampleSize++;
			}
		}

		Log.i("TAG", "inSampleSize=" + inSampleSize);

		return inSampleSize;
	}
}
