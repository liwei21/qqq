package ParaserJson;

import android.util.Log;

import com.lidroid.xutils.exception.DbException;
import com.lkw.myapplication.tools.DbHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Bean.Adv;
import Bean.Cate;
import Bean.News;
import Bean.Special;
import NetworkData.HomeJsonString;

/**
 * Created by aaa on 15-5-1.
 */
public class HomeContentData {
    public static void paraserHomeJsonString() {
        Log.d("!!!!调用从网络获取数据的方法-->", "开始");
        try {
            String homeJsonString = HomeJsonString.getHomeJsonString();
            if(homeJsonString==null){
                Log.d("!!!!homeJsonString-->","null");
            }else{
                Log.d("!!!!homeJsonString-->",homeJsonString);
                parserData(homeJsonString);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void parserData(String homeJsonString) {
        JSONObject obj= null;
        try {
            obj = new JSONObject(homeJsonString);

        JSONObject dataObj=obj.getJSONObject("data");

        JSONArray advArr=dataObj.getJSONArray("adv");
        List<Adv> advList=new ArrayList<>();
        for(int i=0;i<advArr.length();i++){
            JSONObject advObj=advArr.getJSONObject(i);
            Adv adv = new Adv();
            adv.setProjectID(advObj.getString("projectID"));
            adv.setImageUrl(advObj.getString("imageUrl"));
            adv.setH5Url(advObj.getString("h5Url"));
            adv.setType(advObj.getString("type"));
            adv.setTitle(advObj.getString("title"));
            advList.add(adv);
        }
        DbHelper.getUtils().saveOrUpdateAll(advList);

        JSONArray cateArr=dataObj.getJSONArray("cate");
        List<Cate> cateList=new ArrayList<>();
        for(int i=0;i<cateArr.length();i++){
            JSONObject cateObj=cateArr.getJSONObject(i);
            Cate cate = new Cate();
            cate.setCategoryID(cateObj.getString("categoryID"));
            cate.setImageUrl(cateObj.getString("name"));
            cate.setImageUrl(cateObj.getString("imageUrl"));
            cate.setLaunchShow(cateObj.getString("launchShow"));
            cate.setIcon(cateObj.getString("icon"));
            cate.setCount(cateObj.getString("count"));
            cateList.add(cate);
        }
        DbHelper.getUtils().saveOrUpdateAll(cateList);

        List<Special> specialList=new ArrayList<>();
        JSONArray speciaArr = dataObj.getJSONArray("special");
        for(int i=0;i<speciaArr.length();i++){
            JSONObject specialObj= speciaArr.getJSONObject(i);
            Special special = new Special();
            special.setImageUrl(specialObj.getString("imageUrl"));
            special.setH5Url(specialObj.getString("h5Url"));
            special.setType(specialObj.getString("type"));
            special.setTitle(specialObj.getString("title"));
            specialList.add(special);
        }
        DbHelper.getUtils().saveOrUpdateAll(specialList);

        JSONArray newsArr = dataObj.getJSONArray("news");
        List<News> newsList=new ArrayList<>();
        for(int i=0;i<newsArr.length();i++){
            JSONObject newsObj = newsArr.getJSONObject(i);
            News news = new News();
            news.setTitle(newsObj.getString("title"));
            news.setProjectID(newsObj.getString("projectID"));
            newsList.add(news);
        }
        DbHelper.getUtils().saveOrUpdateAll(newsList);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (DbException e) {
            e.printStackTrace();
        }
    }
}
