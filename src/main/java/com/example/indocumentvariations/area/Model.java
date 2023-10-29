package com.example.indocumentvariations.area;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

import java.util.HashMap;

public final class Model {
    private final HashMap<Tag, TagModel> tagModel = new HashMap<>();
    public void addTag(Tag tag) {
        tagModel.putIfAbsent(tag, new TagModel());
    }

    public void getModel(Tag tag) {
        tagModel.get(tag);
    }
}
