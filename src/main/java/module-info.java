module ru.kafpin.lb2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.kafpin.lb2 to javafx.fxml;
    exports ru.kafpin.lb2;
}