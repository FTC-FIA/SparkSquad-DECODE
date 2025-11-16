package org.firstinspires.ftc.teamcode.component.mechanism;

import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.teamcode.Constants;

import java.util.prefs.PreferenceChangeEvent;

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
        crServo.setPower(power);
    }

    public double getForwardPower() {
        return forwardPower;
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
