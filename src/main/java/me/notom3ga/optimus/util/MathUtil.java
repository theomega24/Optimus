package me.notom3ga.optimus.util;

public class MathUtil {

    public static double magnitude(double... points) {
        double sum = 0.0;

        for (double point : points) {
            sum += point * point;
        }

        return Math.sqrt(sum);
    }
}
