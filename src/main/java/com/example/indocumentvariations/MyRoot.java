package com.example.indocumentvariations;

import com.example.indocumentvariations.area.MyArea;
import javafx.beans.binding.Bindings;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MyRoot extends VBox {
    MyArea area;
    final VBox controlBars = new VBox();
    final Label indicator = new Label();

    {
        Button printButton = new Button("print all segments");
        printButton.setOnAction(e -> {
            System.out.printf("size -> %d", area.getAllSegments().size());
            System.out.printf("area.getAllSegments():\n%s\n", area.getAllSegments());
        });

        Button indicatorButton = new Button("indicator");
        indicatorButton.setOnAction(e -> {
            var segment = area.getMySegments().stream().findFirst().get().currentTextProperty();
            indicator.textProperty().bind(Bindings.createStringBinding(() -> String.format("Segment text: \"%s\"", segment.getValue()), segment));
        });

        Button mySegmentButton = new Button("MySegment");
        mySegmentButton.setOnAction(e -> area.replaceSelectionWithMySegment());
        HBox controls1 = new HBox(mySegmentButton, printButton, indicatorButton);

        Spinner<Integer> mySegmentIndexSpinner = new Spinner<>();
        SpinnerValueFactory<Integer> mySegmentIndexValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 0);
        mySegmentIndexSpinner.setValueFactory(mySegmentIndexValueFactory);

        Button cloneButton = new Button("clone");
        cloneButton.setOnAction(e -> area.getMySegments().get(mySegmentIndexSpinner.getValue()).cloneVariation());

        TextField field = new TextField();

        Button editButton = new Button("edit");
        editButton.setOnAction(e -> {
            System.out.println(area.getMySegments());
            var s = area.getMySegments().get(mySegmentIndexSpinner.getValue());
            s.variations.add(s.getCurrentIndex(), field.getText());
        });


        Button upVariationButton = new Button("▲");
        upVariationButton.setOnAction(e -> area.getMySegments().get(mySegmentIndexSpinner.getValue()).increment());
        Button downVariationButton = new Button("▼");
        upVariationButton.setOnAction(e -> area.getMySegments().get(mySegmentIndexSpinner.getValue()).decrement());


        VBox variationButtons = new VBox(upVariationButton, downVariationButton);

        HBox stupidSimpleForm = new HBox(mySegmentIndexSpinner, cloneButton, field, editButton, variationButtons);


        controlBars.getChildren().addAll(controls1, stupidSimpleForm);
    }
    public MyRoot(MyArea area) {
        this.area = area;

            getChildren().addAll(area, controlBars, indicator);

    }
}
