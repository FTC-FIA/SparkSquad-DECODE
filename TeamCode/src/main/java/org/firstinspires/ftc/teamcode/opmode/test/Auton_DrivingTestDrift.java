package org.firstinspires.ftc.teamcode.opmode.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.opmode.auton.AutonBaseOpMode;
import org.firstinspires.ftc.teamcode.task.AutonTaskList;
import org.firstinspires.ftc.teamcode.task.MoveTo;
import org.firstinspires.ftc.teamcode.task.StartAt;
import org.firstinspires.ftc.teamcode.task.Task;
import org.firstinspires.ftc.teamcode.task.TurnTo;

@Autonomous(name="Auton Driving Test - Drift", group="Test")
public abstract class Auton_DrivingTestDrift extends AutonBaseOpMode {

    private final ElapsedTime elapsedTime = new ElapsedTime();

    private static final DistanceUnit DU = DistanceUnit.INCH;
    private static final AngleUnit AU = AngleUnit.DEGREES;

    public void init() {
        super.init();

        this.autonTaskList = new AutonTaskList(
            this,
            new Task[]{
                    // Heading = 0
                    new StartAt(this, 0.0, 0.0, 0.0),
                    new MoveTo(this, 12.0, 12.0),
                    new MoveTo(this, 0.0, 0.0),
                    new MoveTo(this, -12.0, -12.0),
                    new MoveTo(this, 12.0, -12.0),
                    new MoveTo(this, 0.0, 0.0),

                    // Heading = 90
                    new TurnTo(this, 90.0),
                    new MoveTo(this, 12.0, 12.0),
                    new MoveTo(this, 0.0, 0.0),
                    new MoveTo(this, -12.0, -12.0),
                    new MoveTo(this, 12.0, -12.0),
                    new MoveTo(this, 0.0, 0.0),

                    // Heading = 180
                    new TurnTo(this, 180.0),
                    new MoveTo(this, 12.0, 12.0),
                    new MoveTo(this, 0.0, 0.0),
                    new MoveTo(this, -12.0, -12.0),
                    new MoveTo(this, 12.0, -12.0),
                    new MoveTo(this, 0.0, 0.0),

                    // Heading = -90
                    new TurnTo(this, -90.0),
                    new MoveTo(this, 12.0, 12.0),
                    new MoveTo(this, 0.0, 0.0),
                    new MoveTo(this, -12.0, -12.0),
                    new MoveTo(this, 12.0, -12.0),
                    new MoveTo(this, 0.0, 0.0),

                    // reset
                    new TurnTo(this, 0.0)

            }
        );
    }
}

