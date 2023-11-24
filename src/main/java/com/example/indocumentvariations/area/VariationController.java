package com.example.indocumentvariations.area;

import org.fxmisc.richtext.model.ReadOnlyStyledDocument;
import org.reactfx.util.Either;

public class VariationController {
    TagModel model;
    public boolean nextVariation() {
        return model.increment();
    }
    public boolean previousVariation() {
        return model.decrement();
    }
    public VariationController(TagModel bm) {
        model = bm;
    }


}
