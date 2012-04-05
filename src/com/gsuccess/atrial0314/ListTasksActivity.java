package com.gsuccess.atrial0314;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.gsuccess.atrial0314.models.Task;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dinduks
 */
public class ListTasksActivity extends Activity {

    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.listtasks);

        TaskOpenHelper database = new TaskOpenHelper(this);
        // Get all the tasks
        final Cursor cursor = database.getReadableDatabase().query(TaskOpenHelper.TASK_TABLE_NAME, null, null, null, null, null, null);
        // Put the tasks' titles in the TextView with id "taskRow" from the layout called "listtasks-list"
        CursorAdapter adapter = new SimpleCursorAdapter(
            this,
            R.layout.listtasks_list,
            cursor,
            new String[]{TaskOpenHelper.TITLE_COLUMN},
            new int[]{R.id.taskRow}
        );

        ListView lv = new ListView(this);
        lv.setAdapter(adapter);

        // Set an OnClick event on the list's elements
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int taskIndex, long l) {
                Intent intent = new Intent(ListTasksActivity.this, ShowTaskActivity.class);
                cursor.moveToPosition(taskIndex);
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
                Intent intent = new Intent(ListTasksActivity.this, HomepageActivity.class);
                startActivity(intent);
            }
        });
    }

}
