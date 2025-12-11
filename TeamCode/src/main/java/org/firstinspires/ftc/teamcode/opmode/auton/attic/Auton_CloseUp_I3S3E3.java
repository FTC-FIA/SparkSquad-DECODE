package org.firstinspires.ftc.teamcode.opmode.auton.attic;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.opmode.auton.AutonBaseOpMode;
import org.firstinspires.ftc.teamcode.task.AutonTaskList;
import org.firstinspires.ftc.teamcode.task.attic.MoveTo;
import org.firstinspires.ftc.teamcode.task.StartAt;
import org.firstinspires.ftc.teamcode.task.StartFeeder;
import org.firstinspires.ftc.teamcode.task.StartKicker;
import org.firstinspires.ftc.teamcode.task.StartShooterWithVelocity;
import org.firstinspires.ftc.teamcode.task.StopFeeder;
import org.firstinspires.ftc.teamcode.task.StopKicker;
import org.firstinspires.ftc.teamcode.task.StopShooter;
import org.firstinspires.ftc.teamcode.task.Task;
import org.firstinspires.ftc.teamcode.task.attic.TurnTo;
import org.firstinspires.ftc.teamcode.task.Wait;
import org.firstinspires.ftc.teamcode.util.Alliance;

public abstract class Auton_CloseUp_I3S3E3 extends AutonBaseOpMode {

    private final ElapsedTime elapsedTime = new ElapsedTime();
    private Alliance color;

    private static DistanceUnit DU = DistanceUnit.INCH;
    private static AngleUnit AU = AngleUnit.DEGREES;

//    protected void setColor( Alliance color ) {
//        this.color = color;
//    }

    public void init() {
        super.init();
        Pose2D startPose = Constants.CLOSE_START_BLUE.forAlliance(color);
        Pose2D shootPose = Constants.CLOSE_SHOT_BLUE.forAlliance(color);
        Pose2D endPose = Constants.CLOSE_PARK_BLUE.forAlliance(color);

        this.autonTaskList = new AutonTaskList(
            this,
            new Task[]{
                    // Start position
                    new StartAt(this, startPose.getX(DU), startPose.getY(DU), startPose.getHeading(AU)),

                    // Helps keep the balls in while moving, also with shooting
                    //new StartIntake(this),

                    // spin up shooter
                    new StartShooterWithVelocity(this, 560),  // start the shooter

                    // move to shooting position using a TaskList
                    new MoveTo( this, shootPose.getX(DU), shootPose.getY(DU) ),
                    new TurnTo( this, shootPose.getHeading(AU))  ,

                    // Shoot 2 balls!
                    new Wait( this, 8.0 ),                      // wait for shooter to hit target velocity
                    new StartFeeder( this, 0.3),                // start the feeder

                    // Shoot more!
                    new Wait(this, 5.0),
                    new StartKicker(this, 0.1),
                    new Wait ( this, 0.5 ),
                    new StopKicker( this ),

                    // make sure they're all gone!
                    new Wait ( this, 3.0 ),
                    new StartKicker(this, 0.1),

                    new Wait ( this, 5.0 ),

                    // move to end position
                    new TurnTo( this, 0.0 ),
                    new MoveTo( this, endPose.getX(DU), endPose.getY(DU) ),

                    // shut it down
                    new StopKicker(this),
                    new StopShooter(this),
                    new StopFeeder(this),
                    //new StopIntake(this),
            }
        );
    }
}

