package com.example.assignment3.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.assignment3.R;
import com.example.assignment3.model.DBStudent;
import com.example.assignment3.model.Student;

public class SummaryAdapter extends BaseAdapter {

    DBStudent mStudentDB;
    public SummaryAdapter(DBStudent sDB) { mStudentDB = sDB;}

    @Override
    public int getCount(){
        return mStudentDB.getStudents().size();
    }

    @Override
    public Object getItem(int position){
        return mStudentDB.getStudents().get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View row_view;

        if (convertView == null ){
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            row_view = inflater.inflate(R.layout.student_row, parent, false);
        }else row_view = convertView;

        Student stu = mStudentDB    .getStudents().get(position);

        TextView firstview = (TextView) row_view.findViewById(R.id.first_name_id);
        TextView lastview = (TextView) row_view.findViewById(R.id.last_name_id);
        firstview.setText(stu.getFirstname());
        lastview.setText(stu.getLastname());
        row_view.setTag(new Integer(position));

        return row_view;
    }
}
