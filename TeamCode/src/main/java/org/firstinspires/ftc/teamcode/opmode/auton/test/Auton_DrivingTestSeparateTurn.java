package org.firstinspires.ftc.teamcode.opmode.auton.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.opmode.auton.AutonBaseOpMode;
import org.firstinspires.ftc.teamcode.task.AutonTaskList;
import org.firstinspires.ftc.teamcode.task.attic.MoveTo;
import org.firstinspires.ftc.teamcode.task.StartAt;
import org.firstinspires.ftc.teamcode.task.Task;
import org.firstinspires.ftc.teamcode.task.attic.TurnTo;

@Autonomous(name="Auton Driving Test - Separate Turns", group="Test")
@Disabled
public class Auton_DrivingTestSeparateTurn extends AutonBaseOpMode {

    private final ElapsedTime elapsedTime = new ElapsedTime();

    private static final DistanceUnit DU = DistanceUnit.INCH;
    private static final AngleUnit AU = AngleUnit.DEGREES;

    public void init() {
        telemetry.addData("init", "started");
        telemetry.update();
        super.init();
        telemetry.addData("init", "after super");
        telemetry.update();
        this.autonTaskList = new AutonTaskList(
            this,
            new Task[]{
                    // Heading = 0
                    new StartAt(this, 0.0, 0.0, 0.0),
//                    new MoveTo(this, 12.0, 12.0),
//                    new MoveTo(this, 0.0, 0.0),
//                    new MoveTo(this, -12.0, -12.0),
//                    new MoveTo(this, 12.0, -12.0),
//                    new MoveTo(this, 0.0, 0.0),

//                    // Heading = 90
//                    new TurnTo(this, 90.0),
//                    new MoveTo(this, 12.0, 12.0),
//                    new MoveTo(this, 0.0, 0.0),
//                    new MoveTo(this, -12.0, -12.0),
//                    new MoveTo(this, 12.0, -12.0),
//                    new MoveTo(this, 0.0, 0.0),

                    // Heading = 180
                    new TurnTo(this, 180.0),
                    new MoveTo(this, 12.0, 12.0),
                    new MoveTo(this, 0.0, 0.0),
                    new MoveTo(this, -12.0, -12.0),
                    new MoveTo(this, 12.0, -12.0),
                    new MoveTo(this, 0.0, 0.0),

                    // Heading = -90
//                    new TurnTo(this, -90.0),
//                    new MoveTo(this, 12.0, 12.0),
//                    new MoveTo(this, 0.0, 0.0),
//                    new MoveTo(this, -12.0, -12.0),
//                    new MoveTo(this, 12.0, -12.0),
//                    new MoveTo(this, 0.0, 0.0),

                    // reset
                    new TurnTo(this, 0.0)
            }
        );
        telemetry.addData("init is", "finished");
        telemetry.update();
    }
}

