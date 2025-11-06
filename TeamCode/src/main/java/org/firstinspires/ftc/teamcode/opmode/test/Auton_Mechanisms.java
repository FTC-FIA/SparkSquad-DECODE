package org.firstinspires.ftc.teamcode.opmode.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;
import org.firstinspires.ftc.teamcode.task.AutonTaskRunner;
import org.firstinspires.ftc.teamcode.task.Task;
import org.firstinspires.ftc.teamcode.task.StartIntake;
import org.firstinspires.ftc.teamcode.task.StartShooter;
import org.firstinspires.ftc.teamcode.task.StopIntake;
import org.firstinspires.ftc.teamcode.task.StopShooter;
import org.firstinspires.ftc.teamcode.task.Wait;

import java.util.Locale;

@Autonomous(name="Auton_Mechanisms", group="Test")
@Disabled
public class Auton_Mechanisms extends RobotBaseOpMode {

    private AutonTaskRunner autonTaskRunner;

    public void init() {
        super.init();

        StartIntake startIntakeTask = new StartIntake(this);
        Wait waitTask1 = new Wait(this, 1.0);
        StopIntake stopIntakeTask = new StopIntake(this);
        Wait waitTask2 = new Wait(this, 1.0);
        StartShooter startShooterTask = new StartShooter(this);
        Wait waitTask3 = new Wait(this, 1.0);
        StopShooter stopShooterTask = new StopShooter(this);

        Task[] theTasks = {
                startIntakeTask,
                waitTask1,
                stopIntakeTask,
                waitTask2,
                startShooterTask,
                waitTask3,
                stopShooterTask
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

    @Override
    public void init_loop() {
        pinpointDriver.setPosition(new Pose2D(DistanceUnit.INCH, 41.0, 54.5, AngleUnit.DEGREES, 0.0));
        pinpointDriver.update();
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
