package org.firstinspires.ftc.teamcode.opmode.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.component.drive.FieldRelativeDrive;
import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;
import org.firstinspires.ftc.teamcode.task.AutonTaskRunner;
import org.firstinspires.ftc.teamcode.task.Task;
import org.firstinspires.ftc.teamcode.task.Task_RelativeDriveToPose;
import org.firstinspires.ftc.teamcode.task.Wait;


@Autonomous(name="Auton_TaskRunner_RelativeDrive", group="Test")
@Disabled
public class Auton_TaskRunner_RelativeDrive extends RobotBaseOpMode {

    private FieldRelativeDrive relativeDrive = null;
    private Pose2D targetPoseFwd = new Pose2D(DistanceUnit.INCH, 5.0, 0.0, AngleUnit.DEGREES, 0.0);
    private Pose2D targetPoseRight = new Pose2D(DistanceUnit.INCH, 5.0, 5.0, AngleUnit.DEGREES, 0.0);
    private Pose2D targetPoseCW = new Pose2D(DistanceUnit.INCH, 5.0, 5.0, AngleUnit.DEGREES, 45.0);
    private Pose2D targetPose00 = new Pose2D(DistanceUnit.INCH, 0.0, 0.0, AngleUnit.DEGREES, 0.0);

    private AutonTaskRunner autonTaskRunner;

    public void init() {
        super.init();

        //relativeDrive = new FieldRelativeDrive(mecanumDrive, odometer, telemetry);

        Task_RelativeDriveToPose driveTaskFwd = new Task_RelativeDriveToPose(
                "Forward 5",
                relativeDrive,
                pinpointDriver,
                targetPoseFwd,
                telemetry
        );
        Wait waitTask1 = new Wait(this, 2.0);
        Task_RelativeDriveToPose driveTaskRight = new Task_RelativeDriveToPose(
                "Right 5",
                relativeDrive,
                pinpointDriver,
                targetPoseRight,
                telemetry
        );
        Wait waitTask2 = new Wait(this, 2.0);
        Task_RelativeDriveToPose driveTaskCW = new Task_RelativeDriveToPose(
                "CW 45",
                relativeDrive,
                pinpointDriver,
                targetPoseCW,
                telemetry
        );
        Wait waitTask3 = new Wait(this, 2.0);
        Task_RelativeDriveToPose driveTask00 = new Task_RelativeDriveToPose(
                "Back to Start",
                relativeDrive,
                pinpointDriver,
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
//                driveTask00
        };
        autonTaskRunner = new AutonTaskRunner(theTasks, this.telemetry);

        telemetry.update();
    }

    public void loop() {
        autonTaskRunner.execute();
        telemetry.update();
    }
}
