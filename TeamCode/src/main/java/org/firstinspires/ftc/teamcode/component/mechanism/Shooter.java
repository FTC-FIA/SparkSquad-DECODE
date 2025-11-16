package org.firstinspires.ftc.teamcode.component.mechanism;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.configuration.ConfigurationType;

public class Shooter {

    private final DcMotorEx shooterMotor;

    public Shooter(DcMotorEx shooterMotor) {
        this.shooterMotor = shooterMotor;
    }

    public void setVelocity(double vel) {
        shooterMotor.setVelocity(vel);
    }

    public void setPower(double power) {
        shooterMotor.setPower(power);
    }

    public double getPower() {
        return shooterMotor.getPower();
    }

    public void setPIDF(double kP, double kI, double kD) {
        PIDFCoefficients newCoefficients = new PIDFCoefficients(kP, kI, kD, 0.0);
        shooterMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, newCoefficients);
    }

    public PIDFCoefficients getPIDF() {
        return shooterMotor.getPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void stop() {
        shooterMotor.setPower(0.0);
        shooterMotor.setVelocity(0.0);
    }

    public double getShooterVelocity() {
        return ((DcMotorEx)(shooterMotor)).getVelocity();
    }

}
