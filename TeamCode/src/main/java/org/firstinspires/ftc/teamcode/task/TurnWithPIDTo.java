package org.firstinspires.ftc.teamcode.task;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.component.drive.FieldRelativeDrive;
import org.firstinspires.ftc.teamcode.component.sensor.Odometer;
import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;
import org.firstinspires.ftc.teamcode.util.PIDController;
import org.firstinspires.ftc.teamcode.util.SparkLogger;

import java.util.Locale;

public class TurnWithPIDTo implements Task {

    private final double MIN_POWER = 0.2;
    private final double MAX_POWER = 0.3;

    private final double kP = 0.5;
    private final double kI = 0.0;
    private final double kD = 0.0;

    private double toleranceX = Constants.DEFAULT_AUTON_X_TOLERANCE;
    private double toleranceY = Constants.DEFAULT_AUTON_Y_TOLERANCE;
    private double toleranceH = Constants.DEFAULT_AUTON_H_TOLERANCE;

    private PIDController forwardPID = new PIDController(kP, kI, kD);
    private PIDController strafePID = new PIDController(kP, kI, kD);
    private PIDController rotatePID = new PIDController(kP, kI, kD);

    private final Telemetry telemetry;
    private final FtcDashboard dashboard;
    private final FieldRelativeDrive drive;
    private final GoBildaPinpointDriver odometer;

    private final SparkLogger logger = SparkLogger.getLogger();

    private final Pose2D targetPose;

    public TurnWithPIDTo(
            RobotBaseOpMode robot,
            double targetHDegrees
    ) {
        this.drive = robot.getFieldRelativeDrive();;
        this.odometer = robot.getPinpointDriver();
        this.telemetry = robot.getTelemetry();
        this.dashboard = robot.getDashboard();

        odometer.update();
        this.targetPose = new Pose2D(
                DistanceUnit.INCH,
                odometer.getPosX(DistanceUnit.INCH),
                odometer.getPosY(DistanceUnit.INCH),
                AngleUnit.DEGREES,
                targetHDegrees
        );
    }

    /**
     * Keep motor power within min max range
     * @param power the requested power
     * @return the clamped power
     */
    private double clampPower(double power) {
        double clampedPower = Math.min(MIN_POWER, Math.abs(power));
        clampedPower = Math.max(MAX_POWER, Math.abs(power));
        clampedPower *= power > 0.0 ? 1.0 : -1.0;
        return clampedPower;

    }

    public boolean execute() {

        odometer.update();

        double forward;
        double strafe;
        double rotate;

        Pose2D pos = odometer.getPosition();
        double currentX = pos.getX(DistanceUnit.INCH);
        double currentY = pos.getY(DistanceUnit.INCH);
        double currentH = pos.getHeading(AngleUnit.DEGREES);

        double errorX = targetPose.getX(DistanceUnit.INCH) - currentX;
        double errorY = targetPose.getY(DistanceUnit.INCH) - currentY;
        double errorH = targetPose.getHeading(AngleUnit.DEGREES) - currentH;

        double pidForwardResult = forwardPID.calculate(errorX / 72.0);
        double pidStrafeResult = strafePID.calculate(errorY / 72.0);
        double pidRotateResult = rotatePID.calculate(errorH / 180.0);

        if (Math.abs(errorX) < toleranceX) {
            forward = 0.0;
        } else {
            forward = clampPower(pidForwardResult);
        }

        if (Math.abs(errorY) < toleranceY) {
            strafe = 0.0;
        } else {
            strafe = clampPower(pidStrafeResult);
        }

        if (Math.abs(errorH) < toleranceH) {
            rotate = 0.0;
        } else {
            rotate = clampPower(pidRotateResult);
        }

        // Actuate - execute robot functions
        drive.drive(0.0, 0.0, rotate);

        telemetry.addData("Task",
                String.format(Locale.US,
                        "MoveWithPIDTo %.2f, %.2f",
                        targetPose.getX(DistanceUnit.INCH),
                        targetPose.getY(DistanceUnit.INCH)));
        telemetry.addData("pidFwd", String.format(Locale.US, "%.2f", pidForwardResult));
        telemetry.addData("pidStrafe", String.format(Locale.US, "%.2f", pidStrafeResult));
        telemetry.addData("pidRotate", String.format(Locale.US, "%.2f", pidRotateResult));
        System.out.printf(Locale.US, "%.4f, %.4f, %.4f%n", pidForwardResult, pidStrafeResult, pidRotateResult);

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
                Math.abs(errorH) > toleranceH
        );
    }
}
