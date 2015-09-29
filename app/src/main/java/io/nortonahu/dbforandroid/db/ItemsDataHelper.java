package io.nortonahu.dbforandroid.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.v4.content.CursorLoader;

import java.util.ArrayList;
import java.util.List;

import io.nortonahu.dbforandroid.bean.TestItem;
import io.nortonahu.dbforandroid.db.database.BaseDataHelper;
import io.nortonahu.dbforandroid.db.database.Column;
import io.nortonahu.dbforandroid.db.database.DBInterface;
import io.nortonahu.dbforandroid.db.database.SQLiteTable;

/**
 * Author:    Hong Yu
 * Version    V1.0
 * Date:      2015/9/29 15:35.
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * Why & What is modified:
 */
public class ItemsDataHelper extends BaseDataHelper implements DBInterface<TestItem> {

    public ItemsDataHelper(Context context) {
        super(context);
    }

    @Override
    public Uri getContentUri() {
        return DBCotentProvider.ALL_ITEMS_CONTENT_URI;
    }

    @Override
    public String getTableName() {
        return ItemsDBInfo.TEST_TABLE_NAME;
    }

    @Override
    public TestItem query(String id) {
        TestItem testItem = null;
        Cursor cursor;
        cursor = query(null, ItemsDBInfo.ID + "= ?", new String[]{ id }, null);
        if (cursor.moveToFirst()) {
            testItem = TestItem.fromCursor(cursor);
        }
        cursor.close();
        return testItem;
    }

    @Override
    public int clearAll() {
        synchronized (DBCotentProvider.obj) {
            DBCotentProvider.DBHelper2 dbHelper = DBCotentProvider.getDBHelper();
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            return db.delete(ItemsDBInfo.TEST_TABLE_NAME, null, null);
        }
    }

    @Override
    public void bulkInsert(List<TestItem> listData) {
        ArrayList<ContentValues> contentValues = new ArrayList<>();
        for (TestItem item : listData) {
            ContentValues values = getContentValues(item);
            contentValues.add(values);
        }
        ContentValues[] valueArray = new ContentValues[contentValues.size()];
        bulkInsert(contentValues.toArray(valueArray));
    }

    @Override
    public ContentValues getContentValues(TestItem data) {
        ContentValues values = new ContentValues();
        values.put(ItemsDBInfo.ID, data.id);
        values.put(ItemsDBInfo.CONTENT, data.content);
        return values;
    }

    @Override
    public CursorLoader getCursorLoader() {
        return new CursorLoader(getContext(), getContentUri(), null, null, null, ItemsDBInfo.ID + " ASC");
    }

    public static final class ItemsDBInfo implements BaseColumns {
        public ItemsDBInfo() {
        }

        public static final String TEST_TABLE_NAME = "yuhong";

        public static final String ID = "id";

        public static final String CONTENT = "content";

        public static final SQLiteTable TABLE = new SQLiteTable(TEST_TABLE_NAME)
                .addColumn(ID, Column.DataType.INTEGER)
                .addColumn(CONTENT, Column.DataType.TEXT);
    }

}
