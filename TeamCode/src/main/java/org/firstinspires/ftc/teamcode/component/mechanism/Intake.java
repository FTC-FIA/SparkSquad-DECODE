package org.firstinspires.ftc.teamcode.component.mechanism;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

public class Intake {

    private static final double DEFAULT_INTAKE_SPEED = 0.5;
    private double intakeSpeed;
    private final DcMotor intakeMotor;

    public Intake(DcMotor intakeMotor) {
        this(intakeMotor, DEFAULT_INTAKE_SPEED);
    }

    public Intake(DcMotor intakeMotor, double intakeSpeed) {
        this.intakeMotor = intakeMotor;
        this.intakeSpeed = intakeSpeed;
    }

    public double getIntakeSpeed() {
        return intakeSpeed;
    }

    public double getActualIntakePower() {
        return this.intakeMotor.getPower();
    }

    public void setIntakeSpeed(double intakeSpeed) {
        this.intakeSpeed = intakeSpeed;
    }

    public void start() {
        intakeMotor.setPower(intakeSpeed);
    }

    public void stop() {
        intakeMotor.setPower(0.0);
    }
}
