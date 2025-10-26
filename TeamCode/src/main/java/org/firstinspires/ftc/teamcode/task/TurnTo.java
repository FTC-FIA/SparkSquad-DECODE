package org.firstinspires.ftc.teamcode.task;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.component.drive.FieldRelativeDrive;
import org.firstinspires.ftc.teamcode.util.SparkLogger;
import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;

public class TurnTo implements Task {

    private final double DEFAULT_FORWARD_POWER = 0.4;
    private final double DEFAULT_STRAFE_POWER = 0.4;
    private final double DEFAULT_ROTATE_POWER = 0.2;
    private final double DEFAULT_TOLERANCE_X = 1.0; // in inches
    private final double DEFAULT_TOLERANCE_Y = 1.0; // in inches
    private final double DEFAULT_TOLERANCE_H = 10.0; // in deg
    private double forwardPower = DEFAULT_FORWARD_POWER;
    private double strafePower = DEFAULT_STRAFE_POWER;
    private double rotatePower = DEFAULT_ROTATE_POWER;
    private double toleranceX = DEFAULT_TOLERANCE_X;
    private double toleranceY = DEFAULT_TOLERANCE_Y;
    private double toleranceH = DEFAULT_TOLERANCE_H;

    private final RobotBaseOpMode robot;
    private final Telemetry telemetry;
    private final FieldRelativeDrive drive;
    private final GoBildaPinpointDriver odometer;
    private final SparkLogger logger = SparkLogger.getLogger();

    private final Pose2D targetPose;

    public TurnTo(
            RobotBaseOpMode robot,
            double headingDegrees
    ) {
        this.robot = robot;
        this.drive = robot.getFieldRelativeDrive();;
        this.odometer = robot.getOdometer();
        this.telemetry = robot.getTelemetry();

        odometer.update();
        double currentX = odometer.getPosX(DistanceUnit.INCH);
        double currentY = odometer.getPosY(DistanceUnit.INCH);
        this.targetPose = new Pose2D(
                DistanceUnit.INCH,
                currentX,
                currentY,
                AngleUnit.DEGREES,
                headingDegrees
        );
    }

    public void setForwardPower(double forwardPower) {
        this.forwardPower = forwardPower;
    }

    public void setStrafePower(double strafePower) {
        this.strafePower = strafePower;
    }

    public void setRotatePower(double rotatePower) {
        this.rotatePower = rotatePower;
    }

    public void setToleranceX(double toleranceX) {
        this.toleranceX = toleranceX;
    }

    public void setToleranceY(double toleranceY) {
        this.toleranceY = toleranceY;
    }

    public void setToleranceH(double toleranceH) {
        this.toleranceH = toleranceH;
    }

    public boolean execute() {

        odometer.update();

        double forward;
        double strafe;
        double rotate = 0.0;

        Pose2D pos = odometer.getPosition();
        double currentX = pos.getX(DistanceUnit.INCH);
        double currentY = pos.getY(DistanceUnit.INCH);
        double currentH = pos.getHeading(AngleUnit.DEGREES);

        double errorX = currentX - targetPose.getX(DistanceUnit.INCH);
        double errorY = currentY - targetPose.getY(DistanceUnit.INCH);
        double errorH = currentH - targetPose.getHeading(AngleUnit.DEGREES);

//        if (Math.abs(errorX) < toleranceX) {
//            forward = 0.0;
//        } else if (errorX > 0) {
//            forward = -forwardPower;
//        } else {
//            forward = forwardPower;
//        }
//
//        if (Math.abs(errorY) < toleranceY) {
//            strafe = 0.0;
//        } else if (errorY > 0) {
//            strafe = -strafePower;
//        } else {
//            strafe = strafePower;
//        }

        if (Math.abs(errorH) < toleranceH) {
            rotate = 0.0;
        } else if (errorH > 0) {
            rotate = rotatePower;
        } else {
            rotate = -rotatePower;
        }
        if (Math.abs(errorH) > 180.0) {
            rotate = -rotate;
        }
        // Actuate - execute robot functions
        drive.drive(0.0, 0.0, rotate);

        telemetry.addData("Task", "TurnTo");

        // if any errors are > tolerance, keep going
        return (
            Math.abs(errorH) > toleranceH
        );
    }
}
