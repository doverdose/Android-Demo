package com.gsuccess.atrial0314.models;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author dinduks
 */
public class Task {

    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    private String title;
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, String> getMap() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put(TITLE, title);
        map.put(DESCRIPTION, description);
        return map;
    }
}
