package org.firstinspires.ftc.teamcode.task.attic;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.component.drive.FieldRelativeDrive;
import org.firstinspires.ftc.teamcode.component.sensor.Odometer;
import org.firstinspires.ftc.teamcode.task.Task;
import org.firstinspires.ftc.teamcode.util.SparkLogger;
import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;

import java.util.Locale;

public class TurnTo implements Task {

    public static final double DEFAULT_ROTATE_POWER = 0.5;
    public static final double DEFAULT_TOLERANCE_H = 0.5; // in deg]
    public static final double DEFAULT_SPEED_FACTOR = 1.0;

    private double speedFactor;

    private double rotatePower = DEFAULT_ROTATE_POWER;
    private double toleranceH = DEFAULT_TOLERANCE_H;

    private final Telemetry telemetry;
    private final FieldRelativeDrive drive;
    private final Odometer odometer;
    private final SparkLogger logger = SparkLogger.getLogger();

    private final Pose2D targetPose;

    public TurnTo(
            RobotBaseOpMode robot,
            double headingDegrees,
            double speedFactor
    ) {
        this.drive = robot.getFieldRelativeDrive();;
        this.odometer = robot.getOdometer();
        this.telemetry = robot.getTelemetry();
        this.speedFactor = speedFactor;

        odometer.update();
        double currentX = odometer.getX(DistanceUnit.INCH);
        double currentY = odometer.getY(DistanceUnit.INCH);
        this.targetPose = new Pose2D(
                DistanceUnit.INCH,
                currentX,
                currentY,
                AngleUnit.DEGREES,
                headingDegrees
        );
    }


    public TurnTo(
            RobotBaseOpMode robot,
            double headingDegrees
    ) {
        this(robot, headingDegrees, DEFAULT_SPEED_FACTOR);
    }

    public boolean execute() {
        double rotate = 0.0;

        odometer.update();
        double currentH = odometer.getHeading(AngleUnit.DEGREES);
        double errorH = currentH - targetPose.getHeading(AngleUnit.DEGREES);

        if (Math.abs(errorH) < toleranceH) {
            rotate = 0.0;
        } else if (errorH > 0) {
            rotate = -rotatePower;
        } else {
            rotate = rotatePower;
        }
        if (Math.abs(errorH) > 180.0) {
            rotate = -rotate;
        }
        // Actuate - execute robot functions
        drive.drive(0.0, 0.0, rotate);

        telemetry.addData("Task", String.format(
                Locale.US,
                "TurnTo %.1f",
                targetPose.getHeading(AngleUnit.DEGREES)));
        telemetry.addData("H", currentH);
        telemetry.addData("Error", errorH);

        // if any errors are > tolerance, keep going
        return (
            Math.abs(errorH) > toleranceH
        );
    }
}
