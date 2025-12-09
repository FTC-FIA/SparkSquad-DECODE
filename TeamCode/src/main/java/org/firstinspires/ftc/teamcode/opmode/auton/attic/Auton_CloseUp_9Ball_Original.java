package org.firstinspires.ftc.teamcode.opmode.auton.attic;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.opmode.auton.AutonBaseOpMode;
import org.firstinspires.ftc.teamcode.task.AutonTaskList;
import org.firstinspires.ftc.teamcode.task.MoveWithPIDTo;
import org.firstinspires.ftc.teamcode.task.Shoot3;
import org.firstinspires.ftc.teamcode.task.StartAt;
import org.firstinspires.ftc.teamcode.task.StartFeeder;
import org.firstinspires.ftc.teamcode.task.StartIntake;
import org.firstinspires.ftc.teamcode.task.StartShooterWithVelocity;
import org.firstinspires.ftc.teamcode.task.StopFeeder;
import org.firstinspires.ftc.teamcode.task.StopIntake;
import org.firstinspires.ftc.teamcode.task.StopShooter;
import org.firstinspires.ftc.teamcode.task.Task;
import org.firstinspires.ftc.teamcode.task.Wait;
import org.firstinspires.ftc.teamcode.util.Alliance;

public abstract class Auton_CloseUp_9Ball_Original extends AutonBaseOpMode {

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
        Pose2D intake1StartPose = Constants.INTAKE_CLOSE_START_BLUE.forAlliance(color);
        Pose2D intake1EndPose = Constants.INTAKE_CLOSE_END_BLUE.forAlliance(color);
        Pose2D intake2StartPose = Constants.INTAKE_MIDDLE_START_BLUE.forAlliance(color);
        Pose2D intake2EndPose = Constants.INTAKE_MIDDLE_END_BLUE.forAlliance(color);
        Pose2D endPose = Constants.CLOSE_PARK_BLUE.forAlliance(color);

        this.autonTaskList = new AutonTaskList(
            this,
            new Task[]{
                    // Start position
                    new StartAt(this, startPose.getX(DU), startPose.getY(DU), startPose.getHeading(AU)),

                    // spin up shooter
                    new StartShooterWithVelocity(this, Constants.CLOSEUP_SHOOTER_VELOCITY),

                    // move to shooting position
                    new MoveWithPIDTo(this, shootPose.getX(DU), shootPose.getY(DU), shootPose.getHeading(AU), 0.6),

                    // wait for shooter to hit velocity
                    new Wait(this, 1.0),

                    // shoot #1
                    new Shoot3(this),

                    // start intake and keep it running
                    new StartIntake(this, 1.0),

                    // move to intake start position
                    new MoveWithPIDTo(this, intake1StartPose.getX(DU), intake1StartPose.getY(DU), intake1StartPose.getHeading(AU)),

                    // start feeder to help with intake
                    new StartFeeder(this),

                    // move to intake end position
                    new MoveWithPIDTo(this,
                            intake1EndPose.getX(DU),
                            intake1EndPose.getY(DU),
                            intake1EndPose.getHeading(AU),
                            0.5
                    ),

                    // stop feeder so we don't shoot to early
                    new StopFeeder(this),
                    new Wait(this, 0.33),

                    // return to shoot position
                    new MoveWithPIDTo(this, shootPose.getX(DU), shootPose.getY(DU), shootPose.getHeading(AU)),

                    // shoot #2
                    new Shoot3(this),

                    // start intake and keep it running
                    new StartIntake(this, 1.0),

                    // move to intake start position
                    new MoveWithPIDTo(this, intake2StartPose.getX(DU), intake2StartPose.getY(DU), intake2StartPose.getHeading(AU)),

                    // start feeder to help with intake
                    new StartFeeder(this),

                    // move to intake end position
                    new MoveWithPIDTo(this,
                            intake2EndPose.getX(DU),
                            intake2EndPose.getY(DU),
                            intake2EndPose.getHeading(AU),
                            0.5
                    ),

                    // stop feeder so we don't shoot to early
                    new StopFeeder(this),
                    new Wait(this, 0.33),

                    // return to shoot position
                    new MoveWithPIDTo(this, shootPose.getX(DU), shootPose.getY(DU), shootPose.getHeading(AU)),

                    // shoot
                    new Shoot3(this),

                    // get to end position
                    new MoveWithPIDTo(this, endPose.getX(DU), endPose.getY(DU), endPose.getHeading(AU)),

                    // shut it down
                    new StopIntake(this),
                    new StopShooter(this),
            }
        );
    }
}

