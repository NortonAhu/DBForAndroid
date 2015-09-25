package io.nortonahu.dbforandroid.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import io.nortonahu.dbforandroid.db.DBManager;

/**
 * Author:    Hong Yu
 * Version    V1.0
 * Date:      2015/9/25 09:17.
 * Description: Activity 基类
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * Why & What is modified:
 */
public abstract class BaseAppActivity extends AppCompatActivity {

    protected final String TAG = getClass().getSimpleName();

    protected DBManager mDBManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDBManager = new DBManager(this);
    }

    /**
     * 初始化界面
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initDate();

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
