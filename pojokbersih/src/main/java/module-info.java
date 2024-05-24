module com.pojokbersih {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jbcrypt;

    opens com.pojokbersih to javafx.fxml;
    exports com.pojokbersih;
}
