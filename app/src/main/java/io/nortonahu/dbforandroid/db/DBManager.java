package io.nortonahu.dbforandroid.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import io.nortonahu.dbforandroid.bean.Person;

/**
 * Author:    Hong Yu
 * Version    V1.0
 * Date:      2015/9/24 14:30.
 * Description: 数据库管理类
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * Why & What is modified:
 */
public class DBManager {
    private static final String TAG = "DBManager";
    private SQLiteDatabase mDB;
    private Context mContext;
    private DBHelper mDBHelper;

    public DBManager(Context context) {
        this.mContext = context;
        mDBHelper = new DBHelper(context);
        // getWritableDatabase 调用的是mContext.openOrCreateDatabase(TAB_NAME, Context.MODE_PRIVATE, null)
        // 所以要确保context 已初始化,可以讲DBManger的初始化放在Activity的onCreate里
        mDB = mDBHelper.getWritableDatabase();
    }

    /**
     * add person 简单的一种循环插入 事务处理
     * @param persons
     */
    public void add(List<Person> persons) {
        mDB.beginTransaction();
        try {
            for (Person person:persons){
                mDB.execSQL("INSERT INTO " + DBHelper.TABLE_NAME + " VALUES(null,?,?)",new Object[]
                        {person.name, person.age, person.info});
            }
        }finally {
            mDB.endTransaction();
        }
    }

    /**
     * 更新对象年龄
     * @param person
     */
    public void updateAge(Person person) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(Person.KEY_AGE, person.getAge());
        mDB.update(DBHelper.TABLE_NAME, contentValue, Person.KEY_NAME + " = ?", new String[]{person.name});
    }

    /**
     * 删除一个对象
     * @param person
     */
    public void delete(Person person) {
        mDB.delete(DBHelper.TABLE_NAME, Person.KEY_NAME + " = ?", new String[]{person.name});
    }

    /**
     * 查询所有的对象返回一个队列
     * @return
     */
    public List<Person> query() {
        ArrayList<Person> persons = new ArrayList<>();
        Cursor cursor = queryAllCursor();
        while(cursor.moveToNext()){
            Person person = new Person();
            person._id = cursor.getString(cursor.getColumnIndex(Person.KEY_ID));
            person.name = cursor.getString(cursor.getColumnIndex(Person.KEY_NAME));
            person.age = cursor.getInt(cursor.getColumnIndex(Person.KEY_AGE));
            person.info = cursor.getString(cursor.getColumnIndex(Person.KEY_INFO));
            persons.add(person);
        }
        return persons;
    }

    /**
     *
     * @return
     */
    public Cursor queryAllCursor() {
       return mDB.rawQuery("SELECT * FROM " + DBHelper.TABLE_NAME,null);
    }

    /**
     * close DB
     */
    public void closeDB() {
        mDB.close();
    }

}
