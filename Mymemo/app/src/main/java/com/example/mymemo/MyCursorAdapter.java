package com.example.mymemo;



import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.CursorAdapter;
import android.widget.TextView;

import java.util.Calendar;

public class MyCursorAdapter extends CursorAdapter {
    public MyCursorAdapter(Context context, Cursor c) {

        super(context, c);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.list_item,viewGroup,false);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView memo_title = (TextView)view.findViewById(R.id.memo_title);
        TextView create_time = (TextView)view.findViewById(R.id.memo_time);

        memo_title.setText(cursor.getString(1));
        //create_time.setText("시간"+cursor.getString(2));
        long val = cursor.getLong(3);
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(val);
        create_time.setText(c.getTime().toString());
    }
}