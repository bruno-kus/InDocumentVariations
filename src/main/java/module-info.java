module com.example.indocumentvariations {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.fxmisc.richtext;


    opens com.example.indocumentvariations to javafx.fxml;
    exports com.example.indocumentvariations;
    exports com.example.indocumentvariations.area;
}