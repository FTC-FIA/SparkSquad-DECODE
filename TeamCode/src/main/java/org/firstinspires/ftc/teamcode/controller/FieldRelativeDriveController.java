package org.firstinspires.ftc.teamcode.controller;

import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;

public class FieldRelativeDriveController extends RobotRelativeDriveController {

    public FieldRelativeDriveController(RobotBaseOpMode robot) {
        super(robot);
        drive = robot.getFieldRelativeDrive();
    }
}
