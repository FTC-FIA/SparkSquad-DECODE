package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.util.ElapsedTime;

public class PIDController {

    private static final double DEFAULT_KP = 0.04;
    private static final double DEFAULT_KI = 0.0;
    private static final double DEFAULT_KD = 0.0001;
    private double kP;
    private double kI;
    private double kD;

    private ElapsedTime interval = new ElapsedTime();
    private double previousError = 0.0;
    private double integralSum = 0.0;

    public PIDController(double kP, double kI, double kD) {
        this.setPIDParameters(kP, kI, kD);
        interval.reset();
    }

    public PIDController() {
        this(DEFAULT_KP, DEFAULT_KI, DEFAULT_KD);
    }

    public void setPIDParameters(double kP, double kI, double kD) {
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
    }

    public double calculate(double error) {
        double sign = error > 0.0 ? 1.0 : -1.0;
        double absError = Math.abs(error);
        double p = kP * absError;
        integralSum += absError * interval.seconds();
        double i = kI * integralSum;
        double d = kD * Math.abs(absError - previousError) / interval.seconds();
        previousError = absError;
        interval.reset();
        return (p + i + d) * sign;
    }
}
