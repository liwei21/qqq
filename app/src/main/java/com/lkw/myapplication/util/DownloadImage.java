package com.lkw.myapplication.util;

import java.io.File;
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

public class DownloadImage {
	private ImageView imgv;
	private String imgUrl;

	public DownloadImage(ImageView imgv, String imgUrl) {
		super();
		this.imgv = imgv;
		this.imgUrl = imgUrl;
	}

	public void downLoadInternetImg() {
		if (SDCardUtils.isSDCardOk()) {
			File imgFileP = new File(SDCardUtils.getSDCardPath(), "img");

			if (!imgFileP.exists()) {
				new ImgAsync().execute();
			} else {
				File imgFile = new File(imgFileP, imgUrl.substring(imgUrl
						.lastIndexOf("/") + 1));
				if (imgFile.exists()) {
					Bitmap bitmap = BitmapFactory.decodeFile(imgFile
							.getAbsolutePath());
				
					imgv.setImageBitmap(bitmap);
//					return bitmap;
					

				} else {
					new ImgAsync().execute();
				}

			}

		}
//		return null;
	}

	class ImgAsync extends AsyncTask<String, Integer, Bitmap> {

		@Override
		protected Bitmap doInBackground(String... params) {
			HttpClient client = new DefaultHttpClient();
			HttpGet get = new HttpGet(imgUrl);
			try {
				HttpResponse response = client.execute(get);
				if (response.getStatusLine().getStatusCode() == 200) {
					HttpEntity entity = response.getEntity();
					byte[] byteArray = EntityUtils.toByteArray(entity);

					SDCardUtils.saveData(byteArray, "img",
							imgUrl.substring(imgUrl.lastIndexOf("/") + 1));

					Bitmap bitmap = BitmapUtil//图片压缩
							.decodeSampledBitmapFromByteArray(byteArray,
									new BitmapSize(100, 100),
									Bitmap.Config.RGB_565);
					return bitmap;

					
					
					

				}

			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return null;
		}

	}

}
