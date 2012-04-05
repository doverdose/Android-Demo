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
                        .setIndicator("主页")
                        .setContent(intent);
        tabHost.addTab(spec);
        
        intent = new Intent().setClass(this, how.class);
        spec = tabHost  .newTabSpec("some_things")
                        .setIndicator("如何使用")
                        .setContent(intent);
        tabHost.addTab(spec);
        
        tabHost.getTabWidget().getChildAt(0).getLayoutParams().height = 50;
        tabHost.getTabWidget().getChildAt(1).getLayoutParams().height = 50;
    }
	}
