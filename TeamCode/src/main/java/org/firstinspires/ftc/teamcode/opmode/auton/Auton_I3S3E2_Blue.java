package org.firstinspires.ftc.teamcode.opmode.auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
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

@Autonomous(name="Auton_I3S3E2_Blue", group="Test")
public class Auton_I3S3E2_Blue extends RobotBaseOpMode {

    private final ElapsedTime elapsedTime = new ElapsedTime();
    private AutonTaskRunner autonTaskRunner;

    private DistanceUnit duInch = DistanceUnit.INCH;
    private AngleUnit angDegrees = AngleUnit.DEGREES;

    private Pose2D I3Blue = new Pose2D(duInch, 40.0, 60.0, angDegrees, 0.0);
    private Pose2D S3Blue = new Pose2D(duInch, 12.0, 12.0, angDegrees, 45.0);
    private Pose2D E2Blue = new Pose2D(duInch, 0.0, -24.0, angDegrees, 0.0);

    private Pose2D I3 = I3Blue;
    private Pose2D S3 = S3Blue;
    private Pose2D E2 = E2Blue;

    public void init() {
        super.init();
        this.autonTaskRunner = new AutonTaskRunner(
            new Task[] {
                    // Startposition
                    new StartAt( this, I3.getX(duInch), I3.getY(duInch), I3.getHeading(angDegrees) ),

                    // Helps keep the balls in while moving, also with shooting
                    new StartIntake( this ),

                    // move to shooting position
                    new MoveTo( this, S3.getX(duInch), S3.getY(duInch) ),  // center of field
                    new TurnTo( this, S3.getHeading(angDegrees) ),         // turn towards target

                    // Shoot!
                    new StartShooterWithVelocity( this, 600 ),  // start the shooter
                    new StartFeeder( this ),                    // start the feeder
                    new Wait( this, 2.0 ),                      // wait for 2 seconds => 1st shot

                    // Shoot more!
                    new StartKicker( this, 0.4 ),
                    new Wait( this, 8.0 ),

                    // move to end position
                    new TurnTo( this, E2.getHeading(angDegrees) ),          // straighten back out to avoid weirdness
                    new MoveTo( this, E2.getX(duInch), E2.getY(duInch) ),   // move to "safe zone" between triangles

                    // shut it down
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

