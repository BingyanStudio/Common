package net.bingyan.common.constant;

import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.View;

import net.bingyan.common.Common;
import net.bingyan.common.R;
import net.bingyan.common.other.Version;

import java.lang.reflect.Field;

/**
 * Created by 2bab on 15/4/5.
 * 尺寸相关的辅助类
 */
public class Dimen {

    private static final int INVALID_NUM = -1;
    private static int statusBarHeight = INVALID_NUM;
    private static int toolBarHeight = INVALID_NUM;
    private static int fabOffset = INVALID_NUM;
    private static int smallDialogWidth = INVALID_NUM;
    private static int bigDialogWidth = INVALID_NUM;
    private static int halfOfScreenWidth = INVALID_NUM;
    private static int fifthOfScreenWidth = INVALID_NUM;
    private static int thirdOfScreenWidth = INVALID_NUM;
    private static int sixtyOfScreenWidth = INVALID_NUM;

    public static final int FULL_SCREEN_OPTION = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
            | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

    public static final int FULL_SCREEN_CANCEL_OPTION = View.SYSTEM_UI_FLAG_LAYOUT_STABLE;


    /**
     * 获取顶部状态栏高度
     *
     * @return 成功则获取，失败返回0
     */
    public static int getStatusBarHeight() {
        if (statusBarHeight == INVALID_NUM) {
            if (Version.isKitkat() || Version.isOverLollipop()) {
                Class<?> c;
                Object obj;
                Field field;
                int temp;
                try {
                    c = Class.forName("com.android.internal.R$dimen");
                    obj = c.newInstance();
                    field = c.getField("status_bar_height");
                    temp = Integer.parseInt(field.get(obj).toString());
                    statusBarHeight = Common.context.getResources().getDimensionPixelSize(temp);
                } catch (Exception e) {
                    statusBarHeight = 0;
                }
            } else {
                statusBarHeight = 0;
            }
        }
        return statusBarHeight;
    }

    /**
     * 获取ToolBar高度（5.0以下ActionBar的高度并不等同于Toolbar）
     *
     * @return ToolBar高度
     */
    public static int getToolbarHeight() {
        if (toolBarHeight == INVALID_NUM) {
            // ActionBar 的高度在 5.0 以下比Toolbar小，这边直接hack
            /*TypedValue tv = new TypedValue();
            if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
                toolBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, context.getResources().getDisplayMetrics());
            }
            if (toolBarHeight <= 0) {
                toolBarHeight = 0;
            }*/
            toolBarHeight = Common.context.getResources().getDimensionPixelSize(R.dimen.toolbar_height);
        }
        return toolBarHeight;
    }

    public static int getHalfOfScreenWidth() {
        if (halfOfScreenWidth == INVALID_NUM) {
            halfOfScreenWidth = (int)(Dimen.getScreenWidth() / 2f);
        }
        return halfOfScreenWidth;
    }

    public static int getThirdOfScreenWidth() {
        if (thirdOfScreenWidth == INVALID_NUM) {
            thirdOfScreenWidth = (int)(Dimen.getScreenWidth() / 3f);
        }
        return thirdOfScreenWidth;
    }

    /**
     * 屏幕宽高
     */
    public static DisplayMetrics metrics;

    public static int getScreenWidth() {
        return getMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return getMetrics().heightPixels;
    }

    public static DisplayMetrics getMetrics() {
        if (metrics == null) {
            metrics = Resources.getSystem().getDisplayMetrics();
        }
        return metrics;
    }
}
