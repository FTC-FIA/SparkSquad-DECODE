package org.firstinspires.ftc.teamcode.opmode.auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;
import org.firstinspires.ftc.teamcode.task.AutonTaskRunner;
import org.firstinspires.ftc.teamcode.task.StartFeeder;
import org.firstinspires.ftc.teamcode.task.StartKicker;
import org.firstinspires.ftc.teamcode.task.StopKicker;
import org.firstinspires.ftc.teamcode.task.Task;
import org.firstinspires.ftc.teamcode.task.StartShooter;
import org.firstinspires.ftc.teamcode.task.StopShooter;
import org.firstinspires.ftc.teamcode.task.Wait;


import java.util.Locale;

@Autonomous(name="Auton_1", group="Test")
public class Auton_1 extends RobotBaseOpMode {

    private AutonTaskRunner autonTaskRunner;

    public void init() {
        super.init();

        Task[] theTasks = {
//                new StartAt(this, 60, 60, 45),
//                new MoveTo(this, 12, 12),
                new StartShooter(this, 650), // inch forward every shoot
                new StartFeeder(this),
//                new StartKicker(this),  // not using the kicker for when 3 balls in
//                new MoveTo(this, 13, 13), //inch forward
                new Wait(this, 2.0),
                new StopKicker(this),
                new StartKicker(this),
//                new MoveTo(this, 14, 14), //inch forward
                new StopShooter(this),
//                new StopKicker(this),
                //new StartKicker(this),
//                new TurnTo(this, -90),
//                new StartIntake(this),
//                new MoveTo(this, 12, 72.0),
//                new MoveTo(this, 12, 12),
//                new StopIntake(this),
//                new TurnTo(this, 45),
//                new StartShooter(this, 650),
//                new StartShooter(this, 650),
//                new StartShooter(this, 650),
//                new MoveTo(this, 32, -36),  // park

        };

        autonTaskRunner = new AutonTaskRunner(theTasks, this.telemetry);

        double x = pinpointDriver.getPosX(DistanceUnit.INCH);
        double y = pinpointDriver.getPosY(DistanceUnit.INCH);
        double h = pinpointDriver.getHeading(AngleUnit.DEGREES);

        telemetry.addData("X", String.format(Locale.US, "%.1f", x));
        telemetry.addData("Y", String.format(Locale.US, "%.1f", y));
        telemetry.addData("H", String.format(Locale.US, "%.1f", h));
        telemetry.update();
    }

    public void loop() {

        autonTaskRunner.execute();

        pinpointDriver.update();
        Pose2D pos = pinpointDriver.getPosition();
        telemetry.addData("X", pos.getX(DistanceUnit.INCH));
        telemetry.addData("Y", pos.getY(DistanceUnit.INCH));
        telemetry.addData("H", pos.getHeading(AngleUnit.DEGREES));
        telemetry.update();
    }
}
