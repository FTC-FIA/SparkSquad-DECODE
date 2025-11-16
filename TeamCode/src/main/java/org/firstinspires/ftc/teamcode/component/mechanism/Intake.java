package org.firstinspires.ftc.teamcode.component.mechanism;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Constants;

public class Intake {

    private double intakePower;
    private final DcMotor intakeMotor;

    public Intake(DcMotor intakeMotor) {
        this(intakeMotor, Constants.DEFAULT_INTAKE_POWER);
    }

    public Intake(DcMotor intakeMotor, double intakePower) {
        this.intakeMotor = intakeMotor;
        this.intakePower = intakePower;
    }

    public double getIntakePower() {
        return intakePower;
    }

    public void setIntakePower(double intakePower) {
        this.intakePower = intakePower;
    }

    public double getActualIntakePower() {
        return this.intakeMotor.getPower();
    }

    public void start() {
        intakeMotor.setPower(intakePower);
    }

    public void stop() {
        intakeMotor.setPower(0.0);
    }
}
