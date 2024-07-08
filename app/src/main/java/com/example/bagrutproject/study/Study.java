package com.example.bagrutproject.study;

import androidx.fragment.app.Fragment;

public class Study {

    String name;
    String type;
    int icon;
    String fragmentIdentifier;

    public Study(String name, String type, int icon, String fragmentIdentifier) {
        this.name = name;
        this.type = type;
        this.icon = icon;
        this.fragmentIdentifier = fragmentIdentifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getFragmentIdentifier() {
        return fragmentIdentifier;
    }

    public void setFragmentIdentifier(String fragmentIdentifier) {
        this.fragmentIdentifier = fragmentIdentifier;
    }
}
