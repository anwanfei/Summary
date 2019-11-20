package com.anfly.summary.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.anfly.summary.bean.FoodDbBean;

import java.util.ArrayList;

public class SQLiteHelper extends SQLiteOpenHelper {
    public SQLiteHelper(Context context) {
        super(context, "food.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table food(title varchar(20),des varchar(20),url varchar(100))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    /**
     * 插入
     *
     * @param foodDbBean
     */
    private void insert(FoodDbBean foodDbBean) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", foodDbBean.getTitle());
        contentValues.put("url", foodDbBean.getUrl());
        contentValues.put("des", foodDbBean.getDes());

        db.insert("food", null, contentValues);
        db.close();
    }

    /**
     * 删除
     *
     * @param foodDbBean
     */
    private void delete(FoodDbBean foodDbBean) {
        String[] arr = {foodDbBean.getTitle()};
        SQLiteDatabase db = getWritableDatabase();
        db.delete("food", "title=?", arr);
        db.close();
    }

    /**
     * 修改
     *
     * @param foodDbBean
     */
    private void update(FoodDbBean foodDbBean) {
        String[] arr = {foodDbBean.getTitle()};
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", foodDbBean.getTitle());
        db.update("food", contentValues, "title=?", arr);
        db.close();
    }

    /**
     * 查找
     */
    private ArrayList<FoodDbBean> query() {
        SQLiteDatabase db = getWritableDatabase();
        ArrayList<FoodDbBean> list = new ArrayList<>();

        Cursor cursor = db.query("food", null, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String url = cursor.getString(cursor.getColumnIndex("url"));
                String des = cursor.getString(cursor.getColumnIndex("des"));
                long id = cursor.getLong(cursor.getColumnIndex("id"));

                FoodDbBean foodDbBean = new FoodDbBean();

                foodDbBean.setUrl(url);
                foodDbBean.setTitle(title);
                foodDbBean.setDes(des);
                foodDbBean.setId(id);

                list.add(foodDbBean);
            } while (cursor.moveToNext());
        }
        db.close();

        return list;
    }


}
