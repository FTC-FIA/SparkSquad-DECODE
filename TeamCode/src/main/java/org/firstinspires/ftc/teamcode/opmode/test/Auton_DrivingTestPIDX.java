package org.firstinspires.ftc.teamcode.opmode.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.opmode.auton.AutonBaseOpMode;
import org.firstinspires.ftc.teamcode.task.AutonTaskList;
import org.firstinspires.ftc.teamcode.task.MoveToPose;
import org.firstinspires.ftc.teamcode.task.MoveWithPIDTo;
import org.firstinspires.ftc.teamcode.task.StartAt;
import org.firstinspires.ftc.teamcode.task.Task;
import org.firstinspires.ftc.teamcode.task.Wait;

@Autonomous(name="Auton Driving Test - PIDX", group="Test")
public class Auton_DrivingTestPIDX extends AutonBaseOpMode {

    private final ElapsedTime elapsedTime = new ElapsedTime();

    private static final DistanceUnit DU = DistanceUnit.INCH;
    private static final AngleUnit AU = AngleUnit.DEGREES;

    public void init() {
        super.init();

        this.autonTaskList = new AutonTaskList(
            this,
            new Task[]{
                    new StartAt(this, 0.0, 0.0, 0.0),
                    new MoveWithPIDTo(this, 12.0, 0.0, 0.0),
                    new Wait(this, 1.0),
                    new MoveWithPIDTo(this, 0.0, 0.0, 0.0),
            }
        );
    }
}

