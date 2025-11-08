package org.firstinspires.ftc.teamcode.task;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.component.drive.FieldRelativeDrive;
import org.firstinspires.ftc.teamcode.component.sensor.Odometer;
import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;
import org.firstinspires.ftc.teamcode.util.SparkLogger;

import java.util.Locale;

public class MoveToXYH extends TaskList {

    public MoveToXYH(
            RobotBaseOpMode robot,
            double targetXInches,
            double targetYInches,
            double targetHDegrees
    ) {

        super( robot, new Task[] {
                new TurnTo( robot, 0.0 ),
                new MoveTo( robot, targetXInches, targetYInches),
                new TurnTo( robot, targetHDegrees)
        });

    }

}
