package org.firstinspires.ftc.teamcode.opmode.auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.component.drive.FieldRelativeDrive;
import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;
import org.firstinspires.ftc.teamcode.task.AutonTaskRunner;
import org.firstinspires.ftc.teamcode.task.Task;
import org.firstinspires.ftc.teamcode.task.Task_RelativeDriveToPose;
import org.firstinspires.ftc.teamcode.task.Task_Wait;

import java.util.Locale;


@Autonomous(name="Auton_FRFR67", group="Test")
public class Auton_FRFR67 extends RobotBaseOpMode {

    private Pose2D targetPose1 = new Pose2D(DistanceUnit.INCH, 13.0, 24.0, AngleUnit.DEGREES, 0.0);
    private Pose2D targetPoseRight = new Pose2D(DistanceUnit.INCH, 5.0, 5.0, AngleUnit.DEGREES, 0.0);
    private Pose2D targetPoseCW = new Pose2D(DistanceUnit.INCH, 5.0, 5.0, AngleUnit.DEGREES, 45.0);
    private Pose2D targetPose00 = new Pose2D(DistanceUnit.INCH, 0.0, 0.0, AngleUnit.DEGREES, 0.0);

    private AutonTaskRunner autonTaskRunner;

    public void init() {
        super.init();

        Task_Wait waitTask1 = new Task_Wait(2.0, telemetry);
        Task_RelativeDriveToPose driveTask1 = new Task_RelativeDriveToPose(
                "task 1",
                fieldRelativeDrive,
                odometer,
                targetPose1,
                telemetry
        );

        Task_RelativeDriveToPose driveTaskRight = new Task_RelativeDriveToPose(
                "Right 5",
                fieldRelativeDrive,
                odometer,
                targetPoseRight,
                telemetry
        );
        Task_Wait waitTask2 = new Task_Wait(2.0, telemetry);
        Task_RelativeDriveToPose driveTaskCW = new Task_RelativeDriveToPose(
                "CW 45",
                fieldRelativeDrive,
                odometer,
                targetPoseCW,
                telemetry
        );
        Task_Wait waitTask3 = new Task_Wait(2.0, telemetry);
        Task_RelativeDriveToPose driveTask00 = new Task_RelativeDriveToPose(
                "Back to Start",
                fieldRelativeDrive,
                odometer,
                targetPose00,
                telemetry
        );
        Task[] theTasks = {
                waitTask1,
                driveTask1,

        };
        autonTaskRunner = new AutonTaskRunner(theTasks);

        double x = odometer.getPosX(DistanceUnit.INCH);
        double y = odometer.getPosY(DistanceUnit.INCH);
        double h = odometer.getHeading(AngleUnit.DEGREES);

        telemetry.addData("X", String.format(Locale.US, "%.1f", x));
        telemetry.addData("Y", String.format(Locale.US, "%.1f", y));
        telemetry.addData("H", String.format(Locale.US, "%.1f", h));
        telemetry.update();
    }

    @Override
    public void init_loop() {
        odometer.setPosition(new Pose2D(DistanceUnit.INCH, 41.0, 54.5, AngleUnit.DEGREES, 0.0));
        odometer.update();
    }

    public void loop() {
        autonTaskRunner.execute();
        telemetry.update();
    }
}
