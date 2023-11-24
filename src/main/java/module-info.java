module com.example.indocumentvariations {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.fxmisc.richtext;
    requires reactfx;


    opens com.example.indocumentvariations to javafx.fxml;
    exports com.example.indocumentvariations;
    exports com.example.indocumentvariations.area;
    opens com.example.indocumentvariations.area to javafx.fxml;
}