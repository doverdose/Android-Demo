package com.gsuccess.atrial0314;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class welcome extends TabActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage2);
        TabHost tabHost = getTabHost();
        TabHost.TabSpec spec;
        Intent intent;

        intent = new Intent().setClass(this, HomepageActivity.class);
        spec = tabHost  .newTabSpec("some_things")
                        .setIndicator("Schedule View")
                        .setContent(intent);
        tabHost.addTab(spec);
        
        intent = new Intent().setClass(this, ScheduleActivity.class);
        spec = tabHost  .newTabSpec("some_things")
                        .setIndicator("Medication View")
                        .setContent(intent);
        tabHost.addTab(spec);
        
        tabHost.getTabWidget().getChildAt(0).getLayoutParams().height = 50;
        tabHost.getTabWidget().getChildAt(1).getLayoutParams().height = 50;
    }
	}
