package org.firstinspires.ftc.teamcode.opmode.auton;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
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

public abstract class Auton_CloseUp_I3S3E3 extends AutonBaseOpMode {

    private final ElapsedTime elapsedTime = new ElapsedTime();
    private AllianceColor color;

    private static DistanceUnit DU = DistanceUnit.INCH;
    private static AngleUnit AU = AngleUnit.DEGREES;

    protected void setColor( AllianceColor color ) {
        this.color = color;
    }

    public void init() {
        super.init();

        Pose2D startPose = I3.forColor(color);
        Pose2D shootPose = S3.forColor(color);
        Pose2D endPose = E3.forColor(color);

        this.autonTaskList = new AutonTaskList(
            this,
            new Task[]{
                    // Startposition
                    new StartAt(this, startPose.getX(DU), startPose.getY(DU), startPose.getHeading(AU)),

                    // Helps keep the balls in while moving, also with shooting
                    new StartIntake(this),

                    // move to shooting position using a TaskList
                    new MoveTo( this, shootPose.getX(DU), shootPose.getY(DU) ),
                    new TurnTo( this, shootPose.getHeading(AU))  ,

                    //new MoveToXYH(this, shootPose.getX(DU), shootPose.getY(DU), shootPose.getHeading(AU)),

                    // Shoot!
                    new StartShooterWithVelocity(this, 600),  // start the shooter
                    new Wait( this, 8.0 ),
                    new StartFeeder( this ),                    // start the feeder
                    //new Wait(this, 8.0),                      // wait for 2 seconds => 1st shot

                    // Shoot more!

                    new Wait(this, 5.0),
                    new StartKicker(this, 0.1),
                    new Wait ( this, 0.5 ),
                    new StopKicker( this ),

                    new Wait ( this, 3.0 ),
                    new StartKicker(this, 0.1),

                    new Wait ( this, 5.0 ),


//                    // move to end position
                    new TurnTo( this, 0.0 ),
                    new MoveTo( this, endPose.getX(DU), endPose.getY(DU) ),
                    //new MoveToXYH(this, endPose.getX(DU), endPose.getY(DU), endPose.getHeading(AU)),
//
//                    // shut it down
                    new StopKicker(this),
                    new StopShooter(this),
                    new StopFeeder(this),
                    new StopIntake(this),
            }
        );
    }
}

