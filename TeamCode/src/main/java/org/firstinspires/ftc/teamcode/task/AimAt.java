package org.firstinspires.ftc.teamcode.task;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.component.drive.MecanumDrive;
import org.firstinspires.ftc.teamcode.component.sensor.Odometer;
import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;

public class AimAt implements Task {


    private final Odometer odometer;
    private final MecanumDrive mecanumDrive;
    private final Pose2D target;


    private static final double DEFAULT_TURN_SPEED = 0.4;
    private double errorTolerance = Constants.AIM_TOLERANCE ;
    private double turnSpeed = DEFAULT_TURN_SPEED;

    public AimAt(RobotBaseOpMode robot, Pose2D target) {
        this.odometer = robot.getOdometer();
        this.mecanumDrive = robot.getMecanumDrive();
        this.target = target;
    }

    @Override
    public boolean execute() {
        odometer.update();
        double currentH = odometer.getHeading(AngleUnit.DEGREES);
        double targetH = target.getHeading(AngleUnit.DEGREES);
        double errorH = targetH - currentH;

        if (Math.abs(errorH) < errorTolerance) {
            return false;
        }

        if (errorH > 0.0) {
            mecanumDrive.drive(0.0, 0.0, -turnSpeed);
        } else {
            mecanumDrive.drive(0.0, 0.0, turnSpeed);
        }
        return true;
    }
}
