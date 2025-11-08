package org.firstinspires.ftc.teamcode.opmode.auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;
import org.firstinspires.ftc.teamcode.task.AutonTaskRunner;
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

@Autonomous(name="Auton_I1S2E2_Blue", group="Test")
public class Auton_I1S2E2_Blue extends RobotBaseOpMode {

    private final ElapsedTime elapsedTime = new ElapsedTime();
    private AutonTaskRunner autonTaskRunner;

    public void init() {

        super.init();
        this.autonTaskRunner = new AutonTaskRunner(
            new Task[] {
                    new StartAt( this, 18.0, -60.0, 0.0 ),      // in small triangle, towards blue side
                    new StartIntake( this ),                    // helps keep balls in
                    new MoveTo( this, 0.0, 0.0 ),               // center of field
                    new TurnTo( this, 45.0 ),                   // turn towards target

                    new StartShooterWithVelocity( this, 600 ),  // start the shooter
                    new StartFeeder( this ),                    // start the feeder
                    new Wait( this, 2.0 ),                      // wait for 2 seconds => 1st shot

                    new StartKicker( this, 0.4 ),               // try to kick the other two balls out
                    new Wait( this, 8.0 ),                      // ... for 6 seconds

                    new TurnTo( this, 0.0 ),                    // straighten back out to avoid weirdness
                    new MoveTo( this, 0.0, 24.0 ),              // move to "safe zone" between triangles

                    new StopKicker( this ),                     // shut it down
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

