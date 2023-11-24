package com.example.indocumentvariations.area;

import javafx.scene.layout.VBox;

public class VariationPane extends VBox{
    /*
    dzięki sprytnym segmentom nie potrzebuję tego upload'owania całego!
     */
    public MyArea area;
    final BigModel bigModel;


    VariationPane() {
        bigModel = new BigModel();
    }

}
