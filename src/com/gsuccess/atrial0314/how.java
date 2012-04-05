package com.gsuccess.atrial0314;

import java.io.InputStream;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

public class how extends Activity {
	InputStream inputStream;
	SharedPreferences settingsActivity;
	protected Button send;
	public static String name, address;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.how);
        
    }
	}
