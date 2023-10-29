package com.example.indocumentvariations.area;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TagModel {
    ObservableList<Variation<ModelPassage>> variations = FXCollections.observableArrayList();
    IntegerProperty currentIndex = new SimpleIntegerProperty(-1);
    ObjectBinding<Variation<ModelPassage>> activeVariation = Bindings.createObjectBinding(
            () -> variations.get(currentIndex.getValue()), variations, currentIndex
    );

    public ObservableList<Variation<ModelPassage>> getVariations() {
        return variations;
    }
    public int getCurrentIndex() {
        return currentIndex.get();
    }
    public Variation<ModelPassage> getActiveVariation() {
        return activeVariation.get();
    }
}