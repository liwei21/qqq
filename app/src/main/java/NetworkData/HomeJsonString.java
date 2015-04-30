package NetworkData;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by aaa on 15-4-30.
 */
public class HomeJsonString {
    private String homejsonstring=null;

    public String getHomeJsonString() throws IOException {

        final String path = "http://api.zhongchou.cn/index/home";
        new Thread() {
            @Override
            public void run() {
                try {
                    HttpPost httpPost = new HttpPost(path); //path:请求路径
                    httpPost.addHeader("DIVERSION-VERSION", "2");
//                    httpPost.addHeader("Content-Length", "0");
                    httpPost.addHeader("Host", "api.zhongchou.cn");
                    httpPost.addHeader("Connection", "Keep-Alive");
                    httpPost.addHeader("User-Agent", "\u0017y 4.0 android18");
                    DefaultHttpClient client = new DefaultHttpClient(); //相当于浏览器
                    HttpResponse response = client.execute(httpPost);  //相当于执行POST请求

                    if (response.getStatusLine().getStatusCode() == 200) {
                        HttpEntity entity1 = response.getEntity();
                        homejsonstring = EntityUtils.toString(entity1);
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
        return homejsonstring;
    }
}
