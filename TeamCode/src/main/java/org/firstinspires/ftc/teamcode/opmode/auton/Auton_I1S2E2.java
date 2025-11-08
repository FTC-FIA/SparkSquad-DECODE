package org.firstinspires.ftc.teamcode.opmode.auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;
import org.firstinspires.ftc.teamcode.task.AutonTaskList;
import org.firstinspires.ftc.teamcode.task.AutonTaskRunner;
import org.firstinspires.ftc.teamcode.task.MoveTo;
import org.firstinspires.ftc.teamcode.task.MoveToXYH;
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

public class Auton_I1S2E2 extends AutonBaseOpMode {

    private final ElapsedTime elapsedTime = new ElapsedTime();
    private AllianceColor color;

    private static DistanceUnit DU = DistanceUnit.INCH;
    private static AngleUnit AU = AngleUnit.DEGREES;

    protected void setColor( AllianceColor color ) {
        this.color = color;
    }

    public void init() {

        Pose2D startPose = I1.forColor(color);
        Pose2D shootPose = S2.forColor(color);
        Pose2D endPose = E2.forColor(color);

        super.init();
        this.autonTaskList = new AutonTaskList(
            this,
            new Task[] {
                    // starting position
                    new StartAt( this, startPose.getX(DU), startPose.getY(DU), startPose.getHeading(AU) ),

                    new StartIntake( this ),                    // helps keep balls in

                    // shooting position
                    new MoveToXYH( this, shootPose.getX(DU), shootPose.getY(DU), shootPose.getHeading(AU) ),

                    new StartShooterWithVelocity( this, 600 ),  // start the shooter
                    new StartFeeder( this ),                    // start the feeder
                    new Wait( this, 2.0 ),                      // wait for 2 seconds => 1st shot

                    new StartKicker( this, 0.4 ),               // try to kick the other two balls out
                    new Wait( this, 8.0 ),                      // ... for 6 seconds

                    // end position
                    new MoveToXYH( this, endPose.getX(DU), endPose.getY(DU), endPose.getHeading(AU) ),

                    new StopKicker( this ),                     // shut it down
                    new StopShooter( this ),
                    new StopFeeder( this ),
                    new StopIntake( this ),
            }
        );
    }

    public void loop() {
        telemetry.addData("Elapsed time (ms)", elapsedTime.seconds());
        autonTaskList.execute();
        telemetry.addData("X", odometer.getX(DistanceUnit.INCH));
        telemetry.addData("Y", odometer.getY(DistanceUnit.INCH));
        telemetry.addData("H", odometer.getHeading(AngleUnit.DEGREES));
        telemetry.update();
        telemetry.update();
    }
}

