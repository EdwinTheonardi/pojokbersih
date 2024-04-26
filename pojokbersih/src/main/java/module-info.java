module com.pojokbersih {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.pojokbersih to javafx.fxml;
    exports com.pojokbersih;
}
