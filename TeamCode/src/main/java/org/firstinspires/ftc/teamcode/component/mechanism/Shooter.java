package org.firstinspires.ftc.teamcode.component.mechanism;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.configuration.ConfigurationType;

import org.firstinspires.ftc.teamcode.Constants;

public class Shooter {

    private final DcMotorEx shooterMotor;

    public Shooter(DcMotorEx shooterMotor) {
        this.shooterMotor = shooterMotor;
        this.setPIDF(Constants.SHOOTER_P, Constants.SHOOTER_I, Constants.SHOOTER_D, Constants.SHOOTER_F);
    }

    public void setVelocity(double vel) {
        double velocity = Math.min(vel, Constants.MAX_SHOOTER_VELOCITY);
        shooterMotor.setVelocity(velocity);
    }

    public void setPower(double power) {
        shooterMotor.setPower(power);
    }

    public double getPower() {
        return shooterMotor.getPower();
    }

    public void setPIDF(double kP, double kI, double kD, double kF) {
        PIDFCoefficients newCoefficients = new PIDFCoefficients(kP, kI, kD, kF);
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
        return shooterMotor.getVelocity();
    }

}
