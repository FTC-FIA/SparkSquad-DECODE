package org.firstinspires.ftc.teamcode.opmode.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.component.drive.FieldRelativeDrive;
import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;
import org.firstinspires.ftc.teamcode.task.AutonTaskRunner;
import org.firstinspires.ftc.teamcode.task.MoveTo;
import org.firstinspires.ftc.teamcode.task.MoveWithPIDTo;
import org.firstinspires.ftc.teamcode.task.StartAt;
import org.firstinspires.ftc.teamcode.task.StartFeeder;
import org.firstinspires.ftc.teamcode.task.StartIntake;
import org.firstinspires.ftc.teamcode.task.StartKicker;
import org.firstinspires.ftc.teamcode.task.StartShooter;
import org.firstinspires.ftc.teamcode.task.StopFeeder;
import org.firstinspires.ftc.teamcode.task.StopIntake;
import org.firstinspires.ftc.teamcode.task.StopKicker;
import org.firstinspires.ftc.teamcode.task.StopShooter;
import org.firstinspires.ftc.teamcode.task.Task;
import org.firstinspires.ftc.teamcode.task.TurnTo;
import org.firstinspires.ftc.teamcode.task.Wait;


@Autonomous(name="Auton_TestAll", group="Test")
public class Auton_TestAll extends RobotBaseOpMode {

    private FieldRelativeDrive relativeDrive = null;

    private AutonTaskRunner autonTaskRunner;

    public void init() {
        super.init();

        relativeDrive = new FieldRelativeDrive(mecanumDrive, odometer, telemetry);

        Task[] theTasks = {
                new StartAt(this, 5.0, 5.0, 0.0),
                new MoveWithPIDTo(this, 10.0, 10.0),
                new Wait(this, 1.0),
                new MoveTo(this, 0.0, 0.0),
                new Wait(this, 1.0),
                new TurnTo(this, 45.0),
                new Wait(this, 1.0),
                new TurnTo(this, -45.0),
                new MoveTo(this, 5.0, 5.0),
                new StartIntake(this),
                new Wait(this, 1.0),
                new StartShooter(this),
                new Wait(this, 1.0),
                new StartFeeder(this),
                new Wait(this, 1.0),
                new StartKicker(this),
                new Wait(this, 1.0),
                new StopKicker(this),
                new StopFeeder(this),
                new StopShooter(this),
                new StopIntake(this),
                new Wait(this, 1.0),
        };
        autonTaskRunner = new AutonTaskRunner(theTasks);

        telemetry.update();
    }

    public void loop() {
        autonTaskRunner.execute();

        Pose2D currentPose = odometer.getPosition();
        telemetry.addData("X", currentPose.getX(DistanceUnit.INCH));
        telemetry.addData("Y", currentPose.getY(DistanceUnit.INCH));
        telemetry.addData("H", currentPose.getHeading(AngleUnit.DEGREES));
        telemetry.update();
    }
}
