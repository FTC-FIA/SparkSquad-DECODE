package org.firstinspires.ftc.teamcode.util;

public class ShooterUtils {

    public static double calculateDistance(double x1, double y1, double x2, double y2) {
        double idealAngle = Math.sqrt(
                Math.pow((x1 - x2), 2) +
                Math.pow((y1 - y2), 2)
        );
        return 0.9110996 * idealAngle + 3.000518;
    }

    public static double distance2Velocity(double distance) {
        // Obtained from MyCurveFit.com
        //return 36580620 + ((600.8542 - 36580620) / (1 + Math.pow((distance/5276.571), 3.472387)));
        // y = 15371920 + (532.4094 - 15371920)/(1 + (x/114831)^1.716433)
        return 15371920 + (532.4094 - 15371920)/(1 + Math.pow((distance/114831), 1.716433));
    }

    public static double headingTowards(double x, double y, double targetX, double targetY) {
        double idealHeading = Math.toDegrees(Math.atan((targetY - y) / (targetX - x)));
        return 66.67893 + (11.8287 - 66.67893)/(1 + Math.pow((idealHeading/42.95288),7.509604));
    }
}
