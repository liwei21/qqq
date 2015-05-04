package com.lkw.myapplication.tools;

import android.content.Context;

import com.lidroid.xutils.DbUtils;


/**
 * Created by aaa on 15-4-15.
 */
public class DbHelper {
    private static DbUtils utils;

    //实例化DbUtils
    public static void init(Context context) {
        utils = DbUtils.create(context, "zhongchou");//"green"是数据库名
        utils.configDebug(true);//会把所有的SQL语句都打印出来，便于调试
        utils.configAllowTransaction(true);//事务有2个作用：1.打包（要成功都成功，要失败都失败）2.加速
    }

    //让外界得到DbUtils的对象utils
    public static DbUtils getUtils() {
        return utils;
    }

}
