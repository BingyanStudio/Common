package net.bingyan.common.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


/**
 * Created by 2bab on 15/7/26.
 * <p/>
 * 供其他Activity继承
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        ActivityHelper.getInstance().pushActivity(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        ActivityHelper.getInstance().popActivity(this);
    }

}
