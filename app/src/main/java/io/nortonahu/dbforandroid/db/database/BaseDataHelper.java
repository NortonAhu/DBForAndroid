package io.nortonahu.dbforandroid.db.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.content.CursorLoader;

/**
 * Author:    Hong Yu
 * Version    V1.0
 * Date:      2015/9/29 16:11.
 * Description: 数据库的基本操作类 主要是应用内部使用类
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * Why & What is modified:
 */
public abstract class BaseDataHelper {
    private Context mContext;

    public abstract Uri getContentUri();

    public abstract String getTableName();

    public Context getContext() {
        return mContext;
    }

    public BaseDataHelper(Context context) {
        mContext = context;
    }

    public void notifyChange() {
        mContext.getContentResolver().notifyChange(getContentUri(), null);
    }

    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
       return mContext.getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);
    }

    protected final Cursor query(String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return mContext.getContentResolver().query(getContentUri(), projection, selection, selectionArgs, sortOrder);
    }

    protected final Uri insert(ContentValues values) {
        return mContext.getContentResolver().insert(getContentUri(), values);
    }

    protected final int bulkInsert(ContentValues[] values) {
        return mContext.getContentResolver().bulkInsert(getContentUri(), values);
    }

    protected final int update(ContentValues values, String where, String[] whereArgs) {
        return mContext.getContentResolver().update(getContentUri(), values, where, whereArgs);
    }

    protected final int delete(String where, String[] selectionArgs) {
        return mContext.getContentResolver().delete(getContentUri(), where, selectionArgs);
    }

    public CursorLoader getCursorLoader(Context context) {
        return getCursorLoader(context, null, null, null, null);
    }

    public CursorLoader getCursorLoader(Context context, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return new CursorLoader(context, getContentUri(), projection, selection, selectionArgs, sortOrder);
    }

    protected final Cursor getList(String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return mContext.getContentResolver().query(getContentUri(), projection, selection, selectionArgs, sortOrder);
    }
}
