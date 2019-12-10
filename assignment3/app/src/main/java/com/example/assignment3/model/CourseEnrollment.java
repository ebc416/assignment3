package com.example.assignment3.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class CourseEnrollment extends PersisObject {
    protected String mCourseid;
    protected String mGrade;
    protected String mStudent;

    public CourseEnrollment(String courseid, String grade, String student){
        mCourseid = courseid;
        mGrade = grade;
        mStudent = student;
    }

    public CourseEnrollment() {}

    public String getCourseid() {
        return mCourseid;
    }

    public void setCourseid(String courseid) {
        mCourseid = courseid;
    }

    public String getGrade() {
        return mGrade;
    }

    public void setGrade(String grade) {
        mGrade = grade;
    }

    @Override
    public void insert(SQLiteDatabase db ){
        ContentValues vals = new ContentValues();
        vals.put("CourseID", mCourseid);
        vals.put("Grade", mGrade);
        vals.put("Student",mStudent);
        db.insert("CourseEnrollment", null, vals);
    }

    @Override
    public void initFrom(SQLiteDatabase db, Cursor cursor) {
        mCourseid = cursor.getString(cursor.getColumnIndex("CourseID"));
        mGrade = cursor.getString(cursor.getColumnIndex("Grade"));
        mStudent = cursor.getString(cursor.getColumnIndex("Student"));
    }
}
