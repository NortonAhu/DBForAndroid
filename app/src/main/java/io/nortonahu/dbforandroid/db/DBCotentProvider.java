package io.nortonahu.dbforandroid.db;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import java.io.File;

import io.nortonahu.dbforandroid.app.AppContext;

/**
 * Author:    Hong Yu
 * Version    V1.0
 * Date:      2015/9/29 17:16.
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * Why & What is modified:
 */
public class DBCotentProvider extends ContentProvider {

    public static final Object obj = new Object();
    public static final String AUTHORITY = "io.nortonahu.dbforandroid";
    public static final String SCHEME = "content://";

    private static final int ALL_ITEMS = 0;//Demo列表

    public static final String PATH_ALL_ITEMS = "/allItems";//Demo列表

    public static final Uri ALL_ITEMS_CONTENT_URI = Uri.parse(SCHEME + AUTHORITY + PATH_ALL_ITEMS);//Demo列表

    public static DBHelper2 mDBHelper;
    public static final String ALL_ITEMS_CONTENT_TYPE = "vnd.android.cursor.dir/vnd.nortonahu.all.items";//Demo列表

    public static DBHelper2 getDBHelper() {
        if(mDBHelper == null) {
            mDBHelper = new DBHelper2(AppContext.getContext());
        }
        return mDBHelper;
    }
    // TODO return false的影响
    @Override
    public boolean onCreate() {
        Log.e("DB lifecycle", "Content Provide is create");
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        synchronized (obj)
        {
            SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
            queryBuilder.setTables(matchTable(uri));

            SQLiteDatabase db = getDBHelper().getWritableDatabase();
            Cursor cursor = queryBuilder.query(db,
                    projection,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    sortOrder);
            cursor.setNotificationUri(getContext().getContentResolver(), uri);
            return cursor;

        }
    }

    private static final UriMatcher sUriMATCHER = new UriMatcher(UriMatcher.NO_MATCH) {{
        addURI(AUTHORITY, "allItems", ALL_ITEMS);//Demo列表

    }};

    private String matchTable(Uri uri) {

        String table;
        switch (sUriMATCHER.match(uri)) {
            case ALL_ITEMS://Demo列表
                table = ItemsDataHelper.ItemsDBInfo.TEST_TABLE_NAME;
                break;
            default:
                throw new IllegalArgumentException("Unknown Uri" + uri);
        }
        Log.e("DB lifecycle", "Content Provide is create");
        return table;
    }

    @Override
    public String getType(Uri uri) {
        switch (sUriMATCHER.match(uri)) {
            case ALL_ITEMS://Demo列表
                return ALL_ITEMS_CONTENT_TYPE;
            default:
                throw new IllegalArgumentException("Unknown Uri" + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        synchronized (obj) {
            Log.e("DB lifecycle", "Content Provide is insert");
            SQLiteDatabase db = getDBHelper().getWritableDatabase();
            long rowId = 0;
            db.beginTransaction();
            try {
                rowId = db.insert(matchTable(uri), null, values);
                db.setTransactionSuccessful();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                db.endTransaction();
            }
            if (rowId > 0) {
                Uri returnUri = ContentUris.withAppendedId(uri, rowId);
                getContext().getContentResolver().notifyChange(uri, null);
                return returnUri;
            }
            throw new SQLException("Failed to insert row into " + uri);
        }
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        synchronized (obj) {
            Log.e("DB lifecycle", "Content Provide is delete");
            SQLiteDatabase db = getDBHelper().getWritableDatabase();
            int count = 0;
            db.beginTransaction();
            try {
                count = db.delete(matchTable(uri), selection, selectionArgs);
                db.setTransactionSuccessful();
            } finally {
                db.endTransaction();
            }
            getContext().getContentResolver().notifyChange(uri, null);
            return count;
        }
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        synchronized (obj) {
            Log.e("DB lifecycle", "Content Provide is update");
            SQLiteDatabase db = getDBHelper().getWritableDatabase();
            int count;
            db.beginTransaction();
            try {
                count = db.update(matchTable(uri), values, selection, selectionArgs);
                db.setTransactionSuccessful();
            } finally {
                db.endTransaction();
            }
            getContext().getContentResolver().notifyChange(uri, null);
            return count;
        }
    }

    public static void clearDBCache() {
        synchronized (DBCotentProvider.obj) {
            DBHelper2 mDBHelper = DBCotentProvider.getDBHelper();
            SQLiteDatabase db = mDBHelper.getWritableDatabase();
            db.delete(ItemsDataHelper.ItemsDBInfo.TEST_TABLE_NAME, null, null);
        }
    }

    @Override
    public int bulkInsert(Uri uri, ContentValues[] values) {
        return super.bulkInsert(uri, values);
    }

    public static class DBHelper2 extends SQLiteOpenHelper {

        public static final String DATA_BASE_NAME = Environment.getExternalStorageDirectory() + File.separator + "db" +
                File.separator + "norton.db";

        public static final int DATA_BASE_VERSION = 1;

        public static final int DATA_NEW_VERSION = 1;

        public DBHelper2(Context context) {
            super(context, DATA_BASE_NAME, null, DATA_BASE_VERSION);
        }

        /**
         * 第一次会调用
         * @param db
         */
        @Override
        public void onCreate(SQLiteDatabase db) {
            for (int i = DATA_BASE_VERSION; i <= DATA_NEW_VERSION; i++) {
                updateDB(db,i);
            }

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            for (int i = oldVersion; i <= newVersion; i++) {
                updateDB(db,i);
            }
        }


        private void updateDB(SQLiteDatabase db,int version){
            switch (version)
            {
                case 1:
                    ItemsDataHelper.ItemsDBInfo.TABLE.create(db);
                    break;
            }
        }
    }
}
