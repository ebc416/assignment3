package com.example.assignment3;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment3.R;
import com.example.assignment3.adapter.SummaryAdapter;
import com.example.assignment3.model.DBStudent;

public class MainActivity extends AppCompatActivity {
    protected ListView mSummaryView;
    protected SummaryAdapter ad;
    protected DBStudent mDBStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDBStudent = new DBStudent(this);
        mDBStudent.retrieveStudentObjects();

        setContentView(R.layout.studentsummary);
        mSummaryView = findViewById(R.id.student_summary_id);
        ad = new SummaryAdapter(mDBStudent);
        mSummaryView.setAdapter(ad);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.summary_screen_menu, menu);
        menu.findItem(R.id.act_add).setVisible(true);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(this, StuAdd.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mDBStudent.retrieveStudentObjects();
        ad.notifyDataSetChanged();
    }
}
