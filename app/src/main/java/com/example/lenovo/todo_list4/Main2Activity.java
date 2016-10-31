package com.example.lenovo.todo_list4;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends Activity {
    private DatePicker datePicker;
    private Calendar calendar;
    private int year, month, day;

        EditText editTextPersonName;
        EditText dateView;


        /**
         * Called when the activity is first created.
         */
        @Override
        public void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main2);

            editTextPersonName = (EditText) findViewById(R.id.Task_name2);
            dateView= (EditText) findViewById(R.id.Task_Date2);
            calendar = Calendar.getInstance();
            year = calendar.get(Calendar.YEAR);

            month = calendar.get(Calendar.MONTH);
            day = calendar.get(Calendar.DAY_OF_MONTH);
            showDate(year, month+1, day);
        }
    public void setDate(View view) {
        showDialog(999);
        Toast.makeText(getApplicationContext(), "ca", Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
            // arg1 = year
            // arg2 = month
            // arg3 = day
            showDate(arg1, arg2+1, arg3);
        }
    };

    private void showDate(int year, int month, int day) {
        dateView.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }


    public void onClickAdd (View btnAdd) {

        String TaskName = editTextPersonName.getText().toString();
        String TaskDate = dateView.getText().toString();

        if ( TaskName.length() != 0 && TaskDate.length() != 0 ) {

            Intent newIntent = getIntent();
            newIntent.putExtra("task_name", TaskName);
            newIntent.putExtra("task_date", TaskDate);

            this.setResult(RESULT_OK, newIntent);

            finish();
        }
    }
}




