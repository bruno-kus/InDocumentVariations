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
    public String name;
    public ObjectProperty<Paint> color;
    public BigModel model;

    public Paint getColor() {
        return color.get();
    }

    public ObjectProperty<Paint> colorProperty() {
        return color;
    }

    public Tag() {
        /*
        observable list oraz replace in selection, żeby dodać generic...
        można wiele fajnych rzeczy zrobić
        */
    }
    public Tag(String name, Color color, BigModel model) {
        this.model = model;
        this.name = name;
        this.color = new SimpleObjectProperty<>(color);
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
