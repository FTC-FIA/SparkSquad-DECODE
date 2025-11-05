package org.firstinspires.ftc.teamcode.component.mechanism;

import com.qualcomm.robotcore.hardware.CRServo;

public class Feeder {

    private final CRServo crServo;
    private final double DEFAULT_FEEDER_SPEED = 1.0;
    private double feederSpeed = DEFAULT_FEEDER_SPEED;

    public Feeder(CRServo feeder) {
        this.crServo = feeder;
        stop();
    }

    public void start() {
        setPower(feederSpeed);
    }



    public void setPower(double power) {
        crServo.setPower(power);
    }

    public double getPower() {
        return crServo.getPower();
    }

    public void stop() {
        setPower(0.0);
    }
}
