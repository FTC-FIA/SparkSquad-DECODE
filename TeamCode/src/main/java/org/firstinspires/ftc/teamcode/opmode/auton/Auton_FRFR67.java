package org.firstinspires.ftc.teamcode.opmode.auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;
import org.firstinspires.ftc.teamcode.task.AutonTaskRunner;
import org.firstinspires.ftc.teamcode.task.MoveTo;
import org.firstinspires.ftc.teamcode.task.SpinUpAndShoot;
import org.firstinspires.ftc.teamcode.task.StartAt;
import org.firstinspires.ftc.teamcode.task.StartIntake;
import org.firstinspires.ftc.teamcode.task.StopIntake;
import org.firstinspires.ftc.teamcode.task.Task;
import org.firstinspires.ftc.teamcode.task.Task_RelativeDriveToPose;
import org.firstinspires.ftc.teamcode.task.TurnTo;
import org.firstinspires.ftc.teamcode.task.Wait;

import java.util.Locale;

@Autonomous(name="Auton_FRFR67", group="Test")
public class Auton_FRFR67 extends RobotBaseOpMode {

    private AutonTaskRunner autonTaskRunner;

    public void init() {
        super.init();

        Task[] theTasks = {
                new StartAt(this, 41.0, 54.5, 0.0),
                new SpinUpAndShoot(this, 600),
                new MoveTo(this, 13.0, 24.0),
                new TurnTo(this, 90.0),
                new StartIntake(this),
                new MoveTo(this, 13.0, 50.0),
                new StopIntake(this),
                new TurnTo(this, 0.0),
                new SpinUpAndShoot(this, 700)
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

    public void loop() {

        autonTaskRunner.execute();

        odometer.update();
        Pose2D pos = odometer.getPosition();
        telemetry.addData("X", pos.getX(DistanceUnit.INCH));
        telemetry.addData("Y", pos.getY(DistanceUnit.INCH));
        telemetry.addData("H", pos.getHeading(AngleUnit.DEGREES));
        telemetry.update();
    }
}
