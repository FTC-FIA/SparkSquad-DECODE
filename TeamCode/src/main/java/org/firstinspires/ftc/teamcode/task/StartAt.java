package org.firstinspires.ftc.teamcode.task;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.component.sensor.Odometer;
import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;

public class StartAt implements Task {

    private final Odometer odometer;
    private final double startX;
    private final double startY;
    private final double startH;

    public StartAt(
            RobotBaseOpMode robot,
            double startXInches,
            double startYInches,
            double startHDegrees
    ) {
        this.odometer = robot.getOdometer();
        this.startX = startXInches;
        this.startY = startYInches;
        this.startH = startHDegrees;
    }

    /**
     * runs task for 'duration' seconds
     * @return true if still running, false when time is reached
     */
    public boolean execute() {
        odometer.setPosition(
                new Pose2D(
                    DistanceUnit.INCH,
                    startX,
                    startY,
                    AngleUnit.DEGREES,
                    startH
                )
        );
        return false;
    }
}
