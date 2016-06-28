package com.dl7.library.utils;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by long on 2016/6/28.
 * Assets帮助类,测试用
 */
public class AssetsHelper {

    private static final String INFO_FILE = "InfoFile";
    private static final String INFO_LIST = "InfoList";
    private static Context sContext;

    private AssetsHelper() {
    }


    public static void init(Context context) {
        sContext = context;
    }

    public static String readData() {
        InputStream inStream = null;
        String data = null;
        try {
            inStream = sContext.getAssets().open(INFO_FILE);     //打开assets目录中的文本文件
            byte[] bytes = new byte[inStream.available()];  //inStream.available()为文件中的总byte数
            inStream.read(bytes);
            inStream.close();
            data = new String(bytes, "utf-8");        //将bytes转为utf-8字符串
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }


    public static String readList() {
        InputStream inStream = null;
        String data = null;
        try {
            inStream = sContext.getAssets().open(INFO_LIST);     //打开assets目录中的文本文件
            byte[] bytes = new byte[inStream.available()];  //inStream.available()为文件中的总byte数
            inStream.read(bytes);
            inStream.close();
            data = new String(bytes, "utf-8");        //将bytes转为utf-8字符串
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
