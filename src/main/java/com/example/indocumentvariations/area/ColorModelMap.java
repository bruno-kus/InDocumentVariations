package com.example.indocumentvariations.area;

import javafx.scene.control.Button;

import java.util.EnumMap;

public class ColorModelMap extends EnumMap<BackgroundColor, BigModel> {
    public ColorModelMap(BigModel bigModel) {
        super(BackgroundColor.class);
        replaceAll((k, v) -> bigModel);
    }
    class MyButton extends Button {
        MyButton(String s, BackgroundColor backgroundColor) {
            super(s);
            setOnAction(e -> area.writeSegment(backgroundColor));
        }
    }
    /*
    ma pisać nowym tagiem, który składa się z tego koloru
     */

}
