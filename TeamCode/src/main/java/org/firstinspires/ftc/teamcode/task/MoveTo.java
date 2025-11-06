package org.firstinspires.ftc.teamcode.task;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.component.drive.FieldRelativeDrive;
import org.firstinspires.ftc.teamcode.component.sensor.Odometer;
import org.firstinspires.ftc.teamcode.util.SparkLogger;
import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;

import java.util.Locale;

public class MoveTo implements Task {

    private final double DEFAULT_FORWARD_POWER = 0.5;
    private final double DEFAULT_STRAFE_POWER = 0.5;
    private final double DEFAULT_TOLERANCE_X = 1.0; // in inches
    private final double DEFAULT_TOLERANCE_Y = 1.0; // in inches
    private double forwardPower = DEFAULT_FORWARD_POWER;
    private double strafePower = DEFAULT_STRAFE_POWER;
    private double toleranceX = DEFAULT_TOLERANCE_X;
    private double toleranceY = DEFAULT_TOLERANCE_Y;

    private final Telemetry telemetry;
    private final FieldRelativeDrive drive;
    private final Odometer odometer;

    private final SparkLogger logger = SparkLogger.getLogger();

    private final Pose2D targetPose;

    public MoveTo(
            RobotBaseOpMode robot,
            double targetXInches,
            double targetYInches
    ) {
        this.drive = robot.getFieldRelativeDrive();;
        this.odometer = robot.getOdometer();
        this.telemetry = robot.getTelemetry();

        odometer.update();
        double heading = odometer.getHeading(AngleUnit.DEGREES);
        this.targetPose = new Pose2D(
                DistanceUnit.INCH,
                targetXInches,
                targetYInches,
                AngleUnit.DEGREES,
                heading
        );
    }

    public void setForwardPower(double forwardPower) {
        this.forwardPower = forwardPower;
    }

    public boolean execute() {

        odometer.update();

        double forward;
        double strafe;
        double rotate = 0.0;

        double currentX = odometer.getX(DistanceUnit.INCH);
        double currentY = odometer.getY(DistanceUnit.INCH);

        double errorX = targetPose.getX(DistanceUnit.INCH) - currentX;
        double errorY = targetPose.getY(DistanceUnit.INCH) - currentY;

        if (Math.abs(errorX) < toleranceX) {
            forward = 0.0;
        } else if (errorX > 0) {
            forward = forwardPower;
        } else {
            forward = -forwardPower;
        }

        if (Math.abs(errorY) < toleranceY) {
            strafe = 0.0;
        } else if (errorY > 0) {
            strafe = strafePower;
        } else {
            strafe = -strafePower;
        }

        // Actuate - execute robot functions
        rotate = 0.0;
        drive.drive(forward, strafe, rotate);

        telemetry.addData("Task", "MoveTo");
        telemetry.addData("fwdPower", String.format(Locale.US, "%.2f", forward));
        telemetry.addData("stfPower", String.format(Locale.US, "%.2f", strafe));
        telemetry.addData("errorX", String.format(Locale.US, "%.2f", errorX));
        telemetry.addData("errorY", String.format(Locale.US, "%.2f", errorY));

        // if any errors are > tolerance, keep going
        return (
                Math.abs(errorX) > toleranceX
                || Math.abs(errorY) > toleranceY
        );
    }
}
