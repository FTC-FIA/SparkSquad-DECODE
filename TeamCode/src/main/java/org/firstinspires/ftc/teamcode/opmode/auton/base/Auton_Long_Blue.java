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

@Autonomous(name="BLUE - LONG", group="Main")
public class Auton_Long_Blue extends AutonBaseOpMode {

    private final ElapsedTime elapsedTime = new ElapsedTime();

    private static DistanceUnit DU = DistanceUnit.INCH;
    private static AngleUnit AU = AngleUnit.DEGREES;

    public void init() {
        setAlliance(Alliance.BLUE);
        super.init();

        Pose2D startPose = Constants.LONG_START_BLUE.forAlliance(alliance);
        Pose2D shootPose = Constants.LONG_SHOT_BLUE.forAlliance(alliance);
        Pose2D endPose = Constants.LONG_PARK_BLUE.forAlliance(alliance);
        Pose2D intake3StartPose = Constants.INTAKE_FAR_START_BLUE.forAlliance(alliance);
        Pose2D intake3EndPose = Constants.INTAKE_FAR_END_BLUE.forAlliance(alliance);

        this.autonTaskList = new AutonTaskList(
            this,
            new Task[]{
                    // Start position
                    new StartAt(this, startPose.getX(DU), startPose.getY(DU), startPose.getHeading(AU)),

                    // spin up shooter
                    new StartShooterWithVelocity(this, Constants.LONGSHOT_SHOOTER_VELOCITY),

                    // move to shooting position
                    new MoveWithPIDTo(
                            this,
                            shootPose.getX(DU),
                            shootPose.getY(DU),
                            shootPose.getHeading(AU)
                    ),

                    // shoot
                    new Aim(this, 3.0, 0.0),
                    new Wait(this, 1.0),
                    new Shoot3(this),

                    // start intake and keep it running
                    new StartIntake(this, 1.0),

                    // move to intake start position
                    new MoveWithPIDTo(
                            this,
                            intake3StartPose.getX(DU),
                            intake3StartPose.getY(DU),
                            intake3StartPose.getHeading(AU),
                            0.5
                    ),

                    // start feeder to help with intake
                    new StartFeeder(this),

                    // move to intake end position
                    new MoveWithPIDTo(this,
                            intake3EndPose.getX(DU),
                            intake3EndPose.getY(DU),
                            intake3EndPose.getHeading(AU),
                            0.4,
                            2.5
                    ),

                    // stop feeder so we don't shoot to early
                    new Wait(this, 0.42),
                    new StopFeeder(this),

                    // return to shoot position
                    new MoveWithPIDTo(
                            this,
                            shootPose.getX(DU),
                            shootPose.getY(DU),
                            shootPose.getHeading(AU),
                            0.6
                    ),

                    // shoot
                    new Aim(this, 2.0, 0.0), // blue aim is not inverted (red is)
                    new Shoot3(this),

                    // get to end position
                    new MoveWithPIDTo(this, endPose.getX(DU), endPose.getY(DU), endPose.getHeading(AU)),

                    // shut it down
                    new StopIntake(this),
                    new StopShooter(this),
            }
        );
    }

    public void loop() {
        super.loop();
    }
}

