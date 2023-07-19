module com.databasefx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.databasefx to javafx.fxml;
    exports com.databasefx;
}