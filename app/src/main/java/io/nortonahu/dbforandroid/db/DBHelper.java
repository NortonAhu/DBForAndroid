package io.nortonahu.dbforandroid.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Author:    Hong Yu
 * Version    V1.0
 * Date:      2015/9/24 14:09.
 * Description: SQLiteOpenHelper 类的封装
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * Why & What is modified:
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final String TAG = "DBHelper";

    public static final String TABLE_NAME = "person";
    public static final String DATABASE_NAME = "test.db";
    private static final int DB_BASE_VERSION = 1;

    public DBHelper(Context context) {
        // TODO CursorFactory 使用默认值 设置为null 需要了解下
        super(context, DATABASE_NAME, null, DB_BASE_VERSION);
    }

    // 第一次创建数据库的时候会调用，之后就不会调用了
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e(TAG,"onCreate");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME +" (_id INTEGER PRIMARY KEY , name VARCHAR, age INTEGER, info TEXT)");
    }

    // DATABASE_VERSION 值被改为2时，系统发现数据库版本不同的时候 会调用更新方法
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.e(TAG,"onUpgrade");
        db.execSQL("ALERT TABLE " + TABLE_NAME + " ADD COLUMN other STRING");
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }
}
