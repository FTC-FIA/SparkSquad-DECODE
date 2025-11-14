package org.firstinspires.ftc.teamcode.component.mechanism;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;

public class Feeder {

    private final DcMotorEx feederMotor;
    private final double DEFAULT_FEEDER_SPEED = 1.0;
    private double feederSpeed = DEFAULT_FEEDER_SPEED;

    public Feeder(DcMotorEx feeder) {
        this.feederMotor = feeder;
        stop();
    }

    public void start() {
        setPower(feederSpeed);
    }

    public void setPower(double power) {
        feederMotor.setPower(power);
    }

    public double getPower() {
        return feederMotor.getPower();
    }

    public void stop() {
        setPower(0.0);
    }
}
