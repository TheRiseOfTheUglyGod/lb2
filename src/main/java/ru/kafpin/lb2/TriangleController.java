package ru.kafpin.lb2;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class TriangleController implements Initializable {

    @FXML private TextField tfA;
    @FXML private TextField tfB;
    @FXML private TextField tfC;
    @FXML private Slider sliderA;
    @FXML private Slider sliderB;
    @FXML private Slider sliderC;
    @FXML private Label lblResult;
    @FXML private Button btnCheck;
    @FXML private Button btnReset;
    @FXML private Button btnExit;

    private TriangleChecker checker;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Начальные значения
        tfA.setText(String.valueOf((int) sliderA.getValue()));
        tfB.setText(String.valueOf((int) sliderB.getValue()));
        tfC.setText(String.valueOf((int) sliderC.getValue()));

        // Автоматическое вычисление при перемещении слайдера (требование методички)
        sliderA.valueProperty().addListener((obs, oldVal, newVal) -> {
            tfA.setText(String.valueOf(newVal.intValue()));
            calculate();
        });
        sliderB.valueProperty().addListener((obs, oldVal, newVal) -> {
            tfB.setText(String.valueOf(newVal.intValue()));
            calculate();
        });
        sliderC.valueProperty().addListener((obs, oldVal, newVal) -> {
            tfC.setText(String.valueOf(newVal.intValue()));
            calculate();
        });

        calculate(); // расчёт сразу при запуске
    }

    private void calculate() {
        try {
            double a = Double.parseDouble(tfA.getText());
            double b = Double.parseDouble(tfB.getText());
            double c = Double.parseDouble(tfC.getText());
            checker = new TriangleChecker(a, b, c);
            lblResult.setText(checker.exists() ? "Треугольник существует" : "Треугольник не существует");
        } catch (NumberFormatException e) {
            lblResult.setText("Ошибка в числах");
        }
    }

    @FXML
    private void onCheck(ActionEvent event) {
        calculate();
    }

    @FXML
    private void onReset(ActionEvent event) {
        sliderA.setValue(1.0);
        sliderB.setValue(1.0);
        sliderC.setValue(1.0);
        lblResult.setText("");
    }

    @FXML
    private void onExit(ActionEvent event) {
        Platform.exit();
    }
}