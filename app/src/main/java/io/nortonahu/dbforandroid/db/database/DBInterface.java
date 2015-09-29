package io.nortonahu.dbforandroid.db.database;

import android.content.ContentValues;
import android.support.v4.content.CursorLoader;

import java.util.List;

/**
 * Author:    Hong Yu
 * Version    V1.0
 * Date:      2015/9/29 16:20.
 * Description: DB接口操作类
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * Why & What is modified:
 */
public interface DBInterface<T> {

    /**
     * 查找一条记录
     * @param id ID
     * @return 返回第一条查找记录
     */
    public T query(String id);

    /**
     * 删除所有的记录
     * @return 本次操作的条数
     */
    public int clearAll();

    /**
     * 插入数据
     * @param listData LISTDATA
     */
    public void bulkInsert(List<T> listData);

    public ContentValues getContentValues(T data);

    public CursorLoader getCursorLoader();
}
