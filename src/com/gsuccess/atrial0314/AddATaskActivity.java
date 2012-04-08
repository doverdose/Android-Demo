package com.gsuccess.atrial0314;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 *
 * @author Eric
 */
public class AddATaskActivity extends Activity {

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.taskform);

        final Button submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                final EditText taskTitle = (EditText) findViewById(R.id.taskTitle);
                final EditText taskDescription = (EditText) findViewById(R.id.taskDescription);
                final EditText taskfrequency = (EditText) findViewById(R.id.editText1);
                String taskday="0";
                System.out.println(taskfrequency.getText().toString());
                if(taskfrequency.getText().toString().equals("daily")==true){
                	for(int i=0;i<7;i++){
                		taskday=String.valueOf(i);
                        saveTask(taskTitle.getText().toString(), taskDescription.getText().toString(),taskday,taskfrequency.getText().toString());
                	}
                }
                else{
                    saveTask(taskTitle.getText().toString(), taskDescription.getText().toString(),taskday,taskfrequency.getText().toString());
                }
                buildSuccessDialog().show();
            }
        });

        final Button cancel = (Button) findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(AddATaskActivity.this, HomepageActivity.class);
                startActivity(intent);
            }
        });
    }

    private void saveTask(String title, String description, String day, String frequency) {
        SQLiteOpenHelper database = new TaskOpenHelper(this);
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("description", description);
        values.put("day", day);
        values.put("frequency", frequency);
        try {
            database.getWritableDatabase().insert(TaskOpenHelper.TASK_TABLE_NAME, "", values);
        } catch (SQLiteException e) {
            Log.e("Error while writing into the database", e.toString());
        }
        database.close();
    }

    private AlertDialog buildSuccessDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(AddATaskActivity.this);
        builder.setMessage("The task was successfully saved!")
               .setCancelable(false)
               .setNeutralButton("Go back to the homepage", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                       Intent intent = new Intent(AddATaskActivity.this, welcome.class);
                       startActivity(intent);
                   }
               });
        return builder.create();
    }

}
