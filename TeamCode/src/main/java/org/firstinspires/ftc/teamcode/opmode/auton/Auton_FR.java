package org.firstinspires.ftc.teamcode.opmode.auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;
import org.firstinspires.ftc.teamcode.task.AutonTaskRunner;
import org.firstinspires.ftc.teamcode.task.Drive;
import org.firstinspires.ftc.teamcode.task.MoveTo;
import org.firstinspires.ftc.teamcode.task.StartAt;
import org.firstinspires.ftc.teamcode.task.StartFeeder;
import org.firstinspires.ftc.teamcode.task.StartIntake;
import org.firstinspires.ftc.teamcode.task.StartKicker;
import org.firstinspires.ftc.teamcode.task.StartShooterWithVelocity;
import org.firstinspires.ftc.teamcode.task.StopFeeder;
import org.firstinspires.ftc.teamcode.task.StopIntake;
import org.firstinspires.ftc.teamcode.task.StopKicker;
import org.firstinspires.ftc.teamcode.task.StopShooter;
import org.firstinspires.ftc.teamcode.task.Task;
import org.firstinspires.ftc.teamcode.task.TurnTo;
import org.firstinspires.ftc.teamcode.task.Wait;

@Autonomous(name="Auton_FR", group="Test")
public class Auton_FR extends RobotBaseOpMode {

    private final ElapsedTime elapsedTime = new ElapsedTime();
    private AutonTaskRunner autonTaskRunner;

    public void init() {
        super.init();
        this.autonTaskRunner = new AutonTaskRunner(
            new Task[] {
                new StartAt( this, -17.5, -59.2, 0.0 ),
                //new Wait( this, 2.0 ),
                new StartIntake( this ),
                new MoveTo( this, 0.0, 0.0 ),
                new TurnTo( this, 45.0 ),

                new StartShooterWithVelocity(this, 650),      // start the shooter
                new StartFeeder(this),                        // start the feeder
                new Wait( this, 2.0 ),                        // wait for 2 seconds

                // jiggle
//                new Drive( this, 1.0, 0.0, 0.0, 0.5 ),
//                new Wait( this, 0.25 ),
//                new Drive( this, -1.0, 0.0, 0.0, 0.5 ),
//                new Wait( this, 0.25 ),
//                new Drive( this, 0.0, 0.0, 0.0, 0.0 ),

                // start intake and rotate kicker for a while to get rest of balls
                //new StartIntake( this ),
                new StartKicker( this ),
                new Wait( this, 10.0 ),

                new StopKicker( this ),
                new StopShooter( this ),
                new StopFeeder( this ),
                new StopIntake( this ),
            },
            telemetry
        );
    }

    public void loop() {
        telemetry.addData("Elapsed time (ms)", elapsedTime.seconds());
        autonTaskRunner.execute();
        telemetry.addData("X", odometer.getX(DistanceUnit.INCH));
        telemetry.addData("Y", odometer.getY(DistanceUnit.INCH));
        telemetry.addData("H", odometer.getHeading(AngleUnit.DEGREES));
        telemetry.update();
        telemetry.update();
    }
}

