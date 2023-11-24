package com.example.indocumentvariations.area;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.HashMap;

public final class BigModel {
    private final HashMap<Tag, TagModel> tagModel = new HashMap<>();

    public void addTag(Tag tag) {
        tagModel.putIfAbsent(tag, new TagModel());
    }

    public TagModel getModel(Tag tag) {
        return tagModel.get(tag);
    }

    ObjectProperty<Tag> selectedTag = new SimpleObjectProperty<>();

    public Tag getSelectedTag() {
        return selectedTag.get();
    }

    public ObjectProperty<Tag> selectedTagProperty() {
        return selectedTag;
    }

    public void setSelectedTag(Tag selectedTag) {
        this.selectedTag.set(selectedTag);
    }

    public BigModel() {
    }
}
