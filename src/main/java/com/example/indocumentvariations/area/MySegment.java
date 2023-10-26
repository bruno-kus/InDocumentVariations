package com.example.indocumentvariations.area;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class MySegment {
    /*
    może podpinanie wartości do propertisa nie być takie dobre
     */
    public final List<String> variations;

    IntegerProperty currentIndex = new SimpleIntegerProperty(-1);

    StringProperty currentText = new SimpleStringProperty();


    public MySegment(MySegment mySegment, int i, int i1) {
        this.variations = mySegment.variations;
        this.setCurrentIndex(mySegment.getCurrentIndex());

        {
            currentTextProperty().bind(Bindings.createStringBinding(() -> {
//            System.out.printf("MySegment::\nvariations -> %s\n", variations);
//                System.out.println("MySegment::$binding lambda$");
//                System.out.printf("variations -> %s\ngetCurrentIndex() -> %d\n", variations, getCurrentIndex());
                return variations.isEmpty() ? "BACKSLASH-ZERO" : variations.get(getCurrentIndex());
            }, currentIndexProperty()));
        }

        System.out.printf("getCurrentText() -> %s\n.substring(%d, %d) -> %s\n", getCurrentText(), i, i1, getCurrentText().substring(i, i1));
//        variations.set(getCurrentIndex(), variations.get(getCurrentIndex()).substring(i, i1));

    }

    public MySegment(String text) { // from scratch constructor
        variations = new LinkedList<>();
        {
            currentTextProperty().bind(Bindings.createStringBinding(() -> {
//            System.out.printf("MySegment::\nvariations -> %s\n", variations);
//                System.out.println("MySegment::$binding lambda$");
//                System.out.printf("variations -> %s\ngetCurrentIndex() -> %d\n", variations, getCurrentIndex());
                return variations.isEmpty() ? "BACKSLASH-ZERO" : variations.get(getCurrentIndex());
            }, currentIndexProperty()));
        }



        System.out.println("MySegment::MySegment");
        System.out.printf("text -> %s\n", text);
        variations.add(text);
//        System.out.printf("variations -> %s\n", variations);
        setCurrentIndex(0);
//        System.out.printf("getCurrentIndex() -> %d\n", getCurrentIndex());
//        System.out.println(variations.get(getCurrentIndex()));
//        System.out.printf("getCurrentText() -> %s\n", getCurrentText());
//        System.out.println();
    }

    public String getCurrentText() {
//        return currentTextProperty().get();
//        return stringBinding.get();
        return currentTextProperty().getValue();
    }

    public StringProperty currentTextProperty() {
        return currentText;
    }





    public int getCurrentIndex() {
        return currentIndex.get();
    }

    public IntegerProperty currentIndexProperty() {
        return currentIndex;
    }

    public boolean setCurrentIndex(int index) {
        if (!variations.isEmpty() && index >= 0 && index < variations.size()) {
            this.currentIndexProperty().setValue(index);
            return true;
        } else {
            System.out.printf("variation.size() is %d, index is %d", variations.size(), index);
            return false;
        }
    }
    public boolean increment() {
        return setCurrentIndex(getCurrentIndex() + 1);
    }
    public boolean decrement() {
        return setCurrentIndex(getCurrentIndex() - 1);
    }
    public void cloneVariation() {
        variations.add(variations.get(getCurrentIndex()));
    }

    @Override
    public String toString() {
        return "MySegment{" +
                "variations=" + variations +
                ", currentIndex=" + currentIndex +
//                ", currentText=" + currentText +
//                ", getCurrentText()=" + getCurrentText() +
                '}';
    }
}
