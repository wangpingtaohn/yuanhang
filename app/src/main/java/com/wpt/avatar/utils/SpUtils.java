package com.wpt.avatar.utils;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by YYL on 2016/9/27.
 */
public class SpUtils {
    //获取preferences的对象
    public static SharedPreferences getSp(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("sale",
                Context.MODE_PRIVATE);
        return preferences;
    }

    //设置字符串值
    public static void putString(Context context, String key, String value) {
        getSp(context).edit().putString(key, value).commit();
    }

    //获取字符串值
    public static String getString(Context context, String key) {
        // return getSp(context).getString(key, "");
        return getString(context, key, "");
    }

    //获取字符串值,如果字符串不存在可以设置默认值
    public static String getString(Context context, String key, String defValue) {
        return getSp(context).getString(key, defValue);
    }

    //设置 boolean类型的值
    public static void putBoolean(Context context, String key, Boolean value) {
        getSp(context).edit().putBoolean(key, value).commit();
    }

    //获取boolean类型的值，如果该值不存在有默认值
    public static Boolean getBoolean(Context context, String key,
                                     Boolean defValue) {
        return getSp(context).getBoolean(key, defValue);
    }

    //获取boolean类型的值
    public static Boolean getBoolean(Context context, String key) {
        return getSp(context).getBoolean(key, true);
    }

    // 设置 int类型的值
    public static void putInt(Context context, String key, int value) {
        getSp(context).edit().putInt(key, value).commit();
    }

    // 获取 int类型的值,如果没有将获取到默认值
    public static int getInt(Context context, String key, int defValue) {
        return getSp(context).getInt(key, defValue);
    }

    // 获取 int类型的值，如果没有将获取到默认值0
    public static int getInt(Context context, String key) {
        return getSp(context).getInt(key, 0);
    }

}

