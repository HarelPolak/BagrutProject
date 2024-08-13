package com.example.bagrutproject.instructions;

public class Algorithm {

    String name;
    String notations;
    int image;

    public Algorithm(String name, String notations, int image) {
        this.name = name;
        this.notations = notations;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotations() {
        return notations;
    }

    public void setNotations(String notations) {
        this.notations = notations;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
