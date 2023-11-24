package com.example.indocumentvariations.area;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;

public class MySegment {
    /*
    okej
    i problem jest w tym, że obydwa segmenty dzielą listę
    nie myślałem o tym w ten sposób
    tak jakbym znowu potrzebował śledzenia zmian...

    tak!
    że 2 krotnie odnoszę się do tego który dzielę
    ale żeby stworzyć podsegment modyfikuję listę którą dzielą obydwa!
     */
    Tag tag; // powinien być final ale weź ogarnij konstruktor, musiałbym zrobić, żeby empty był nieco inteligentniejszy
    IntegerProperty inVariationIndex = new SimpleIntegerProperty(-1);
    StringBinding currentText;

    static MySegment empty() {
        return new MySegment();
    }
    private MySegment() {

    }
    /*
    każdy tag może należeć do jakiegoś modelu!
    wtedy mogę mieć dwa takie same tagi, ale należące do różnych modeli
    i to bardzo dobrze!
     */
    MySegment(Tag tag) {
        this.tag = tag;
        var bigModel = tag.model;
        currentText = Bindings.createStringBinding(
                () -> bigModel.getModel(tag).getActiveVariation().get(inVariationIndex.getValue()),
                bigModel.getModel(tag).activeVariationBinding(), inVariationIndex
                );
    }
    public String getCurrentText() {
        return currentText.get();
    }
    public int getInVariationIndex() {
        return inVariationIndex.get();
    }
    public IntegerProperty inVariationIndexProperty() {
        return inVariationIndex;
    }
    public void setCurrentIndex(int index) {
        inVariationIndexProperty().setValue(index);
    }
    @Override
    public String toString() {
        return "MySegment{" +
                ", currentIndex=" + inVariationIndex +
//                ", currentText=" + currentText +
                ", getCurrentText()=" + getCurrentText() +
                '}';
    }

    public Tag getTag() {
        return tag;
    }
}
