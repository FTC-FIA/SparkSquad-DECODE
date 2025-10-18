package org.firstinspires.ftc.teamcode.task;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.component.drive.FieldRelativeDrive;
import org.firstinspires.ftc.teamcode.component.drive.MecanumDrive;
import org.firstinspires.ftc.teamcode.component.util.SparkLogger;

import java.util.Locale;

public class Task_RelativeDriveToPose implements Task {

    private final double DEFAULT_FORWARD_POWER = 0.5;
    private final double DEFAULT_STRAFE_POWER = 0.5;
    private final double DEFAULT_ROTATE_POWER = 0.5;
    private final double DEFAULT_TOLERANCE_X = 50; // in mm
    private final double DEFAULT_TOLERANCE_Y = 50;
    private final double DEFAULT_TOLERANCE_H = 15; // in deg

    private final Telemetry telemetry;
    private final FieldRelativeDrive drive;
    private final GoBildaPinpointDriver pinpoint;
    private final SparkLogger logger = SparkLogger.getLogger();

    private final Pose2D targetPose;
    private double forwardPower = DEFAULT_FORWARD_POWER;
    private double strafePower = DEFAULT_STRAFE_POWER;
    private double rotatePower = DEFAULT_ROTATE_POWER;
    private double toleranceX = DEFAULT_TOLERANCE_X;
    private double toleranceY = DEFAULT_TOLERANCE_Y;
    private double toleranceH = DEFAULT_TOLERANCE_H;

    public Task_RelativeDriveToPose(
            FieldRelativeDrive drive,
            GoBildaPinpointDriver pinpoint,
            Pose2D targetPose,
            Telemetry telemetry) {
        this.drive = drive;
        this.pinpoint = pinpoint;
        this.targetPose = targetPose;
        this.telemetry = telemetry;
    }

    public boolean execute() {

        pinpoint.update();

        double forward;
        double strafe;
        double rotate = 0.0;

        Pose2D pos = pinpoint.getPosition();
        double currentX = pos.getX(DistanceUnit.MM);
        double currentY = pos.getY(DistanceUnit.MM);
        double currentH = pos.getHeading(AngleUnit.DEGREES);

        double errorX = currentX - targetPose.getX(DistanceUnit.MM);
        double errorY = currentY - targetPose.getY(DistanceUnit.MM);
        double errorH = currentH - targetPose.getHeading(AngleUnit.DEGREES);

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

        // Print telemetry
        String position = String.format(
                Locale.US, "{CurrX: %.3f, CurrY: %.3f, CurrH: %.3f}",
                pos.getX(DistanceUnit.MM),
                pos.getY(DistanceUnit.MM),
                pos.getHeading(AngleUnit.DEGREES)
        );
        telemetry.addData("Position", position);
        logger.log("Position: " + position);

        String error = String.format(
                Locale.US, "{XErr: %.3f, YErr: %.3f, HErr: %.3f}",
                errorX, errorY, errorH
        );
        telemetry.addData("Error", error);
        telemetry.addData("Pinpoint Device Status", pinpoint.getDeviceStatus());

        logger.log("Error: " + error);
        logger.log("Pinpoint status: " + pinpoint.getDeviceStatus());

        // if any errors are > tolerance, keep going
        return (
                Math.abs(errorX) > toleranceX
                || Math.abs(errorY) > toleranceY
                || Math.abs(errorH) > toleranceH
        );
    }
}
