package org.firstinspires.ftc.teamcode.util;

public class ShooterUtils {

    public static double calculateDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(
                Math.pow((x1 - x2), 2) +
                Math.pow((y1 - y2), 2)
        );
    }

    public static double distance2Velocity(double distance) {
        // Obtained from MyCurveFit.com
        return 36580620 + ((600.8542 - 36580620) / (1 + Math.pow((distance/5276.571), 3.472387)));
    }

    public static double headingTowards(double x, double y, double targetX, double targetY) {
        return Math.toDegrees(Math.atan((targetY - y) / (targetX - x)));
    }
}
