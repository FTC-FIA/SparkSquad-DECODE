package org.firstinspires.ftc.teamcode.component.mechanism;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

public class Shooter {

    private final double TRIGGER_INCREMENT = 0.5;
    private final DcMotor shooterMotor;
    private final Servo trigger;

    public Shooter(DcMotor shooterMotor, Servo trigger) {
        this.shooterMotor = shooterMotor;
        this.trigger = trigger;
    }

    public void spinUp(double power) {
        shooterMotor.setPower(power);
    }

    public void stop() {
        shooterMotor.setPower(0.0);
    }

    public void shoot() {
        double triggerPosition = trigger.getPosition();
        trigger.setPosition(triggerPosition + TRIGGER_INCREMENT);
    }

    public void triggerActivate() {
        trigger.setPosition(.4);
    }

    public void triggerDeactivate() {
        trigger.setPosition(1);
    }

    public double getTriggerPosition() {
        return trigger.getPosition();
    }

    public double getShooterVelocity() {
        return ((DcMotorEx)(shooterMotor)).getVelocity();
    }

}
