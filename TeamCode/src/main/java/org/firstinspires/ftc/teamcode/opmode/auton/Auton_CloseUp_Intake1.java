package org.firstinspires.ftc.teamcode.opmode.auton;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.Constants;
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
import org.firstinspires.ftc.teamcode.util.AllianceColor;

public abstract class Auton_CloseUp_Intake1 extends AutonBaseOpMode {

    private final ElapsedTime elapsedTime = new ElapsedTime();
    private AllianceColor color;

    private static DistanceUnit DU = DistanceUnit.INCH;
    private static AngleUnit AU = AngleUnit.DEGREES;

    protected void setColor( AllianceColor color ) {
        this.color = color;
    }

    public void init() {
        super.init();
        Pose2D startPose = Constants.I3.forColor(color);
        Pose2D shootPose = Constants.S3.forColor(color);
        Pose2D intake1StartPose = Constants.IS1.forColor(color);
        Pose2D intake1EndPose = Constants.IE1.forColor(color);
        Pose2D endPose = Constants.E3.forColor(color);

        this.autonTaskList = new AutonTaskList(
            this,
            new Task[]{
                    // Start position
                    new StartAt(this, startPose.getX(DU), startPose.getY(DU), startPose.getHeading(AU)),

                    // spin up shooter
                    new StartShooterWithVelocity(this, 600),  // start the shooter

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
                    new StartIntake(this),
                    new TurnTo(this, intake1StartPose.getHeading(AU)),
                    new MoveTo(this, intake1StartPose.getX(DU), intake1StartPose.getY(DU)),

                    // drive over the balls
                    new TurnTo(this, intake1EndPose.getHeading(AU)),
                    new MoveTo(this, intake1EndPose.getX(DU), intake1EndPose.getY(DU)),

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
}

