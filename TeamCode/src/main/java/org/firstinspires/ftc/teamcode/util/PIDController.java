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

    //TODO: figure out if we should normalize error for auton driving (e.g. error/MAX_ERROR)
    public double calculate(double error) {
        double p = kP * error;
        integralSum += error * interval.seconds();
        double i = kI * integralSum;
        double d = kD * (error - previousError) / (interval.seconds());
        previousError = error;
        interval.reset();
        return p + i + d;
    }
}
