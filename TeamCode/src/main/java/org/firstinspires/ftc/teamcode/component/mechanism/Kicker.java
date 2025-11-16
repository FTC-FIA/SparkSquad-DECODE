package org.firstinspires.ftc.teamcode.component.mechanism;

import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.teamcode.Constants;

public class Kicker {

    private double forwardPower = Constants.DEFAULT_KICKER_FORWARD_POWER;
    private double reversePower = Constants.DEFAULT_KICKER_REVERSE_POWER;

    private final CRServo crServo;

    public Kicker(CRServo kicker) {
        this.crServo = kicker;
        stop();
    }

    public double getPosition() {
        int servoPort = crServo.getPortNumber();
        return crServo.getController().getServoPosition(servoPort);
    }

    public void setForwardPower(double power) {
        forwardPower = Math.abs(power);
        crServo.setPower(power);
    }

    public void setReversePower(double power) {
        reversePower = Math.abs(power);
        crServo.setPower(-power);
    }

    public double getPower() {
        return crServo.getPower();
    }

    public void forward() {
        setForwardPower(forwardPower);
    }

    public void reverse() {
        setReversePower(reversePower);
    }

    public void stop() {
        setForwardPower(0.0);
    }
}
