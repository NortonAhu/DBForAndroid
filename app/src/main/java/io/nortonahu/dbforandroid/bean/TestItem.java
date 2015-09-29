package io.nortonahu.dbforandroid.bean;

import android.database.Cursor;

import io.nortonahu.dbforandroid.db.ItemsDataHelper;

/**
 * Author:    Hong Yu
 * Version    V1.0
 * Date:      2015/9/29 15:31.
 * Description: 测试数据库数据体
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * Why & What is modified:
 */
public class TestItem {

    public int id;
    public String content;

    public TestItem() {
    }

    public TestItem(int id, String content) {
        this.id = id;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public static TestItem fromCursor(Cursor c) {
        TestItem item = new TestItem();
        item.id = c.getInt(c.getColumnIndex(ItemsDataHelper.ItemsDBInfo.ID));
        item.content = c.getString(c.getColumnIndex(ItemsDataHelper.ItemsDBInfo.CONTENT));
        return item;
    }

    @Override
    public String toString() {
        return "id:" + id + " content:" + content;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof TestItem && ((TestItem) o).getId() == id) {
            return true;
        } else {
            return false;
        }
    }
}
