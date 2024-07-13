module dinus.pbo.appperpus3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    opens dinus.pbo.appperpus3 to javafx.fxml;
    exports dinus.pbo.appperpus3;
    exports dinus.pbo.appperpus3.controllers;
    exports dinus.pbo.appperpus3.models;
    opens dinus.pbo.appperpus3.controllers to javafx.fxml;

    requires java.desktop;
}