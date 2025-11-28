package org.firstinspires.ftc.teamcode.task;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.component.drive.FieldRelativeDrive;
import org.firstinspires.ftc.teamcode.component.sensor.Odometer;
import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;
import org.firstinspires.ftc.teamcode.util.SparkLogger;

import java.util.Locale;

public class MoveToPose implements Task {

    private final double DEFAULT_FORWARD_POWER = 0.4;
    private final double DEFAULT_STRAFE_POWER = 0.4;
    private final double DEFAULT_ROTATE_POWER = 0.2;
    private final double DEFAULT_TOLERANCE_X = 1.0; // in inches
    private final double DEFAULT_TOLERANCE_Y = 1.0; // in inches
    private final double DEFAULT_TOLERANCE_H = 1.0; // in deg

    private final Telemetry telemetry;
    private final FieldRelativeDrive drive;
    private final Odometer odometer;
    private final SparkLogger logger = SparkLogger.getLogger();

    private double forwardPower = DEFAULT_FORWARD_POWER;
    private double strafePower = DEFAULT_STRAFE_POWER;
    private double rotatePower = DEFAULT_ROTATE_POWER;
    private double toleranceX = DEFAULT_TOLERANCE_X;
    private double toleranceY = DEFAULT_TOLERANCE_Y;
    private double toleranceH = DEFAULT_TOLERANCE_H;

    private double targetX;
    private double targetY;
    private double targetH;


    public MoveToPose(
            RobotBaseOpMode robot,
            double targetXInches,
            double targetYInches,
            double targetHDegrees
    ) {
        this.drive = robot.getFieldRelativeDrive();
        this.odometer = robot.getOdometer();
        this.telemetry = robot.getTelemetry();

        targetX = targetXInches;
        targetY = targetYInches;
        targetH = targetHDegrees;
    }


    public boolean execute() {

        odometer.update();

        double forward;
        double strafe;
        double rotate;

        double currentX = odometer.getX(DistanceUnit.INCH);
        double currentY = odometer.getY(DistanceUnit.INCH);
        double currentH = odometer.getHeading(AngleUnit.DEGREES);


        double errorX = targetX - currentX;
        double errorY = targetY - currentY;
        double errorH = targetH - currentH;

        if (Math.abs(errorX) < toleranceX) {
            forward = 0.0;
        } else if (errorX > 0) {
            forward = -forwardPower;
        } else {
            forward = forwardPower;
        }

        if (Math.abs(errorY) < toleranceY) {
            strafe = 0.0;
        } else if (errorY > 0) {
            strafe = -strafePower;
        } else {
            strafe = strafePower;
        }

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
        drive.drive(forward, strafe, rotate);

        telemetry.addData("Task", String.format(Locale.US, "MoveToPose %.1f %.1f %.1f", targetX, targetY, targetH);
        telemetry.addData("Error X", String.format(Locale.US, "%.2f (%.2f)", errorX, Math.abs(errorX)));
        telemetry.addData("Error Y", String.format(Locale.US, "%.2f (%.2f)", errorY, Math.abs(errorY)));
        telemetry.addData("Error H", String.format(Locale.US, "%.2f (%.2f)", errorH, Math.abs(errorH)));
        telemetry.addData("fwdPower", String.format(Locale.US, "%.2f", forward));
        telemetry.addData("stfPower", String.format(Locale.US, "%.2f", strafe));
        telemetry.addData("rotPower", String.format(Locale.US, "%.2f", strafe));

        // if any errors are > tolerance, keep going
        return (
                Math.abs(errorX) > toleranceX
                || Math.abs(errorY) > toleranceY
                || Math.abs(errorH) > toleranceH
        );
    }
}
