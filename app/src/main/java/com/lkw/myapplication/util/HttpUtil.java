package com.lkw.myapplication.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.os.Handler;
import android.util.Log;

public class HttpUtil {
	private static Handler handler = new Handler();
	private static boolean isQuestOk = false;

	public interface OnNetStrDataListener {
		void successed(String result);

		void failed();
	}

	public static void getNetData(final HashMap<String, String> strUrl,
			final OnNetStrDataListener listenr) {
		new Thread() {
			public void run() {
				String path = null;
				List<NameValuePair> data = new ArrayList<NameValuePair>();

				for (Map.Entry<String, String> entry : strUrl.entrySet()) {
					if ("path".equals(entry.getKey())) {
						path = entry.getValue();
					} else {
						NameValuePair valuePair = new BasicNameValuePair(
								entry.getKey(), entry.getValue());
						data.add(valuePair);
					}
				}

				HttpClient client = new DefaultHttpClient();
				HttpPost post = new HttpPost(path);

				try {
					post.setEntity(new UrlEncodedFormEntity(data));
					HttpResponse response = client.execute(post);

					if (response.getStatusLine().getStatusCode() == 200) {
						HttpEntity entity = response.getEntity();
					
						final String result = EntityUtils.toString(entity);

						isQuestOk = true;

						handler.post(new Runnable() {

							@Override
							public void run() {
								listenr.successed(result);
							}
						});
					}

				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {

					if (!isQuestOk) {
						handler.post(new Runnable() {

							@Override
							public void run() {
								listenr.failed();
							}
						});
					}
				}

			};
		}.start();
	}

}
