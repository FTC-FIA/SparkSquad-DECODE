package org.firstinspires.ftc.teamcode.opmode.auton.base;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.opmode.auton.AutonBaseOpMode;
import org.firstinspires.ftc.teamcode.task.AutonTaskList;
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
import org.firstinspires.ftc.teamcode.util.Alliance;

public abstract class Auton_LongShot_Intake3 extends AutonBaseOpMode {

    private final ElapsedTime elapsedTime = new ElapsedTime();
    private Alliance color;

    private static DistanceUnit DU = DistanceUnit.INCH;
    private static AngleUnit AU = AngleUnit.DEGREES;

    protected void setColor( Alliance color ) {
        this.color = color;
    }

    public void init() {
        super.init();

        Pose2D startPose = Constants.I1.forAlliance(color);
        Pose2D shootPose = Constants.S1.forAlliance(color);
        Pose2D endPose = Constants.E1.forAlliance(color);
        Pose2D intake3StartPose = Constants.IS3.forAlliance(color);
        Pose2D intake3EndPose = Constants.IE3.forAlliance(color);

        this.autonTaskList = new AutonTaskList(
            this,
            new Task[]{
                    new StartAt(this, startPose.getX(DU), startPose.getY(DU), startPose.getHeading(AU)),

                    // spin up shooter
                    new StartShooterWithVelocity(this, Constants.LONGSHOT_SHOOTER_VELOCITY),  // start the shooter

                    // move to shooting position using a TaskList
                    new MoveTo( this, shootPose.getX(DU), shootPose.getY(DU) ),
                    new TurnTo( this, shootPose.getHeading(AU))  ,

                    // ======== START SHOOTING =========== //
                    // Shoot 2 balls!
                    new Wait( this, 1.0 ),                      // wait for shooter to hit target velocity
                    new StartFeeder( this, 0.3),                // start the feeder

                    // Shoot more!
                    new Wait(this, 2.0),
                    new StartKicker(this, 0.1),

                    // make sure they're all gone!
                    new Wait (this, 3.0),
                    new StopKicker(this),
                    // ======== END SHOOTING =========== //

                    // move to intake position
                    new StartIntake(this, 0.7),
                    new TurnTo(this, intake3StartPose.getHeading(AU)),
                    new MoveTo(this, intake3StartPose.getX(DU), intake3StartPose.getY(DU)),

                    new StartFeeder(this, 0.5),

                    // drive over the balls
                    new TurnTo(this, intake3EndPose.getHeading(AU), 0.5), // minor correctiom, slow is ok
                    new MoveTo(this, intake3EndPose.getX(DU), intake3EndPose.getY(DU), 0.5), // go slow

                    new Wait(this, 1.0), // make sure balls are captured
                    new StopFeeder(this),

                    // return to shoot position
                    new MoveTo(this, shootPose.getX(DU), shootPose.getY(DU)),
                    new TurnTo(this, shootPose.getHeading(AU)),
                    new StopIntake(this),

                    // ======== START SHOOTING =========== //
                    // Shoot 2 balls!
                    new Wait( this, 1.0 ),                      // wait for shooter to hit target velocity
                    new StartFeeder( this, 0.3),                // start the feeder

                    // Shoot more!
                    new Wait(this, 2.0),
                    new StartKicker(this, 0.1),

                    // make sure they're all gone!
                    new Wait (this, 3.0),
                    new StopKicker(this),
                    // ======== END SHOOTING =========== //

                    // get to end position
                    new MoveTo(this, endPose.getX(DU), endPose.getY(DU) ),
                    // shut it down
                    new StopShooter(this),
                    new StopFeeder(this)
            }
        );
    }

    public void loop() {
        super.loop();
    }
}

