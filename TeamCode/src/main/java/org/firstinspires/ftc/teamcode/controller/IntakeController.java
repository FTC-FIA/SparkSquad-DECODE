package org.firstinspires.ftc.teamcode.controller;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.component.mechanism.Intake;
import org.firstinspires.ftc.teamcode.component.mechanism.Shooter;
import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;

public class IntakeController {

    private final double DEFAULT_INTAKE_POWER = 1.0;


    private final Intake intake;
    private final Gamepad driverGamepad;
    private final Telemetry telemetry;

    private boolean isRunning = false;
    private double shooterPower = DEFAULT_INTAKE_POWER;

    public IntakeController(RobotBaseOpMode robot) {
        this.intake = robot.getIntake();
        this.driverGamepad = robot.getDriverGamepad();
        this.telemetry = robot.getTelemetry();
    }

    public void handleInput() {
        if (driverGamepad.rightBumperWasPressed() || driverGamepad.leftBumperWasPressed()) {
            isRunning = !isRunning;
        }
        if (isRunning) {
            intake.start();
        } else {
            intake.stop();
        }
    }

}
