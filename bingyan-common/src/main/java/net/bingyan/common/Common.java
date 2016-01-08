package net.bingyan.common;

import android.content.Context;

/**
 * Created by 2bab on 15/12/16.
 */
public class Common {

    public static Context context;

    /**
     * Init at the beginning of application onCreate
     * @param c Application Context
     */
    public static void init(Context c) {
        context = c;
    }

}
