package ru.kafpin.lb2;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class TriangleController implements Initializable {

    @FXML
    private TextField tfA;
    @FXML
    private TextField tfB;
    @FXML
    private TextField tfC;

    @FXML
    private Slider sliderA;
    @FXML
    private Slider sliderB;
    @FXML
    private Slider sliderC;

    @FXML
    private Label lblResult;

    @FXML
    private Button btnCheck;
    @FXML
    private Button btnReset;
    @FXML
    private Button btnExit;

    private TriangleChecker checker;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tfA.setText(String.valueOf((int) sliderA.getValue()));
        tfB.setText(String.valueOf((int) sliderB.getValue()));
        tfC.setText(String.valueOf((int) sliderC.getValue()));

        sliderA.valueProperty().addListener((obs, oldVal, newVal) -> {
            int val = newVal.intValue();
            tfA.setText(String.valueOf(val));
            calculate();
        });
        sliderB.valueProperty().addListener((obs, oldVal, newVal) -> {
            int val = newVal.intValue();
            tfB.setText(String.valueOf(val));
            calculate();
        });
        sliderC.valueProperty().addListener((obs, oldVal, newVal) -> {
            int val = newVal.intValue();
            tfC.setText(String.valueOf(val));
            calculate();
        });

        tfA.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal) { // потеря фокуса
                syncTextFieldToSlider(tfA, sliderA);
            }
        });
        tfB.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal) {
                syncTextFieldToSlider(tfB, sliderB);
            }
        });
        tfC.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal) {
                syncTextFieldToSlider(tfC, sliderC);
            }
        });
        calculate();
    }

    private void syncTextFieldToSlider(TextField tf, Slider slider) {
        try {
            double val = Double.parseDouble(tf.getText().trim());
            if (val <= 0) {
                lblResult.setText("Значение должно быть положительным!");
                return;
            }

            int intVal = (int) Math.round(val);

            intVal = (int) clamp(intVal, slider.getMin(), slider.getMax());
            slider.setValue(intVal);

            tf.setText(String.valueOf(intVal));
            lblResult.setText("");
        } catch (NumberFormatException e) {
            lblResult.setText("Ошибка: введите число");
        }
    }

    private double clamp(double val, double min, double max) {
        return Math.max(min, Math.min(max, val));
    }

    private void calculate() {
        try {
            double a = Double.parseDouble(tfA.getText());
            double b = Double.parseDouble(tfB.getText());
            double c = Double.parseDouble(tfC.getText());

            checker = new TriangleChecker(a, b, c);
            if (checker.exists()) {
                lblResult.setText("Треугольник существует");
            } else {
                lblResult.setText("Треугольник не существует");
            }
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
        sliderA.setValue(1);
        sliderB.setValue(1);
        sliderC.setValue(1);
        lblResult.setText("");
    }

    @FXML
    private void onExit(ActionEvent event) {
        javafx.application.Platform.exit();
    }
}