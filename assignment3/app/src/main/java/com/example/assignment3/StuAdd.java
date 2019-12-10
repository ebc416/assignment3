package com.example.assignment3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment3.R;
import com.example.assignment3.model.CourseEnrollment;
import com.example.assignment3.model.DBStudent;
import com.example.assignment3.model.Student;

import java.util.ArrayList;

public class StuAdd extends AppCompatActivity {
    ArrayList<EditText> courseViewList = new ArrayList<EditText>();
    DBStudent mDBStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_student);
        mDBStudent = new DBStudent(this);
        courseViewList.add((EditText) findViewById(R.id.courseid_id));
        courseViewList.add((EditText) findViewById(R.id.grade_id));
        Button addCourseButton = findViewById(R.id.add_course_button_id);
        addCourseButton.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        LinearLayout coursell = findViewById(R.id.course_ll_id);
                        LinearLayout gradell = findViewById(R.id.grade_ll_id);
                        LayoutInflater inflater = LayoutInflater.from(v.getContext());
                        EditText courseid = (EditText) inflater.inflate(R.layout.edit_course,
                                coursell, false);
                        EditText grade = (EditText) inflater.inflate(R.layout.edit_course,
                                gradell, false);
                        courseViewList.add(courseid);
                        courseViewList.add(grade);
                        coursell.addView(courseid);
                        gradell.addView(grade);
                    }
                }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_scr_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.action_done){
            ArrayList<CourseEnrollment> courseList = new ArrayList<CourseEnrollment>();
            for(int i = 0; i < courseViewList.size(); i += 2){
                courseList.add(new CourseEnrollment(courseViewList.get(i).getText().toString(),
                        courseViewList.get(i+1).getText().toString(),
                        (((EditText) findViewById(R.id.edit_cwid_id)).getText().toString())));
            }
            Student s = new Student(((EditText) findViewById(R.id.edit_first_id)).getText().toString(),
                    ((EditText) findViewById(R.id.edit_last_id)).getText().toString(),
                    (((EditText) findViewById(R.id.edit_cwid_id)).getText().toString()));
            s.setCourses(courseList);
            mDBStudent.addStudent(s);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
