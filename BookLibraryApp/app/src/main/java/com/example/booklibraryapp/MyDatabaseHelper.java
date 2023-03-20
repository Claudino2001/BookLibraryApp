package com.example.booklibraryapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "BookLibrary.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "tb_MyLibrary";
    private static final String COLUM_ID = "_id";
    private static final String COLUM_TITLE = "book_title";
    private static final String COLUM_AUTHOR = "book_author";
    private static final String COLUM_PAGES = "book_pages";

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                "(" + COLUM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUM_TITLE + " TEXT, " +
                COLUM_AUTHOR + " TEXT, " +
                COLUM_PAGES + " INTEGER);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(("DROP TABLE IF EXISTS " + TABLE_NAME));
        onCreate(db);
    }

    void addBook(String title, String author, int pages){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUM_TITLE, title);
        cv.put(COLUM_AUTHOR, author);
        cv.put(COLUM_PAGES, pages);

        long result = db.insert(TABLE_NAME, null, cv);

        if(result == -1){
            Toast.makeText(context, "Failed: Erro #001", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(context, "Add successfully!", Toast.LENGTH_LONG).show();
        }
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

}
