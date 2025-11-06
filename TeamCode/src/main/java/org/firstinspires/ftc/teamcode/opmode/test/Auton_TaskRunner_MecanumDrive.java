package org.firstinspires.ftc.teamcode.opmode.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;
import org.firstinspires.ftc.teamcode.task.AutonTaskRunner;
import org.firstinspires.ftc.teamcode.task.Task;
import org.firstinspires.ftc.teamcode.task.Task_DriveToPose;

import java.util.Locale;


@Autonomous(name="Auton_TaskRunner_MecanumDrive", group="Test")
@Disabled
public class Auton_TaskRunner_MecanumDrive extends RobotBaseOpMode {

    private Pose2D targetPoseFwd = new Pose2D(DistanceUnit.MM, 500.0, 0.0, AngleUnit.DEGREES, 0.0);
    private Pose2D targetPoseRight = new Pose2D(DistanceUnit.MM, 0.0, 500.0, AngleUnit.DEGREES, 0.0);

    private AutonTaskRunner autonTaskRunner;

    public void init() {
        super.init();

        Task_DriveToPose driveTaskFwd = new Task_DriveToPose(
                mecanumDrive,
                pinpointDriver,
                targetPoseFwd,
                telemetry
        );
        Task_DriveToPose driveTaskRight = new Task_DriveToPose(
                mecanumDrive,
                pinpointDriver,
                targetPoseRight,
                telemetry
        );
        Task[] theTasks = {driveTaskFwd, driveTaskRight};
        autonTaskRunner = new AutonTaskRunner(theTasks, this.telemetry);

        // PRINT TELEMETRY
        Pose2D pos = pinpointDriver.getPosition();
        String position = String.format(
                Locale.US, "{X: %.3f, Y: %.3f, H: %.3f}",
                pos.getX(DistanceUnit.MM),
                pos.getY(DistanceUnit.MM),
                pos.getHeading(AngleUnit.DEGREES)
        );
        telemetry.addData("Position", position);
        telemetry.update();
        logger.log("Odometer starting position:" + position);
    }

    public void loop() {
        autonTaskRunner.execute();
    }
}
