package org.firstinspires.ftc.teamcode.task;

import org.firstinspires.ftc.teamcode.component.drive.MecanumDrive;
import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;

public class Drive implements Task {

    private final RobotBaseOpMode robot;
    private final MecanumDrive drive;

    private final double forward;
    private final double strafe;
    private final double rotate;
    private final double speedFactor;

    public Drive(
            RobotBaseOpMode robot,
            double forward,
            double strafe,
            double rotate,
            double speedFactor )
    {
        this.robot = robot;
        this.drive = robot.getMecanumDrive();

        this.forward = forward;
        this.strafe = strafe;
        this.rotate = rotate;
        this.speedFactor = speedFactor;
    }

    public boolean execute() {
        drive.drive(
            this.forward,
            this.strafe,
            this.rotate,
            this.speedFactor );
        return false;
    }
}
