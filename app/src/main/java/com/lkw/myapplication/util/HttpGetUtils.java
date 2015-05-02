package com.lkw.myapplication.util;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;



import android.os.Handler;
import android.util.Log;

public class HttpGetUtils {

	private static Handler handler = new Handler();
	private static boolean isQuestOk = false;
	public interface OnNetStrDataListener {
		void successed(String result);
		void failed();
	}



	public static void getJSONString(final String strUrl,
			final OnNetStrDataListener onNetStrDataListener) {
		// TODO Auto-generated method stub
		new Thread() {
			public void run() {
				HttpClient client = new DefaultHttpClient();
				HttpGet get = new HttpGet(strUrl);
				try {
					HttpResponse response = client.execute(get);
					if (response.getStatusLine().getStatusCode() == 200) {
					HttpEntity entity = response.getEntity();
						final String string = EntityUtils.toString(entity);

						isQuestOk = true;

					
						handler.post(new Runnable() {

							@Override
							public void run() {
								onNetStrDataListener.successed(string);
							}
						});
					}
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {

					if (!isQuestOk) {
						handler.post(new Runnable() {

							@Override
							public void run() {
								onNetStrDataListener.failed();
							}
						});
					}
				}

			};
		}.start();
		
		
	}







}
