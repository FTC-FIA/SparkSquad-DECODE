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


@Autonomous(name="Auton_TaskRunner_RelativeDrive3", group="Test")
public class Auton_TaskRunner_RelativeDrive3 extends RobotBaseOpMode {

    private FieldRelativeDrive relativeDrive = null;
    private Pose2D targetPoseFwdTurn = new Pose2D(DistanceUnit.INCH, 5.0, 0.0, AngleUnit.DEGREES, 45.0);
    private Pose2D targetPoseBackTurn = new Pose2D(DistanceUnit.INCH, 0.0, -5.0, AngleUnit.DEGREES, 0.0);
    private Pose2D targetPoseCW = new Pose2D(DistanceUnit.INCH, -5.0, -5.0, AngleUnit.DEGREES, -45.0);
    private Pose2D targetPose00 = new Pose2D(DistanceUnit.INCH, 0.0, 0.0, AngleUnit.DEGREES, 0.0);

    private AutonTaskRunner autonTaskRunner;

    public void init() {
        super.init();

        relativeDrive = new FieldRelativeDrive(mecanumDrive, odometer, telemetry);

        Task_RelativeDriveToPose driveTaskFwdTurn = new Task_RelativeDriveToPose(
                "Forward 5",
                relativeDrive,
                odometer,
                targetPoseFwdTurn,
                telemetry
        );
        Task_Wait waitTask1 = new Task_Wait(2.0, telemetry);
        Task_RelativeDriveToPose driveTaskBackTurn = new Task_RelativeDriveToPose(
                "Right 5",
                relativeDrive,
                odometer,
                targetPoseBackTurn,
                telemetry
        );

        Task[] theTasks = {
                driveTaskFwdTurn,
                waitTask1,
                driveTaskBackTurn,
        };
        autonTaskRunner = new AutonTaskRunner(theTasks);

        telemetry.update();
    }

    public void loop() {
        autonTaskRunner.execute();
        telemetry.update();
    }
}
