package io.nortonahu.dbforandroid;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.nortonahu.dbforandroid.adapter.NormalRecyclerViewAdapter;
import io.nortonahu.dbforandroid.app.AppContext;
import io.nortonahu.dbforandroid.bean.Person;
import io.nortonahu.dbforandroid.db.DBManager;


public class MainActivity extends AppCompatActivity {

    @Bind(R.id.recycle_view)
    RecyclerView recyclerView;
    SQLiteDatabase db;

    DBManager mDBManager;

    private static final String TAB_NAME = Environment.getExternalStorageDirectory() + File.separator + "db" + File.separator+"test.db";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setTitle(AppContext.string(R.string.main_title_zh));
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(new NormalRecyclerViewAdapter(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
//        dataBaseOperation();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void dataBaseOperation() {
        db = openOrCreateDatabase(TAB_NAME, Context.MODE_PRIVATE, null);
        db.execSQL("DROP TABLE IF EXISTS person");
        // 创建 person 表
        db.execSQL("CREATE TABLE person (_id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, age SMALLINT)");

        Person person = new Person();
        person.name = "LiLei";
        person.age = 12;
        // 插入数据
        db.execSQL("INSERT INTO person VALUES (NULL, ?, ?)", new Object[]{person.getName(), person.getAge()});
//        Cursor cursor = db.rawQuery("SELECT * FROM PERSON ", new String[]{});
//        showDBInfo(cursor);
//
//        person.name = "David";
//        person.age = 45;
//
//        // 使用 ContentValue (本身是一个 map) 采用键值对的方式插入数据库
//
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("name", person.name);
//        contentValues.put("age", person.age);
//        // 插入数据库
//        db.insert("person", null, contentValues);
//        cursor = db.rawQuery("SELECT * FROM PERSON ", new String[]{});
//        showDBInfo(cursor);
//
//        contentValues = new ContentValues();
//        contentValues.put("age", 58);
//        // 更新数据
//        db.update("person", contentValues, "name = ?", new String[]{"LiLei"});
//        cursor = db.rawQuery("SELECT * FROM PERSON ", new String[]{});
//        showDBInfo(cursor);
//        // 按条件查找
//        cursor = db.rawQuery("SELECT * FROM PERSON WHERE AGE >= ?", new String[]{"46"});
//        showDBInfo(cursor);
//
//        // 删除数据
//        db.delete("person", "name = ?", new String[]{"LiLei"});
//        cursor = db.rawQuery("SELECT * FROM PERSON ", new String[]{});
//        showDBInfo(cursor);
//
//        cursor.close();

        // 删除数据
//        deleteDatabase(TAB_NAME);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        if (mDBManager != null) {
            mDBManager.closeDB();
        }
    }

    private void showDBInfo(Cursor cursor) {
        // TODO 以前记得这里是要注意的
        while (cursor.moveToNext()) {
            int _id = cursor.getInt(cursor.getColumnIndex("_id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            int age = cursor.getInt(cursor.getColumnIndex("age"));
            Log.e("yhtest", "_id = " + _id + ", name = " + name + ", age=" + age);
        }
    }

}
