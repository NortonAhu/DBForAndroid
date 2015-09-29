package io.nortonahu.dbforandroid.db.database;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.ArrayList;

/**
 * Author:    Hong Yu
 * Version    V1.0
 * Date:      2015/9/29 15:50.
 * Description: 数据库表创建和删除操作
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * Why & What is modified:
 */
public class SQLiteTable {
    String mTableName;

    ArrayList<Column> mColumnsDefinitions = new ArrayList<>();

    public SQLiteTable(String tableName) {
        mTableName = tableName;
        mColumnsDefinitions.add(new Column(BaseColumns._ID, Column.Constraint.PRIMARY_KEY,
                Column.DataType.INTEGER));
    }

    public String getTableName() {
        return mTableName;
    }

    public SQLiteTable addColumn(Column column){
        mColumnsDefinitions.add(column);
        return this;
    }

    public SQLiteTable addColumn(String columnName, Column.Constraint constraint,Column.DataType dataType) {
        mColumnsDefinitions.add(new Column(columnName,constraint,dataType));
        return this;
    }

    public SQLiteTable addColumn(String columnName,Column.DataType dataType) {
        mColumnsDefinitions.add(new Column(columnName, null, dataType));
        return this;
    }

    public void create(SQLiteDatabase db) {
        String formatter = " %s";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("CREATE TABLE IF NOT EXISTS ");
        stringBuilder.append(mTableName);
        stringBuilder.append("(");
        int columnCount = mColumnsDefinitions.size();
        int index = 0;
        for (Column columnsDefinition : mColumnsDefinitions) {
            stringBuilder.append(columnsDefinition.getColumnName()).append(
                    String.format(formatter, columnsDefinition.getDataType().name()));
            Column.Constraint constraint = columnsDefinition.getConstraint();

            if (constraint != null) {
                stringBuilder.append(String.format(formatter, constraint.toString()));
            }
            if (index < columnCount - 1) {
                stringBuilder.append(",");
            }
            index++;
        }
        stringBuilder.append(");");
        db.execSQL(stringBuilder.toString());
    }

    public void delete(final SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS " + mTableName);
    }
}
