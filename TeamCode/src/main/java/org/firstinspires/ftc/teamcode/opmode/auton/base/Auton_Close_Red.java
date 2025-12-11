package org.firstinspires.ftc.teamcode.opmode.auton.base;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.opmode.auton.AutonBaseOpMode;
import org.firstinspires.ftc.teamcode.task.Aim;
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

@Autonomous(name="RED - CLOSE", group="Main")
public class Auton_Close_Red extends AutonBaseOpMode {

    private final ElapsedTime elapsedTime = new ElapsedTime();

    private static DistanceUnit DU = DistanceUnit.INCH;
    private static AngleUnit AU = AngleUnit.DEGREES;

    @Override
    public void init() {
        setAlliance(Alliance.RED);
        super.init();

        Pose2D startPose = Constants.CLOSE_START_RED.forAlliance(alliance);

        Pose2D shootPose1 = Constants.CLOSE_SHOT_1_RED.forAlliance(alliance);
        Pose2D shootPose2 = Constants.CLOSE_SHOT_2_RED.forAlliance(alliance);
        Pose2D shootPose3 = Constants.CLOSE_SHOT_3_RED.forAlliance(alliance);

        Pose2D intake1StartPose = Constants.INTAKE_CLOSE_START_RED.forAlliance(alliance);
        Pose2D intake1EndPose = Constants.INTAKE_CLOSE_END_RED.forAlliance(alliance);
        Pose2D intake2StartPose = Constants.INTAKE_MIDDLE_START_RED.forAlliance(alliance);
        Pose2D intake2EndPose = Constants.INTAKE_MIDDLE_END_RED.forAlliance(alliance);

        Pose2D endPose = Constants.CLOSE_PARK_RED.forAlliance(alliance);

        this.autonTaskList = new AutonTaskList(
            this,
            new Task[]{
                    // Start position
                    new StartAt(
                            this,
                            startPose.getX(DU),
                            startPose.getY(DU),
                            startPose.getHeading(AU)
                    ),

                    // spin up shooter
                    new StartShooterWithVelocity(this, Constants.CLOSEUP_SHOOTER_VELOCITY),

                    // move to shooting position
                    new MoveWithPIDTo(
                            this,
                            shootPose1.getX(DU),
                            shootPose1.getY(DU),
                            shootPose1.getHeading(AU),
                            0.6
                    ),

                    // shoot #1
                    new Aim(this, 2.0, -30.0),
                    new Shoot3(this),

                    // intake #1 - start intake
                    new StartIntake(this, 1.0),

                    // move to intake start position
                    new MoveWithPIDTo(
                            this,
                            intake1StartPose.getX(DU),
                            intake1StartPose.getY(DU),
                            intake1StartPose.getHeading(AU),
                            0.6
                    ),

                    // start feeder to help with intake
                    new StartFeeder(this),

                    // move to 1st intake end position
                    new MoveWithPIDTo(
                            this,
                            intake1EndPose.getX(DU),
                            intake1EndPose.getY(DU),
                            intake1EndPose.getHeading(AU),
                            0.5,
                            2.0
                    ),

                    // pause to inhale last ball, stop feeder so we don't shoot to early
                    new Wait(this, 0.6),
                    new StopFeeder(this),

                    // go to 2nd shoot position
                    new MoveWithPIDTo(
                            this,
                            shootPose2.getX(DU),
                            shootPose2.getY(DU),
                            shootPose2.getHeading(AU)
                    ),

                    // shoot #2
                    new Aim(this, 1.0, -20.0),
                    new Shoot3(this),

                    // start intake and keep it running
                    new StartIntake(this, 1.0),

                    // move to intake start position
                    new MoveWithPIDTo(
                            this,
                            intake2StartPose.getX(DU),
                            intake2StartPose.getY(DU),
                            intake2StartPose.getHeading(AU),
                            0.6
                    ),

                    // start feeder to help with intake
                    new StartFeeder(this),

                    // move to intake end position
                    new MoveWithPIDTo(this,
                            intake2EndPose.getX(DU),
                            intake2EndPose.getY(DU),
                            intake2EndPose.getHeading(AU),
                            0.4,
                            2.5
                    ),

                    // pause to inhale last ball, stop feeder so we don't shoot to early
                    new Wait(this, 0.6),
                    new StopFeeder(this),

                    // return to shoot position
                    new MoveWithPIDTo(
                            this,
                            shootPose3.getX(DU),
                            shootPose3.getY(DU),
                            shootPose3.getHeading(AU)
                    ),

                    // shoot
                    new Aim(this, 15.0, -30.0),
                    new Shoot3(this),

                    // Just stay put. No need to move to an end position

                    // shut it down
                    new StopIntake(this),
                    new StopShooter(this),
            }
        );
    }
}

