module vut.feec.bpc {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires com.sun.jna;
    requires de.mkammerer.argon2.nolibs;

    opens vut.feec.bpc to javafx.fxml;
    exports vut.feec.bpc;
}