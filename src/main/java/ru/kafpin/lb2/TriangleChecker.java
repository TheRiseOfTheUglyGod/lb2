package ru.kafpin.lb2;

public class TriangleChecker {
    private double a, b, c;

    public TriangleChecker(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public boolean exists() {
        return a > 0 && b > 0 && c > 0 &&
                a + b > c && a + c > b && b + c > a;
    }
}