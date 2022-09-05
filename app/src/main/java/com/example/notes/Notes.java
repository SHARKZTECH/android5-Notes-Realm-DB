package com.example.notes;

import io.realm.RealmObject;

public class Notes extends RealmObject {
    String title;
    String description;
    long createdTim;

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

    public long getCreatedTim() {
        return createdTim;
    }

    public void setCreatedTim(long createdTim) {
        this.createdTim = createdTim;
    }
}
