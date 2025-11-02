package org.firstinspires.ftc.teamcode.task;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.component.drive.FieldRelativeDrive;
import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;
import org.firstinspires.ftc.teamcode.util.PIDController;
import org.firstinspires.ftc.teamcode.util.SparkLogger;

import java.util.Locale;

public class MoveWithPIDTo implements Task {

    private final double DEFAULT_TOLERANCE_X = 1.0; // in inches
    private final double DEFAULT_TOLERANCE_Y = 1.0; // in inches
    private final double DEFAULT_TOLERANCE_H = 3; // in deg
    private final double MIN_POWER = 0.1;

    private double toleranceX = DEFAULT_TOLERANCE_X;
    private double toleranceY = DEFAULT_TOLERANCE_Y;
    private double toleranceH = DEFAULT_TOLERANCE_H;

    private PIDController forwardPID = new PIDController();
    private PIDController strafePID = new PIDController();
    private PIDController rotatePID = new PIDController();

    private final Telemetry telemetry;
    private final FtcDashboard dashboard;
    private final FieldRelativeDrive drive;
    private final GoBildaPinpointDriver odometer;

    private final SparkLogger logger = SparkLogger.getLogger();

    private final Pose2D targetPose;

    public MoveWithPIDTo(
            RobotBaseOpMode robot,
            double targetXInches,
            double targetYInches
    ) {
        this.drive = robot.getFieldRelativeDrive();;
        this.odometer = robot.getOdometer();
        this.telemetry = robot.getTelemetry();
        this.dashboard = robot.getDashboard();

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

    public void setToleranceX(double toleranceX) {
        this.toleranceX = toleranceX;
    }

    public void setToleranceY(double toleranceY) {
        this.toleranceY = toleranceY;
    }

    public void setToleranceH(double toleranceH) {
        this.toleranceH = toleranceH;
    }


    /**
     * If power to motors gets too low they won't move at all
     * @param power the requested power
     * @return power if > MIN_POWER else MIN_POWER
     */
    private double boostLowPower(double power) {
        return Math.min(MIN_POWER, Math.abs(power)) *
                power > 0.0 ? 1.0 : -1.0;
    }

    public boolean execute() {

        double rotatePower = 0.2; //TODO: get PID working for rotate
        odometer.update();

        double forward;
        double strafe;
        double rotate = 0.0;

        Pose2D pos = odometer.getPosition();
        double currentX = pos.getX(DistanceUnit.INCH);
        double currentY = pos.getY(DistanceUnit.INCH);
        double currentH = pos.getHeading(AngleUnit.DEGREES);

        double errorX = targetPose.getX(DistanceUnit.INCH) - currentX;
        double errorY = targetPose.getY(DistanceUnit.INCH) - currentY;
        double errorH = targetPose.getHeading(AngleUnit.DEGREES) - currentH;

        if (Math.abs(errorX) < toleranceX) {
            forward = 0.0;
        } else {
            forward = boostLowPower(forwardPID.calculate(errorX));
        }

        if (Math.abs(errorY) < toleranceY) {
            strafe = 0.0;
        } else {
            strafe = boostLowPower(strafePID.calculate(errorY));
        }

//        if (Math.abs(errorH) < toleranceH) {
//            rotate = 0.0;
//        } else if (errorH > 0) {
//            rotate = rotatePower;
//        } else {
//            rotate = -rotatePower;
//        }
//        if (Math.abs(errorH) > 180.0) {
//            rotate = -rotate;
//        }

        // Actuate - execute robot functions
        drive.drive(forward * 0.25, strafe * 0.25, rotate);

        telemetry.addData("Task",
                String.format(Locale.US,
                        "MoveWithPIDTo %.2f, %.2f",
                        targetPose.getX(DistanceUnit.INCH),
                        targetPose.getY(DistanceUnit.INCH)));
        telemetry.addData("fwdPower", String.format(Locale.US, "%.2f", forward));
        telemetry.addData("stfPower", String.format(Locale.US, "%.2f", strafe));
        telemetry.addData("rotPower", String.format(Locale.US, "%.2f", rotate));
        telemetry.addData("errorX", String.format(Locale.US, "%.2f", errorX));
        telemetry.addData("errorY", String.format(Locale.US, "%.2f", errorY));
        telemetry.addData("errorH", String.format(Locale.US, "%.2f", errorH));

        dashboard.getTelemetry().addData("errorX", String.format(Locale.US, "%.2f", errorX));
        dashboard.getTelemetry().addData("errorY", String.format(Locale.US, "%.2f", errorY));
        dashboard.getTelemetry().addData("errorH", String.format(Locale.US, "%.2f", errorH));

        logger.log(String.format(Locale.US, "errorX %.2f, errorY %.2f, errorH %.2f", errorX, errorY, errorH));
        // if any errors are > tolerance, keep going
        return (
                Math.abs(errorX) > toleranceX
                || Math.abs(errorY) > toleranceY
                || Math.abs(errorH) > toleranceH
        );
    }
}
