package com.gsuccess.atrial0314;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

/**
 *
 * 
 */
public class ScheduleActivity extends Activity {
	private String[] allColumns = { "_id",TaskOpenHelper.TITLE_COLUMN,
			TaskOpenHelper.DESCRIPTION_COLUMN,TaskOpenHelper.DAY_COLUMN,TaskOpenHelper.FREQUENCY_COLUMN};
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.listtasks);
        java.util.Date date= new java.util.Date();
   	    System.out.println(new Timestamp(date.getTime()));

        TaskOpenHelper database = new TaskOpenHelper(this);
        // Get all the tasks
      //final Cursor cursor = database.getReadableDatabase().query(TaskOpenHelper.TASK_TABLE_NAME, allColumns, TaskOpenHelper.TITLE_COLUMN +"='lala'", null, null, null, null);
        final Cursor cursor = database.getReadableDatabase().query(TaskOpenHelper.TASK_TABLE_NAME, allColumns, null, null, null, null, null);
        final Cursor cursor0 = database.getReadableDatabase().query(TaskOpenHelper.TASK_TABLE_NAME, allColumns, TaskOpenHelper.DAY_COLUMN +"='0'", null, null, null, null);

        //  final Cursor cursor = database.getReadableDatabase().rawQuery("SELECT TITLE, DISCRIPTION FROM " +
       // 		TaskOpenHelper.TASK_TABLE_NAME +" ORDER BY TITLE", null);
        // Put the tasks' titles in the TextView with id "taskRow" from the layout called "listtasks-list"
        SeparatedListAdapter adapter2 = new SeparatedListAdapter(this); 
        adapter2.addSection("medication", new SimpleCursorAdapter(
                this,
                R.layout.list_complex,
                cursor0,
                new String[]{TaskOpenHelper.TITLE_COLUMN,TaskOpenHelper.DESCRIPTION_COLUMN,TaskOpenHelper.FREQUENCY_COLUMN},
                new int[]{R.id.list_complex_title,R.id.list_complex_caption,R.id.list_complex_frequency}

            ));
       
        ListView lv = new ListView(this);
        lv.setAdapter(adapter2);

        // Set an OnClick event on the list's elements
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int taskIndex, long l) {
                Intent intent = new Intent(ScheduleActivity.this, ShowTaskActivity.class);
                //System.out.println(taskIndex);
                cursor.moveToPosition((int)(taskIndex/2));
                int columnIndex = cursor.getColumnIndex("_id");
                int taskId = (int) cursor.getLong(columnIndex);
                intent.putExtra(ShowTaskActivity.TASK_ID, taskId);
                startActivity(intent);
            }
        });

        LinearLayout layout = (LinearLayout) findViewById(R.id.listTasksLayout);
        layout.addView(lv);

        final Button goBackButton = (Button) findViewById(R.id.goBackButton);
        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ScheduleActivity.this, welcome.class);
                startActivity(intent);
            }
        });
    }

}
