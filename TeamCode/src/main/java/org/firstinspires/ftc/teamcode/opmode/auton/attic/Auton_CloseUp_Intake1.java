package org.firstinspires.ftc.teamcode.opmode.auton.attic;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.opmode.auton.AutonBaseOpMode;
import org.firstinspires.ftc.teamcode.task.AutonTaskList;
import org.firstinspires.ftc.teamcode.task.attic.MoveTo;
import org.firstinspires.ftc.teamcode.task.Shoot3;
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
import org.firstinspires.ftc.teamcode.task.attic.TurnTo;
import org.firstinspires.ftc.teamcode.task.Wait;
import org.firstinspires.ftc.teamcode.util.Alliance;

public abstract class Auton_CloseUp_Intake1 extends AutonBaseOpMode {

    private final ElapsedTime elapsedTime = new ElapsedTime();
    private Alliance color;

    private static DistanceUnit DU = DistanceUnit.INCH;
    private static AngleUnit AU = AngleUnit.DEGREES;

//    protected void setColor( Alliance color ) {
//        this.color = color;
//    }

    public void init() {
        super.init();

        Pose2D startPose = Constants.CLOSE_START.forAlliance(color);
        Pose2D shootPose = Constants.CLOSE_SHOT.forAlliance(color);
        Pose2D intake1StartPose = Constants.INTAKE_CLOSE_START.forAlliance(color);
        Pose2D intake1EndPose = Constants.INTAKE_CLOSE_END.forAlliance(color);
        Pose2D endPose = Constants.CLOSE_PARK.forAlliance(color);

        this.autonTaskList = new AutonTaskList(
            this,
            new Task[]{
                    // Start position
                    new StartAt(this, startPose.getX(DU), startPose.getY(DU), startPose.getHeading(AU)),

                    // spin up shooter
                    new StartShooterWithVelocity(this, Constants.CLOSEUP_SHOOTER_VELOCITY),  // start the shooter

                    // move to shooting position using a TaskList
                    new MoveTo(this, shootPose.getX(DU), shootPose.getY(DU)),
                    new TurnTo(this, shootPose.getHeading(AU))  ,

                    // ======== START SHOOTING =========== //
                    // Shoot 2 balls!
                    // new Wait(this, 1.0),                        // wait for shooter to hit target velocity
                    new StartFeeder(this, 0.5),                    // start the feeder

                    // Shoot more!
                    new Wait(this, 3.0),
                    new StartKicker(this, 0.3),

                    // make sure they're all gone!
                    new Wait(this, 3.0),
                    new StopKicker(this),
                    new StopFeeder(this),
                    // ======== END SHOOTING =========== //

                    // move to intake start position
                    new StartIntake(this, 1.0),
                    new TurnTo(this, intake1StartPose.getHeading(AU)),
                    //new Wait(this, 1.0), // for debugging
                    new StartFeeder(this),
                    new MoveTo(this, intake1StartPose.getX(DU), intake1StartPose.getY(DU)),
                    new TurnTo(this, intake1StartPose.getHeading(AU), 0.3),
                    //new Wait(this, 1.0), // for debugging

                    // drive over the balls to pick them up
                    //new Wait(this, 1.0), // for debugging
                    new MoveTo(this, intake1EndPose.getX(DU), intake1EndPose.getY(DU), 0.5), // slow it down
                    //new Wait(this, 1.0), // for debugging
                    new StopFeeder(this),

                    // return to shoot position
                    new MoveTo(this, shootPose.getX(DU), shootPose.getY(DU), 0.7),
                    new TurnTo(this, shootPose.getHeading(AU)),

                    new Shoot3(this), // TODO: see if this works

                    // get to end position
                    new MoveTo(this, endPose.getX(DU), endPose.getY(DU)),
                    // shut it down
                    new StopIntake(this),

                    new StopShooter(this),
                    new StopFeeder(this)
            }
        );
    }
}

