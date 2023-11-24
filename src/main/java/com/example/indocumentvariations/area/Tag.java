package com.example.indocumentvariations.area;


import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.Objects;

public class Tag {
//    public static Tag PINK = new Tag("PINK", Color.PINK);
//    public static Tag ORANGE = new Tag("ORANGE", Color.ORANGE);
//    public static Tag GREEN = new Tag("GREEN", Color.GREEN);
//    public static Tag BLUE = new Tag("BLUE", Color.BLUE);

    // nie mogę tworzyć statycznych tagów
    // tylko dopiero mogę je stworzyć po tym jak powstanie model
    // tak jakbym symulował użytkownika, który ręcznie dodał pulę
    // a więc tworzę sobie w którymś momencie model
    // a dopiero NASTĘPNIE pulę tagów
    // jak przechowam taką pulę tagów
    // odpowiedź: mogę stworzyć mapę, która ma niemodyfikowalny zbiór kluczy i do każdego niemodyfikowalnego klucza
    // przypisać jakiś guzik, który by za niego odpowiadał
    // podoba mi się to!
    /*
    1. model
    2. tagi przypisane do tego modelu
    3. guziki, które każą strefie pisać
     */
//    public String name;
    public BigModel model;
    public BackgroundColor enumColor;
//    public ObjectProperty<Paint> color;

//    public Paint getColor() {
//        return color.get();
//    }

//    public ObjectProperty<Paint> colorProperty() {
//        return color;
//    }

    public Tag() {
        /*
        observable list oraz replace in selection, żeby dodać generic...
        można wiele fajnych rzeczy zrobić
        */
    }
//    public Tag(String name, Color color, BigModel model) {
//        this.model = model;
//        this.name = name;
//        this.color = new SimpleObjectProperty<>(color);
//    }


    public Tag(String name, BigModel model, BackgroundColor enumColor) {
//        this.name = name;
        this.model = model;
        this.enumColor = enumColor;
    }


    @Override
    public String toString() {
        return "Tag{" +
                "model=" + model +
                ", enumColor=" + enumColor +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, enumColor);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return model.equals(tag.model) && enumColor == tag.enumColor;
    }
}
