package com.gsuccess.atrial0314;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.Button;
import android.util.Log;
import android.view.View;

/**
 *
 * @author dinduks
 */
public class HomepageActivity extends Activity {

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.homepage);

        final Button addATaskButton = (Button)findViewById(R.id.addATaskButton);
        addATaskButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(HomepageActivity.this, AddATaskActivity.class);
                startActivity(intent);
            }
        });

        final Button viewAllTheTasksButton = (Button)findViewById(R.id.viewAllTheTasksButton);
        viewAllTheTasksButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(HomepageActivity.this, ListTasksActivity.class);
                startActivity(intent);
            }
        });
        SQLiteOpenHelper database = new TaskOpenHelper(this);
        ContentValues values = new ContentValues();
        values.put("title", "lala");
        values.put("description", "baba");
        try {
            database.getWritableDatabase().insert(TaskOpenHelper.TASK_TABLE_NAME, "", values);
        } catch (SQLiteException e) {
            Log.e("Error while writing into the database", e.toString());
        }
        database.close();
    }

}