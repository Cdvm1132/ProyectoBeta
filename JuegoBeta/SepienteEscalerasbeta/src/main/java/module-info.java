module com.example.sepienteescalerasbeta {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.sepienteescalerasbeta to javafx.fxml;
    exports com.example.sepienteescalerasbeta;
}