package NetworkData;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by aaa on 15-4-30.
 */
public class HomeJsonString {
    private static String homejsonstring;

    public static String getHomeJsonString() throws IOException {
        Log.d("!!!!进入getHomeJsonString方法","。");
        final String path = "http://api.zhongchou.cn/index/home";

        new Thread() {
            @Override
            public void run() {
                UrlEncodedFormEntity entity = null;    //pairs:请求参数   encoding:编码方式
                try {
//                    entity = new UrlEncodedFormEntity(null, "UTF-8");
                    HttpPost httpPost = new HttpPost(path); //path:请求路径
//                    httpPost.setEntity(entity);
                    httpPost.addHeader("DIVERSION-VERSION", "2");
//                    httpPost.addHeader("Content-Length", "0");
                    httpPost.addHeader("Host", "api.zhongchou.cn");
                    httpPost.addHeader("Connection", "Keep-Alive");
                    httpPost.addHeader("User-Agent", "\u0017y 4.0 android18");
                    httpPost.addHeader("Cookie", "PHPSESSID=9lsrbkd3tr816b1im6qnqie5d1\n");
                    httpPost.addHeader("Cookie2", "$Version=1");
                    DefaultHttpClient client = new DefaultHttpClient(); //相当于浏览器
                    HttpResponse response = client.execute(httpPost);  //相当于执行POST请求

                    Log.v("--DEBUG--","方法执行查看--->");
                    if (response.getStatusLine().getStatusCode() == 200) {
                        HttpEntity entity1 = response.getEntity();
                        homejsonstring = EntityUtils.toString(entity1);

                        Log.v("--------DEBUG--", " ------secondWay - >" + homejsonstring);
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        if (homejsonstring != null) {
            return homejsonstring;
        } else {
            return null;
        }
    }
}
