package org.firstinspires.ftc.teamcode.controller;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.component.drive.FieldRelativeDrive;
import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;

public class DriveController {

    private final FieldRelativeDrive drive;
    private final Gamepad driverGamepad;
    private final Telemetry telemetry;

    public DriveController(RobotBaseOpMode robot) {
        drive = robot.getFieldRelativeDrive();
        driverGamepad = robot.getDriverGamepad();
        telemetry = robot.getTelemetry();
    }

    public void handleInput() {
        double forward = -driverGamepad.left_stick_y;
        double strafe = driverGamepad.left_stick_x;
        double rotate = driverGamepad.right_stick_x;

        drive.drive(forward, strafe, rotate);

        telemetry.addData("Drive params", "forward (%.2f), strafe (%.2f), rotate (%.2f)", forward, strafe, rotate);

    }
}
