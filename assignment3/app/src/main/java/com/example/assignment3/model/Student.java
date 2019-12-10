package com.example.assignment3.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class Student extends PersisObject   {
    protected String mFirstname;
    protected String mLastname;
    protected String mCWID;
    protected ArrayList<CourseEnrollment> mCourses;

    public Student(String firstname, String lastname, String CWID)
    {
        mFirstname = firstname;
        mLastname = lastname;
        mCWID = CWID;
    }

    public Student() { }

    public String getFirstname() {
        return mFirstname;
    }

    public void setFirstname(String firstname) {
        mFirstname = firstname;
    }

    public String getLastname() {
        return mLastname;
    }

    public void setLastname(String lastname) {
        mLastname = lastname;
    }

    public String getCWID() {
        return mCWID;
    }

    public void setCWID(String CWID) {
        mCWID = CWID;
    }

    public ArrayList<CourseEnrollment> getCourses() {
        return mCourses;
    }

    public void setCourses(ArrayList<CourseEnrollment> courses) {
        mCourses = courses;
    }

    @Override
    public void insert(SQLiteDatabase db) {
        ContentValues vals = new ContentValues();
        vals.put("FirstName", mFirstname);
        vals.put("LastName", mLastname);
        vals.put("CWID", mCWID);
        db.insert("Student", null, vals);
        for(int i=0; i < mCourses.size(); i++){
            mCourses.get(i).insert(db);
        }
    }

    @Override
    public void initFrom(SQLiteDatabase db, Cursor cursor) {
        mFirstname = cursor.getString(cursor.getColumnIndex("FirstName"));
        mLastname = cursor.getString(cursor.getColumnIndex("LastName"));
        mCWID = cursor.getString(cursor.getColumnIndex("CWID"));

        mCourses = new ArrayList<CourseEnrollment>();
        Cursor c = db.query("CourseEnrollment", null, "Student=?", new String[]{mCWID}, null, null, null);
        if(c.getCount() > 0){
            while(c.moveToNext()){
                CourseEnrollment cObj = new CourseEnrollment();
                cObj.initFrom(db, c);
                mCourses.add(cObj);
            }
        }
    }
}
