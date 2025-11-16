package org.firstinspires.ftc.teamcode.component.mechanism;

import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.teamcode.Constants;

public class Kicker {

    private double forwardPower;
    private double reversePower;

    private final CRServo crServo;

    public Kicker(CRServo kicker) {
        this.crServo = kicker;
        forwardPower = Constants.DEFAULT_KICKER_FORWARD_POWER;
        reversePower = Constants.DEFAULT_KICKER_REVERSE_POWER;

        stop();
    }

    public void setForwardPower(double power) {
        forwardPower = Math.abs(power);
    }

    public double getForwardPower() {
        return forwardPower;
    }

    public void setReversePower(double power) {
        reversePower = -1.0 * Math.abs(power);
    }

    public double getReversePower() {
        return reversePower;
    }

    private void setPower(double power) {
        crServo.setPower(power);
    }

    public double getPower() {
        return crServo.getPower();
    }

    public void forward() {
        setPower(forwardPower);
    }

    public void reverse() {
        setPower(reversePower);
    }

    public void stop() {
        setPower(0.0);
    }
}
