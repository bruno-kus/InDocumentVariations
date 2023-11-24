package com.example.indocumentvariations.area;

import javafx.beans.binding.Bindings;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Arrays;
import java.util.stream.Collectors;


public class MyRoot extends VBox  { // nie powinno!
    // zamiast tego zamiast dodawać rzeczy do samej siebie dodaję do VBoxa
    // i przekierowuję metody z interfejsu parent do VBoxa też
    // HAHAAHAHAHAH parent to nie interfejs, jedyny sposób, aby go zaimplementować to dziedziczyć po nim!!
    final VBox controlBars = new VBox();
    final Label indicator = new Label();
    MyArea area;
    BigModel bigModel = new BigModel();
    BoxPane boxPane = new BoxPane();
    // BoxPane nie powinien dziedziczyć po vboxie, jeżeli nie korzystam z metod tamtego
    // ale z drugiej strony chciałbym, żeby był Node'm który mogę łatwo dodać
    // teoretycznie mógłbym zaimplementować w nim bycie dzieckiem, które by delegowało wszystko do VBoxa
    // i to by było może nawet bardziej idiomtayczne!
    // rzeczywiście coś mi nie gra że dziedzicząc po komponencie implementujemy logikę, może lepiej gdyby
    // box pane miał pole typu node
    // lepiej!
    // nie pole a getter bo pole byłoby prywatne VBox
    // w ten sposób uniknę korzystania z wzorców, a wykorzystam potęgę polimorfizmu :)

    Button printButton = new Button("print all segments");

    {
        var s = new ColorModelMap(bigModel);

        Arrays.stream(BackgroundColor.values())
                .map(bc -> new ColorModelMap.MyButton(bc.toString(), bc))
                .collect(Collectors.toUnmodifiableSet());


        /*
        właśnie wygenerowałem 8 guzików
         */

        /*
        tagi powinny być porównywalne
         */
    }

    {




        printButton.setOnAction(e -> {
            System.out.printf("size -> %d", area.getAllSegments().size());
            System.out.printf("area.getAllSegments():\n%s\n", area.getAllSegments());
        });


        Button indicatorButton = new Button("indicator");


        Button mySegmentButton = new Button("MySegment");
//                mySegmentButton.setOnAction(e -> area.replaceSelectionWithMySegment());
        HBox controls1 = new HBox(mySegmentButton, printButton, indicatorButton);

        Spinner<Integer> mySegmentIndexSpinner = new Spinner<>();
        SpinnerValueFactory<Integer> mySegmentIndexValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 0);
        mySegmentIndexSpinner.setValueFactory(mySegmentIndexValueFactory);


        Button cloneButton = new Button("clone");

        TextField field = new TextField();

        Button editButton = new Button("edit");


        Button upVariationButton = new Button("▲");
        Button downVariationButton = new Button("▼");

        {

        }

        VBox variationButtons = new VBox(upVariationButton, downVariationButton);

        HBox stupidSimpleForm = new HBox(mySegmentIndexSpinner, cloneButton, field, editButton, variationButtons);


        controlBars.getChildren().

                addAll(controls1, stupidSimpleForm);

    }


    // w jaki sposób obserwować mogę model?

    /*
    do zrobienia:
    styl tła
    fabryka
    rewizja guziczków
    drukowanie modeli
     */

    public MyRoot() {
        this.area = new MyArea(bigModel);
        /*
        nie chcę, żeby area miała monopol na model!
        program powinien działać nawet, gdy nie będzie arei w ogóle!
         */
        this.area.selectedTagProperty().bindBidirectional(bigModel.selectedTagProperty());

        this.area.selectedTagProperty().addListener((ob, ov, nv) -> {
            Bindings.unbindContent(boxPane.texts, bigModel.getModel(ov).activeVariation.get());
            boxPane.texts.clear();
            boxPane.texts.addAll(bigModel.getModel(nv).activeVariation.get());
            Bindings.bindContent(boxPane.texts, bigModel.getModel(nv).activeVariation.get());
        });

//        this.area.beingUpdatedProperty().addListener((ob, ov, nv) -> {
//            // można zoptymalizować...
//            var segments = area.getMySegments();
//            var set = segments.stream().map(seg -> seg.tag).collect(Collectors.toUnmodifiableSet());
//            for (var tag : set) {
//                int i = 0;
//                for (var seg : segments) {
//                    seg.setCurrentIndex(i++);
//                }
//            }
//        });

        getChildren().addAll(area, controlBars, indicator, boxPane);

    }
}
