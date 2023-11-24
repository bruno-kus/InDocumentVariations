package com.example.indocumentvariations.area;

import javafx.scene.Node;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.paint.Color;
import org.fxmisc.richtext.TextExt;
import org.fxmisc.richtext.model.StyledSegment;
import org.reactfx.util.Either;
import java.util.function.Function;
// import org.reactfx.util.*;

public class MyFactory implements Function<StyledSegment<Either<String, MySegment>, String>, Node> {
    @Override
    public Node apply(StyledSegment<Either<String, MySegment>, String> styledSegment) {

        Either<String, MySegment>  either = styledSegment.getSegment();
        var style = styledSegment.getStyle();
        TextExt text = new TextExt();
        if (either.isLeft()) {
            String string = either.getLeft();
            text.setText(string);
        }
        if (either.isRight()) {
            MySegment seg = either.getRight();
            text.setText(seg.getCurrentText());
            text.backgroundColorProperty().bind(seg.getTag().colorProperty());
        }
        return text;
    }
}
/*
            text.setOnMouseEntered(e -> {
                text.setBackgroundColor(Color.GRAY);
            });
            text.setOnMouseExited(e -> {
                text.setBackgroundColor(Color);
            });
            mógłbym zrobić jakiś obiekt, który by zarządzał tym zachowaniem
 */