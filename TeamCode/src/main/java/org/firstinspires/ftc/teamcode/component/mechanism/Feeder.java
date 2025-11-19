package org.firstinspires.ftc.teamcode.component.mechanism;

import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.Constants;

public class Feeder {

    private final DcMotorEx feederMotor;
    private double feederSpeed = Constants.DEFAULT_FEEDER_POWER;

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
