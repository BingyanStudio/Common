package net.bingyan.common.view;

import android.widget.Toast;

import net.bingyan.common.Common;

/**
 * Created by 2bab on 15/4/25.
 * Toast 封装
 */
public class Toaster {

    public static void shortMsg(String msg){
        Toast.makeText(Common.context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void longMsg(String msg){
        Toast.makeText(Common.context, msg, Toast.LENGTH_LONG).show();
    }
}
