package org.firstinspires.ftc.teamcode.opmode.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.component.drive.FieldRelativeDrive;
import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;
import org.firstinspires.ftc.teamcode.task.AutonTaskRunner;
import org.firstinspires.ftc.teamcode.task.Task;
import org.firstinspires.ftc.teamcode.task.Task_DriveToPose;
import org.firstinspires.ftc.teamcode.task.Task_RelativeDriveToPose;

import java.util.Locale;


@Autonomous(name="Auton_TaskRunnerDrive_Demo", group="Test")
public class Auton_TaskRunnerRelativeDrive extends RobotBaseOpMode {

    private FieldRelativeDrive relativeDrive = null;
    private Pose2D targetPoseFwd = new Pose2D(DistanceUnit.MM, 500.0, 0.0, AngleUnit.DEGREES, 45.0);
    private Pose2D targetPoseRight = new Pose2D(DistanceUnit.MM, 0.0, 500.0, AngleUnit.DEGREES, -45.0);

    private AutonTaskRunner autonTaskRunner;

    public void init() {
        super.init();

        relativeDrive = new FieldRelativeDrive(mecanumDrive, odometer, telemetry);

        Task_RelativeDriveToPose driveTaskFwd = new Task_RelativeDriveToPose(
                relativeDrive,
                odometer,
                targetPoseFwd,
                telemetry
        );
        Task_RelativeDriveToPose driveTaskRight = new Task_RelativeDriveToPose(
                relativeDrive,
                odometer,
                targetPoseRight,
                telemetry
        );
        Task[] theTasks = {driveTaskFwd, driveTaskRight};
        autonTaskRunner = new AutonTaskRunner(theTasks);

        // PRINT TELEMETRY
        Pose2D pos = odometer.getPosition();
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
