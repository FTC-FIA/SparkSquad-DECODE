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


@Autonomous(name="Auton_TaskRunner_RelativeDrive2", group="Test")
public class Auton_TaskRunner_RelativeDrive2 extends RobotBaseOpMode {

    private FieldRelativeDrive relativeDrive = null;
    private Pose2D targetPoseFwd = new Pose2D(DistanceUnit.INCH, -5.0, 0.0, AngleUnit.DEGREES, 0.0);
    private Pose2D targetPoseRight = new Pose2D(DistanceUnit.INCH, -5.0, -5.0, AngleUnit.DEGREES, 0.0);
    private Pose2D targetPoseCW = new Pose2D(DistanceUnit.INCH, -5.0, -5.0, AngleUnit.DEGREES, -45.0);
    private Pose2D targetPose00 = new Pose2D(DistanceUnit.INCH, 0.0, 0.0, AngleUnit.DEGREES, 0.0);

    private AutonTaskRunner autonTaskRunner;

    public void init() {
        super.init();

        relativeDrive = new FieldRelativeDrive(mecanumDrive, odometer, telemetry);

        Task_RelativeDriveToPose driveTaskFwd = new Task_RelativeDriveToPose(
                "Forward 5",
                relativeDrive,
                odometer,
                targetPoseFwd,
                telemetry
        );
        Task_Wait waitTask1 = new Task_Wait(2.0, telemetry);
        Task_RelativeDriveToPose driveTaskRight = new Task_RelativeDriveToPose(
                "Right 5",
                relativeDrive,
                odometer,
                targetPoseRight,
                telemetry
        );
        Task_Wait waitTask2 = new Task_Wait(2.0, telemetry);
        Task_RelativeDriveToPose driveTaskCW = new Task_RelativeDriveToPose(
                "CW 45",
                relativeDrive,
                odometer,
                targetPoseCW,
                telemetry
        );
        Task_Wait waitTask3 = new Task_Wait(2.0, telemetry);
        Task_RelativeDriveToPose driveTask00 = new Task_RelativeDriveToPose(
                "Back to Start",
                relativeDrive,
                odometer,
                targetPose00,
                telemetry
        );
        Task[] theTasks = {
                driveTaskFwd,
                waitTask1,
                driveTaskRight,
                waitTask2,
                driveTaskCW,
                waitTask3,
                driveTask00
        };
        autonTaskRunner = new AutonTaskRunner(theTasks);

        telemetry.update();
    }

    public void loop() {
        autonTaskRunner.execute();
        telemetry.update();
    }
}
