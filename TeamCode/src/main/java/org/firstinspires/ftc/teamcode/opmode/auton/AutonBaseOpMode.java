package org.firstinspires.ftc.teamcode.opmode.auton;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;
import org.firstinspires.ftc.teamcode.task.AutonTaskList;
import org.firstinspires.ftc.teamcode.util.AllianceColor;
import org.firstinspires.ftc.teamcode.util.ColorPose2D;

public abstract class AutonBaseOpMode extends RobotBaseOpMode {

    private final ElapsedTime elapsedTime = new ElapsedTime();
    protected AutonTaskList autonTaskList;

    public void loop() {
        telemetry.addData("Elapsed time (ms)", elapsedTime.seconds());

        autonTaskList.execute();

        telemetry.addData("X", odometer.getX(DistanceUnit.INCH));
        telemetry.addData("Y", odometer.getY(DistanceUnit.INCH));
        telemetry.addData("H", odometer.getHeading(AngleUnit.DEGREES));
        telemetry.update();
    }
}

