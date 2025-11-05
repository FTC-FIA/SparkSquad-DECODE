package org.firstinspires.ftc.teamcode.component.mechanism;

import com.qualcomm.robotcore.hardware.CRServo;

public class Kicker {

    private final double DEFAULT_KICKER_POWER = 0.4;
    private double kickerPower = DEFAULT_KICKER_POWER;
    private final CRServo crServo;

    public Kicker(CRServo kicker) {
        this.crServo = kicker;
        stop();
    }

    public double getPosition() {
        int servoPort = crServo.getPortNumber();
        return crServo.getController().getServoPosition(servoPort);
    }

    public void setPower(double power) {
        crServo.setPower(power);
    }

    public double getPower() {
        return crServo.getPower();
    }

    public void forward() {
        setPower(kickerPower);
    }

    public void reverse() {
        setPower(-kickerPower);
    }

    public void stop() {
        setPower(0.0);
    }
}
