

package com.example.indocumentvariations.area;
import com.example.indocumentvariations.area.Passage;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ModelPassage implements Passage {
    /*
    po modyfikowaniu będę musiał ponownie posortować ale może to i lepiej, bo tak to bym musiał się martwić
    o kolejność modyfikacji
     */

    StringProperty textProperty = new SimpleStringProperty();
    IntegerProperty positionProperty = new SimpleIntegerProperty();

    ModelPassage(Passage tp, Variation<ModelPassage> variation, UploadableTo<Variation<ModelPassage>> uploadableTo) {
        textProperty.setValue(tp.getText());
        positionProperty.setValue(tp.getPosition());
        {
            textProperty.addListener((ob, ov, nv) -> {
                variation.adjustPositions(getPosition(), ov.length() - nv.length());
                uploadableTo.upload(variation);
                // i to do czego zostało zadanie oddelegowane będzie śledziło czy aby na pewno uploadować czy może nie!
            });
        }
    }


    public String getText() {
        return textProperty.get();
    }


    // PassageEntry oraz PassageBox powinny dziedziczyć po PassageEntity czy coś w tym stylu
    // wtedy z propertiesami to by była klasa abstrakcyjna
    // najtrudniej to jest haha
    // ponazywać te wszystkie klasy
    @Override
    public void setPosition(int position) {
        positionProperty().setValue(position);
    }

    @Override
    public void setText(String text) {
        textProperty().setValue(text);
    }

    public StringProperty textProperty() {
        return textProperty;
    }

    public int getPosition() {
        return positionProperty.get();
    }

    public IntegerProperty positionProperty() {
        return positionProperty;
    }

    PassageState toTextPosition() {
        return new PassageState(positionProperty.get(), textProperty.get());
    }

    void setValues(PassageState tp) {
        textProperty.setValue(tp.getText());
        positionProperty.setValue(tp.getPosition());
    }


    @Override
    public String toString() {
        return String.format("{%s, %s}", textProperty.getValue(), positionProperty.getValue());
    }
}
