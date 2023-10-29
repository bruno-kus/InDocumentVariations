package com.example.indocumentvariations.area;


import javafx.scene.paint.Color;

import java.util.Objects;

public class Tag {
    public static Tag PINK = new Tag("PINK", Color.PINK);
    public static Tag ORANGE = new Tag("ORANGE", Color.ORANGE);
    public static Tag GREEN = new Tag("GREEN", Color.GREEN);
    public static Tag BLUE = new Tag("BLUE", Color.BLUE);
    public String name;
    public Color color;

    public Tag() {

    }
    public Tag(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    public String toString() {
        return "GroupTag{" + name + "}";
    }
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != getClass()) {
            return false;
        } else {
            Tag that = (Tag) obj;
            return Objects.equals(this.name, that.name);
        }
    }
}
