package net.bingyan.common.activity;

import android.app.Activity;

import java.util.ArrayList;

/**
 * Created by 2bab on 15/10/29.
 * 统计Activity的一些东西
 */
public class ActivityHelper {

    private static ActivityHelper instance;

    private ArrayList<Activity> activities = new ArrayList<>();

    private ActivityHelper() {

    }

    public static ActivityHelper getInstance() {
        if (instance == null) {
            synchronized (ActivityHelper.class) {
                if (instance == null) {
                    instance = new ActivityHelper();
                }
            }
        }
        return instance;
    }

    public void pushActivity(Activity activity) {
        if (!activities.contains(activity)) {
            activities.add(0, activity);
        }
    }

    public void popActivity(Activity activity) {
        activities.remove(activity);
    }

    public Activity getLatestActivity() {
        return activities.get(activities.size() - 1);
    }

    public boolean isForeground() {
        return activities.size() > 0;
    }
}
