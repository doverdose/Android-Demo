package com.gsuccess.atrial0314;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

/**
 *
 */
public class ShowTaskActivity extends Activity {

    public static final String TASK_ID = "taskId";
    public static final String TASK_TITLE = "title";

    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.showtask);

        Bundle bundle = this.getIntent().getExtras();
        final int taskId = bundle.getInt(TASK_ID);
        final String titlef= bundle.getString(TASK_TITLE);

        final TaskOpenHelper database = new TaskOpenHelper(this);
        Cursor cursor = database.getReadableDatabase().query(
            TaskOpenHelper.TASK_TABLE_NAME,
            null,
            "_id=?",
            new String[]{String.valueOf(taskId)},
            null,
            null,
            null
        );

        cursor.moveToFirst();
        String title = cursor.getString(cursor.getColumnIndex(TaskOpenHelper.TITLE_COLUMN));
        String description = cursor.getString(cursor.getColumnIndex(TaskOpenHelper.DESCRIPTION_COLUMN));
        
        TextView titleView = (TextView) findViewById(R.id.title);
        TextView descriptionView = (TextView) findViewById(R.id.description);
        titleView.setText(title);
        descriptionView.setText(description);

        Button deleteTaskButton = (Button) findViewById(R.id.deleteTask);
        deleteTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database.getWritableDatabase().delete(
                    TaskOpenHelper.TASK_TABLE_NAME,
                    "_id=?",
                    new String[]{String.valueOf(taskId)}
                );
                buildDeleteSuccessDialog().show();
            }
        });

        Button editTaskButton = (Button) findViewById(R.id.editTask);
        editTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShowTaskActivity.this, EditTaskActivity.class);
                intent.putExtra(TASK_ID, taskId);
                startActivity(intent);
            }
        });
    }

    private AlertDialog buildDeleteSuccessDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ShowTaskActivity.this);
        builder.setMessage("The Task was successfully removed!")
               .setCancelable(false)
               .setNeutralButton("Continue", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                       Intent intent = new Intent(ShowTaskActivity.this, welcome.class);
                       startActivity(intent);
                   }
               });
        return builder.create();
    }

}