package com.example.lenovo.todo_list4;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private CustomCursorAdapter customAdapter;
    public TaskDatabaseHelper databaseHelper;
    private static final int ENTER_DATA_REQUEST_CODE = 1;
    private ListView listView;


    private static final String TAG = MainActivity.class.getSimpleName();

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new TaskDatabaseHelper(this);

        listView = (ListView) findViewById(R.id.list_data);



         new Handler().post(new Runnable() {
            @Override
            public void run() {
                customAdapter = new CustomCursorAdapter(MainActivity.this, databaseHelper.getAllData());
                listView.setAdapter(customAdapter);


            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, final long id) {
                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                alertDialogBuilder.setMessage("Are you sure,You want to delete task?");

                alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        databaseHelper.deleteItem(id);
                        customAdapter.notifyDataSetChanged();
                        new Handler().post(new Runnable() {
                            @Override
                            public void run() {
                                customAdapter = new CustomCursorAdapter(MainActivity.this, databaseHelper.getAllData());
                                listView.setAdapter(customAdapter);


                            }
                        });


                        Toast.makeText(getApplicationContext(),"item removed",Toast.LENGTH_SHORT).show();

                    }
                });

                alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

            }
        });






        }



    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.//Menu Resource, Menu
        return true;
    }

    public void onClickEnterData(View btnAdd) {

        startActivityForResult(new Intent(this, Main2Activity.class), ENTER_DATA_REQUEST_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ENTER_DATA_REQUEST_CODE && resultCode == RESULT_OK) {

            databaseHelper.insertData(data.getExtras().getString("task_name"), data.getExtras().getString("task_date"));

            customAdapter.changeCursor(databaseHelper.getAllData());
        }
    }



}
