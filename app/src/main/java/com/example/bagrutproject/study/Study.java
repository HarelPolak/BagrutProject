package com.example.bagrutproject.study;

public class Study {

    String name;
    String type;
    int icon;
    int image;

    public Study(String name, String type, int icon, int image) {
        this.name = name;
        this.type = type;
        this.icon = icon;
        this.image = image;
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

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
