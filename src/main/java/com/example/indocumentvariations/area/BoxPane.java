package com.example.indocumentvariations.area;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class BoxPane extends VBox {
    ObservableList<String> texts = FXCollections.observableList(new LinkedList<>());
    private final ObservableList<TextField> fields = FXCollections.observableArrayList(new LinkedList<>());
    {
        Bindings.bindContent(getChildren(), fields); // działa jednostronnie!
    }
    {
        texts.addListener((ListChangeListener.Change<? extends String> change) -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    fields.addAll(change.getFrom(), change.getAddedSubList().stream().map(TextField::new).toList());
                }
               if (change.wasRemoved()) {
                    fields.remove(change.getFrom());
               }
            }
        });

        fields.addListener((ListChangeListener.Change<? extends TextField> change) -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    texts.addAll(change.getFrom(), change.getAddedSubList().stream().map(TextField::getText).toList());
                }
                if (change.wasRemoved()) {
                    fields.remove(change.getFrom());
                }
            }
        });
    }
}
