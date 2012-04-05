package com.gsuccess.atrial0314;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 *
 * @author dinduks
 */
public class EditTaskActivity extends Activity {

    public static final String TASK_ID = "taskId";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.taskform);

        Bundle bundle = this.getIntent().getExtras();
        final int taskId = bundle.getInt(ShowTaskActivity.TASK_ID);

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
        
        EditText titleView = (EditText) findViewById(R.id.taskTitle);
        EditText descriptionView = (EditText) findViewById(R.id.taskDescription);

        titleView.setText(title);
        descriptionView.setText(description);

        Button saveButton = (Button) findViewById(R.id.submit);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText titleView = (EditText) findViewById(R.id.taskTitle);
                EditText descriptionView = (EditText) findViewById(R.id.taskDescription);

                ContentValues values = new ContentValues();
                values.put("title", titleView.getText().toString());
                values.put("description", descriptionView.getText().toString());
                database.getWritableDatabase().update(
                    TaskOpenHelper.TASK_TABLE_NAME,
                    values,
                    "_id=?",
                    new String[]{String.valueOf(taskId)}
                );
                buildSuccessDialog(taskId).show();
            }
        });

        final Button cancel = (Button) findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(EditTaskActivity.this, ShowTaskActivity.class);
                intent.putExtra(ShowTaskActivity.TASK_ID, taskId);
                startActivity(intent);
            }
        });
    }

    private AlertDialog buildSuccessDialog(final int taskId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(EditTaskActivity.this);
        builder.setMessage("The task was successfully edited!")
               .setCancelable(false)
               .setNeutralButton("Go back to the homepage", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                       Intent intent = new Intent(EditTaskActivity.this, ShowTaskActivity.class);
                       intent.putExtra(ShowTaskActivity.TASK_ID, taskId);
                       startActivity(intent);
                   }
               });
        return builder.create();
    }

}