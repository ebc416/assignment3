package com.example.assignment3.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public abstract class PersisObject {
    public abstract void insert(SQLiteDatabase db);
    public abstract void initFrom(SQLiteDatabase db, Cursor cursor);
}
