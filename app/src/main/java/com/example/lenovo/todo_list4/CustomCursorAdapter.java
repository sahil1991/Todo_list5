package com.example.lenovo.todo_list4;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by lenovo on 29-10-2016.
 */
public class CustomCursorAdapter extends CursorAdapter{
Context context;
    Cursor allData;


    public CustomCursorAdapter(MainActivity context, Cursor allData) {
        super(context,allData);
        this.context=context;
        this.allData=allData;
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // when the view will be created for first time,
        // we need to tell the adapters, how each item will look
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View retView = inflater.inflate(R.layout.single_row_item, parent, false);

        return retView;
    }

    @Override
    public void bindView(View view, Context context, final Cursor cursor) {


        TextView textViewPersonName = (TextView) view.findViewById(R.id.task_name);
        textViewPersonName.setText(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(1))));

        TextView textViewPersonPIN = (TextView) view.findViewById(R.id.task_Date);
        textViewPersonPIN.setText(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(2))));
        final String itemId = cursor.getString(cursor.getColumnIndex(cursor.getColumnName(0)));
        


    }

}
